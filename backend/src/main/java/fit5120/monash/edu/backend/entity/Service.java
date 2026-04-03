package fit5120.monash.edu.backend.entity;

import lombok.Data;

@Data
public class Service {

    private Integer service_id;
    private String service_name;
    private String service_description;
    private String service_type;
    private String phone_number;
    private String opening_hours;
    private Boolean is_verified;
}
