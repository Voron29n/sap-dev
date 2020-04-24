package com.epam.sap.developers.core.utils;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceUtils {
    private ServiceUtils() {
        throw new IllegalStateException("Utility class");
    }

//    @Nullable
    public static List<String> getStrListFromResourceListWithOneProperty(List<Resource> resourceList, String propertyName){
        return  (resourceList == null || resourceList.isEmpty()) ? Collections.emptyList() : resourceListToStrList(resourceList, propertyName);
    }

//    @Nullable
    public static String getCrxPath(String path){
        return (path != null && !path.isEmpty()) ? "/" + path : path;
    }

    private static List<String> resourceListToStrList(List<Resource> resourceList, String propertyName) {
        return resourceList.stream()
                .map(e -> e.adaptTo(ValueMap.class).get(propertyName, String.class))
                .collect(Collectors.toList());
    }
}
