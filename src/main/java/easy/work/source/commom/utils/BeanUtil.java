package easy.work.source.commom.utils;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanUtil {
	 private static AnnotationConfigApplicationContext ctx;
	    static {
	        ctx = new AnnotationConfigApplicationContext();
	        ctx.scan("com.chenjh");
	        ctx.refresh();
	    }
	     
	    public static <T> T getBean(Class<T> clazz) {
	        return ctx.getBean(clazz);
	    }

}
