package msscbeerservice.services.order;

import lombok.RequiredArgsConstructor;
import msscbeerservice.config.JmsConfig;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import sfg.brewery.model.events.ValidateOrderRequest;
import sfg.brewery.model.events.ValidateOrderResult;

@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final BeerOrderValidator validator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest validateOrderRequest){
        Boolean isValid = validator.validateOrder(validateOrderRequest.getBeerOrder());

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE,
                ValidateOrderResult.builder()
                        .isValid(isValid)
                        .orderId(validateOrderRequest.getBeerOrder().getId())
                        .build());
    }
}
