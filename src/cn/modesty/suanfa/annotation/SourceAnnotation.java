package cn.modesty.suanfa.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@IntDef({AnnotationMain.STATUS_OPEN,AnnotationMain.STATUS_CLOSE})//安卓注解库
@Retention(value = RetentionPolicy.SOURCE)//y应用
@Target(ElementType.PARAMETER)
public @interface SourceAnnotation {
}


