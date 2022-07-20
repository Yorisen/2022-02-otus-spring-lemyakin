package ru.otus.homework.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import ru.otus.homework.domain.EquipmentForMaintenance;
import ru.otus.homework.domain.MaintainedEquipment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SkiServiceIntegrationTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testIntegrationGateway() {
        MessageChannel requestChannel =
                applicationContext.getBean("skiServiceFlow.input",
                        MessageChannel.class);
        QueueChannel responseChannel = applicationContext
                .getBean("maintainedEquipmentChannel", QueueChannel.class);

        EquipmentForMaintenance equipmentForMaintenance =
                new EquipmentForMaintenance("ski","repair", "model");

        requestChannel
                .send(new GenericMessage<>(List.of(equipmentForMaintenance)));

        assertThat(responseChannel.receive(10000).getPayload())
                .isEqualTo(
                        List.of(
                                new MaintainedEquipment(equipmentForMaintenance.getEquipmentType(),
                                    equipmentForMaintenance.getModel())
                        )
                );
    }
}