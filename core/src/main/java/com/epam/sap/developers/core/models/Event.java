package com.epam.sap.developers.core.models;

public interface Event {

    String getEventDateStr();

    String getEventTitle();

    String getEventDescription();

    String getEventTopic();

    String getEventType();

    String getEventTopicIconNumber();

    boolean isEquals();
}
