package com.epam.sap.developers.core.services.impl;

import com.epam.sap.developers.core.services.ColumnService;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;


@Component(
        service = ColumnService.class,
        immediate = true)
public class ColumnServiceImpl implements ColumnService {

    private static final String GRID_TEMPLATE_COLUMNS = "grid-template-columns: repeat(%d, 1fr);";
    private static final String PAR_NODE_NAME_START = "column_";

    @Override
    public String getColumnStyleByNumberColumns(int numberColumns) {
        return String.format(GRID_TEMPLATE_COLUMNS, numberColumns);
    }

    @Override
    public List<String> getColumnNodeNamesByNumberColumns(int numberColumns) {
        List<String> parNodeNamesList = new ArrayList<>();
        for (int i = 1; i <= numberColumns; i++) {
            parNodeNamesList.add(PAR_NODE_NAME_START + i);
        }
        return parNodeNamesList;
    }
}
