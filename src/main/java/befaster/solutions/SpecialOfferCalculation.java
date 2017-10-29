package befaster.solutions;

import java.util.List;

public class SpecialOfferCalculation {
    private final int offersSum;
    private final List<String> remainingProducts;

    public SpecialOfferCalculation(int offersSum, final List<String> remainingProducts) {
        this.offersSum = offersSum;
        this.remainingProducts = remainingProducts;
    }

    public int getOffersSum() {
        return offersSum;
    }

    public List<String> getRemainingProducts(){
        return remainingProducts;
    }

}
