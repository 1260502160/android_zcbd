package com.abner.ming.base.mvp.presenter;


import com.abner.ming.base.mvp.model.BaseModel;
import com.abner.ming.base.mvp.view.BaseView;

import org.greenrobot.greendao.annotation.Id;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * author:AbnerMing
 * date:2019/4/18
 */
public class BasePresenterIml implements BasePresenter, BaseModel.CallBackListener {

    private BaseModel baseModel;
    private BaseView baseView;

    public BasePresenterIml(BaseModel baseModel, BaseView baseView) {
        this.baseModel = baseModel;
        this.baseView = baseView;
    }

    @Override
    public void get(int type, String url, Map<String, String> map) {
        baseModel.get(type, url, map, this);
    }

    @Override
    public void post(int type, String url, Map<String, String> map) {
        baseModel.post(type, url, map, this);
    }

    @Override
    public void post(int type, String url, RequestBody requestBody) {
        baseModel.post(type, url, requestBody, this);
    }

    @Override
    public void put(int type, String url, Map<String, String> map) {
        baseModel.put(type, url, map, this);
    }

    @Override
    public void delete(int type, String url, Map<String, String> map) {
        baseModel.delete(type, url, map, this);
    }

    //返回成功
    @Override
    public void success(int type, String data) {
        if (baseView != null){
            baseView.success(type, data);
        }
    }

    //返回成功  返回JavaBean
    @Override
    public void successBean(int type, Object o) {
        baseView.successBean(type, o);
    }


    //返回失败
    @Override
    public void fail(int type, String error) {
        if (baseView != null) {
            baseView.success(type, error);
        }
    }
    public void destory() {
        if (baseModel != null) {
            baseModel = null;
        }
        if (baseView != null) {
            baseView = null;
        }
//        System.gc();

    }
}
