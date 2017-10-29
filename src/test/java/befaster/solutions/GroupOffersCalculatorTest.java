package befaster.solutions;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;


import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class GroupOffersCalculatorTest {

    private GroupOffersCalculator calculator = new GroupOffersCalculator();

    @Test
    public void allOneProduct_SSS(){
        final List<String> skus = Arrays.asList("S", "S", "S");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(45));
        assertThat(result.getRemainingProducts().size(), is(0));
    }

    @Test
    public void allOneProduct_SYZ(){
        final List<String> skus = Arrays.asList("S", "Y", "Z");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(45));
        assertThat(result.getRemainingProducts().size(), is(0));
    }

    @Test
    public void productFromOutsideTheGroup(){
        final List<String> skus = Arrays.asList("S", "Y", "Z", "A");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(45));
        assertThat(result.getRemainingProducts().size(), is(1));
        assertThat(result.getRemainingProducts(), hasItems("A"));
    }

    @Test
    public void shouldFavourTheClientAndRemoveMostExpensiveProducts(){
        final List<String> skus = Arrays.asList("S", "X", "Y", "Z", "X");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(45));
        assertThat(result.getRemainingProducts().size(), is(2));
        assertThat(result.getRemainingProducts(), hasItems("X", "X"));
    }

    @Test
    public void shouldFavourTheClientAndRemoveMostExpensiveProducts_whenMultipleOccurences(){
        final List<String> skus = Arrays.asList("X", "Y", "T", "T", "X");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(45));
        assertThat(result.getRemainingProducts().size(), is(2));
        assertThat(result.getRemainingProducts(), hasItems("X", "X"));
    }

    @Test
    public void moreThanOneOffer(){
        final List<String> skus = Arrays.asList("S", "Y", "Z", "A", "X", "Y", "T");
        final SpecialOfferCalculation result = calculator.calculate(skus);
        assertThat(result.getOffersSum(), is(90));
        assertThat(result.getRemainingProducts().size(), is(1));
        assertThat(result.getRemainingProducts(), hasItems("A"));
    }


}
