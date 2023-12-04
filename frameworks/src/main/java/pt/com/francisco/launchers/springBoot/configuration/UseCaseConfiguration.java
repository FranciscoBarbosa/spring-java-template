package pt.com.francisco.launchers.springBoot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.com.francisco.useCases.databaseGateways.TaskGateway;
import pt.com.francisco.useCases.interactorPort.TaskUseCaseImpl;

@Configuration
public class UseCaseConfiguration {

    @Bean
    TaskUseCaseImpl taskGateway(TaskGateway taskGateway){
        return new TaskUseCaseImpl(taskGateway);
    }
}
