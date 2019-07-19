package com.longjiang.stormstout.response;

import com.longjiang.stormstout.request.Request;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:longjiang
 * @date:下午2:11 2019/7/15
 * @Description:
 **/
@Data
@NoArgsConstructor
public class Result<T> {
    private List<Request> requests = new ArrayList<>();
    private T item;


    public Result addRequest(Request request) {
        this.requests.add(request);
        return this;
    }

    public Result addRequests(List<Request> requests) {
        if (!requests.isEmpty()) {
            this.requests.addAll(requests);
        }
        return this;
    }
}
