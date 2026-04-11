package fit5120.monash.edu.features.searchingService.dto.request;

import lombok.Data;

@Data
public class AllServicesRequest {

    private Integer limit;
    private String serviceType;

    private Double latitude;
    private Double longitude;


}
