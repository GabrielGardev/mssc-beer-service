package msscbeerservice.repositories;

import msscbeerservice.domain.Beer;
import msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageable);

    Page<Beer> findAllByBeerName(String beerName, Pageable pageable);

    Optional<Beer> findByUpc(String upc);

    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pageable);

    Optional<Beer> getById(UUID id);
}
