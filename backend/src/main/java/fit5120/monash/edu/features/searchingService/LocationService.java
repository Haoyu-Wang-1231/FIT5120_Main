package fit5120.monash.edu.features.searchingService;

import fit5120.monash.edu.common.exception.ResourcesNotFoundException;
import fit5120.monash.edu.common.result.RespEnum;
import fit5120.monash.edu.common.util.GeoCalculator;
import fit5120.monash.edu.common.util.JsonUtil;
import fit5120.monash.edu.entity.Location;
import fit5120.monash.edu.features.searchingService.dto.model.AccessibilityOptionsModel;
import fit5120.monash.edu.features.searchingService.dto.model.ServiceDetail;
import fit5120.monash.edu.features.searchingService.dto.request.AccessibilityServicesRequire;
import fit5120.monash.edu.features.searchingService.dto.request.SearchingAllLocationRequire;
import fit5120.monash.edu.features.searchingService.dto.response.AccessibilityOptionServiceResponse;
import fit5120.monash.edu.features.searchingService.dto.response.LocationResponse;
import fit5120.monash.edu.features.searchingService.dto.response.ServiceDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class LocationService {



    @Autowired
    private LocationMapper locationMapper;

    private static Boolean timeChecker(List<String> weekdayDescriptions){

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
//        log.info("today's hours: " + todayHours);
        if (todayHours == null) return false;

        try {
            String timeRange = todayHours.split(": ")[1];
            if(timeRange.equals("Open 24 hours")){
                return true;
            }else if(timeRange.equals("Closed")){
                return false;
            }

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


    private LocationResponse buildLocationResponse(Location location, Double distance){
        LocationResponse response = new LocationResponse();
        response.setId(location.getId());
        response.setAddress(location.getAddress());
        response.setSuburb(location.getSuburb());
        response.setState(location.getState());
        response.setPostcode(location.getPostcode());
        response.setLatitude(location.getLatitude());
        response.setLongitude(location.getLongitude());
        response.setDistance(distance);
        return response;
    }

    private AccessibilityOptionServiceResponse buildAccessibilityOptionServiceResponse(AccessibilityOptionsModel model, Double distance){
        AccessibilityOptionServiceResponse response = new AccessibilityOptionServiceResponse();
        response.setServiceName(model.getServiceName());
        response.setPlaceName(model.getPlaceName());
        response.setRating(model.getRating());
        response.setRatingNumber(model.getRatingNumber());

        response.setLatitude(model.getLatitude());
        response.setLongitude(model.getLongitude());
        response.setServiceType(model.getServiceType());

        response.setDistance(distance);

        response.setOpenDescription(JsonUtil.toList(model.getWeekdayDescription()));
        log.info(response.getOpenDescription().toString());
//
        response.setOpenNow(timeChecker(response.getOpenDescription()));
        return response;
    }

    public List<LocationResponse> getAllLocations(SearchingAllLocationRequire searchingAllLocationRequire){
        List<Location> locations = locationMapper.getAllLocations();
        List<LocationResponse> responses = new ArrayList<>();

        for(Location location: locations){
            Double distance = GeoCalculator.calculateDistance(
                    searchingAllLocationRequire.getLatitude(),
                    searchingAllLocationRequire.getLongitude(),
                    location.getLatitude(),
                    location.getLongitude()
            );
            LocationResponse response = buildLocationResponse(location, distance);
            responses.add(response);
        }

        responses.sort(Comparator.comparing(LocationResponse::getDistance));

        Integer limit = searchingAllLocationRequire.getLimit();

        if(limit != null){
            if(responses.size() > limit){
                return responses.stream()
                        .limit(limit)
                        .toList();
            }
        }


        return responses;
    }

    public List<?> getAccessibilityOptionService(AccessibilityServicesRequire asr){
        log.info("get accessibility option service.");
        Integer limit = asr.getLimit();
        if(asr.getAccessibilityOptions().getIsNeedAccessibility() == false){
            List<Location> locations = locationMapper.getAllLocations();
            List<LocationResponse> responses = new ArrayList<>();

            for(Location location: locations){
                Double distance = GeoCalculator.calculateDistance(
                        asr.getLatitude(),
                        asr.getLongitude(),
                        location.getLatitude(),
                        location.getLongitude()
                );
                LocationResponse response = buildLocationResponse(location, distance);
                responses.add(response);
            }


            responses.sort(Comparator.comparing(LocationResponse::getDistance));
            if(limit != null){
                if(responses.size() > 10){
                    return responses.stream()
                            .limit(10)
                            .toList();
                }
            }
            return responses;
        }


        List<AccessibilityOptionsModel> models = locationMapper.getAccessibilityOptionService(
                asr.getAccessibilityOptions().getWheelchairPark(),
                asr.getAccessibilityOptions().getWheelchairEntrance(),
                asr.getAccessibilityOptions().getWheelchairRestroom(),
                asr.getAccessibilityOptions().getWheelchairSeating()
        );
        log.info("model size: " + models.size());
        List<AccessibilityOptionServiceResponse> responses = new ArrayList<>();
        for(AccessibilityOptionsModel model: models){
            Double distance = GeoCalculator.calculateDistance(
                    asr.getLatitude(),
                    asr.getLongitude(),
                    model.getLatitude(),
                    model.getLongitude()
            );
            AccessibilityOptionServiceResponse response =buildAccessibilityOptionServiceResponse(model, distance);
            responses.add(response);
        }

        responses.sort(Comparator.comparing(AccessibilityOptionServiceResponse::getDistance));

        if(limit != null){
            if(responses.size() > limit){
                return responses.stream()
                        .limit(limit)
                        .toList();
            }
        }


        return responses;
    }

    private ServiceDetailResponse buildServiceDetailResponse(ServiceDetail detail){
        ServiceDetailResponse response = new ServiceDetailResponse();
        response.setId(detail.getId());
        response.setAddress(detail.getAddress());
        response.setPlaceName(detail.getPlaceName());
        response.setContactNumber(detail.getContactNumber());
        response.setWebsite(detail.getWebsite());
        response.setRating(detail.getRating());
        response.setRatingNumber(detail.getRatingNumber());

        response.setWheelchairPark(detail.getWheelchairPark());
        response.setWheelchairEntrance(detail.getWheelchairEntrance());
        response.setWheelchairRestroom(detail.getWheelchairRestroom());
        response.setWheelchairSeating(detail.getWheelchairSeating());


        response.setAddress(detail.getAddress());
        response.setSuburb(detail.getSuburb());
        response.setState(detail.getState());
        response.setPostcode(detail.getPostcode());
        response.setLatitude(detail.getLatitude());
        response.setLongitude(detail.getLongitude());

        response.setOpenDescription(JsonUtil.toList(detail.getWeekdayDescription()));
        response.setOpenNow(timeChecker(response.getOpenDescription()));

        return response;
    }

    public ServiceDetailResponse getServiceDetail(Integer id){
        log.info("getServiceDetail");

        ServiceDetail detail = locationMapper.getServiceDetailById(id);
        if(detail ==null){
            throw new ResourcesNotFoundException(RespEnum.NOT_FOUND);

        }


        return buildServiceDetailResponse(detail);
    }


}
