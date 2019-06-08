package cn.mutounao.demo;

import cn.mutounao.demo.config.AppErrorPageRegistrar;
import cn.mutounao.demo.util.pojo.CColor;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.HttpMessageConverter;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ServletComponentScan
@ImportResource(locations = {"classpath:druid-bean.xml"})
@MapperScan(basePackages = "cn.mutounao.demo.mapper")
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.print(CColor.BLUE_BACKGROUND);
        System.out.print(CColor.RED); //设置前景色 为YELLOW
        System.out.println("\r\n\t\t应用启动完成 :) 应用启动完成\r\n");
        System.out.print(CColor.RESET);
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1. 需要定义一个converter转换消息的对象
        FastJsonHttpMessageConverter fasHttpMessageConverter = new FastJsonHttpMessageConverter();
        // 2. 添加fastjson的配置信息，比如:是否需要格式化返回的json的数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 3. 在converter中添加配置信息
        fasHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fasHttpMessageConverter;
        return new HttpMessageConverters(converter);
    }

    @Bean
    public ErrorPageRegistrar errorPageRegistrar(){
        return new AppErrorPageRegistrar();
    }
}

