package com.github.chenhaiyangs.dto;

import java.io.Serializable;

/**
 * @author chenhaiyang
 */
public class DemoResponse implements Serializable{

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "DemoResponse{" +
                "result='" + result + '\'' +
                '}';
    }
}
