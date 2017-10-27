package com.activiti.demo.entity;

public class JsonResult {
    /**
     * 状态码
     */
    private int resultState;
    /**
     * 状态消息
     */
    private String resultMessage;
    /**
     * 返回对象
     */
    private Object resultData;
    
    public int getResultState() {
        return resultState;
    }
    public void setResultState(int resultState) {
        this.resultState = resultState;
    }
    public String getResultMessage() {
        return resultMessage;
    }
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
    public Object getResultData() {
        return resultData;
    }
    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }
	
}
