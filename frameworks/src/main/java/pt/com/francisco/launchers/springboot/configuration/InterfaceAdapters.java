package pt.com.francisco.launchers.springboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.com.francisco.interfaceadapters.controllers.TaskController;
import pt.com.francisco.usecases.task.TaskInputBoundary;

@Configuration
public class InterfaceAdapters {

    @Bean
    TaskController taskController(TaskInputBoundary taskInputBoundary){
        return new TaskController(taskInputBoundary);
    }
}
