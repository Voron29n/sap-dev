package com.epam.sap.developers.core.models.impl;

import com.epam.sap.developers.core.models.Intro;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {Intro.class},
        resourceType = {IntroImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class IntroImpl implements Intro {

    protected static final String RESOURCE_TYPE = "sap/components/custom/intro";

    @SlingObject
    private Resource currentResource;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String buttonText;

    @ValueMapValue
    private String buttonHref;

    @ValueMapValue
    private boolean isButtonNeed;

    @ValueMapValue
    private String fileName;

    @ValueMapValue
    private String fileReference;

    @Override
    public String getButtonHref() {
        return buttonHref;
    }

    @Override
    public String getButtonText() {
        return buttonText;
    }

    @Override
    public String getIntroTitle() {
        return title;
    }

    @Override
    public String getIntroDescription() {
        return description;
    }

    @Override
    public String getImagePath() {
        if (fileName == null && fileReference == null) {
            return null;
        } else if (fileReference != null) {
            return fileReference;
        }
        return currentResource.getPath().concat("/file");
    }

    @Override
    public String getMaxHeight() {
        return (getImagePath() != null) ? "min-height: 300px;" : "";
    }

    @Override
    public boolean isEmpty() {
        return title == null && description == null && getImagePath() == null && (!isButtonNeed || (isButtonNeed && buttonText == null));
    }

    @Override
    public boolean isButtonNeed() {
        return isButtonNeed;
    }
}
