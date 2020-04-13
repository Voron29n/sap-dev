package com.epam.sap.developers.core.utils;

import com.day.cq.wcm.api.Page;
import com.epam.sap.developers.core.entities.SimpleLink;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleLinkUtils {

    private static final String PATTERN_FOR_LINK_AT_CURRENT_PROJECT = "/content/developers";

    private SimpleLinkUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getLinkForButtonFromPathField(String buttonHref) {
        return (isLinkApplyToCurrentProject(buttonHref)) ? buttonHref.concat(".html") : buttonHref;
    }

    private static boolean isLinkApplyToCurrentProject(String buttonHref) {
        Pattern pattern = Pattern.compile(PATTERN_FOR_LINK_AT_CURRENT_PROJECT);
        Matcher matcher = pattern.matcher(buttonHref);
        return matcher.find();
    }

    public static SimpleLink getSimpleLinkFromPageWithCssClass(Page childPageItem, Page currentPage) {
        String pageJcrTitle = childPageItem.getTitle();
        String pageNaveTitle = childPageItem.getNavigationTitle();

        String pageTitle = (pageNaveTitle != null) ? pageNaveTitle : pageJcrTitle;

        return new SimpleLink(pageTitle, childPageItem.getPath(), isPageActive(childPageItem.getPath(), currentPage.getPath()));
    }

    private static String isPageActive(String childPageItemPath, String currentPagePath) {
        return childPageItemPath.equals(currentPagePath) ? "active" : "";
    }
}
