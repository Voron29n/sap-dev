package com.epam.sap.developers.core.models;

import java.util.List;

public interface Intro {

    String getIntroTitle();

    String getIntroDescription();

    String getImagePath();

    String getMaxHeight();

    List<String> getItemList();

    boolean isEmpty();
}
