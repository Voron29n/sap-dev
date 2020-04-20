package com.epam.sap.developers.core.models;

import java.util.List;

public interface YouTube {

    String getYoutubeVideoSrc();

    String getTitle();

    String getDescription();

    List<String> getItemList();

    boolean isOnlyVideo();

    boolean isEmpty();
}
