package VendingMachine.Entity;

public class CashPayment extends Payment {

    @Override
   public double checkout(Product product) {
        return product.getPrice() * 1;
    }
}
