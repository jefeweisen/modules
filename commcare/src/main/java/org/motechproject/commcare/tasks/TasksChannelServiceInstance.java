package org.motechproject.commcare.tasks;

import org.motechproject.commcare.tasks.builder.ChannelRequestBuilder;
import org.motechproject.tasks.contract.ChannelRequest;
import org.motechproject.tasks.service.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The <code>TasksChannelServiceInstance</code> class, holds Tasks-related beans. The reason this class
 * must exist is because no Spring beans should be initialised if the Tasks module is not present. Therefore,
 * we should first make sure that the Tasks module is present and only then we use Tasks classes, to
 * avoid <code>NoClassDefFoundError</code>.
 */
public class TasksChannelServiceInstance {

    private static final Logger LOGGER = LoggerFactory.getLogger(TasksChannelServiceInstance.class);

    private ChannelService channelService;
    private ChannelRequestBuilder channelRequestBuilder;

    public TasksChannelServiceInstance(Object service, ChannelRequestBuilder builder) {
        if (service instanceof ChannelService) {
            this.channelService = (ChannelService) service;
        }
        this.channelRequestBuilder = builder;
    }

    /**
     * Registers or updates the task channel if it contains any task triggers, if it doesn't the channel
     * will be unregistered.
     */
    public void updateTaskChannel() {

        ChannelRequest channelRequest = channelRequestBuilder.buildChannelRequest();

        if (channelService != null) {
            if (!channelRequest.getTriggerTaskEvents().isEmpty()) {
                LOGGER.trace("Registering channel with the following request: {}", channelRequest);
                channelService.registerChannel(channelRequest);
            } else {
                LOGGER.trace("Unregistering channel with the following request: {}", channelRequest);
                channelService.unregisterChannel(channelRequest.getModuleName());
            }
        }
    }
}
