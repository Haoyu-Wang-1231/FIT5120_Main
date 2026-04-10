package fit5120.monash.edu.features.searchingService.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AccessibilityOptionServiceResponse {

    private String serviceName;
    private String placeName;

    private String rating;
    private Boolean ratingNumber;

    private Double distance;
    private String weekdayDescription;
    //location
    private Double latitude;
    private Double longitude;

    //serviceType
    private String serviceType;



    //list of weekday description
    private List<String> openDescription;
    private Boolean openNow;
}
