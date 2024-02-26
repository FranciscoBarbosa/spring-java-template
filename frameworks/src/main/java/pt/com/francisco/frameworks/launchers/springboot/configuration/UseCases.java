package pt.com.francisco.frameworks.launchers.springboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.com.francisco.usecases.task.TaskGateway;
import pt.com.francisco.usecases.task.TaskInputBoundary;
import pt.com.francisco.usecases.task.TaskInteractor;
import pt.com.francisco.usecases.task.mappers.TaskRequestMapper;
import pt.com.francisco.usecases.task.mappers.TaskRequestMapperImpl;
import pt.com.francisco.usecases.task.mappers.TaskResponseMapper;
import pt.com.francisco.usecases.task.mappers.TaskResponseMapperImpl;

@Configuration
public class UseCases {
    @Bean
    TaskResponseMapper taskResponseMapper() {
        return new TaskResponseMapperImpl();
    }

    @Bean
    TaskRequestMapper taskRequestMapper() {
        return new TaskRequestMapperImpl();
    }

    @Bean
    TaskInputBoundary taskInputBoundary(
            TaskGateway taskGateway,
            TaskRequestMapper taskRequestMapper,
            TaskResponseMapper taskResponseMapper) {
        return new TaskInteractor(taskGateway, taskResponseMapper, taskRequestMapper);
    }
}
