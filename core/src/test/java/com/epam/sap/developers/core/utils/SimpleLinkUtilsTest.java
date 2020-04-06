package com.epam.sap.developers.core.utils;

import com.day.cq.wcm.api.Page;
import com.epam.sap.developers.core.entities.SimpleLink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SimpleLinkUtilsTest {

    private final Page currentPage = mock(Page.class);
    private final Page childPageItem = mock(Page.class);
    private SimpleLink expectedSimpleLink;

    @BeforeEach
    public void init(){

        String pageTitle = "title";
        String pagePath = "/content/developers/uk/sap";

        this.expectedSimpleLink = new SimpleLink(pageTitle, pagePath , "active");

        when(currentPage.getPath())
                .thenReturn(pagePath);
        when(childPageItem.getPath())
                .thenReturn(pagePath);
        when(childPageItem.getTitle())
                .thenReturn(pageTitle);
        when(childPageItem.getNavigationTitle())
                .thenReturn(null);

    }

    @Test
    void getSimpleLinkFromPage() {
        SimpleLink actualSimpleLink = SimpleLinkUtils.getSimpleLinkFromPage(this.childPageItem, this.currentPage);
        assertEquals(expectedSimpleLink, actualSimpleLink);
    }
}