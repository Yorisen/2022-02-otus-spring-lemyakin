package ru.otus.homework.viewer;

import org.springframework.stereotype.Component;
import ru.otus.homework.domain.EquipmentForMaintenance;

@Component
public class MaintenanceViewerImpl implements MaintenanceViewer {
    @Override
    public void startMaintenance(EquipmentForMaintenance equipmentForMaintenance) {
        System.out.println("Starting maintenance of " + equipmentForMaintenance.getEquipmentType() +
                " \"" + equipmentForMaintenance.getModel() + "\" ...");
    }

    @Override
    public void maintenanceIsEnded(EquipmentForMaintenance equipmentForMaintenance) {
        System.out.println("Maintenance of " + equipmentForMaintenance.getEquipmentType() +
                " \"" + equipmentForMaintenance.getModel() + "\" is ended");
    }

    @Override
    public void startRepair(EquipmentForMaintenance equipmentForMaintenance) {
        System.out.println("Starting repair of " + equipmentForMaintenance.getEquipmentType() +
                " \"" + equipmentForMaintenance.getModel() + "\" ...");
    }

    @Override
    public void repairIsEnded(EquipmentForMaintenance equipmentForMaintenance) {
        System.out.println("Repair of " + equipmentForMaintenance.getEquipmentType() +
                " \"" + equipmentForMaintenance.getModel() + "\" is ended");
    }

    @Override
    public void edgeSharpening(EquipmentForMaintenance equipmentForMaintenance) {
        System.out.println(equipmentForMaintenance.getEquipmentType() +
                " \"" + equipmentForMaintenance.getModel() + "\" edge sharpening");
    }

    @Override
    public void paraffinCoating(EquipmentForMaintenance equipmentForMaintenance) {
        System.out.println(equipmentForMaintenance.getEquipmentType() +
                " \"" + equipmentForMaintenance.getModel() + "\" paraffin coating");
    }

    @Override
    public void baseRepair(EquipmentForMaintenance equipmentForMaintenance) {
        System.out.println(equipmentForMaintenance.getEquipmentType() +
                " \"" + equipmentForMaintenance.getModel() + "\" base repair");
    }

    @Override
    public void edgeRepairs(EquipmentForMaintenance equipmentForMaintenance) {
        System.out.println(equipmentForMaintenance.getEquipmentType() +
                " \"" + equipmentForMaintenance.getModel() + "\" edge repair");
    }
}
