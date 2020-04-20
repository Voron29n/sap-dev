package com.epam.sap.developers.core.services;

import java.util.List;

public interface ColumnService {
    String getColumnStyleByNumberColumns(int numberColumns);

    List<String> getColumnNodeNamesByNumberColumns(int numberColumns);
}
