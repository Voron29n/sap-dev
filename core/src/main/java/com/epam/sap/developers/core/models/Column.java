package com.epam.sap.developers.core.models;

import com.epam.sap.developers.core.entities.SimpleLink;

import java.util.List;

public interface Column {

    boolean isTitleNeed();

    boolean isEmpty();

    String getColumnsStyle();

    String getTitle();

    List<String> getColumnNodeListName();
}
