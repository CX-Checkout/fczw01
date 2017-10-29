package befaster.solutions;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SpecialOfferCalculator {

    private static final Map<String, Pair<Integer, Integer>> specialOffers = new HashMap<String, Pair<Integer, Integer>>(){{
        put("A", new ImmutablePair<>(3, 130));
        put("B", new ImmutablePair<>(2, 45));
    }};

    public SpecialOfferCalculation calculate(final List<String> basket) {

        final List<String> basketCopy = new ArrayList<>(basket);
        final Map<String, List<String>> productCounts = basketCopy.stream()
                .collect(Collectors.groupingBy(Function.identity()));

        int sum = 0;
        for(Map.Entry<String, List<String>> productCount : productCounts.entrySet()){

            if(specialOffers.containsKey(productCount.getKey())){
                final Pair<Integer, Integer> specialOffer = specialOffers.get(productCount.getKey());
                final int offerNum = productCount.getValue().size() / specialOffer.getLeft();
                sum += offerNum * specialOffer.getRight();
                for(int i = 0; i < offerNum * specialOffer.getLeft(); i++){
                    basketCopy.remove(productCount.getValue().get(i));
                }
            }

        }

        return new SpecialOfferCalculation(sum, basketCopy);
    }
}
