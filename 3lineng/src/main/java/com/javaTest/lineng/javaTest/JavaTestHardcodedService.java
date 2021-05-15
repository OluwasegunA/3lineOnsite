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
        testauth.add(new JavaTest("e27f85eed0c70da142a0f9030c2b47317e9fbcd228972ccc2c538d4f97385d260183b558160d6e303616edbdf00ceb3eaca2f8423698d80d74d09b9fb20d5714", "test_20191123132233", 1617953042));
        
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
    
    public static String hashAppKeyAndTimeStamp(String appKey, long timeStamp){
        String generatedHash = null;
        String valueToHash = appKey + timeStamp;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
//            md.update(salt.getBytes(StandardCharsets.UTF_8));
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
    
    public ResponseEntity<?> findWithAuth(String appKey, long timeStamp, String hashed) {
        if (appKey.isEmpty() || timeStamp == 0){
            return new ResponseEntity("Invalid Request", HttpStatus.BAD_REQUEST);
        } else {
            String authorization = JavaTestHardcodedService.hashAppKeyAndTimeStamp(appKey, timeStamp);
            for (JavaTest test : testauth){

                if (authorization == hashed) {
                    JavaTest myTest = new JavaTest("3line " + hashed, appKey, timeStamp);
                    return new ResponseEntity(myTest, HttpStatus.OK);
                }
                else{
                    return new ResponseEntity("Invalid Authorization Key", HttpStatus.BAD_REQUEST);
                }
            }
        }
        return new ResponseEntity("In", HttpStatus.OK);
    }
    
    public static void main(String[] args) {
        JavaTestHardcodedService myTest = new JavaTestHardcodedService();
       String hashedValue = "e27f85eed0c70da142a0f9030c2b47317e9fbcd228972ccc2c538d4f97385d260183b558160d6e303616edbdf00ceb3eaca2f8423698d80d74d09b9fb20d5714";
       if(myTest.findWithAuth("test_20191123132233", 1617953042, hashedValue) == null) {
           System.out.println("Invalid Authorization key");          
       }
       else{
           System.out.println(myTest.findWithAuth("test_20191123132233", 1617953042, hashedValue));
       }
        
    }
}
