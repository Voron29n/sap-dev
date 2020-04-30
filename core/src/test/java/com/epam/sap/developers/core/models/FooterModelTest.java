package com.epam.sap.developers.core.models;

import com.epam.sap.developers.core.entities.SimpleLink;
import com.epam.sap.developers.core.models.bean.SimpleLinkBean;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Test
    public void footerPathTest_2(){
        String pfthToSearch1 = null;
        String pagePath1 = "/content/developers/uk/sap";
        Pattern pattern = Pattern.compile("/content/developers/[^/]+/[^/]+");
        Matcher matcher = pattern.matcher(pagePath1);
        if (matcher.find()) {
            System.out.println(pagePath1.substring(matcher.start(), matcher.end()));
        } else {
            System.out.println("/content/developers/us/sap");
        }
    }

    @Test
    public void footerLinksTest(){
        Map<Integer, List<SimpleLinkBean>> mapOfSimpleLinkBean = new HashMap<>();
        int size = 10;
        int rowNum = (int) Math.ceil((float) size / 4 );
        while (rowNum > 0){
           mapOfSimpleLinkBean.put(rowNum, new ArrayList<>());
            rowNum--;
        }
    }


}
