package com.epam.sap.developers.core.models.impl;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.epam.sap.developers.core.entities.SimpleLink;
import com.epam.sap.developers.core.entities.footer.FooterLinksWithTopic;
import com.epam.sap.developers.core.models.Footer;
import com.epam.sap.developers.core.services.FooterService;
import com.epam.sap.developers.core.utils.SapDevelopersPathUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {Footer.class},
        resourceType = {FooterImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterImpl implements Footer {

    protected static final String RESOURCE_TYPE = "developers/components/totemplate/footer";

    @ScriptVariable
    private Page currentPage;

    @SlingObject
    private ResourceResolver resourceResolver;

    @Inject
    private FooterService footerService;

    private Resource contentOfPageWithFooterProperty;

    @Override
    public Map<String, String> getFooterSocialMedia() {
        return footerService.footerSocialMedia(contentOfPageWithFooterProperty);
    }

    @Override
    public Map<Integer, List<SimpleLink>> getFooterCopyright() {
        List<SimpleLink> copyrightLinks = footerService.footerCopyright(contentOfPageWithFooterProperty);
        return convertListWithSimpleLinksToMapWithCountOfRow(copyrightLinks);
    }

    @Override
    public List<FooterLinksWithTopic> getFooterLinksWithTopic() {
        return footerService.footerLinksWithTopic(contentOfPageWithFooterProperty);
    }


    @PostConstruct
    public void init() {
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        String pagePathWithFooterProperty = getPathToPageWithFooterProperty(currentPage.getPath());
        contentOfPageWithFooterProperty = pageManager.getPage(pagePathWithFooterProperty).getContentResource();
    }

    private Map<Integer, List<SimpleLink>> convertListWithSimpleLinksToMapWithCountOfRow(List<SimpleLink> simpleLinks) {
        Map<Integer, List<SimpleLink>> mapOfSimpleLinks = new HashMap<>();
        mapOfSimpleLinks.put(1, new ArrayList<>());
        mapOfSimpleLinks.put(2, new ArrayList<>());

        int lengthOfSimpleLinks = simpleLinks.size();

        //FIXME Change this hard code to normal version and add padding-top at footer css dependent of count of row
        AtomicInteger index = new AtomicInteger();
        simpleLinks.forEach(simpleLink -> {
            if (index.get() < (lengthOfSimpleLinks / 2)) {
                mapOfSimpleLinks.get(1).add(simpleLink);
            } else {
                mapOfSimpleLinks.get(2).add(simpleLink);
            }
            index.getAndIncrement();
        });

        return mapOfSimpleLinks;
    }

    private String getPathToPageWithFooterProperty(String currentPagePath) {
        String mainPagePath = SapDevelopersPathUtils.getPathByLevelRelativeToRootPath(currentPagePath, NavigationBarImpl.LEVEL_OF_MAIN_PAGE);
        return (mainPagePath != null) ? mainPagePath : NavigationBarImpl.DEFAULT_MAIN_PAGE_PATH;
    }
}
