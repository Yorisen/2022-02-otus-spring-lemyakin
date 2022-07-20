package ru.otus.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MaintainedEquipment {
    private String equipmentType;
    private String model;
}
