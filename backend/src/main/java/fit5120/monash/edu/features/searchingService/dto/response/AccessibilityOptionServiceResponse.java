package fit5120.monash.edu.features.searchingService.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AccessibilityOptionServiceResponse {

    private Integer id;
    private String serviceName;
    private String placeName;

    private String rating;
    private Integer ratingNumber;

    private Double distance;
    //location
    private Double latitude;
    private Double longitude;

    //serviceType
    private String serviceType;



    //list of weekday description
    private List<String> openDescription;
    private Boolean openNow;
}
