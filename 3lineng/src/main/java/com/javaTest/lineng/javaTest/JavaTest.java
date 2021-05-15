/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaTest.lineng.javaTest;

import java.util.Date;


/**
 *
 * @author user
 */
public class JavaTest {

    private String authorization;
    private String appKey;
    private long timeStamp;
    private int Id;

    public JavaTest(String authorization, String appKey, long timeStamp) {
        this.Id = Id;
        this.authorization = authorization;
        this.appKey = appKey;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "JavaTest{" + "authorization=" + authorization + ", appKey=" + appKey + ", timeStamp=" + timeStamp + '}';
    }
    
}
