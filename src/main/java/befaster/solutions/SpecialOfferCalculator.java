package befaster.solutions;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SpecialOfferCalculator {

    private static class ProductSpecialOffers {
        private final int priority;
        private final List<SpecialOffer> offers;

        public ProductSpecialOffers(int priority, List<SpecialOffer> offers) {
            this.priority = priority;
            this.offers = offers;
        }

        public int getPriority() {
            return priority;
        }

        public List<SpecialOffer> getOffers() {
            return offers;
        }
    }

    private static class SpecialOffer {
        private final int prodNum;
        private final int newPrice;
        private final List<String> freeProducts;
        private boolean removeFromBasket;

        public SpecialOffer(int prodNum, int newPrice) {
            this.prodNum = prodNum;
            this.newPrice = newPrice;
            this.removeFromBasket = true;
            this.freeProducts = new ArrayList<>();
        }

        public SpecialOffer(int prodNum, int newPrice, List<String> freeProducts, boolean removeFromBasket) {
            this.prodNum = prodNum;
            this.newPrice = newPrice;
            this.freeProducts = freeProducts;
            this.removeFromBasket = removeFromBasket;
        }

        public int getProdNum() {
            return prodNum;
        }

        public int getNewPrice() {
            return newPrice;
        }

        public List<String> getFreeProducts() {
            return freeProducts;
        }

        public boolean isRemoveFromBasket() {
            return removeFromBasket;
        }
    }

    private static final Map<String, ProductSpecialOffers> specialOffers = new HashMap<String, ProductSpecialOffers>(){{
        // order is important bigger offer first!
        put("A", new ProductSpecialOffers(1, Arrays.asList(new SpecialOffer(5, 200), new SpecialOffer(3,130))));
        put("H", new ProductSpecialOffers(1, Arrays.asList(new SpecialOffer(10, 80), new SpecialOffer(5,45))));
        put("V", new ProductSpecialOffers(1, Arrays.asList(new SpecialOffer(3, 130), new SpecialOffer(2,90))));

        put("K", new ProductSpecialOffers(1, Arrays.asList(new SpecialOffer(2,150))));
        put("P", new ProductSpecialOffers(1, Arrays.asList(new SpecialOffer(5,200))));
        put("Q", new ProductSpecialOffers(3, Arrays.asList(new SpecialOffer(3,80))));
        put("B", new ProductSpecialOffers(3, Arrays.asList(new SpecialOffer(2,45))));

        put("E", new ProductSpecialOffers(2, Arrays.asList(new SpecialOffer(2,0, Arrays.asList("B"), false))));
        put("F", new ProductSpecialOffers(1, Arrays.asList(new SpecialOffer(3,0, Arrays.asList("F"), false))));
        put("N", new ProductSpecialOffers(1, Arrays.asList(new SpecialOffer(3,0, Arrays.asList("M"), false))));
        put("R", new ProductSpecialOffers(2, Arrays.asList(new SpecialOffer(3,0, Arrays.asList("Q"), false))));
        put("U", new ProductSpecialOffers(2, Arrays.asList(new SpecialOffer(4,0, Arrays.asList("U"), false))));
    }};

    public SpecialOfferCalculation calculate(final List<String> basket) {

        final List<String> basketCopy = new ArrayList<>(basket);
        final Map<String, List<String>> productCounts = basketCopy.stream()
                .collect(Collectors.groupingBy(Function.identity()));

        // order by offer priority,
        final List<Map.Entry<String, List<String>>> entries = productCounts.entrySet().stream()
                .sorted(getProductPriorityComparator())
                .collect(Collectors.toList());

        int sum = 0;
        for(Map.Entry<String, List<String>> productCount : entries){

            if(specialOffers.containsKey(productCount.getKey())){
                int productNumber = productCount.getValue().size();
                for(final SpecialOffer specialOffer : specialOffers.get(productCount.getKey()).getOffers()){
                    final int offerNum = productNumber / specialOffer.getProdNum();
                    for(int offerApply = 0; offerApply < offerNum; offerApply++) {
                    sum += specialOffer.getNewPrice();
                    final int productsToDelete = specialOffer.getProdNum();
                    productNumber -= productsToDelete;

                    if(specialOffer.isRemoveFromBasket()) {
                        for (int i = 0; i < productsToDelete; i++) {
                            basketCopy.remove(productCount.getValue().get(i));
                        }
                    }
                    specialOffer.getFreeProducts().forEach(s -> basketCopy.remove(s));
                    specialOffer.getFreeProducts().forEach(s -> productCounts.getOrDefault(s, new ArrayList<>()).remove(s));
                    }
                }

            }

        }
        return new SpecialOfferCalculation(sum, basketCopy);
    }

    private Comparator<Map.Entry<String, List<String>>> getProductPriorityComparator(){
        return (o1, o2) -> {
            final int offer1Priority = specialOffers.containsKey(o1.getKey()) ? specialOffers.get(o1.getKey()).getPriority() : -1;
            final int offer2Priority = specialOffers.containsKey(o2.getKey()) ? specialOffers.get(o2.getKey()).getPriority() : -1;
            return Integer.compare(offer1Priority, offer2Priority);
        };
    }

}
