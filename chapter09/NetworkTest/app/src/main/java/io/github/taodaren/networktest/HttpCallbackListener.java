package io.github.taodaren.networktest;

/**
 * 网络回掉接口
 */

public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
