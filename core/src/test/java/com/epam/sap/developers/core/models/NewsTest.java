package com.epam.sap.developers.core.models;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsTest {

    @Test
    public void newsDateFormat(){
        String pattern = "MMMM d, YYYY";
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern);
        System.out.println(simpleDateFormat.format(new Date()));
    }
}
