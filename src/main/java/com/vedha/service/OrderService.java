package com.vedha.service;

import com.vedha.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class OrderService {

    // fixedRate: makes Spring run the task on periodic intervals even if the last invocation may still be running.
    // fixedDelay: specifically controls the next execution time when the last execution finishes.
    // use a default message channel, if not specified in config: DirectChannel
    @ServiceActivator(inputChannel = "orderChannel" ,
            outputChannel = "processOrderChannel"
//            , poller = @Poller(fixedDelay = "100") // is for queue channel
    )
    public Message<OrderDTO> placeOrder(Message<OrderDTO> orderDTOMessage) {

        log.info("message id {}", orderDTOMessage.getHeaders().getId());
        log.info("message payload {}", orderDTOMessage.getPayload());

        orderDTOMessage.getPayload().setStatus("PLACED");
        return orderDTOMessage;
    }

    @ServiceActivator(inputChannel = "processOrderChannel", outputChannel = "replyChannel"
//            , poller = @Poller(fixedDelay = "100")
    )
    public Message<OrderDTO> processOrder(Message<OrderDTO> orderDTOMessage) {

//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        orderDTOMessage.getPayload().setStatus("PROCESSED");
        return orderDTOMessage;
    }

    @ServiceActivator(inputChannel = "replyChannel"
//            , poller = @Poller(fixedDelay = "100")
    )
    public Message<OrderDTO> reply(Message<OrderDTO> orderDTOMessage) {

        MessageChannel replyChannel = ((MessageChannel) orderDTOMessage.getHeaders().getReplyChannel());
        assert replyChannel != null;
        replyChannel.send(orderDTOMessage);

        return orderDTOMessage;
    }
}
