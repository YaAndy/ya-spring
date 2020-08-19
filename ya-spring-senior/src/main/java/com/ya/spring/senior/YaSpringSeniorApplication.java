package com.ya.spring.senior;

import com.ya.spring.senior.async.AsyncTaskExecutorService;
import com.ya.spring.senior.aware.AwareService;
import com.ya.spring.senior.conf.AwareConfig;
import com.ya.spring.senior.conf.ScheduledConfig;
import com.ya.spring.senior.conf.TaskExecutorConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class YaSpringSeniorApplication {

    public static void main(String[] args) {
//        SpringApplication.run(YaSpringSeniorApplication.class, args);


        YaSpringSeniorApplication application = new YaSpringSeniorApplication();
//        application.aware();
//        application.asyncTaskExecutor();
        application.scheduled();


    }


    public  void scheduled(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScheduledConfig.class);
    }



    /**
     * 结果是并发执行并不是顺序执行
     */
    public void asyncTaskExecutor(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
        AsyncTaskExecutorService asyncTaskExecutorService = context.getBean(AsyncTaskExecutorService.class);
        for (int i = 0; i < 6 ; i++) {
            asyncTaskExecutorService.executeAsyncTask(i);
            asyncTaskExecutorService.executeAsyncTaskPlus(i);
        }
        context.close();
    }


    public void aware(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);
        AwareService awareService = context.getBean(AwareService.class);
        awareService.outputResult();
        context.close();

    }

}
