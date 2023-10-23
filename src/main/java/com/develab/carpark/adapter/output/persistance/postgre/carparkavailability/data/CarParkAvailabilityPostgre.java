package com.develab.carpark.adapter.output.persistance.postgre.carparkavailability.data;

import com.develab.carpark.util.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table("public.\"car_park_availability\"")
@NoArgsConstructor
@AllArgsConstructor
public class CarParkAvailabilityPostgre extends AbstractEntity<CarParkAvailabilityPostgre, BigDecimal> {

    @Id
    private BigDecimal id;
    private String carParkNumber;
    private String lotType;
    private int totalLots;
    private int availableLots;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
