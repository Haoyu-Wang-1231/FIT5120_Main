package fit5120.monash.edu.backend.entity;

import lombok.Data;

@Data
public class Location {

    private Integer location_id;
    private String address_line;
    private String suburb;
    private String state_territory;
    private String postcode;
    private String region_type;
    private Float latitude;
    private Float longitude;

}
