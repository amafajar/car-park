package com.develab.carpark.adapter.input.rest.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class CarParkInformationCsv {

    @CsvBindByName(column = "car_park_no")
    private String carParkNumber;
    @CsvBindByName(column = "address")
    private String address;
    @CsvBindByName(column = "x_coord")
    private String xCoordinate;
    @CsvBindByName(column = "y_coord")
    private String yCoordinate;
    @CsvBindByName(column = "car_park_type")
    private String carParkType;
    @CsvBindByName(column = "type_of_parking_system")
    private String parkingSystemType;
    @CsvBindByName(column = "short_term_parking")
    private String shortTermParking;
    @CsvBindByName(column = "free_parking")
    private String freeParking;
    @CsvBindByName(column = "night_parking")
    private String nightParking;
    @CsvBindByName(column = "car_park_decks")
    private String carParkDecks;
    @CsvBindByName(column = "gantry_height")
    private String gantryHeight;
    @CsvBindByName(column = "car_park_basement")
    private String carParkBasement;

    @Override
    public String toString() {
        return "{\"CarParkInformationCsv\":{"
                + "\"carParkNumber\":\"" + carParkNumber + "\""
                + ", \"address\":\"" + address + "\""
                + ", \"xCoordinate\":\"" + xCoordinate + "\""
                + ", \"yCoordinate\":\"" + yCoordinate + "\""
                + ", \"carParkType\":\"" + carParkType + "\""
                + ", \"parkingSystemType\":\"" + parkingSystemType + "\""
                + ", \"shortTermParking\":\"" + shortTermParking + "\""
                + ", \"freeParking\":\"" + freeParking + "\""
                + ", \"nightParking\":\"" + nightParking + "\""
                + ", \"carParkDecks\":\"" + carParkDecks + "\""
                + ", \"gantryHeight\":\"" + gantryHeight + "\""
                + ", \"carParkBasement\":\"" + carParkBasement + "\""
                + "}}";
    }
}
