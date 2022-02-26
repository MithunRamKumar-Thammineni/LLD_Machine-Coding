package VendingMachine.Service;

import VendingMachine.Entity.Payment;
import VendingMachine.Entity.Product;

import java.util.List;

public class CustomerService {
    VendingMachineService vendingMachineService;
    List<Product>cart;

  public   CustomerService(VendingMachineService vendingMachineService){
        this.vendingMachineService=vendingMachineService;
    }

    public boolean select(String productId){
        Product product=vendingMachineService.order(productId);
        if(product!=null){
            cart.add(product);
            return true;
        }
        return false;
    }

    public double checkout(Payment payment){
        return  vendingMachineService.checkout(cart,payment);
    }
}
