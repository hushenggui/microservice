package com.shenzhou.hu.springboot.moudles.annotation.entity;

import com.shenzhou.hu.springboot.moudles.annotation.TesMethodAnnotion;
import com.shenzhou.hu.springboot.moudles.annotation.TestAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TestAnnotation(id = 20190811,name="test")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Car implements Serializable{
    private String name;
    private String id;

    @TesMethodAnnotion(id = 4)
    public String getName(){
        return "my name";
    }
}
