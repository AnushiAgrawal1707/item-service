package com.mykart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	       // add parameter
	 @Bean
	    public Docket api() {
		 //Adding Header
		 ParameterBuilder aParameterBuilder = new ParameterBuilder();
		 aParameterBuilder.name("Authorization")                 // name of header
				 .modelRef(new ModelRef("string"))
				 .parameterType("header")               // type - header
				 .defaultValue("Basic em9uZTpteXBhc3N3b3Jk")        // based64 of - zone:mypassword
				 .required(true)                // for compulsory
				 .build();
		 java.util.List<Parameter> aParameters = new ArrayList<>();
		 aParameters.add(aParameterBuilder.build());
		 return new Docket(DocumentationType.SWAGGER_2).select()
	            .apis(RequestHandlerSelectors
	                .basePackage("com.mykart"))
	            .paths(PathSelectors.regex("/.*"))
	            .build().apiInfo(apiEndPointsInfo())
				 .globalOperationParameters(aParameters);
	    }
	 
	 private ApiInfo apiEndPointsInfo() {
	        return new ApiInfoBuilder().title("Mykart User Data Service")
	            .description("User Data Service REST API")
	            .contact(new Contact("Anuja  Harane", null,null))
	            .license("Apache 2.0")
	            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
	            .version("1.0.0")
	            .build();
	    }
	

}
