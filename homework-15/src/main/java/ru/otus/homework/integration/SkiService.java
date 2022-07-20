package ru.otus.homework.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.homework.domain.EquipmentForMaintenance;
import ru.otus.homework.domain.MaintainedEquipment;

import java.util.Collection;

@MessagingGateway
public interface SkiService {
    @Gateway(requestChannel = "skiServiceFlow.input", replyChannel = "maintainedEquipmentChannel")
    Collection<MaintainedEquipment> process(Collection<EquipmentForMaintenance> orderItem);
}
