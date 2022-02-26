package VendingMachine.Entity;

public class Water extends Product {
    String product_name;


    @Override
    float getPrice() {
        return 10;
    }
}
