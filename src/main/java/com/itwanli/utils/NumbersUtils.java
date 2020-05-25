package com.itwanli.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class NumbersUtils {

    public static String RandomCode(){
        String str = new SimpleDateFormat("yyMMddHHmmss").format(new Date());

        Random random = new Random();
        int rannum = (int) ((random.nextDouble()*(9999-1000+1))+1000);
        return "SD"+str+rannum;
    }

    public static void main(String[] args) {
        System.out.println(RandomCode());
    }

}
