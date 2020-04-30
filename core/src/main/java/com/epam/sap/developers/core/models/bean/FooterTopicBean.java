package com.epam.sap.developers.core.models.bean;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterTopicBean {

    @ValueMapValue
    private String topicTitle;

    @Inject
    private List<Resource> topicItems;

    private List<SimpleLinkBean> topicItemsSimpleLink;

    @PostConstruct
    protected void init() {
        topicItemsSimpleLink = convertTopicItemsToSimpleLinkBean();
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public List<SimpleLinkBean> getTopicItemsSimpleLink() {
        return topicItemsSimpleLink;
    }

    private List<SimpleLinkBean> convertTopicItemsToSimpleLinkBean() {
        return (topicItems == null || topicItems.isEmpty()) ? Collections.emptyList()
                : topicItems.stream()
                .map(el -> el.adaptTo(SimpleLinkBean.class))
                .collect(Collectors.toList());
    }


}
