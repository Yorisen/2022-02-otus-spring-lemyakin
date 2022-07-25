package ru.otus.homework.viewer;

import org.springframework.stereotype.Component;
import ru.otus.homework.domain.MaintainedEquipment;

import java.util.Collection;

@Component
public class MaintainedEquipmentViewerImpl implements MaintainedEquipmentViewer {
    @Override
    public void view(Collection<MaintainedEquipment> maintainedEquipmentList) {
        for (MaintainedEquipment maintainedEquipment: maintainedEquipmentList) {
            System.out.println(maintainedEquipment.getEquipmentType() +
                    " \"" + maintainedEquipment.getModel() + "\" is ready for pickup");
        }
    }
}
