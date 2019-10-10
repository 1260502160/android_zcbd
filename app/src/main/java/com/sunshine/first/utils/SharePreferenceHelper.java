package com.sunshine.first.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by lijingyin on 2017/12/13.
 * sharePreference 存储
 */

public class SharePreferenceHelper {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    //保存在手机里的名字
    public static final String fileName = "share_datas";

    private static SharePreferenceHelper instance;

    public SharePreferenceHelper(Context mContext) {

        sharedPreferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SharePreferenceHelper getInstance(Context mContext) {

        if (instance == null) {
            instance = new SharePreferenceHelper(mContext);
        }
        return instance;
    }


    /**
     * 保存数据的方法，拿到数据保存数据的基本类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public void put(String key, Object object) {

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.commit();
    }

    /**
     * 获取保存数据的方法，我们根据默认值的到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key           键的值
     * @return
     */

    public String getString(String key, String str) {
        return sharedPreferences.getString(key, str);
    }

    public int getInt(String key, int i) {
        return sharedPreferences.getInt(key, i);
    }

    public boolean getBoolean(String key, boolean bool) {
        return sharedPreferences.getBoolean(key, bool);
    }

    public float getFloat(String key, float f) {
        return sharedPreferences.getFloat(key, f);
    }

    public long getLong(String key, long l) {
        return sharedPreferences.getLong(key, l);
    }


    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除所有的数据
     */
    public void clear() {
        editor.clear();
        editor.commit();
    }

    /**
     * 查询某个key是否存在
     *
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }


}
