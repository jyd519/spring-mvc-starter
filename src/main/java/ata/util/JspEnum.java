package ata.util;
import java.util.HashMap;

/**
 * 允许JSP通过EL访问E中定义的Enum类型
 * Created by jiyongdong on 15/6/17.
 */
public class JspEnum <E extends Enum<E>> extends HashMap<String, Object> {
    private final E[] enums;

    public  JspEnum(Class<E> type) {
        this.enums = type.getEnumConstants();

        for(Enum<E> e : enums) {
            put(e.name(), e);
        }
    }

    public static <E extends Enum<E>> JspEnum wrapper(Class<E> type) {
        return new JspEnum(type);
    }

    @Override
    public Object get(Object key) {
        Object result = super.get(key);
        if(result == null) {
            throw new IllegalArgumentException("Check key! The key is wrong, no such Enum! "
                    + (key!=null ? key.toString():"null"));
        }
        return result;
    }
}
