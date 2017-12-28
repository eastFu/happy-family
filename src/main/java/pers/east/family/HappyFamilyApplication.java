package pers.east.family;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : east.Fu
 * @Description : application entrance
 * @Date : Created in  2017/12/19 14:32
 */
@RestController
@SpringBootApplication
public class HappyFamilyApplication implements EmbeddedServletContainerCustomizer {

    private static final int PORT = 80;

    @RequestMapping("/say")
    public String sayHello(){
        return "hello family!";
    }

    public static void main(String[] args) {
        SpringApplication.run(HappyFamilyApplication.class,args);
    }


    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setPort(PORT);
    }
}
