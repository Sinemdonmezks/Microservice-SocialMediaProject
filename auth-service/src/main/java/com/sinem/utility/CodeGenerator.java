package com.sinem.utility;

import org.springframework.stereotype.Component;

import java.util.UUID;

public class CodeGenerator {

    public static String generateCode(String value){
        String[] data=value.split("-");
    StringBuilder newcode=new StringBuilder();
    for (String s : data){
     newcode.append(s.charAt(0));
    }
        return newcode.toString();
         }
    }
