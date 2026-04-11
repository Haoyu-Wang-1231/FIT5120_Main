package fit5120.monash.edu.api;

public final class ApiPaths {


    public static final class Location{
        public static final String BASE = "/location";

        public static final String GETALLSERVICESLOCATION = "/allServicesLocation";
        public static final String GETSERVICELOCATIONBYID = "/getServiceLocation";
        public static final String GETLOCATIONDETAILS = "/getLocationDetail";
    }

    public static final class Service{
        public static final String BASE = "/service";

        public static final String getAllServiceLocation = "/getAllServicesLocation";
        public static final String getAccessibilityOptionService = "/getAccessibilityService";
        public static final String getServiceDetail = "/getServiceDetail/{id}";





    }

}
