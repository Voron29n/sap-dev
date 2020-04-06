package com.epam.sap.developers.core.models.impl;

import com.day.cq.commons.Filter;
import com.day.cq.wcm.api.NameConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.epam.sap.developers.core.entities.SimpleLink;
import com.epam.sap.developers.core.models.NavigationBar;
import com.epam.sap.developers.core.utils.SapDevelopersPathUtils;
import com.epam.sap.developers.core.utils.SimpleLinkUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {NavigationBar.class},
        resourceType = {NavigationBarImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NavigationBarImpl implements NavigationBar {

    protected static final String RESOURCE_TYPE = "sap/components/totemplate/navbar";
    protected static final int LEVEL_OF_MAIN_PAGE = 2;
    protected static final String DEFAULT_MAIN_PAGE_PATH = "/content/developers/uk/sap";

    @ScriptVariable
    private Page currentPage;

    @SlingObject
    private ResourceResolver resourceResolver;

    private Page mainPage;

    @PostConstruct
    public void init() {
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        String pathToMainPage = getMainPagePath(currentPage.getPath());
        mainPage = pageManager.getPage(pathToMainPage);
        getSimpleLinkOfMainPage();
    }


    /**
     * Main page in {@link NavigationBar} class not sensitive to {@link NameConstants#PN_HIDE_IN_NAV}
     */
    @Override
    public SimpleLink getSimpleLinkOfMainPage() {
        return SimpleLinkUtils.getSimpleLinkFromPage(this.mainPage, this.currentPage);
    }

    @Override
    public List<SimpleLink> getSecondLevelLinks() {
        List<SimpleLink> secondLevelPagesLinks = new ArrayList<>();
        Iterator<Page> listChildPages = mainPage.listChildren(new HidePageFilter(), false);
        while (listChildPages.hasNext()){
            Page childPageItem = listChildPages.next();
            secondLevelPagesLinks.add(SimpleLinkUtils.getSimpleLinkFromPage(childPageItem, this.currentPage));
        }

        return secondLevelPagesLinks;
    }

    private String getMainPagePath(String currentPagePath) {
        String mainPagePath = SapDevelopersPathUtils.getPathByLevelRelativeToRootPath(currentPagePath, LEVEL_OF_MAIN_PAGE);
        return (mainPagePath != null) ? mainPagePath : DEFAULT_MAIN_PAGE_PATH;
    }

    class HidePageFilter implements Filter {
        @Override
        public boolean includes(Object childPage) {
            return !((Page) childPage).isHideInNav();
        }
    }

}
