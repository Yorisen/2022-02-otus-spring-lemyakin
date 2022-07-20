package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.EquipmentForMaintenance;
import ru.otus.homework.domain.MaintainedEquipment;
import ru.otus.homework.viewer.MaintenanceViewer;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {
    private final MaintenanceViewer maintenanceViewer;

    @Override
    public MaintainedEquipment maintenance(EquipmentForMaintenance equipmentForMaintenance) throws InterruptedException {
        maintenanceViewer.startMaintenance(equipmentForMaintenance);

        edgeSharpening(equipmentForMaintenance);
        paraffinCoating(equipmentForMaintenance);

        maintenanceViewer.maintenanceIsEnded(equipmentForMaintenance);

        return new MaintainedEquipment(equipmentForMaintenance.getEquipmentType(), equipmentForMaintenance.getModel());
    }

    @Override
    public MaintainedEquipment repair(EquipmentForMaintenance equipmentForMaintenance) throws InterruptedException {
        maintenanceViewer.startRepair(equipmentForMaintenance);

        edgeRepairs(equipmentForMaintenance);
        baseRepair(equipmentForMaintenance);
        edgeSharpening(equipmentForMaintenance);
        paraffinCoating(equipmentForMaintenance);

        maintenanceViewer.repairIsEnded(equipmentForMaintenance);

        return new MaintainedEquipment(equipmentForMaintenance.getEquipmentType(), equipmentForMaintenance.getModel());
    }

    public void edgeSharpening(EquipmentForMaintenance equipmentForMaintenance) throws InterruptedException {
        maintenanceViewer.edgeSharpening(equipmentForMaintenance);
        Thread.sleep(1000);
    }


    public void paraffinCoating(EquipmentForMaintenance equipmentForMaintenance) throws InterruptedException {
        maintenanceViewer.paraffinCoating(equipmentForMaintenance);
        Thread.sleep(1000);
    }


    public void baseRepair(EquipmentForMaintenance equipmentForMaintenance) throws InterruptedException {
        maintenanceViewer.baseRepair(equipmentForMaintenance);
        Thread.sleep(2000);
    }


    public void edgeRepairs(EquipmentForMaintenance equipmentForMaintenance) throws InterruptedException {
        maintenanceViewer.edgeRepairs(equipmentForMaintenance);
        Thread.sleep(3000);
    }
}
