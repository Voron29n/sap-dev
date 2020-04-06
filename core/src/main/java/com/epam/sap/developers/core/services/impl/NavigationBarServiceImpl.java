package com.epam.sap.developers.core.services.impl;

import com.day.cq.commons.Filter;
import com.day.cq.wcm.api.Page;
import com.epam.sap.developers.core.entities.SimpleLink;
import com.epam.sap.developers.core.services.NavigationBarService;
import com.epam.sap.developers.core.utils.SimpleLinkUtils;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component(
        service = NavigationBarService.class,
        immediate = true)
public class NavigationBarServiceImpl implements NavigationBarService {

    @Override
    public List<SimpleLink> getSecondLevelPagesLinksWithCssClass(Page mainPage, Page pageWithComponent) {
        List<SimpleLink> secondLevelPagesLinks = new ArrayList<>();
        Iterator<Page> listChildPages = mainPage.listChildren(new HidePageFilter(), false);
        while (listChildPages.hasNext()) {
            Page childPageItem = listChildPages.next();
            secondLevelPagesLinks.add(SimpleLinkUtils.getSimpleLinkFromPageWithCssClass(childPageItem, pageWithComponent));
        }
        return secondLevelPagesLinks;
    }

    class HidePageFilter implements Filter {
        @Override
        public boolean includes(Object childPage) {
            return !((Page) childPage).isHideInNav();
        }
    }

}


