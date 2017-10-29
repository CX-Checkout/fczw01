package befaster.solutions;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class SpecialOfferCalculatorTest {

    private SpecialOfferCalculator calculator = new SpecialOfferCalculator();

    @Test
    public void shouldReturnSpecialOfferCalculationFor_3A(){
        final List<String> skus = Arrays.asList("A", "A", "A");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(130));
        assertThat(result.getRemainingProducts().size(), is(0));
    }

    @Test
    public void shouldReturnSpecialOfferCalculationFor_4A1B(){
        final List<String> skus = Arrays.asList("A", "A", "A", "B", "A");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(130));
        assertThat(result.getRemainingProducts(), hasItems("A","B"));
    }

    @Test
    public void shouldReturnSpecialOfferCalculationFor_7A2B1Z(){
        final List<String> skus = Arrays.asList("A", "A", "A", "B", "A", "A", "A", "B", "Z", "A");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(130 + 130 + 45));
        assertThat(result.getRemainingProducts(), hasItems("A", "Z"));
    }
}
