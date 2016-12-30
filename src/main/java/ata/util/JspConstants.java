package ata.util;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 允许JSP通过EL访问E中定义的静态常量
 */
public class JspConstants extends HashMap<String, String> {

    public JspConstants(Class c) {
        Field[] fields = c.getDeclaredFields();
        for(Field field : fields) {
            int modifier = field.getModifiers();
            if(Modifier.isPublic(modifier) && Modifier.isStatic(modifier) && Modifier.isFinal(modifier)) {
                try {
                    put(field.getName(), field.get(null).toString());
                } catch(IllegalAccessException ignored) {
                }
            }
        }
    }

    private static ConcurrentHashMap<Class, JspConstants> const_cache = new ConcurrentHashMap<>();

    public static <E extends Class> JspConstants wrapper(E t) {
        if (const_cache.containsKey(t)) {
            return const_cache.get(t);
        }

        JspConstants jc = new JspConstants(t);
        const_cache.put(t, jc);
        return jc;
    }

    @Override
    public String get(Object key) {
        String result = super.get(key);
        if(StringUtils.isEmpty(result)) {
            throw new IllegalArgumentException("Check key! The key is wrong, no such constant! "
                    + (key!=null ? key.toString():"null"));
        }
        return result;
    }
}
