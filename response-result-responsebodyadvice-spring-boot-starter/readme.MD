使用方法

(1) 客户module的POM配置中引入依赖

    <groupId>org.lcm.response</groupId>
    <artifactId>response-result-spring-boot-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>

(2) 客户module的主启动类上不需要使用注解。
    注：使用了@AutoConfigureBefore(WebMvcAutoConfiguration.class)，
    解决了必须在客户module的主启动类上使用注解@EnableWebMvc的问题。
    