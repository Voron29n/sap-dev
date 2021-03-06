package com.epam.sap.developers.core.models.impl;

import com.epam.sap.developers.core.models.News;
import com.epam.sap.developers.core.utils.ModelUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.Date;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {News.class},
        resourceType = {NewsImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NewsImpl implements News {

    protected static final String RESOURCE_TYPE = "developers/components/custom/news";

    @ValueMapValue
    private String newsTitle;

    @ValueMapValue
    private String newsDescription;

    @ValueMapValue
    private Date newsDate;

    @Override
    public String getNewsTitle() {
        return newsTitle;
    }

    @Override
    public String getDate() {
        return ModelUtils.formatDateToStr(newsDate);
    }

    @Override
    public String getDescription() {
        return newsDescription;
    }

    @Override
    public boolean isEquals() {
        return (newsDate == null || newsTitle == null || newsDescription == null);
    }
}
