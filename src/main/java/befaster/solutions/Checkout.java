package befaster.solutions;

import befaster.runner.SolutionNotImplementedException;

public class Checkout {
    public static Integer checkout(String skus) {
        return new SupermarketApp().checkout(skus);
    }
}
