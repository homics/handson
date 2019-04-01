package com.homics.monolith.service;

import com.homics.monolith.model.Order;
import com.homics.monolith.repository.OrderStatsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StatsService {
    final String ORDER_STAT_URL = "http://localhost:9002/stats/api/orders";
    private RestTemplate restTemplate;
    private OrderStatsRepository orderStatsRepository;

    public StatsService(RestTemplate restTemplate, OrderStatsRepository orderStatsRepository) {
        this.restTemplate = restTemplate;
        this.orderStatsRepository = orderStatsRepository;
    }

    public void addOrderPayMessage(Order order) {
        // TODO 1 :
        //  Save an orderPayMessage in the database.
        //  The scheduled task will send it later.
    }

    public void sendStats() {
        // TODO 3:
        //  Fetch all orderPayMessages
        //  Send them as orderPayMessagesDto to the micro service with a post on : http://localhost:9002/stats/api/orders
        //  If the post is successful, remove it from the database
    }


    private boolean sendStat() {
        // TODO 3:
        //  Edit the method to send the stats via restTemplate.
        ResponseEntity<String> response = restTemplate.postForEntity("MY_URL", "MY_ENTITY_TO_SEND", String.class);
        return HttpStatus.OK.equals(response.getStatusCode());
    }
}
