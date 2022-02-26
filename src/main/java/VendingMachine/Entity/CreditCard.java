package VendingMachine.Entity;

public class CreditCard extends Payment{

    @Override
  public double checkout(Product product) {
       return product.getPrice()*1.25;
    }
}
