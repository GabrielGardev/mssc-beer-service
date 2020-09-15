package msscbeerservice.bootstrap;

import lombok.RequiredArgsConstructor;
import msscbeerservice.domain.Beer;
import msscbeerservice.repositories.BeerRepository;
import msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) {
        if (beerRepository.count() == 0) {
            loadBeerObjects();
        }
    }

    private void loadBeerObjects() {
        Beer b1 = Beer.builder()
                .version(1L)
                .beerName("Mango Bobs")
                .beerStyle(BeerStyleEnum.IPA.name())
                .quantityToBrew(200)
                .minOnHand(12)
                .upc(BEER_1_UPC)
                .price(new BigDecimal("12.95"))
                .build();

        Beer b2 = Beer.builder()
                .version(1L)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.PALE_ALE.name())
                .quantityToBrew(200)
                .minOnHand(12)
                .upc(BEER_2_UPC)
                .price(new BigDecimal("12.95"))
                .build();

        Beer b3 = Beer.builder()
                .version(1L)
                .beerName("Pinball Porter")
                .beerStyle(BeerStyleEnum.PORTER.name())
                .quantityToBrew(200)
                .minOnHand(12)
                .upc(BEER_3_UPC)
                .price(new BigDecimal("12.95"))
                .build();

        beerRepository.saveAll(List.of(b1, b2, b3));
    }
}
