package org.motechproject.http.agent.components;

import org.motechproject.event.MotechEvent;
import org.motechproject.http.agent.listener.HttpClientEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Synchronous implementation of {@link org.motechproject.http.agent.components.CommunicationType}
 */
@Component
public class SynchronousCall implements CommunicationType {

    private HttpClientEventListener httpClientEventListener;

    @Autowired
    public SynchronousCall(HttpClientEventListener httpClientEventListener) {
        this.httpClientEventListener = httpClientEventListener;
    }

    @Override
    public void send(MotechEvent motechEvent) {
        httpClientEventListener.handle(motechEvent);
    }

    @Override
    public ResponseEntity<?> sendWithReturnType(MotechEvent motechEvent) {
        return httpClientEventListener.handleWithReturnType(motechEvent);
    }
}
