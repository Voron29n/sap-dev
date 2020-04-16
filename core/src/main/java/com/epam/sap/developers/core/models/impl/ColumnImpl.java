package com.epam.sap.developers.core.models.impl;

import com.epam.sap.developers.core.models.Column;
import com.epam.sap.developers.core.services.ColumnService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {Column.class},
        resourceType = {ColumnImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ColumnImpl implements Column {

    protected static final String RESOURCE_TYPE = "developers/components/custom/column";

    @Inject
    private ColumnService columnService;

    @ValueMapValue
    boolean isTitleNeed;
    @ValueMapValue
    int numberColumns;
    @ValueMapValue
    String title;

    @Override
    public boolean isTitleNeed() {
        return isTitleNeed;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getColumnsStyle() {
        return columnService.getColumnStyleByNumberColumns(numberColumns);
    }

    @Override
    public List<String> getColumnNodeListName() {
        return columnService.getColumnNodeNamesByNumberColumns(numberColumns);
    }

    @Override
    public boolean isEmpty() {
        return numberColumns == 0;
    }
}
