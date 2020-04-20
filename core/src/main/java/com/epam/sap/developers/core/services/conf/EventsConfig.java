package com.epam.sap.developers.core.services.conf;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition
public @interface EventsConfig {
    /**
     * List size that will be get into a {@link } method
     */
    @AttributeDefinition(name = "Default Row size", type = AttributeType.INTEGER)
    int defaultRowSize() default 2;

    @AttributeDefinition(name = "Default Scheduler to archive existed events", type = AttributeType.BOOLEAN)
    boolean defaultSchedulerStatus() default false;

    @AttributeDefinition(name = "Default time to archive existed events", type = AttributeType.INTEGER)
    int defaultArchiveTime() default 2;

}
