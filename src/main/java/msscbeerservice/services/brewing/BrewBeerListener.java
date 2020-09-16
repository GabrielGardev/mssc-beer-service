package msscbeerservice.services.brewing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msscbeerservice.config.JmsConfig;
import msscbeerservice.domain.Beer;
import msscbeerservice.repositories.BeerRepository;
import msscbeerservice.web.controller.NotFoundException;
import msscbeerservice.web.model.BeerDto;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sfg.common.events.BrewBeerEvent;
import sfg.common.events.NewInventoryEvent;

@Slf4j
@RequiredArgsConstructor
@Service
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event){
        BeerDto beerDto = event.getBeerDto();
        Beer beer = beerRepository.getById(beerDto.getId()).orElseThrow(NotFoundException::new);

        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        log.debug("Brewed beer " + beer.getMinOnHand() + " : QOH: " + beerDto.getQuantityOnHand());
        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }
}
