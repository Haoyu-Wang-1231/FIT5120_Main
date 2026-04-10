package fit5120.monash.edu.features.searchingService.dto.request;

import lombok.Data;

@Data
public class AccessibilityServicesRequire {


    private Double latitude;
    private Double longitude;

    private AccessibilityOptions accessibilityOptions;

    @Data
    public static class AccessibilityOptions{
        private Boolean isNeedAccessibility;

        private Boolean wheelchairPark;
        private Boolean wheelchairEntrance;
        private Boolean wheelchairRestroom;
        private Boolean wheelchairSeating;


    }



}
