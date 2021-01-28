package cn.modesty.suanfa.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

@Target({ANNOTATION_TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface IntDef {//安卓注解包里面的
    int[] value() default {};
}
