package ru.otus.homework.service;

import ru.otus.homework.domain.EquipmentForMaintenance;
import ru.otus.homework.domain.MaintainedEquipment;

public interface MaintenanceService {
    MaintainedEquipment maintenance(EquipmentForMaintenance equipmentForMaintenance) throws InterruptedException;
    MaintainedEquipment repair(EquipmentForMaintenance equipmentForMaintenance) throws InterruptedException;
}
