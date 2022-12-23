package com.example.demotda.util;


public class FormatNumber {
    public  String Format(int number){
        String newNumber=String.format("%,d",number);
        return newNumber;
    }
}
