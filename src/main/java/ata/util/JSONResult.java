package ata.util;

import com.alibaba.fastjson.JSONObject;

public class JSONResult {

    public static String success() {
        JSONObject json = new JSONObject();
        json.put("result", true);
        return json.toJSONString();
    }

    public static String success(Object data) {
        JSONObject json = new JSONObject();
        json.put("result", true);
        json.put("data", data);
        return json.toJSONString();
    }

    public static String success(String name, Object value) {
        JSONObject json = new JSONObject();
        json.put("result", true);
        json.put(name, value);
        return json.toJSONString();
    }

    public static String error(String error) {
        JSONObject json = new JSONObject();
        json.put("result", false);
        json.put("error", error);
        return json.toJSONString();
    }

    public static String error(String code, String error) {
        JSONObject json = new JSONObject();
        json.put("result", false);
        json.put("code", code);
        json.put("error", error);
        return json.toJSONString();
    }
}
