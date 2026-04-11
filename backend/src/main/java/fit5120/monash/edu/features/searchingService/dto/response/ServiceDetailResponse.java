package fit5120.monash.edu.features.searchingService.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ServiceDetailResponse {
    private Integer id;
    private String serviceName;
    private String placeName;
    private String contactNumber;
    private String website;
    private String rating;
    private Integer ratingNumber;

    private Boolean wheelchairPark;
    private Boolean wheelchairEntrance;
    private Boolean wheelchairRestroom;
    private Boolean wheelchairSeating;

    private Boolean openNow;
    private List<String> openDescription;

    private String address;
    private String suburb;
    private String state;
    private String postcode;
    private Double latitude;
    private Double longitude;

    private String serviceType;






}
