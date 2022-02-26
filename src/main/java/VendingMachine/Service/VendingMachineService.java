package VendingMachine.Service;
import VendingMachine.Entity.CashPayment;
import VendingMachine.Entity.CreditCard;
import VendingMachine.Entity.Payment;
import VendingMachine.Entity.Product;

import  java.util.*;
public class VendingMachineService {
    Map<String, Product>slots=new HashMap<>();
    int capacity;
    public VendingMachineService(int cap){
        this.capacity=cap;
    }

 public boolean addproduct(String product_id,Product product){
        if(this.capacity>=slots.size())
            return false;
        slots.put(product_id,product);
        return true;
    }
   public Product order(String product){
        return slots.containsKey(product)?slots.get(product):null;
    }

    double checkout(List<Product> cart, Payment payment) {
        int totalAmount = 0;
        for(Product product : cart) {
            totalAmount +=payment.checkout(product);
        }
        return totalAmount;
    }


}
