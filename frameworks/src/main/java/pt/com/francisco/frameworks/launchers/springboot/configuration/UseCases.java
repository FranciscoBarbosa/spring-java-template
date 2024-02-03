package pt.com.francisco.frameworks.launchers.springboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.com.francisco.usecases.task.TaskGateway;
import pt.com.francisco.usecases.task.TaskInputBoundary;
import pt.com.francisco.usecases.task.TaskInteractor;

@Configuration
public class UseCases {
    @Bean
    TaskInputBoundary taskInputBoundary(TaskGateway taskGateway) {
        return new TaskInteractor(taskGateway);
    }
}
