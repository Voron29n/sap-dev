package com.epam.sap.developers.core.models.bean;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SocialMediaBean {

    private static final String NOT_NEED_ICON = "none";

    @ValueMapValue
    private String mediaTitle;
    @ValueMapValue
    private String mediaIcon;
    @ValueMapValue
    private String mediaUrl;
    @ValueMapValue
    private String mediaUrlTarget;

    private boolean isIconNeed;

    public String getMediaTitle() {
        return mediaTitle;
    }

    public String getMediaIcon() {
        return mediaIcon;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public String getMediaUrlTarget() {
        return mediaUrlTarget;
    }

    public boolean isIconNeed() {
        return !mediaIcon.equals(NOT_NEED_ICON);
    }
}
