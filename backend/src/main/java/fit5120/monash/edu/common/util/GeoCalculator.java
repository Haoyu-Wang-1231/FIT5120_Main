package fit5120.monash.edu.common.util;

public class GeoCalculator {

    private static final double EARTH_RADIUS = 6371.0;

    public static Double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2){
        Double lat1Rad = Math.toRadians(lat1);
        Double lat2Rad = Math.toRadians(lat2);
        Double deltaLat = Math.toRadians(lat2 - lat1);
        Double deltaLon = Math.toRadians(lon2 - lon1);

        Double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        Double c = 2 * Math.asin(Math.sqrt(a));

        return EARTH_RADIUS * c; // km
    }

//    public static double distance(double lat1, double lon1,
//                                  double lat2, double lon2) {
//
//        double lat1Rad = Math.toRadians(lat1);
//        double lat2Rad = Math.toRadians(lat2);
//        double deltaLat = Math.toRadians(lat2 - lat1);
//        double deltaLon = Math.toRadians(lon2 - lon1);
//
//        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
//                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
//                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
//
//        double c = 2 * Math.asin(Math.sqrt(a));
//
//        return EARTH_RADIUS * c; // km
//    }
}
