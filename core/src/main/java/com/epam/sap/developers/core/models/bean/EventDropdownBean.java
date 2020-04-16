package com.epam.sap.developers.core.models.bean;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class EventDropdownBean {

    @Inject
    private List<Resource> eventTopics;

    @Inject
    private List<Resource> eventTypes;

    private Map<String,String> eventDropdownTopics;
    private Map<String,String> eventDropdownTypes;

    @PostConstruct
    private void init() {
        eventDropdownTopics = convertEventTopicsToList();
        eventDropdownTypes = convertEventTypesToList();
    }

    public Map<String,String> getEventTopics() {
        return eventDropdownTopics;
    }

    public Map<String,String> getEventTypes() {
        return eventDropdownTypes;
    }

    private Map<String, String> convertEventTopicsToList() {
        return (eventTopics == null || eventTopics.isEmpty()) ? null : eventTopics.stream()
                .map(el -> el.adaptTo(EventDropdownItemBean.class))
                .collect(Collectors.toMap(EventDropdownItemBean::getDropdownTitle, EventDropdownItemBean::getIconNumber));
    }


    private Map<String,String> convertEventTypesToList() {
        return (eventTypes == null || eventTypes.isEmpty()) ? null : eventTypes.stream()
                .map(el -> el.adaptTo(EventDropdownItemBean.class))
                .collect(Collectors.toMap(EventDropdownItemBean::getDropdownTitle, EventDropdownItemBean::getIconNumber));
    }
}
