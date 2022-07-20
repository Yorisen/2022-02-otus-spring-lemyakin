package ru.otus.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EquipmentForMaintenance {
    private String equipmentType;
    private String typeOfWork;
    private String model;
}
