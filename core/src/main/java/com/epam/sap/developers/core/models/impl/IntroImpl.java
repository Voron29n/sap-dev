package com.epam.sap.developers.core.models.impl;

import com.day.cq.wcm.foundation.Image;
import com.epam.sap.developers.core.entities.SimpleLink;
import com.epam.sap.developers.core.models.Button;
import com.epam.sap.developers.core.models.Intro;
import com.epam.sap.developers.core.services.YouTubeService;
import com.epam.sap.developers.core.utils.ModelUtils;
import com.epam.sap.developers.core.utils.SimpleLinkUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.via.ResourceSuperType;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {Intro.class},
        resourceType = {IntroImpl.RESOURCE_TYPE_INTRO,IntroImpl.RESOURCE_TYPE_INTRO_MIN},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class IntroImpl implements Intro {

    protected static final String RESOURCE_TYPE_INTRO = "developers/components/custom/intro";
    protected static final String RESOURCE_TYPE_INTRO_MIN = "developers/components/custom/intro-min";

    @Inject
    private YouTubeService youTubeService;

    @SlingObject
    private Resource currentResource;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String fileName;

    @ValueMapValue
    private String fileReference;

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
        return ModelUtils.getImageSrc(fileName, fileReference, currentResource);
    }

    @Override
    public String getMaxHeight() {
        return (getImagePath() != null) ? "min-height: 300px;" : "";
    }

    @Override
    public List<String> getItemList() {
        return youTubeService.getItemsOfMultiefieldProperty(currentResource);
    }

    @Override
    public boolean isEmpty() {
        return title == null && description == null && getImagePath() == null;
    }
}
