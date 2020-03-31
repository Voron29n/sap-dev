package com.epam.sap.developers.core.models.impl;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.epam.sap.developers.core.models.Footer;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {Footer.class},
        resourceType = {FooterImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterImpl implements Footer {

    protected static final String RESOURCE_TYPE = "sap/components/content/footer";
    protected static final String SOCIAL_MEDIA_PROPERTY = "socialMedia";

    @ScriptVariable
    private Page currentPage;

    @SlingObject
    private ResourceResolver resourceResolver;

    @Override
    public Map<String, String> getFooterSocialMedia() {
        return footerSocialMedia();
    }

    private Map<String, String> footerSocialMedia() {
        Map<String, String> socialMedia = new HashMap<>();

        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);

        String pagePathWithFooterProperty = getPageWithFooterProperty(currentPage.getPath());

        Page pageWithFooterProperty = pageManager.getPage(pagePathWithFooterProperty);

        ValueMap contentOfPageWithFooterProperty = pageWithFooterProperty.getContentResource().getValueMap();

        List<String> selectedSocialMedia = Arrays.asList((String[]) contentOfPageWithFooterProperty.get(SOCIAL_MEDIA_PROPERTY));

        selectedSocialMedia.stream().forEach(p -> {
            socialMedia.put(p, (String) contentOfPageWithFooterProperty.get(p));
        });

        return socialMedia;
    }

    private String getPageWithFooterProperty(String currentPagePath) {
        Pattern pattern = Pattern.compile("/content/developers/[^/]+/[^/]+");
        Matcher matcher = pattern.matcher(currentPagePath);
        if (matcher.find()) {
            return currentPagePath.substring(matcher.start(), matcher.end());
        } else {
            return "/content/developers/uk/sap";
        }
    }
}
