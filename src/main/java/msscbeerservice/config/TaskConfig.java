package msscbeerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


@EnableAsync
@Component
@EnableScheduling
public class TaskConfig {

    @Bean(name = "taskExecutor")
    public TaskExecutor taskExecutor(){
        return new SimpleAsyncTaskExecutor();
    }
}
