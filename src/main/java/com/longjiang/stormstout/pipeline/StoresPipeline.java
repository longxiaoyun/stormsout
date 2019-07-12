package com.longjiang.stormstout.pipeline;

/**
 * @Author:longjiang
 * @date:下午5:03 2019/7/12
 * @Description: 数据存储
 **/
public interface StoresPipeline<T> {

    /**
     * 存储数据
     * @param result
     * @return T
     */
    public T storeToTarget(String result);
}
