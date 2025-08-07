package lucidity.util;

import lucidity.model.Location;

public class GeoUtils {
    public static final double EARTH_RADIUS = 6371.0; // in km

     private static double haversine(Location a, Location b) {
        double dLat = Math.toRadians(b.getLatitude() - a.getLatitude());
        double dLon = Math.toRadians(b.getLongitude() - a.getLongitude());
        double lat1 = Math.toRadians(a.getLatitude());
        double lat2 = Math.toRadians(b.getLatitude());

        double aH = Math.pow(Math.sin(dLat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dLon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(aH));
        return EARTH_RADIUS * c;
    }

    public static double travelTimeInMinutes(Location a, Location b) {
        double distance = haversine(a, b);
        return (distance / 20.0) * 60.0; // speed = 20 km/hr
    }
}
