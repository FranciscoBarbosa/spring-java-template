package pt.com.francisco.frameworks.launchers.springboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.com.francisco.interfaceadapters.controllers.TaskController;
import pt.com.francisco.usecases.task.TaskGateway;
import pt.com.francisco.usecases.task.interactors.TaskInputBoundaryFactory;

@Configuration
public class InterfaceAdapters {

    @Bean
    TaskInputBoundaryFactory taskInputBoundaryFactory(TaskGateway taskGateway) {
        return new TaskInputBoundaryFactory(taskGateway);
    }

    @Bean
    TaskController taskController(TaskInputBoundaryFactory taskInputBoundaryFactory) {
        return new TaskController(taskInputBoundaryFactory);
    }
}
