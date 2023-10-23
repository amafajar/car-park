package com.develab.carpark.domain.service;

import com.develab.carpark.domain.entity.CarPark;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Comparator;

@Service
public class CarParkService {

    private final int earthRadius = 6371;
    public Double calculateDistance (double originLatitude, double originLongitude,
                                     double targetLatitude, double targetLongitude){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double radiusLat = degreeToRadius(targetLatitude-originLatitude);
        double radiusLong = degreeToRadius(targetLongitude -originLongitude);
        double haversine =
                Math.sin(radiusLat/2) * Math.sin(radiusLat/2) +
                        Math.cos(degreeToRadius(originLatitude)) * Math.cos(degreeToRadius(targetLatitude)) *
                                Math.sin(radiusLong/2) * Math.sin(radiusLong/2)
                ;
        double distance = 2 * Math.atan2(Math.sqrt(haversine), Math.sqrt(1-haversine));
        double distanceInMeters = earthRadius * distance * 1000;
        return Double.parseDouble(decimalFormat.format(distanceInMeters));
    }

    public Comparator<CarPark> sortCarPark(){
        Comparator<CarPark> asc = Comparator.comparing(carPark ->
                carPark.getDistance());
        return asc;
    }

    private double degreeToRadius(double deg) {
        return deg * (Math.PI/180);
    }
}
