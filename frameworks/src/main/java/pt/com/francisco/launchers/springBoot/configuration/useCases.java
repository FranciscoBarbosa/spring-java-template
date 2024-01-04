package pt.com.francisco.launchers.springBoot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.com.francisco.entities.Task;
import pt.com.francisco.useCases.task.TaskGateway;
import pt.com.francisco.useCases.task.TaskInputBoundary;
import pt.com.francisco.useCases.task.TaskInteractor;

@Configuration
public class useCases {
    @Bean
    TaskInputBoundary taskInputBoundary(TaskGateway taskGateway){
        return new TaskInteractor(taskGateway);
    }
}
