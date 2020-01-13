package cn.edu.bjfu.igarden;

import cn.edu.bjfu.igarden.controller.WebSocketServer;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@EnableConfigurationProperties
@SpringBootApplication
public class IgardenApplication extends WebMvcConfigurerAdapter {
    private static ConfigurableApplicationContext applicationContext;

    @RequestMapping("/")
    public String goUploadImg() {
        //跳转到 templates 目录下的 uploadimg.html
        return "uploadimg";
    }

    @RequestMapping(value = "/shutdown", method = RequestMethod.POST)
    public @ResponseBody
    String closeApp() {
        final int exitCode = 5;
        ExitCodeGenerator exitCodeGenerator = () -> exitCode;
        SpringApplication.exit(applicationContext, exitCodeGenerator);
        return "Have fun!";
    }

//    public static void main(String[] args) {
//        applicationContext = SpringApplication.run(IgardenApplication.class, args);
//    }

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(IgardenApplication.class);
        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
        //解决WebSocket不能注入的问题
        WebSocketServer.setApplicationContext(configurableApplicationContext);
    }
}
