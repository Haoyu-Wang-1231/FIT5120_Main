package fit5120.monash.edu.features.searchingService;

import fit5120.monash.edu.common.exception.ResourcesNotFoundException;
import fit5120.monash.edu.common.result.RespEnum;
import fit5120.monash.edu.common.util.GeoCalculator;
import fit5120.monash.edu.entity.Location;
import fit5120.monash.edu.features.searchingService.request.SearchingAllServiceRequire;
import fit5120.monash.edu.features.searchingService.request.SearchingServiceRequire;
import fit5120.monash.edu.features.searchingService.response.LocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ServiceSearchingService {

    @Autowired
    private LocationMapper locationMapper;

    private static LocationResponse getLocationResponse(Location location, Double distance) {
        LocationResponse response = new LocationResponse();
        response.setId(location.getId());
        response.setAddress(location.getAddress());
        response.setSuburb(location.getSuburb());
        response.setState(location.getState());
        response.setPostcode(location.getPostcode());
        response.setType(location.getType());
        response.setDistance(distance);
        response.setLatitude(location.getLatitude());
        response.setLongitude(location.getLongitude());
        return response;
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

        return getLocationResponse(location, distance);
    }




}
