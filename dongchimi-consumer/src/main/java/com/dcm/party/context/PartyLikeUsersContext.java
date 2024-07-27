package com.dcm.party.context;

import com.dcm.message.dto.PartyLikePublishRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.springframework.stereotype.Component;

@Component
public class PartyLikeUsersContext {

    private final Queue<PartyLikePublishRequest> queue;

    public PartyLikeUsersContext() {
        this.queue = new ConcurrentLinkedQueue<>();
    }

    public void add(PartyLikePublishRequest request) {
        queue.add(request);
    }

    public List<PartyLikePublishRequest> getElements() {
        List<PartyLikePublishRequest> elements = new ArrayList<>();
        while (queue.isEmpty()) {
            elements.add(queue.poll());
        }
        return elements;
    }

}
