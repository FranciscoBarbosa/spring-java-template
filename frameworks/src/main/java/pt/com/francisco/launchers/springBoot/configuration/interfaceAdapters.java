package pt.com.francisco.launchers.springBoot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.com.francisco.interfaceAdapters.controllers.TaskController;
import pt.com.francisco.useCases.task.TaskInputBoundary;

@Configuration
public class interfaceAdapters {

    @Bean
    TaskController taskController(TaskInputBoundary taskInputBoundary){
        return new TaskController(taskInputBoundary);
    }
}
