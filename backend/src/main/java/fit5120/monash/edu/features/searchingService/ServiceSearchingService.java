package fit5120.monash.edu.features.searchingService;

import fit5120.monash.edu.client.GoogleMapClient;
import fit5120.monash.edu.features.searchingService.dto.clientRequest.GoogleMapNearbyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
@Deprecated
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

    private static  Boolean timeChecker(List<String> weekdayDescriptions){

        LocalDateTime now = LocalDateTime.now();
        DayOfWeek today = now.getDayOfWeek();
        LocalTime currentTime = now.toLocalTime();

        String todayStr = today.toString();
        todayStr = todayStr.substring(0,1) + todayStr.substring(1).toLowerCase();

        String finalTodayStr = todayStr;
        String todayHours = weekdayDescriptions.stream()
                .filter(s -> s.startsWith(finalTodayStr))
                .findFirst()
                .orElse(null);
        log.info("today's hours: " + todayHours);
        if (todayHours == null) return false;

        try {
            String timeRange = todayHours.split(": ")[1];

            timeRange = timeRange
                    .replace("\u202F", " ")
                    .replace("\u2009", " ")
                    .replace("–", "-");

            String[] times = timeRange.split(" - ");


            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);

            LocalTime openTime = LocalTime.parse(times[0], formatter);
            LocalTime closeTime = LocalTime.parse(times[1], formatter);


            return !currentTime.isBefore(openTime)
                    && currentTime.isBefore(closeTime);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


//    private static LocationResponse getLocationResponse(Location location, Double distance) {
//        LocationResponse response = new LocationResponse();
//        response.setId(location.getId());
//        response.setAddress(location.getAddress());
//        response.setSuburb(location.getSuburb());
//        response.setState(location.getState());
//        response.setPostcode(location.getPostcode());
//        response.setDistance(distance);
//        response.setLatitude(location.getLatitude());
//        response.setLongitude(location.getLongitude());
//
//
//        if(location.getAccessibilityOption() != null){
//            response.setAccessibilityOption(
//                    JsonUtil.toMap(location.getAccessibilityOption())
//            );
//        }
//        if(location.getOpenTime() != null){
//
//            GoogleMapDetailClientResponse.CurrentOpeningHours googleFormatOpenTime =
//                    JsonUtil.toObject(location.getOpenTime(), GoogleMapDetailClientResponse.CurrentOpeningHours.class);
//
//            if(googleFormatOpenTime.getWeekdayDescriptions() != null){
//                LocationResponse.OpenTime openTime = new LocationResponse.OpenTime();
//                openTime.setWeekdayDescriptions(googleFormatOpenTime.getWeekdayDescriptions());
////                log.info(openTime.getWeekdayDescriptions().toString());
////                log.info(timeChecker(openTime.getWeekdayDescriptions()).toString());
//                openTime.setOpenNow(timeChecker(openTime.getWeekdayDescriptions()));
//                response.setOpenTime(openTime);
//            }
//        }
//        return response;
//    }

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

//    private Location addNewLocation(GoogleMapDetailClientResponse response){
//        Location location = new Location();
//
//        location.setAddress(response.getFormattedAddress());
//
//        if(response.getLocation() != null){
//            location.setLatitude(response.getLocation().getLatitude());
//            location.setLongitude(response.getLocation().getLongitude());
//        }
//        if(response.getPostalAddress() != null){
//            location.setSuburb(response.getPostalAddress().getLocality());
//            location.setState(response.getPostalAddress().getAdministrativeArea());
//            location.setPostcode(response.getPostalAddress().getPostalCode());
//        }
//        // problem
//        if(response.getAccessibilityOptions() != null){
////            location.setAccessibilityOption(response.getAccessibilityOptions().toString());
//            location.setAccessibilityOption(JsonUtil.toJson(response.getAccessibilityOptions()));
//
//        }
//        if(response.getCurrentOpeningHours() != null){
////            location.setOpenTime(response.getCurrentOpeningHours().toString());
//            location.setOpenTime(JsonUtil.toJson(response.getCurrentOpeningHours()));
//        }
//        log.warn(location.toString());
//        return location;
//    }

    /**
     * Get all the location's information.
     * @param sasr
     * @return
     */
//    public List<LocationResponse> getAllServicesLocation(SearchingAllServiceRequire sasr){
//        log.info("get all services location");
//        List<Location> locations = locationMapper.getAllServicesLocation();
//        List<LocationResponse> responses = new ArrayList<>();
//
//        for(Location location: locations){
//            Double distance = GeoCalculator.calculateDistance(sasr.getLatitude(), sasr.getLongitude(), location.getLatitude(), location.getLongitude());
//            LocationResponse response = getLocationResponse(location, distance);
//
//            responses.add(response);
//        }
//        return responses;
//    }

//    public LocationResponse getServiceLocation(SearchingServiceRequire ssr){
//        Location location = locationMapper.selectLocationById(ssr.getId());
//        if(location == null){
//            throw new ResourcesNotFoundException(RespEnum.NOT_FOUND);
//        }
//        Double distance = GeoCalculator.calculateDistance(ssr.getLatitude(), ssr.getLongitude(), location.getLatitude(), location.getLongitude());
//        LocationResponse location1  = null;
//        try{
//            location1 = getLocationResponse(location, distance);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return location1;
//    }


    /**
     * Get location by coordinate.
     * @param ssr: id: location's id, latitude, longitude: visitor's coordinate.
     * @return
     * @param <T>
     */
//    public <T> T  getLocationDetails(SearchingServiceRequire ssr){
//        Location location = null;
//        location = locationMapper.selectLocationById(ssr.getId());
//        if(location == null){
//            GoogleMapNearbyClientResponse response = googleMapClient.searchNearby(
//                    apiKey,nearbyFieldMask, buildGoogleMapNearbyRequest(ssr.getLatitude(), ssr.getLongitude()));
//            log.info(response.toString());
//
//            String locationId;
//
////            if(response.getPlaces().size() > 1){
////                log.info("size larger than 1, this line");
////                try{
////                    locationId = response.getPlaces().get(0).getId();
////
////                    GoogleMapDetailClientResponse detailClientResponse = googleMapClient.getDetails(apiKey, detailFieldMask, locationId);
////
////
////                    int result = locationMapper.addNewLocation(addNewLocation(detailClientResponse));
////                    log.info("result: " + result);
////
////                    return (T) detailClientResponse;
////                }catch (Exception e){
////                    e.printStackTrace();
////                }
////            }
////            GoogleMapDetailClientResponse detailClientResponse = googleMapClient.getDetails(apiKey, detailFieldMask, );
//            return (T) response;
//        }
//        Double distance = GeoCalculator.calculateDistance(ssr.getLatitude(), ssr.getLongitude(), location.getLatitude(), location.getLongitude());
//
//        return (T) getLocationResponse(location, distance);
//
//    }


}
