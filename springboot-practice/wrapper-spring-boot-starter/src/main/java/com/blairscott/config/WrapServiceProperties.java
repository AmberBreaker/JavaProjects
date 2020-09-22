package com.blairscott.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 要读取配置文件中wrap.service.prefix、wrap.service.suffix
 */
@Data
@ConfigurationProperties("wrap.service")
public class WrapServiceProperties {

    private String prefix;

    private String suffix;

}
