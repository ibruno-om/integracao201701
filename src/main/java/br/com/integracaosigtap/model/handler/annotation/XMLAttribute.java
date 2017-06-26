package br.com.integracaosigtap.model.handler.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface XMLAttribute {
	String fieldName() default "";
}
