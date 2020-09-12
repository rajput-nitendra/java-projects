package com.niten.springbootstarter.topic;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopicService {
    private List<Topic> topics = new ArrayList<>(Arrays.asList(
            new Topic("spring", "Spring framework", "Spring framework description"),
            new Topic("angular", "Angular framework", "Angular framework description"),
            new Topic("javascript", "Javascript framework", "Javascript framework description")
    ));

    public List<Topic> getAllTopics() {
        return topics;
    }

    public Topic getTopic(String id) {
        return topics.stream()
                .filter(topic -> topic.getId().equals(id))
                .findFirst()
                .get();
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public Topic updateTopic(String id, Topic topic) {
        for (Topic _topic : topics) {
            if (id.equals(_topic.getId())) {
                _topic.setId(topic.getId());
                _topic.setName(topic.getName());
                _topic.setDescription(topic.getDescription());

                return topic;
            }
        }
        return null;
    }
}
