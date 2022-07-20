package ru.otus.homework.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.homework.domain.EquipmentForMaintenance;

@IntegrationComponentScan
@ComponentScan
@Configuration
@EnableIntegration
public class SkiServiceIntegration {
    @Bean
    public IntegrationFlow skiServiceFlow() {
        return flow -> flow
                .log()
                .split()
                .log()
                .<EquipmentForMaintenance, String> route(
                        EquipmentForMaintenance::getTypeOfWork,
                        mapping -> mapping
                                .subFlowMapping("repair",
                                        sf -> sf.handle( "maintenanceServiceImpl", "repair" ))
                                .subFlowMapping("maintenance",
                                        sf -> sf.handle( "maintenanceServiceImpl", "maintenance" ))
                )
                .log()
                .aggregate()
                .log()
                .channel("maintainedEquipmentChannel");
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate( 100 ).maxMessagesPerPoll( 2 ).get();
    }

    @Bean
    public QueueChannel maintainedEquipmentChannel() {
        return MessageChannels.queue().get();
    }


}
