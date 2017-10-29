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

    }

    @Test
    public void shouldReturnFailureIfUnkownProduct(){

        assertThat(supermarketApp.checkout("2"), is(-1));
    }

    @Test
    public void shouldReturnFailureIfInvalidInput(){

        assertThat(supermarketApp.checkout("A1"), is(-1));
        assertThat(supermarketApp.checkout(null), is(-1));
        assertThat(supermarketApp.checkout("Some testk A"), is(-1));
    }

    @Test
    public void shouldCalculatePriceForListOfSingleProducts(){
        assertThat(supermarketApp.checkout("A B C D"), is(115));
        assertThat(supermarketApp.checkout("A   D   "), is(65));
        assertThat(supermarketApp.checkout("A B C"), is(100));
    }

    @Test
    public void shouldReturnZeroForEmptyBasket(){
        assertThat(supermarketApp.checkout(""), is(0));
        assertThat(supermarketApp.checkout("     "), is(0));
    }

    @Test
    public void shouldCalculateSpecialOffers(){
        assertThat(supermarketApp.checkout("A A A"), is(130));
        assertThat(supermarketApp.checkout("B B"), is(45));
        assertThat(supermarketApp.checkout("A B A B A"), is(130 + 45));
        assertThat(supermarketApp.checkout("A B A B A A"), is(130 + 45 + 50));
    }


}
