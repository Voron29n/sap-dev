package com.epam.sap.developers.core.utils;

import org.apache.sling.api.resource.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ModelUtils {

    private static final String IMAGE_FILE_NAME = "/file";
    private static final String DATE_PATTERN = "MMMM d, YYYY";

    private ModelUtils() {
        throw new IllegalStateException("Utility class");
    }

//    @Nullable
    public static String getImageSrc(String fileName, String fileReference, Resource currentResource) {
        if (fileName == null && fileReference == null) {
            return null;
        } else if (fileReference != null) {
            return fileReference;
        }
        return currentResource.getPath().concat(IMAGE_FILE_NAME);
    }

//    @Nullable
    public static String formatDateToStr(Date date) {
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
            return formatter.format(date);
        } else {
            return null;
        }
    }
}
