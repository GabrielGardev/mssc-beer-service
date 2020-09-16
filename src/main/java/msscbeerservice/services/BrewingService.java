package msscbeerservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msscbeerservice.config.JmsConfig;
import msscbeerservice.domain.Beer;
import msscbeerservice.events.BrewBeerEvent;
import msscbeerservice.repositories.BeerRepository;
import msscbeerservice.services.inventory.BeerInventoryService;
import msscbeerservice.web.mappers.BeerMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {
    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory(){
        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            int invQH = beerInventoryService.getOnHandInventory(beer.getId());

            log.debug("Min Onhand is: " + beer.getMinOnHand());
            log.debug("Inventory is: " + invQH);

            if (beer.getMinOnHand() >= invQH){
               jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE,
                       new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}
