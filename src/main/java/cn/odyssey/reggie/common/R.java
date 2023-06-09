package cn.odyssey.reggie.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 把所有的响应结果都包装成这个类，返回给前端
 * @param <T>
 */
@Data
public class R<T> {

    private Integer code;
    private String msg;
    private T data;
    private Map map = new HashMap();

    public static <T> R<T> success(T object) {
        R<T> tr = new R<T>();
        tr.code = 1;
        tr.data = object;
        return tr;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.code = 0;
        r.msg = msg;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
