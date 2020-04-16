package com.epam.sap.developers.core.models;

public interface HtlEventsDropdownDataStore {

    String EVENT_TOPIC_DROPDOWN = "eventTopic";

    String EVENT_TYPE_DROPDOWN = "eventType";

    String PATH_TO_DROPDOWN = "/content/developers/aboutus/jcr:content/dropdownMultifield";

    void activate() throws Exception;
}
