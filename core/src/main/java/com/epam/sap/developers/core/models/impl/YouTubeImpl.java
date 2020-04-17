package com.epam.sap.developers.core.models.impl;


import com.epam.sap.developers.core.models.YouTube;
import com.epam.sap.developers.core.services.YouTubeService;
import com.epam.sap.developers.core.utils.ServiceUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {YouTube.class},
        resourceType = {YouTubeImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class YouTubeImpl implements YouTube {

    protected static final String RESOURCE_TYPE = "developers/components/custom/youtube";
    private static final String DESCRIPTION_ITEM_PROPERTY = "title";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SlingObject
    private Resource currentResource;

    @Inject
    private YouTubeService youTubeService;


    @ValueMapValue
    private String title;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String youtubeLink;

    @ValueMapValue
    private boolean isOnlyVideo;

    @Inject
    private List<Resource> descriptionList;

    private List<String> itemsOfDescriptions;

    @Override
    public String getYoutubeVideoSrc() {
        String youtubeVideoSrc = youTubeService.getYouTubeVideoSrcToEmbedAtHtml(youtubeLink);
        if (youtubeVideoSrc == null) {
            logger.error("User's youtube link not valid");
        }
        return youtubeVideoSrc;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<String> getItemList() throws RepositoryException {
        itemsOfDescriptions = ServiceUtils.getStrListFromResourceListWithOneProperty(descriptionList, DESCRIPTION_ITEM_PROPERTY);
        if (itemsOfDescriptions == null) {
            logger.error("Description items not found");
        }
        return itemsOfDescriptions;
    }

    @Override
    public boolean isOnlyVideo() {
        return isOnlyVideo;
    }

    @Override
    public boolean isEmpty() {
        if (isOnlyVideo) {
            return (youtubeLink == null);
        } else {
            return (title == null && youtubeLink == null && description == null && itemsOfDescriptions == null);
        }
    }
}
