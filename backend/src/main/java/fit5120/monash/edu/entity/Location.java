package fit5120.monash.edu.entity;

import lombok.Data;

import java.util.Map;

@Data
public class Location {

    private Integer id;
    private String address;
    private String suburb;
    private String state;
    private String postcode;
    private String accessibilityOption;
    private String openTime;
    private Double latitude;
    private Double longitude;

}
