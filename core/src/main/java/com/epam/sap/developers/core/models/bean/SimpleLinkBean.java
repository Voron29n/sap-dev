package com.epam.sap.developers.core.models.bean;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SimpleLinkBean {

    @ValueMapValue
    private String linkTitle;
    @ValueMapValue
    private String linkUrl;
    @ValueMapValue
    private String linkUrlTarget;

    public String getLinkTitle() {
        return linkTitle;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public String getLinkUrlTarget() {
        return linkUrlTarget;
    }
}
