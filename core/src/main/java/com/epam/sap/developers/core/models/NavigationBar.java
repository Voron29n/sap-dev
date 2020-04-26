package com.epam.sap.developers.core.models;

import com.epam.sap.developers.core.entities.SimpleLink;

import java.util.List;

public interface NavigationBar {

    String getSiteTitle();

    SimpleLink getSimpleLinkOfMainPage();

    List<SimpleLink> getSecondLevelLinks();
}
