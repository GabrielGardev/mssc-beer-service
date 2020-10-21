package msscbeerservice.services.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msscbeerservice.repositories.BeerRepository;
import org.springframework.stereotype.Component;
import sfg.brewery.model.events.BeerOrderDto;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidator {

    private final BeerRepository beerRepository;

    public Boolean validateOrder(BeerOrderDto beerOrder){

        AtomicInteger beersNotFound = new AtomicInteger();

        beerOrder.getBeerOrderLines().forEach(orderLine -> {
            if(beerRepository.findByUpc(orderLine.getUpc()) == null){
                beersNotFound.incrementAndGet();
            }
        });

        return beersNotFound.get() == 0;
    }

}
