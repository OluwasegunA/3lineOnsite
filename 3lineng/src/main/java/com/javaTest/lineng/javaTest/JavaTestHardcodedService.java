/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaTest.lineng.javaTest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author user
 */
@Service
public class JavaTestHardcodedService {
    private static List<JavaTest> testauth = new ArrayList();
    
    
    static{
        testauth.add(new JavaTest("4n+F7tDHDaFCoPkDDCtHMX6fvNIolyzMLFONT5c4XSYBg7VYFg1uMDYW7b3wDOs+rKL4QjaY2A100Jufsg1XFA==", "test_20191123132233", new Date()));
        
    }
    
    
    public JavaTest save(JavaTest test){
        testauth.add(test);
        
        return test;
    }
    
    public JavaTest findByNo(int id){
        for(JavaTest todo : testauth){
            if(todo.getId() == id) return todo;
        }
        
        return null;
    }
    
    public static String hashAppKeyAndTimeStamp(String appKey, Date timeStamp, String salt){
        String generatedHash = null;
        String valueToHash = appKey + timeStamp;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(valueToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedHash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedHash;
    }
    
    public JavaTest findWithAuth(String appKey, Date timeStamp, String hashed) {
        for (JavaTest test : testauth){
            String authorization = JavaTestHardcodedService.hashAppKeyAndTimeStamp(appKey, timeStamp, "3line");
            if (authorization == hashed) {
                return new JavaTest("3line " + hashed, appKey, timeStamp);
            }
                          
        }
        return null;
    }
    
    public static void main(String[] args) {
        JavaTestHardcodedService myTest = new JavaTestHardcodedService();
        System.out.println(myTest.findWithAuth("test_20191123132233", new Date(), "4n+F7tDHDaFCoPkDDCtHMX6fvNIolyzMLFONT5c4XSYBg7VYFg1uMDYW7b3wDOs+rKL4QjaY2A100Jufsg1XFA=="));
    }
}
