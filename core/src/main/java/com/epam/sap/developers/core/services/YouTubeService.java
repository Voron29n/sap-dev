package com.epam.sap.developers.core.services;

import jdk.internal.jline.internal.Nullable;
import org.apache.sling.api.resource.Resource;

import java.util.List;

public interface YouTubeService {

    @Nullable
    String getYouTubeVideoSrcToEmbedAtHtml(String youtubeVideoUrl);

    @Nullable
    List<String> getDescriptionItems(List<Resource> descriptionList);
}
