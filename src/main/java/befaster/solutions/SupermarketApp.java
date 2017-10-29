package befaster.solutions;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SupermarketApp {

    private final SpecialOfferCalculator specialOfferCalculator = new SpecialOfferCalculator();
    private final GroupOffersCalculator groupOffersCalculator = new GroupOffersCalculator();

    final static Map<String, Integer> PRICE_MAP = new HashMap<String, Integer>(){{
        put("A", 50);
        put("B", 30);
        put("C", 20);
        put("D", 15);
        put("E", 40);
        put("F", 10);
        put("G", 20);
        put("H", 10);
        put("I", 35);
        put("J", 60);
        put("K", 70);
        put("L", 90);
        put("M", 15);
        put("N", 40);
        put("O", 10);
        put("P", 50);
        put("Q", 30);
        put("R", 50);
        put("S", 20);
        put("T", 20);
        put("U", 40);
        put("V", 50);
        put("W", 20);
        put("X", 17);
        put("Y", 20);
        put("Z", 21);
    }};


    public Integer checkout(final String skus){
        if(skus == null) return -1;
        final List<String> products = Arrays.asList(skus.trim().split("")).stream()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        final SpecialOfferCalculation groupOfferCalculation = groupOffersCalculator.calculate(products);
        final SpecialOfferCalculation specialOfferCalculation = specialOfferCalculator.calculate(groupOfferCalculation.getRemainingProducts());
        int sum = groupOfferCalculation.getOffersSum() + specialOfferCalculation.getOffersSum();
        for(final String product : specialOfferCalculation.getRemainingProducts()){
                final int price = PRICE_MAP.getOrDefault(product, -1);
                if(price == -1) return price;
                sum += price;
        }
        return sum;
    }
}
