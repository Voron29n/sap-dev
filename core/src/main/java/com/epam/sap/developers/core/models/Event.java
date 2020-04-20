package com.epam.sap.developers.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Event {

    String getEventDateStr();

    String getEventTitle();

    String getEventDescription();

    String getEventTopicIconClass();

    @JsonIgnore
    String getEventTopic();
    @JsonIgnore
    String getEventType();
    @JsonIgnore
    long getJcrCreated();
    @JsonIgnore
    boolean isEquals();
}
