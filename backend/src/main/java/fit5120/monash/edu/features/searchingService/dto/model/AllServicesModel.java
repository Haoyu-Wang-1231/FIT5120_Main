package fit5120.monash.edu.features.searchingService.dto.model;


import lombok.Data;

@Data
public class AllServicesModel {

    // service
    private Integer id;
    private String serviceName;
    private String placeName;
    private String contactNumber;
    private String website;
    private String weekdayDescription;

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



}
