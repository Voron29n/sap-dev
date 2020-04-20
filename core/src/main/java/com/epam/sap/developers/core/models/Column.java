package com.epam.sap.developers.core.models;

import java.util.List;

public interface Column {

    boolean isTitleNeed();

    boolean isEmpty();

    String getColumnsStyle();

    String getTitle();

    List<String> getColumnNodeListName();
}
