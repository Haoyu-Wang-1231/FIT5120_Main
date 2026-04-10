package fit5120.monash.edu.features.searchingService.dto.model;

import lombok.Data;

@Data
public class ServiceDetail {
    private Integer id;
    private String serviceName;
    private String placeName;
    private String contactNumber;
    private String website;
    private String rating;
    private Boolean ratingNumber;

    private Boolean wheelchairPark;
    private Boolean wheelchairEntrance;
    private Boolean wheelchairRestroom;
    private Boolean wheelchairSeating;

    private String weekdayDescription;

    private String address;
    private String suburb;
    private String state;
    private String postcode;
    private Double latitude;
    private Double longitude;

    private String serviceType;

}
