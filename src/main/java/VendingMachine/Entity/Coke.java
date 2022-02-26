package VendingMachine.Entity;

public class Coke extends Product{
    String product_name;
    @Override
    float getPrice() {
        return 30;
    }
}
