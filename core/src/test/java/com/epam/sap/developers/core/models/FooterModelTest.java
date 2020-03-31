package com.epam.sap.developers.core.models;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FooterModelTest {

    @Test
    public void footerPathTest(){
        String pfthToSearch1 = null;
        String pagePath1 = "/content/sap_dev/us_221/en";
        Pattern pattern = Pattern.compile("/content/sap_dev/[^/]+");
        Matcher matcher = pattern.matcher(pagePath1);
        if (matcher.find()) {
            pfthToSearch1 = pagePath1.substring(matcher.start(), matcher.end());
        }
    }
}
