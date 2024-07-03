package ru.itmo.wp.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//-> то, к чему применяется аннотация (у нас к методу)
@Target(ElementType.METHOD)
//-> аннотация должна быть доступна в рантайме
@Retention(RetentionPolicy.RUNTIME)
public @interface Json {
}
