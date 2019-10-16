package com.abner.ming.base.mvp.presenter;


import java.util.Map;

import okhttp3.RequestBody;

/**
 * author:AbnerMing
 * date:2019/4/18
 */
public interface BasePresenter {

    void get(int type, String url, Map<String, String> map);

    void post(int type, String url, Map<String, String> map);

    void post(int type, String url, RequestBody requestBody);

    void put(int type, String url, Map<String, String> map);

    void delete(int type, String url, Map<String, String> map);
}
