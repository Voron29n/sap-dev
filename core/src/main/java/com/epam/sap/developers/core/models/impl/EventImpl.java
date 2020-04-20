package com.epam.sap.developers.core.models.impl;

import com.epam.sap.developers.core.models.Event;
import com.epam.sap.developers.core.models.bean.EventDropdownBean;
import com.epam.sap.developers.core.services.EventsService;
import com.epam.sap.developers.core.utils.ModelUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Date;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class},
        adapters = {Event.class},
        resourceType = EventImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class EventImpl implements Event {

    protected static final String RESOURCE_TYPE = "developers/components/custom/event";

    @Inject
    private EventsService eventsService;

    @ValueMapValue(name = "jcr:created")
    private Date jcrCreated;
    @ValueMapValue
    private Date eventDate;
    @ValueMapValue
    private String eventTitle;
    @ValueMapValue
    @Default(values = "")
    private String eventDescription;
    @ValueMapValue
    private String eventTopic;
    @ValueMapValue
    private String eventType;

    private String eventDateStr;

    private String eventTopicIconClass;

    @PostConstruct
    protected void init() throws LoginException {
        eventTopicIconClass = getIconClassFromTopic();
        eventDateStr = ModelUtils.formatDateToStr(eventDate);
        eventTitle = (eventTitle != null) ? eventTitle.replaceAll("\"", "'") : null;
    }

    @Override
    public long getJcrCreated() {
        return jcrCreated.getTime();
    }

    @Override
    public String getEventTopicIconClass() {
        return eventTopicIconClass;
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

    private String getIconClassFromTopic() throws LoginException {
        if (eventTopic != null) {
            EventDropdownBean bean = eventsService.getEventDropdownBean();
            return bean.getEventTopics().get(eventTopic);
        }
        return null;
    }

}
