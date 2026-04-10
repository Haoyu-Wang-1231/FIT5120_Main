package fit5120.monash.edu.features.searchingService.dto.clientResponse;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
public class GoogleMapDetailClientResponse {
    private String name;
    private String formattedAddress;
    private String nationalPhoneNumber;
    private String googleMapsUri;
    private String websiteUri;
    private Map<String, Boolean> accessibilityOptions;
    private Location location;
    private PostalAddress postalAddress;
    private CurrentOpeningHours currentOpeningHours;

    @Data
    public static class Location{
        private Double latitude;
        private Double longitude;
    }
    @Data
    public static class PostalAddress{
        private String regionCode;
        private String postalCode;// postcode
        private String administrativeArea;// state
        private String locality; //suburb

    }
    @Data
    public static class CurrentOpeningHours{
        private Boolean openNow;
        private List<String> weekdayDescriptions;
        private String nextOpenTime;
    }
}
