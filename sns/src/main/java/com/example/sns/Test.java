package com.example.sns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

class Data{
    String key;
    String value;

    public Data(String key, String value) {
        this.key = key;
        this.value = value;
    }
}

public class Test {

    public static void main(String[] args) {
//        JSONArray ja = new JSONArray();
//        ja.put("a");
//        ja.put("b");
//        ja.put("c");

        Map<String, String> map = new HashMap<>();
        map.put("test", "1");
        map.put("test", "2");

        JSONObject test = new JSONObject();
        test.put("test", "1");
        test.put("test", "2");
        test.put("test", "3");

        JSONObject obj = new JSONObject();
        obj.put("test", map);

        System.out.println(obj);


    }
}
