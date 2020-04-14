package com.epam.sap.developers.core.service;

import com.epam.sap.developers.core.services.YouTubeService;
import com.epam.sap.developers.core.services.impl.YouTubeServiceImpl;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.Assert.assertEquals;

public class YouTubeServiceTest {

    private static final String PATTER_YOUTUBE_WATCH = "^https:\\/\\/www.youtube.com\\/watch\\?v=.+";
    private static final String PATTER_YOUTUBE_EMBED = "^https:\\/\\/www.youtube.com\\/embed\\/.+";

    private String youtubeWatchVideoUrl;
    private String youtubeEmbedVideoUrl;

    private YouTubeService youTubeService;

    @BeforeEach
    public void setUp(){
        youtubeWatchVideoUrl = "https://www.youtube.com/watch?v=_b_RhGJ1xfU";
        youtubeEmbedVideoUrl = "https://www.youtube.com/embed/_b_RhGJ1xfU";

        youTubeService = new YouTubeServiceImpl();
    }

    @Test
    public void getYouTubeVideoSrcWithCorrectLink(){
        assertEquals(youtubeEmbedVideoUrl, youTubeService.getYouTubeVideoSrcToEmbedAtHtml(youtubeWatchVideoUrl));
    }

    @Test
    public void embedPatternTest(){
        Pattern embedPattern = Pattern.compile(PATTER_YOUTUBE_EMBED);
        Matcher embedMatcher = embedPattern.matcher(youtubeEmbedVideoUrl);

        assertEquals(true, embedMatcher.find());
    }

    @Test
    public void watchPatternTest(){
        Pattern watchPattern = Pattern.compile(PATTER_YOUTUBE_WATCH);
        Matcher watchMatcher = watchPattern.matcher(youtubeWatchVideoUrl);

        assertEquals(true, watchMatcher.find());
    }

}
