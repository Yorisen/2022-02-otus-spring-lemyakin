package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.domain.EquipmentForMaintenance;
import ru.otus.homework.integration.SkiService;
import ru.otus.homework.viewer.MaintainedEquipmentViewer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

@ShellComponent
@RequiredArgsConstructor
public class SkiServiceCommands {
    private final SkiService skiService;
    private final MaintainedEquipmentViewer maintainedEquipmentViewer;

    @ShellMethod(value = "Ski maintenance", key = {"ski-maintenance", "sm"})
    public void skiMaintenance(@ShellOption({"-m", "--model"}) String model) {
        maintainedEquipmentViewer.view(
                skiService.process(
                        List.of(new EquipmentForMaintenance("ski", "maintenance", model))
                )
        );
    }

    @ShellMethod(value = "Ski repair", key = {"ski-repair", "sr"})
    public void skiRepair(@ShellOption({"-m", "--model"}) String model) {
        maintainedEquipmentViewer.view(
                skiService.process(
                        List.of(new EquipmentForMaintenance("ski", "repair", model))
                )
        );
    }

    @ShellMethod(value = "Snowboard maintenance", key = {"snowboard-maintenance", "sbm"})
    public void snowboardMaintenance(@ShellOption({"-m", "--model"}) String model) {
        maintainedEquipmentViewer.view(
                skiService.process(
                        List.of(new EquipmentForMaintenance("snowboard", "maintenance", model))
                )
        );
    }

    @ShellMethod(value = "Snowboard repair", key = {"snowboard-repair", "sbr"})
    public void snowboardRepair(@ShellOption({"-m", "--model"}) String model) {
        maintainedEquipmentViewer.view(
                skiService.process(
                        List.of(new EquipmentForMaintenance("snowboard", "repair", model))
                )
        );
    }

    @ShellMethod(value = "Multiple snowboards maintenance, models separated by commas",
            key = {"multi-snowboard-maintenance", "msbm"})
    public void multipleSnowboardsMaintenance(@ShellOption({"-m", "--models"}) String models) {
        List<String> modelArray = List.of(models.split(","));
        List<EquipmentForMaintenance> equipmentForMaintenanceList = new ArrayList<>();

        for (String model: modelArray) {
            equipmentForMaintenanceList.add(
                    new EquipmentForMaintenance("snowboard", "maintenance", model));
        }

        maintainedEquipmentViewer.view(
            skiService.process(equipmentForMaintenanceList)
        );
    }

    @ShellMethod(value = "Multiple snowboards repair, models separated by commas",
            key = {"multi-snowboard-repair", "msbr"})
    public void multipleSnowboardsRepair(@ShellOption({"-m", "--models"}) String models) {
        List<String> modelArray = List.of(models.split(","));
        List<EquipmentForMaintenance> equipmentForMaintenanceList = new ArrayList<>();

        for (String model: modelArray) {
            equipmentForMaintenanceList.add(
                    new EquipmentForMaintenance("snowboard", "repair", model));
        }

        maintainedEquipmentViewer.view(
                skiService.process(equipmentForMaintenanceList)
        );
    }

    @ShellMethod(value = "Multiple skis repair, models separated by commas",
            key = {"multi-ski-repair", "msr"})
    public void multipleSkisRepair(@ShellOption({"-m", "--models"}) String models) {
        List<String> modelArray = List.of(models.split(","));
        List<EquipmentForMaintenance> equipmentForMaintenanceList = new ArrayList<>();

        for (String model: modelArray) {
            equipmentForMaintenanceList.add(
                    new EquipmentForMaintenance("ski", "repair", model));
        }

        maintainedEquipmentViewer.view(
                skiService.process(equipmentForMaintenanceList)
        );
    }

    @ShellMethod(value = "Multiple skis maintenance, models separated by commas",
            key = {"multi-ski-maintenance", "msm"})
    public void multipleSkisMaintenance(@ShellOption({"-m", "--models"}) String models) {
        List<String> modelArray = List.of(models.split(","));
        List<EquipmentForMaintenance> equipmentForMaintenanceList = new ArrayList<>();

        for (String model: modelArray) {
            equipmentForMaintenanceList.add(
                    new EquipmentForMaintenance("ski", "maintenance", model));
        }

        maintainedEquipmentViewer.view(
                skiService.process(equipmentForMaintenanceList)
        );
    }
}
