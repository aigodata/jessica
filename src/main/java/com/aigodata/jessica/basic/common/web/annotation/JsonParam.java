package com.aigodata.jessica.basic.common.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.ValueConstants;

/**
 * JSON请求参数绑定注解, 方法参数绑定到请求JSON的属性中
 * 
 * @author mengxiangyun
 *
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonParam {

	String value() default "";

	boolean required() default true;

	String defaultValue() default ValueConstants.DEFAULT_NONE;

}
