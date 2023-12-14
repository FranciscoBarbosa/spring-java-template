package pt.com.francisco.launchers.springBoot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.com.francisco.launchers.springBoot.dataAccess.TaskGatewayImpl;
import pt.com.francisco.useCases.databaseGateways.TaskGateway;
import pt.com.francisco.useCases.inputPort.TaskUseCase;
import pt.com.francisco.useCases.interactorPort.TaskUseCaseImpl;

@Configuration
public class UseCaseConfiguration {

    @Bean
    TaskGateway taskGateway(){
        return new TaskGatewayImpl();
    }

    @Bean
    TaskUseCase taskUseCase(TaskGateway taskGateway){
        return new TaskUseCaseImpl(taskGateway);
    }

}
