package fit5120.monash.edu.features.searchingService.dto.response;


import lombok.Data;

import java.util.List;

@Data
public class AllServicesResponse {
    // service
    private Integer id;
    private String serviceName;
    private String placeName;
    private String contactNumber;
    private String website;

    private Boolean wheelchairPark;
    private Boolean wheelchairEntrance;
    private Boolean wheelchairRestroom;
    private Boolean wheelchairSeating;

    // location
    private String suburb;
    private String state;
    private String postcode;
    private Double latitude;
    private Double longitude;

    // serviceType
    private String serviceType;

    // calculate
    private Double distance;

    //list of weekday description
    private List<String> openDescription;
    private Boolean openNow;

}
