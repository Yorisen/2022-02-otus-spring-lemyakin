package ru.otus.homework.viewer;

import ru.otus.homework.domain.EquipmentForMaintenance;

public interface MaintenanceViewer {
    void startMaintenance(EquipmentForMaintenance equipmentForMaintenance);
    void maintenanceIsEnded(EquipmentForMaintenance equipmentForMaintenance);
    void startRepair(EquipmentForMaintenance equipmentForMaintenance);
    void repairIsEnded(EquipmentForMaintenance equipmentForMaintenance);
    void edgeSharpening(EquipmentForMaintenance equipmentForMaintenance);
    void paraffinCoating(EquipmentForMaintenance equipmentForMaintenance);
    void baseRepair(EquipmentForMaintenance equipmentForMaintenance);
    void edgeRepairs(EquipmentForMaintenance equipmentForMaintenance);
}
