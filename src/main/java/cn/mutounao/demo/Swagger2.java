/*
 *  +----------------------------------------------------------------------
 *  | demo [ Swagger2Condig ]
 *  +----------------------------------------------------------------------
 *  | Copyright (c) 2018- strgeon All rights reserved.
 *  +----------------------------------------------------------------------
 *  | Licensed ( https://www.apache.org/licenses/LICENSE-2.0 )
 *  +----------------------------------------------------------------------
 *  | Author: strgeon <rnbug@qq.com> (2019-01-04)
 *  +----------------------------------------------------------------------
 *  | Remarks:
 *  |    1. edit me...
 *  +----------------------------------------------------------------------
 */

package cn.mutounao.demo;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2 {
    @Bean
    public Docket webApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("应用文档")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.mutounao.demo.controllers.apis"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API 文档")
                .description("应用基础API")
                .contact(new Contact("mutounao", "http://baidu.com", "1948919174@qq.com"))
                .version("1.0")
                .build();
    }
}
