package fit5120.monash.edu.features.searchingService.dto.clientRequest;

import fit5120.monash.edu.api.ApiPaths;
import lombok.Data;

import java.util.List;

@Data
public class GoogleMapNearbyRequest {

//    private List<String> includedTypes;
    private Integer maxResultCount;
    private LocationRestriction locationRestriction;

    @Data
    public static class LocationRestriction{
        private Circle circle;
    }

    @Data
    public static class Circle{
        private Center center;
        private Double radius;
    }

    @Data
    public static class Center{
        public Double latitude;
        public Double longitude;

    }

}
