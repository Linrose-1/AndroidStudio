package com.rose.order.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rose.order.bean.ShopBean;

import java.lang.reflect.Type;
import java.util.List;

public class JsonParse {
    // 单例模式的实例变量
    private static JsonParse instance;

    // 私有的构造方法，防止外部创建实例
    private JsonParse() {
    }

    // 获取单例实例的方法
    public static JsonParse getInstance() {
        // 如果实例为空，则创建一个新的实例
        if (instance == null) {
            instance = new JsonParse();
        }
        // 返回实例
        return instance;
    }

    // 将 JSON 字符串解析为 ShopBean 对象的列表
    public List<ShopBean> getShopList(String json) {
        // 创建 Gson 对象，用于解析 JSON
        Gson gson = new Gson();
        // 定义泛型类型，用于解析 List<ShopBean>
        Type listType = new TypeToken<List<ShopBean>>(){}.getType();
        // 将 JSON 字符串解析为 ShopBean 对象的列表
        List<ShopBean> shopList = gson.fromJson(json, listType);
        // 返回解析后的 ShopBean 对象列表
        return shopList;
    }
}
