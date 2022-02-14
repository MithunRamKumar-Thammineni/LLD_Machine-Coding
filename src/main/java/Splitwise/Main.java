package Splitwise;

import Splitwise.Model.Command;
import Splitwise.Model.ExpenseType;
import Splitwise.Model.User;
import Splitwise.Service.ShareExpenses;
import Splitwise.Service.UserService;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        User user1 = new User(1, "u1", "u1@gmail.com", "9890098900");
        User user2 = new User(2, "u2", "u2@gmail.com", "9999999999");
        User user3 = new User(3, "u3", "u3@gmail.com", "9898989899");
        User user4 = new User(4, "u4", "u4@gmail.com", "8976478292");

        List<User> users = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));

        UserService userService = new UserService(users);
        ShareExpenses shareExpenses = new ShareExpenses(users);

        while (true) {
            Scanner scn = new Scanner(System.in);
            Command command = Command.valueOf(scn.next());
            switch (command) {
                case EXPENSE:
                    String user = scn.next();
                    int amtspent = scn.nextInt();
                    int totatmembers = scn.nextInt();
                    List<User> owedusers = new ArrayList<>();
                    for (int i = 0; i < totatmembers; i++)
                        owedusers.add(userService.getUser(scn.next()).get());

                    ExpenseType expenseType = ExpenseType.of(scn.next());
                    switch (expenseType) {
                        case EQUAL:
                            shareExpenses.spliteqaulexpenses(user, amtspent, owedusers);
                            break;
                        case EXCAT:
                            HashMap<User, Double> owedamountmap = new HashMap<>();
                            double sum = 0;
                            for (int i = 0; i < totatmembers; i++) {
                                double amt = scn.nextDouble();
                                sum += amt;
                                owedamountmap.put(owedusers.get(i), amt);
                            }
                            if (sum != amtspent) {
                                System.out.println("Sum not matches actual split amount");
                                break;
                            }
                            shareExpenses.splitexcatexpenses(user, sum, owedusers, owedamountmap);
                            break;
                        case PERCENT:
                            HashMap<User, Integer> ownedpercetmap = new HashMap<>();
                            int total_per = 0;
                            for (int i = 0; i < totatmembers; i++) {
                                int per = scn.nextInt();
                                total_per += per;
                                ownedpercetmap.put(owedusers.get(i), per);
                            }
                            if (total_per != 100) {
                                System.out.println("Total percentage is not eqaul to 100");
                                break;
                            } else {
                                shareExpenses.splitpercentexpenses(user, amtspent, owedusers, ownedpercetmap);
                            }
                            break;
                    }
                    break;

                case SHOW:
                    String username = scn.next();
                  shareExpenses.showexpenses(username);
                    break;
                case QUIT:
                    System.out.println("Qutting..");
                    return;
                default:
                    System.out.println("No expected argument found");
                    break;


            }

        }
    }
}
