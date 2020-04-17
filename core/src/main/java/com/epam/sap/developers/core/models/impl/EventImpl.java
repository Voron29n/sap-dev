package com.epam.sap.developers.core.models.impl;

import com.epam.sap.developers.core.models.Event;
import com.epam.sap.developers.core.utils.ModelUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.Date;

@Model(adaptables = {SlingHttpServletRequest.class,Resource.class},
        adapters = {Event.class},
        resourceType = EventImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class EventImpl implements Event {

    protected static final String RESOURCE_TYPE = "developers/components/custom/event";
    protected static final int ICON_LENGTH = 5;

    @ValueMapValue
    private Date eventDate;
    @ValueMapValue
    private String eventTitle;
    @ValueMapValue
    private String eventDescription;
    @ValueMapValue
    private String eventTopic;
    @ValueMapValue
    private String eventType;

    private String eventDateStr;

    private String eventTopicIconNumber;

    @PostConstruct
    protected void init(){
        eventTopicIconNumber = getIconNumberFromTopic();
        eventDateStr = ModelUtils.formatDateToStr(eventDate);
    }

    @Override
    public String getEventTopicIconNumber() {
        return eventTopicIconNumber;
    }

    @Override
    public String getEventDateStr() {
        return eventDateStr;
    }

    @Override
    public String getEventTitle() {
        return eventTitle;
    }

    @Override
    public String getEventDescription() {
        return eventDescription;
    }

    @Override
    public String getEventTopic() {
        return eventTopic;
    }

    @Override
    public String getEventType() {
        return eventType;
    }

    @Override
    public boolean isEquals() {
        return (eventDate == null && eventTopic == null && eventType == null && eventTitle == null);
    }

    private String getIconNumberFromTopic() {
        if (eventTopic != null) {
            return StringUtils.substring(eventTopic, eventTopic.length() - ICON_LENGTH);
        }
        return null;
    }

}
