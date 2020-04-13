package com.epam.sap.developers.core.services;

import org.apache.sling.api.resource.Resource;

import java.util.List;

public interface YouTubeService {

    String getYouTubeVideoSrcToEmbedAtHtml(String youtubeVideoUrl);

    List<String> getItemsOfMultiefieldProperty(Resource youtubeComponent);
}
