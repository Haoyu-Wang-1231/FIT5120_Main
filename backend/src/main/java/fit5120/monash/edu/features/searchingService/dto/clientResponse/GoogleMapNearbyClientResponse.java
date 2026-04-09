package fit5120.monash.edu.features.searchingService.dto.clientResponse;

import lombok.Data;

import java.util.List;

@Data
public class GoogleMapNearbyClientResponse {

    private List<Place> places;

    @Data
    public static class Place{
        private String id;
        private String formattedAddress;
        private DisplayName displayName;


        @Data
        public static class DisplayName{
            private String text;
            private String languageCode;
        }
    }
}
