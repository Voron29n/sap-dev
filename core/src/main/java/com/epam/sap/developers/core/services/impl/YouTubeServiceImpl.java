package com.epam.sap.developers.core.services.impl;

import com.epam.sap.developers.core.services.YouTubeService;
import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component(
        service = YouTubeService.class,
        immediate = true)
public class YouTubeServiceImpl implements YouTubeService {

    private static final String PATTER_YOUTUBE_WATCH = "^https:\\/\\/www.youtube.com\\/watch\\?v=.+";
    private static final String PATTER_YOUTUBE_EMBED = "^https:\\/\\/www.youtube.com\\/embed\\/.+";

    private static final String YOUTUBE_EMBED_URL = "https://www.youtube.com/embed/";
    private static final String MULTIFIELD_ITEM_TITLE_PROPERTY = "title";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getYouTubeVideoSrcToEmbedAtHtml(String youtubeVideoUrl) {
        if (isEmbedYouTubeVideoUrl(youtubeVideoUrl)) {
            return youtubeVideoUrl;
        } else if (isWatchYouTubeVideoUrl(youtubeVideoUrl)) {
            String youtubeVideoId = youtubeVideoUrl.substring(32);
            return YOUTUBE_EMBED_URL.concat(youtubeVideoId);
        } else {
            return null;
        }
    }

    private boolean isEmbedYouTubeVideoUrl(String youtubeVideoUrl) {
        Pattern embedPattern = Pattern.compile(PATTER_YOUTUBE_EMBED);
        Matcher embedMatcher = embedPattern.matcher(youtubeVideoUrl);
        return embedMatcher.find();
    }

    private boolean isWatchYouTubeVideoUrl(String youtubeVideoUrl) {
        Pattern watchPattern = Pattern.compile(PATTER_YOUTUBE_WATCH);
        Matcher watchMatcher = watchPattern.matcher(youtubeVideoUrl);
        return watchMatcher.find();
    }

    @Override
    public List<String> getItemsOfDescriptions(Resource youtubeComponent) {
        List<String> itemsList = new ArrayList<>();
        Node youtubeComponentNode = youtubeComponent.adaptTo(Node.class);
        try {
            NodeIterator nodeItems = youtubeComponentNode.getNodes();
            while (nodeItems.hasNext()) {
                Node item = nodeItems.nextNode();
                itemsList.add(item.getProperty(MULTIFIELD_ITEM_TITLE_PROPERTY).getString());
            }
        } catch (RepositoryException e) {
            logger.error("Get items of description failed");
        }
        return itemsList;
    }
}
