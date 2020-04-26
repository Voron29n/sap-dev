package com.epam.sap.developers.core.services.impl;

import com.epam.sap.developers.core.models.Event;
import com.epam.sap.developers.core.models.bean.EventDropdownBean;
import com.epam.sap.developers.core.services.EventsService;
import com.epam.sap.developers.core.services.conf.EventsConfig;
import com.epam.sap.developers.core.utils.ServiceUtils;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component(
        service = EventsService.class,
        immediate = true)
@Designate(ocd = EventsConfig.class)
public class EventsServiceImpl implements EventsService {

    public static final String DROPDOWN_MULTIFIELD_NODE = ServiceUtils.getCrxPath("dropdownMultifield");
    private static final String SERVICE_USER_NAME = "testuser";
    private static final String PATH_TO_PAR_NODE_WITH_EVENTS = ServiceUtils.getCrxPath("jcr:content/events");

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference
    private ResourceResolverFactory resolverFactory;

    private int rowSize;
    private String pathToPageWithEvents;

    @Override
    public void setPathToPageWithEvents(String pathToPageWithEvents) {
        this.pathToPageWithEvents = pathToPageWithEvents.concat(PATH_TO_PAR_NODE_WITH_EVENTS);
    }

    @Activate
    @Modified
    public void activate(EventsConfig config) {
        this.rowSize = config.defaultRowSize();
    }

    @Override
    public List<EventsWrapper> getEventsWrapperByType() throws LoginException {
        Map<String, List<Event>> eventMap = getEventsByTypes();
        return eventMap.keySet().stream()
                .map(e -> new EventsWrapper(e, eventMap.get(e), true, this.rowSize))
                .collect(Collectors.toList());
    }

    @Override
    public List<EventsWrapper> getEventsWrapperByTopic() throws LoginException {
        Map<String, List<Event>> eventMap = getEventsByTopics();
        return eventMap.keySet().stream()
                .map(e -> new EventsWrapper(e, eventMap.get(e), false, this.rowSize))
                .collect(Collectors.toList());
    }


    @Override
    public List<Event> getEventsColumnForType(String type, int numColumn) throws LoginException {
        Map<String, List<Event>> eventMap = getEventsByTypes();
        return eventsSubListByNumColumn(eventMap.get(type), numColumn);
    }

    @Override
    public List<Event> getEventsColumnForTopic(String topic, int numColumn) throws LoginException {
        Map<String, List<Event>> eventMap = getEventsByTopics();
        return eventsSubListByNumColumn(eventMap.get(topic), numColumn);
    }

    @Override
    public EventDropdownBean getEventDropdownBean(String pathToDropdown) throws LoginException {
        ResourceResolver resolver = getResourceResolver();
        EventDropdownBean eventDropdownBean = resourceAdaptToEventDropdownBean(resolver, pathToDropdown);
        if (!resolver.isLive()) {
            resolver.close();
        }
        return eventDropdownBean;
    }

    private EventDropdownBean resourceAdaptToEventDropdownBean(ResourceResolver resolver, String pathToDropdown) {
        Resource dropdownResource = resolver.getResource(pathToDropdown);
        return dropdownResource != null ? dropdownResource.adaptTo(EventDropdownBean.class) : null;
    }

    private List<Event> eventsSubListByNumColumn(List<Event> eventList, int numColumn) {
        int fromIndex = rowSize * numColumn;
        fromIndex = fromIndex > eventList.size() ? rowSize * (numColumn - 1) : fromIndex;
        int toIndex = Math.min(rowSize * (numColumn + 1), eventList.size());
        return eventList.subList(fromIndex, toIndex);
    }

    private List<Resource> getListWithEventsResource(ResourceResolver resolver) {
        Resource parEventsResource = resolver.getResource(this.pathToPageWithEvents);
        if (parEventsResource == null) {
            logger.error("Resource with events not exist");
            throw new IllegalArgumentException("Resource with events not exist");
        }
        Iterable<Resource> eventResources = parEventsResource.getChildren();
        return StreamSupport.stream(eventResources.spliterator(), false)
                .collect(Collectors.toList());
    }

    private Map<String, List<Event>> getEventsByTypes() throws LoginException {
        List<Event> eventList = getAllEvents();
        return eventList.stream()
                .filter(el -> !el.isEquals())
                .collect(Collectors.groupingBy(Event::getEventType));
    }

    private Map<String, List<Event>> getEventsByTopics() throws LoginException {
        List<Event> eventList = getAllEvents();
        return eventList.stream()
                .filter(el -> !el.isEquals())
                .collect(Collectors.groupingBy(Event::getEventTopic));
    }

    private List<Event> getAllEvents() throws LoginException {
        ResourceResolver resolver = getResourceResolver();
        List<Resource> eventResources = getListWithEventsResource(resolver);
        List<Event> eventList = eventResources.stream()
                .map(e -> e.adaptTo(Event.class))
                .sorted(Comparator.comparing(event -> event != null ? event.getJcrCreated() : 0))
                .collect(Collectors.toList());

        if (!resolver.isLive()) {
            resolver.close();
        }
        return eventList;
    }

    private ResourceResolver getResourceResolver() throws LoginException {
        Map<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, SERVICE_USER_NAME);

        return resolverFactory.getServiceResourceResolver(param);
    }
    public class EventsWrapper {

        private String title;
        private List<Event> eventList;
        private int numColumn;
        private String iconClass;
        private boolean isType;
        private int rowSize;

        public EventsWrapper(String title, List<Event> eventList, boolean isType, int rowSize) {
            this.title = title;
            this.numColumn = (int) Math.ceil((double) eventList.size() / rowSize);
            this.eventList = eventList.subList(0, Math.min(rowSize, eventList.size()));
            this.iconClass = eventList.get(0).getEventTopicIconClass();
            this.isType = isType;
            this.rowSize = rowSize;
        }

        public String getTitle() {
            return title;
        }

        public int getNumColumn() {
            return numColumn;
        }

        public List<Event> getEventList() {
            return eventList;
        }

        public String getIconClass() {
            return iconClass;
        }

        public boolean isType() {
            return isType;
        }

        public int getRowSize() {
            return rowSize;
        }
    }
}
