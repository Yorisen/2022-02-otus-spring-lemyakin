package ru.otus.homework.viewer;

import ru.otus.homework.domain.MaintainedEquipment;

import java.util.Collection;

public interface MaintainedEquipmentViewer {
    void view(Collection<MaintainedEquipment> maintainedEquipmentList);
}
