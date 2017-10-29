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
        assertThat(result.getOffersSum(), is(200 + 45));
        assertThat(result.getRemainingProducts(), hasItems("A", "A", "Z"));
    }

    @Test
    public void shouldReturnSpecialOfferCalculationFor_9A(){
        final List<String> skus = Arrays.asList("A", "A", "A", "A", "A", "A", "A", "A", "A");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(200 + 130));
        assertThat(result.getRemainingProducts(), hasItems("A"));
    }

    @Test
    public void shouldReturnSpecialOfferCalculationFor_10A(){
        final List<String> skus = Arrays.asList("A", "A", "A", "A", "A", "A", "A", "A", "A", "A");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(200 + 200));
        assertThat(result.getRemainingProducts().size(), is(0));
    }

    @Test
    public void shouldReturnSpecialOfferCalculationFor_2E(){
        final List<String> skus = Arrays.asList("E", "E");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(0));
        assertThat(result.getRemainingProducts(), hasItems("E", "E"));
    }


    @Test
    public void shouldReturnSpecialOfferCalculationFor_2E1B1C(){
        final List<String> skus = Arrays.asList("E", "E", "B", "C");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(0));
        assertThat(result.getRemainingProducts().size(), is(3));
        assertThat(result.getRemainingProducts(), hasItems("C", "E", "E"));
    }

    @Test
    public void shouldReturnSpecialOfferCalculationFor_CBBEE(){
        final List<String> skus = Arrays.asList("C", "B", "B", "E", "E");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(0));
        assertThat(result.getRemainingProducts().size(), is(4));
        assertThat(result.getRemainingProducts(), hasItems("C", "E", "E", "B"));
    }

    @Test
    public void shouldReturnSpecialOfferCalculationFor_BBBBEE(){
        final List<String> skus = Arrays.asList("B", "B", "B", "E", "E", "B");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(45));
        assertThat(result.getRemainingProducts().size(), is(3));
        assertThat(result.getRemainingProducts(), hasItems("E", "E", "B"));
    }

    @Test
    public void shouldReturnSpecialOfferCalculationFor_BEBEEE(){
        final List<String> skus = Arrays.asList("B", "E", "B", "E", "E", "E");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(0));
        assertThat(result.getRemainingProducts().size(), is(4));
        assertThat(result.getRemainingProducts(), hasItems("E", "E", "E", "E"));
    }

    @Test
    public void shouldReturnSpecialOfferCalculationFor_3F(){
        final List<String> skus = Arrays.asList("F", "F", "F");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(0));
        assertThat(result.getRemainingProducts().size(), is(2));
    }

    @Test
    public void shouldReturnSpecialOfferCalculationFor_6F(){
        final List<String> skus = Arrays.asList("F", "F", "F", "F", "F", "F");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(0));
        assertThat(result.getRemainingProducts().size(), is(4));
    }

    @Test
    public void shouldReturnSpecialOfferCalculationFor_5F(){
        final List<String> skus = Arrays.asList("F", "F", "F", "F", "F");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(0));
        assertThat(result.getRemainingProducts().size(), is(4));
    }

    @Test
    public void shouldReturnSpecialOfferCalculationFor_3U(){
        final List<String> skus = Arrays.asList("U", "U", "U");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(0));
        assertThat(result.getRemainingProducts().size(), is(3));
    }

    @Test
    public void shouldReturnSpecialOfferCalculationFor_4U(){
        final List<String> skus = Arrays.asList("U", "U", "U", "U");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(0));
        assertThat(result.getRemainingProducts().size(), is(3));
    }


}
