package VendingMachine;

import VendingMachine.Entity.Coke;
import VendingMachine.Entity.CreditCard;
import VendingMachine.Entity.Payment;
import VendingMachine.Entity.Water;
import VendingMachine.Service.CustomerService;
import VendingMachine.Service.VendingMachineService;

public class Main {
    public static void main(String[] args) {
        Water w1 = new Water();
        Water w2 = new Water();
        Coke c1 = new Coke();
        Coke c2 = new Coke();

        VendingMachineService vm = new VendingMachineService(5);
        vm.addproduct("A1", w1);
        vm.addproduct("A2", w1);
        vm.addproduct("A3", c1);
        vm.addproduct("A4", c2);

        CustomerService customerService = new CustomerService(vm);
        customerService.select("A1");
        customerService.select("A2");

        Payment card = new CreditCard();
        customerService.checkout(card);

    }
}
