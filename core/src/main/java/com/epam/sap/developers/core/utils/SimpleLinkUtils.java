package com.epam.sap.developers.core.utils;

import com.day.cq.wcm.api.Page;
import com.epam.sap.developers.core.entities.SimpleLink;

public class SimpleLinkUtils {


    private SimpleLinkUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static SimpleLink getSimpleLinkFromPageWithCssClass(Page childPageItem, Page currentPage) {
        String pageJcrTitle = childPageItem.getTitle();
        String pageNaveTitle = childPageItem.getNavigationTitle();

        String pageTitle = (pageNaveTitle != null) ? pageNaveTitle : pageJcrTitle;

        return new SimpleLink(pageTitle, childPageItem.getPath(), isThisPageActive(childPageItem.getPath(), currentPage.getPath()));
    }

    private static String isThisPageActive(String childPageItemPath, String currentPagePath) {
        return childPageItemPath.equals(currentPagePath) ? "active" : "";
    }
}
