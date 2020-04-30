package com.epam.sap.developers.core.models.impl;

import com.epam.sap.developers.core.entities.SimpleLink;
import com.epam.sap.developers.core.entities.footer.FooterLinksWithTopic;
import com.epam.sap.developers.core.models.Footer;
import com.epam.sap.developers.core.models.bean.FooterTopicBean;
import com.epam.sap.developers.core.models.bean.SimpleLinkBean;
import com.epam.sap.developers.core.models.bean.SocialMediaBean;
import com.epam.sap.developers.core.services.ColumnService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {Footer.class},
        resourceType = {FooterExprImp.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterExprImp implements Footer {

    protected static final String RESOURCE_TYPE = "developers/components/custom/footer";

    @Inject
    @Via("resource")
    private List<Resource> copyrightLinks;
    @Inject
    @Via("resource")
    private List<Resource> footerLinks;
    @Inject
    @Via("resource")
    private List<Resource> socialMedia;

    @Inject
    private ColumnService columnService;

    private List<SimpleLinkBean> footerCopyrightList;

    private List<FooterTopicBean> footerTopicBeanList;

    private List<SocialMediaBean> socialMediaBeanList;

    @PostConstruct
    protected void init() {
        footerCopyrightList = convertCopyrightLinksToSimpleLinkBeans();
        footerTopicBeanList = convertFooterLinksToListTopicBean();
        socialMediaBeanList = convertSocialMediaListToSocialMediaBeanList();
        getFooterCopyrightMapVersion2();
    }

    @Override
    public List<SimpleLinkBean> getFooterCopyrightList() {
        return footerCopyrightList;
    }

    @Override
    public Map<Integer, List<SimpleLink>> getFooterCopyrightMap() {
        return null;
    }

    @Override
    public Map<Integer, List<SimpleLinkBean>> getFooterCopyrightMapVersion2() {
        Map<Integer, List<SimpleLinkBean>> mapOfSimpleLinks = new HashMap<>();

        int rowCount = (int) Math.ceil( (double) footerCopyrightList.size() / 4);
        AtomicInteger iteratorOfRow = new AtomicInteger(rowCount);
        while (rowCount > 0){
            mapOfSimpleLinks.put(rowCount, new ArrayList<>());
            rowCount--;
        }

        footerCopyrightList.forEach(el -> {
            if (mapOfSimpleLinks.get(iteratorOfRow.intValue()).size() < 4){
                mapOfSimpleLinks.get(iteratorOfRow.intValue()).add(el);
            } else {
                iteratorOfRow.decrementAndGet();
                mapOfSimpleLinks.get(iteratorOfRow.intValue()).add(el);
            }
        });

        return mapOfSimpleLinks;
    }

    @Override
    public List<FooterTopicBean> getFooterTopicBeanList() {
        return footerTopicBeanList;
    }

    @Override
    public List<SocialMediaBean> getSocialMediaBeanList() {
        return socialMediaBeanList;
    }

    @Override
    public String getFooterLinksStyle() {
        return columnService.getColumnStyleByNumberColumns(footerLinks.size());
    }

    @Override
    public boolean isEmpty() {
        return (copyrightLinks == null && footerLinks == null && socialMedia == null);
    }

    @Override
    public Map<String, String> getFooterSocialMedia() {
        return null;
    }

    @Override
    public Map<Integer, List<SimpleLink>> getFooterCopyright() {
        return null;
    }

    @Override
    public List<FooterLinksWithTopic> getFooterLinksWithTopic() {
        return null;
    }

    private List<SimpleLinkBean> convertCopyrightLinksToSimpleLinkBeans() {
        return (copyrightLinks == null || copyrightLinks.isEmpty())
                ? Collections.emptyList()
                : copyrightLinks.stream()
                .map(el -> el.adaptTo(SimpleLinkBean.class))
                .collect(Collectors.toList());
    }


    private List<SocialMediaBean> convertSocialMediaListToSocialMediaBeanList() {
        return (socialMedia == null || socialMedia.isEmpty())
                ? Collections.emptyList()
                : socialMedia.stream()
                .map(el -> el.adaptTo(SocialMediaBean.class))
                .collect(Collectors.toList());
    }

    private List<FooterTopicBean> convertFooterLinksToListTopicBean() {
        return (footerLinks == null || footerLinks.isEmpty())
                ? Collections.emptyList()
                : footerLinks.stream()
                .map(el -> el.adaptTo(FooterTopicBean.class))
                .collect(Collectors.toList());
    }
}