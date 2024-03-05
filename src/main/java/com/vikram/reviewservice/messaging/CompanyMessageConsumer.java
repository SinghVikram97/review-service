package com.vikram.reviewservice.messaging;

import com.vikram.reviewservice.dto.CompanyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CompanyMessageConsumer {
    private static final Logger logger = LoggerFactory.getLogger(CompanyMessageConsumer.class);

    @RabbitListener(queues = "q.delete-reviews")
    public void consumeMessage(CompanyMessage companyMessage) {
        logger.info("Message received with companyId: {}",companyMessage.getCompanyId());
    }

    // Consumer for DLQ
    @RabbitListener(queues = "q.delete-reviews-dlq")
    public void consumeMessageFromDLQ(CompanyMessage companyMessage) {
        logger.info("Message received from DLQ with companyId: {}",companyMessage.getCompanyId());
    }
}
