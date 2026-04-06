package fit5120.monash.edu.backend.dto.request;

import lombok.Data;

@Data
public class ServiceLooking {

    private Float longitude;
    private Float Latitude;

    private String serviceType;
    private String accessibility;

}
