package com.blairscott.config;

import com.blairscott.service.WrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 所谓自动配置，就是配置或创建核心业务实例
 */
@Configuration
@ConditionalOnClass(WrapService.class)
@EnableConfigurationProperties(WrapServiceProperties.class)
public class WrapServiceAutoConfiguration {

    @Autowired
    private WrapServiceProperties properties;

    @Bean
    @ConditionalOnProperty(name = "wrap.service.enable", havingValue = "true", matchIfMissing = true)
    public WrapService wrapService() {
        return new WrapService(properties.getPrefix(), properties.getSuffix());
    }

    // 发现当前实体没有被创建，则创建
    // @Configuration注解是按照代码顺序创建实体的，
    // 不能随意修改这其中的顺序，若将该方法放在首位执行，
    // @Configuration很有可能会创建两个实体，这样在启动的时候就会报错
    @Bean
    @ConditionalOnMissingBean
    public WrapService wrapService2() {
        return new WrapService("", "");
    }

}
