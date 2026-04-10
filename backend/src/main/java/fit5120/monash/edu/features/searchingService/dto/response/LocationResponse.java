package fit5120.monash.edu.features.searchingService.dto.response;

import fit5120.monash.edu.features.searchingService.dto.clientResponse.GoogleMapDetailClientResponse;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class LocationResponse {

    private Integer id;
    private String address;
    private String suburb;
    private String state;
    private String postcode;
    private Map<String, Object> accessibilityOption;
    private OpenTime openTime;
    private Double distance;
    private Double latitude;
    private Double longitude;


    @Data
    public static class OpenTime{
        private Boolean openNow;
        private List<String> weekdayDescriptions;


    }


}
