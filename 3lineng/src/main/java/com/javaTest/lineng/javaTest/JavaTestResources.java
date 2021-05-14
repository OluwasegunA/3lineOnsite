/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaTest.lineng.javaTest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
public class JavaTestResources {
    @Autowired
    private JavaTestHardcodedService hardcodedResources;
    
    @GetMapping(path = "/card-scheme/verify/{no}")
    public List<JavaTest> verifyCard(@PathVariable int no){
        return (List<JavaTest>) hardcodedResources.findByNo(no);
    }
}
