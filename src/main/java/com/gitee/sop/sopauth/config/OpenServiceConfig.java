package com.gitee.sop.sopauth.config;

import com.gitee.sop.servercommon.bean.ServiceConfig;
import com.gitee.sop.servercommon.configuration.AlipayServiceConfiguration;
import com.gitee.sop.servercommon.swagger.SwaggerSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.context.annotation.Configuration;

/**
 * 使用支付宝开放平台功能
 *
 * @author liumf
 */
@Configuration
public class OpenServiceConfig extends AlipayServiceConfiguration {


    static {
        ServiceConfig.getInstance().getI18nModules().add("i18n/isp/goods_error");
    }
 // 开启文档
    @Configuration
    @EnableSwagger2
    public static class Swagger2 extends SwaggerSupport {
        @Override
        protected String getDocTitle() {
            return "授权";
        }
    }

}
