package fit5120.monash.edu.features.searchingService.dto.model;

import lombok.Data;

@Data
public class AccessibilityOptionsModel {
    private String serviceName;
    private String placeName;

    private String rating;
    private Integer ratingNumber;
    private String weekdayDescription;

    //location
    private Double latitude;
    private Double longitude;

    //serviceType
    private String serviceType;


}
