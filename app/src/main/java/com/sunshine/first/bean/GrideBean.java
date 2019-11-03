package com.sunshine.first.bean;

import java.io.Serializable;

/**
 * @author dell
 * @CreateDate: 2019-11-03 16:12
 * @Description:
 * @Version: 1.0
 */
public class GrideBean implements Serializable{
    public int icon;
    public String name;

    public GrideBean(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }
}
