package befaster.solutions;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;


public class SupermarketAppTest {

    private SupermarketApp supermarketApp = new SupermarketApp();

    @Test
    public void shouldCalculatePriceForSingleProducts(){

        assertThat(supermarketApp.checkout("A"), is(50));
        assertThat(supermarketApp.checkout("B"), is(30));
        assertThat(supermarketApp.checkout("C"), is(20));
        assertThat(supermarketApp.checkout("D"), is(15));
        assertThat(supermarketApp.checkout("E"), is(40));
    }

    @Test
    public void shouldReturnFailureIfUnkownProduct(){

        assertThat(supermarketApp.checkout("Z"), is(-1));
    }

    @Test
    public void shouldReturnFailureIfInvalidInput(){

        assertThat(supermarketApp.checkout("A1"), is(-1));
        assertThat(supermarketApp.checkout(null), is(-1));
        assertThat(supermarketApp.checkout("Some testk A"), is(-1));
    }

    @Test
    public void shouldCalculatePriceForListOfSingleProducts(){
        assertThat(supermarketApp.checkout("ABCD"), is(115));
        assertThat(supermarketApp.checkout("AD"), is(65));
        assertThat(supermarketApp.checkout("ABC"), is(100));
        assertThat(supermarketApp.checkout("ABCDE"), is(155));
    }

    @Test
    public void shouldReturnZeroForEmptyBasket(){
        assertThat(supermarketApp.checkout(""), is(0));
        assertThat(supermarketApp.checkout("     "), is(0));
    }

    @Test
    public void shouldCalculateSpecialOffers(){
        assertThat(supermarketApp.checkout("AAA"), is(130));
        assertThat(supermarketApp.checkout("BB"), is(45));
        assertThat(supermarketApp.checkout("ABABA"), is(130 + 45));
        assertThat(supermarketApp.checkout("ABABAA"), is(130 + 45 + 50));
    }


}
