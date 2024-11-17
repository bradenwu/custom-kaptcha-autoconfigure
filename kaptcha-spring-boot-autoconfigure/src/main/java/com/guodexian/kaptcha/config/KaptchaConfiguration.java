package com.guodexian.kaptcha.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Properties;

@Configuration
@ConditionalOnClass(KaptchaServlet.class)
@Import(KaptchaProperties.class) //导入属性文件接收类
public class KaptchaConfiguration {
    @Autowired
    private KaptchaProperties kaptchaProperties;
    @Bean
    public Producer producer() {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.image.width", kaptchaProperties.getImageWidth() + "");
        properties.setProperty("kaptcha.image.height", kaptchaProperties.getImageHeigth() + "");

        properties.setProperty("kaptcha.textproducer.font.size", kaptchaProperties.getTextproducerFontSize() + "");
        properties.setProperty("kaptcha.textproducer.font.color", kaptchaProperties.getTextproducerFontColor());
        properties.setProperty("kaptcha.textproducer.char.string", kaptchaProperties.getTextproducerCharString());
        properties.setProperty("kaptcha.textproducer.char.length", kaptchaProperties.getTextproducerCharLength() + "");
        properties.setProperty("kaptcha.noise.impl", kaptchaProperties.getNoiseImpl());
        DefaultKaptcha kaptchaProducer = new DefaultKaptcha();
        Config config = new Config(properties);
        kaptchaProducer.setConfig(config);
        return kaptchaProducer;
    }
}
