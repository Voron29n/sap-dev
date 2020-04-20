package com.epam.sap.developers.core.services;

import com.epam.sap.developers.core.models.Event;
import com.epam.sap.developers.core.models.bean.EventDropdownBean;
import com.epam.sap.developers.core.services.impl.EventsServiceImpl.EventsWrapper;
import org.apache.sling.api.resource.LoginException;

import java.util.List;
import java.util.Map;

public interface EventsService {

    EventDropdownBean getEventDropdownBean() throws LoginException;

    List<Event> getAllEvents() throws LoginException;

    Map<String, List<Event>> getEventsByTypes() throws LoginException;

    Map<String, List<Event>> getEventsByTopics() throws LoginException;

    List<Event> getEventsColumnForTopic(String topic, int numColumn) throws LoginException;

    List<Event> getEventsColumnForType(String type, int numColumn) throws LoginException;

    List<EventsWrapper> getEventsWrapperByType() throws LoginException;

    List<EventsWrapper> getEventsWrapperByTopic() throws LoginException;

}
