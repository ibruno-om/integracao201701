package br.com.integracaosigtap.model.handler.annotation;

public @interface XMLClass {
	String nodeName() default "";

	String codigo() default "";

	String nome() default "";
}
