package befaster.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GroupOffersCalculator {

    private static final List<String> GROUP_PRODUCTS = Arrays.asList("S", "T", "X", "Y", "Z");
    private static final int GROUP_SIZE = 3;
    private static final int GROUP_PRICE = 45;

    public SpecialOfferCalculation calculate(List<String> basket) {
        final List<String> basketCopy  = new ArrayList<>(basket);
        int sum = 0;
        final List<String> groupProducts = basketCopy.stream()
                .filter(GROUP_PRODUCTS::contains)
                .sorted(orderWithMostExpensiveFirst())
                .collect(Collectors.toList());

        final int offerNum = groupProducts.size() / GROUP_SIZE;
        final int productsToDelete = offerNum * GROUP_SIZE;
        sum += offerNum * GROUP_PRICE;
        for(int i = 0; i < productsToDelete; i++){
            basketCopy.remove(groupProducts.get(i));
        }
        return new SpecialOfferCalculation(sum, basketCopy);
    }

    private Comparator<? super String> orderWithMostExpensiveFirst(){
        return (o1, o2) -> Integer.compare(SupermarketApp.PRICE_MAP.getOrDefault(o2, -1), SupermarketApp.PRICE_MAP.getOrDefault(o1, -1));
    }

}
