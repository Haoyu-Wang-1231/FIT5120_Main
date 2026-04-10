package fit5120.monash.edu.features.searchingService;

import com.fasterxml.jackson.core.JsonToken;
import fit5120.monash.edu.client.GoogleMapClient;
import fit5120.monash.edu.common.exception.ResourcesNotFoundException;
import fit5120.monash.edu.common.result.RespEnum;
import fit5120.monash.edu.common.util.GeoCalculator;
import fit5120.monash.edu.common.util.JsonUtil;
import fit5120.monash.edu.entity.Location;
import fit5120.monash.edu.features.searchingService.dto.clientRequest.GoogleMapNearbyRequest;
import fit5120.monash.edu.features.searchingService.dto.clientResponse.GoogleMapDetailClientResponse;
import fit5120.monash.edu.features.searchingService.dto.clientResponse.GoogleMapNearbyClientResponse;
import fit5120.monash.edu.features.searchingService.dto.request.SearchingAllServiceRequire;
import fit5120.monash.edu.features.searchingService.dto.request.SearchingServiceRequire;
import fit5120.monash.edu.features.searchingService.dto.response.LocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ServiceSearchingService {

    @Value("${google.api.key}")
    private String apiKey;

    private static final String nearbyFieldMask = "places.id,places.displayName,places.formattedAddress";
    private static final String detailFieldMask = "name,formattedAddress,location,currentOpeningHours,nationalPhoneNumber,accessibilityOptions,postalAddress";

//    name, formatted_phone_number, website, formatted_address
    @Autowired
    private GoogleMapClient googleMapClient;

    @Autowired
    private LocationMapper locationMapper;

    private void prepareRequest(){

    }


    private static LocationResponse getLocationResponse(Location location, Double distance) {
        LocationResponse response = new LocationResponse();
        response.setId(location.getId());
        response.setAddress(location.getAddress());
        response.setSuburb(location.getSuburb());
        response.setState(location.getState());
        response.setPostcode(location.getPostcode());
        response.setDistance(distance);
        response.setLatitude(location.getLatitude());
        response.setLongitude(location.getLongitude());

        response.setAccessibilityOption(
                JsonUtil.toMap(location.getAccessibilityOption())
        );

        response.setOpenTime(
                JsonUtil.toObject(location.getOpenTime(), GoogleMapDetailClientResponse.CurrentOpeningHours.class)
        );

        return response;
    }

    private GoogleMapNearbyRequest buildGoogleMapNearbyRequest(Double latitude, Double longitude){
        GoogleMapNearbyRequest request = new GoogleMapNearbyRequest();

        request.setMaxResultCount(10);
        GoogleMapNearbyRequest.Center center = new GoogleMapNearbyRequest.Center();
        center.setLatitude(latitude);
        center.setLongitude(longitude);

        GoogleMapNearbyRequest.Circle circle = new GoogleMapNearbyRequest.Circle();
        circle.setCenter(center);
        circle.setRadius(50.0);

        GoogleMapNearbyRequest.LocationRestriction locationRestriction = new GoogleMapNearbyRequest.LocationRestriction();
        locationRestriction.setCircle(circle);

        request.setLocationRestriction(locationRestriction);
        return request;
    }

    private Location addNewLocation(GoogleMapDetailClientResponse response){
        Location location = new Location();

        location.setAddress(response.getFormattedAddress());

        if(response.getLocation() != null){
            location.setLatitude(response.getLocation().getLatitude());
            location.setLongitude(response.getLocation().getLongitude());
        }
        if(response.getPostalAddress() != null){
            location.setSuburb(response.getPostalAddress().getLocality());
            location.setState(response.getPostalAddress().getAdministrativeArea());
            location.setPostcode(response.getPostalAddress().getPostalCode());
        }
        // problem
        if(response.getAccessibilityOptions() != null){
//            location.setAccessibilityOption(response.getAccessibilityOptions().toString());
            location.setAccessibilityOption(JsonUtil.toJson(response.getAccessibilityOptions()));

        }
        if(response.getCurrentOpeningHours() != null){
//            location.setOpenTime(response.getCurrentOpeningHours().toString());
            location.setOpenTime(JsonUtil.toJson(response.getCurrentOpeningHours()));
        }
        log.warn(location.toString());
        return location;
    }

    public List<LocationResponse> getAllServicesLocation(SearchingAllServiceRequire sasr){
        log.info("get all services location");
        List<Location> locations = locationMapper.getAllServicesLocation();
        List<LocationResponse> responses = new ArrayList<>();

        for(Location location: locations){
            Double distance = GeoCalculator.calculateDistance(sasr.getLatitude(), sasr.getLongitude(), location.getLatitude(), location.getLongitude());
            LocationResponse response = getLocationResponse(location, distance);

            responses.add(response);
        }
        return responses;
    }


    public LocationResponse getServiceLocation(SearchingServiceRequire ssr){
        Location location = locationMapper.selectLocationById(ssr.getId());
        if(location == null){
            throw new ResourcesNotFoundException(RespEnum.NOT_FOUND);
        }
        Double distance = GeoCalculator.calculateDistance(ssr.getLatitude(), ssr.getLongitude(), location.getLatitude(), location.getLongitude());
        LocationResponse location1  = null;
        try{
            location1 = getLocationResponse(location, distance);
        }catch (Exception e){
            e.printStackTrace();
        }
        return location1;
    }



    // this is duplicate method
    public <T> T  getLocationDetails(SearchingServiceRequire ssr){
        log.info("location detail starts");
        Location location = locationMapper.selectLocationByDistance(ssr.getLatitude(), ssr.getLongitude());
        log.info("location detail ends");

        if(location == null){
            GoogleMapNearbyClientResponse response = googleMapClient.searchNearby(
                    apiKey,nearbyFieldMask, buildGoogleMapNearbyRequest(ssr.getLatitude(), ssr.getLongitude()));
            log.info(response.toString());

            String locationId;

            if(response.getPlaces().size() > 1){
                log.info("size larger than 1, this line");
                try{
                    locationId = response.getPlaces().get(0).getId();

                    GoogleMapDetailClientResponse detailClientResponse = googleMapClient.getDetails(apiKey, detailFieldMask, locationId);


                    int result = locationMapper.addNewLocation(addNewLocation(detailClientResponse));
                    log.info("result: " + result);

                    return (T) detailClientResponse;
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
//            GoogleMapDetailClientResponse detailClientResponse = googleMapClient.getDetails(apiKey, detailFieldMask, );


            return (T) response;
        }
        Double distance = GeoCalculator.calculateDistance(ssr.getLatitude(), ssr.getLongitude(), location.getLatitude(), location.getLongitude());

        return (T) getLocationResponse(location, distance);

    }


}
