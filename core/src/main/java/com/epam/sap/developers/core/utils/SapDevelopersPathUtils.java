package com.epam.sap.developers.core.utils;

import org.apache.jackrabbit.oak.commons.PathUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SapDevelopersPathUtils {

    private static final String ROOT_PAGE_PATH = ServiceUtils.getCrxPath("content/developers");
    private static final String PATTER_ONE_LEVEL = "/[^/]+";

    private SapDevelopersPathUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Return NULL if path not found
     */
//    @Nullable
    public static String getPathByLevelRelativeToRootPath(String pagePath, int levelToCut) {
        if (PathUtils.isValid(pagePath) && levelToCut > 0) {

            Pattern pattern = Pattern.compile(getPatternByLevel(levelToCut));
            Matcher matcher = pattern.matcher(pagePath);
            if (matcher.find()) {
                return pagePath.substring(matcher.start(), matcher.end());
            } else {
                return pagePath;
            }
        } else {
            return pagePath;
        }
    }

    private static String getPatternByLevel(int levelToCut) {
        StringBuilder pattern = new StringBuilder(ROOT_PAGE_PATH);
        for (int i = 0; i < levelToCut; i++) {
            pattern.append(PATTER_ONE_LEVEL);
        }
        return pattern.toString();
    }
}
