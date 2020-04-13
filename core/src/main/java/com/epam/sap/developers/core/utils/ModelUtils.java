package com.epam.sap.developers.core.utils;

import org.apache.sling.api.resource.Resource;

public class ModelUtils {

    private static final String IMAGE_FILE_NAME = "/file";

    private ModelUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getImageSrc(String fileName, String fileReference, Resource currentResource) {
        if (fileName == null && fileReference == null) {
            return null;
        } else if (fileReference != null) {
            return fileReference;
        }
        return currentResource.getPath().concat(IMAGE_FILE_NAME);
    }
}
