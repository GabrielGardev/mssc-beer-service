package msscbeerservice.events;

import lombok.*;
import msscbeerservice.web.model.BeerDto;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -4538291296134598887L;

    private BeerDto beerDto;
}
