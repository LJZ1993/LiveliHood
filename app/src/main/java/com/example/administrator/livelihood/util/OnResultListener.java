package com.example.administrator.livelihood.util;

import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
public interface OnResultListener<T> {
    void onSuccess(List<T> data);

    void onFailded(String message);
}
