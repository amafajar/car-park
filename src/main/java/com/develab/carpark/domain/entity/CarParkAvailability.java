package com.develab.carpark.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarParkAvailability {
    private BigDecimal id;
    private String carParkNumber;
    private String lotType;
    private int totalLots;
    private int availableLots;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
