package Splitwise.Service;

import Splitwise.Model.OwedUser;
import Splitwise.Model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class ShareExpenses {
    List<User> userList;
    HashMap<User, HashMap<User, OwedUser>> expensesMap = new HashMap<>();

    public ShareExpenses(List<User> users) {
        this.userList = users;
    }

    public void spliteqaulexpenses(String username, double totalamount, List<User> owedUsers) {
        Optional<User> user = getUser(username);
        int totalsharedusers = owedUsers.size();
        if (user.isPresent()) {
            double shares = totalamount / owedUsers.size();
            HashMap<User, OwedUser> userspecificmap;
            if (expensesMap.get(user) != null) {
                userspecificmap = expensesMap.get(user);
            } else
                userspecificmap = new HashMap<>();
            for (User oweduser : owedUsers) {
                if (expensesMap.get(oweduser) != null && oweduser != user.get()) {
                    double rem = expensesMap.get(oweduser).get(user).getBalance() - shares;
                    if (rem > 0) {
                        expensesMap.get(oweduser).get(user).setBalance(rem);
                    } else if (rem < 0) {
                        expensesMap.get(oweduser).remove(user);
                        OwedUser owedUserobj = new OwedUser(oweduser, Math.abs(rem));
                        userspecificmap.put(oweduser, owedUserobj);
                    } else {
                        expensesMap.get(oweduser).remove(user.get());
                    }
                } else {
                    if (userspecificmap.get(oweduser) != null) {
                        double initialbal = userspecificmap.get(oweduser).getBalance();
                        userspecificmap.get(oweduser).setBalance(initialbal + shares);
                    } else {
                        OwedUser owedUserobj = new OwedUser(oweduser, shares);
                        userspecificmap.put(oweduser, owedUserobj);

                    }
                }


            }
            expensesMap.put(user.get(), userspecificmap);


        } else
            System.out.println("User in not in the provided input");
    }

    public void splitexcatexpenses(String username, double totalamount, List<User> owedusers, HashMap<User, Double> owedamount) {
        Optional<User> user = getUser(username);
        if (user.isPresent()) {
            HashMap<User, OwedUser> userspecificmap;
            if (expensesMap.get(user.get()) != null)
                userspecificmap = expensesMap.get(user);
            else
                userspecificmap = new HashMap<>();
            for (User oweduser : owedusers) {
                if (expensesMap.get(oweduser) != null && oweduser != user.get()) {
                    double remaining = expensesMap.get(oweduser).get(user).getBalance() - owedamount.get(oweduser);
                    if (remaining > 0) {
                        expensesMap.get(oweduser).get(user).setBalance(remaining);
                    } else if (remaining < 0) {
                        expensesMap.get(oweduser).remove(user);
                        OwedUser owedUserobj = new OwedUser(oweduser, Math.abs(remaining));
                        userspecificmap.put(oweduser, owedUserobj);
                    } else {
                        //if remaing is o,then remove it from owed  map
                        expensesMap.get(oweduser).remove(user);
                    }
                } else {
                    if (userspecificmap.get(oweduser) != null) {
                        double inital_bal = userspecificmap.get(oweduser).getBalance();
                        userspecificmap.get(oweduser).setBalance(inital_bal + owedamount.get(oweduser));
                    } else {
                        OwedUser owedUserobj = new OwedUser(oweduser, owedamount.get(oweduser));
                        userspecificmap.put(oweduser, owedUserobj);
                    }
                }
            }
            expensesMap.put(user.get(), userspecificmap);


        } else
            System.out.println("User is not in the provided input");
    }

    public void splitpercentexpenses(String username, double totalamt, List<User> owedusers, HashMap<User, Integer> owendpercentmap) {
        Optional<User> user = getUser(username);
        if (user.isPresent()) {
            HashMap<User, OwedUser> userspecificmap;
            if (expensesMap.get(user) != null) {
                userspecificmap = expensesMap.get(user);
            } else
                userspecificmap = new HashMap<>();
            for (User oweduser : owedusers) {
                if (expensesMap.get(oweduser) != null && oweduser != user.get()) {
                    double rem = expensesMap.get(oweduser).get(user).getBalance() - getpercenateamt(totalamt, owendpercentmap.get(user));
                    if (rem > 0) {
                        expensesMap.get(oweduser).get(user).setBalance(rem);
                    } else if (rem < 0) {
                        expensesMap.get(oweduser).remove(user);
                        OwedUser oweduserobj = new OwedUser(oweduser, Math.abs(rem));
                        userspecificmap.put(oweduser, oweduserobj);
                    } else {
                        expensesMap.get(oweduser).remove(user);
                    }
                } else {
                    if (userspecificmap.get(oweduser) != null) {
                        double inital_bal = userspecificmap.get(oweduser).getBalance();
                        double perct_val = getpercenateamt(totalamt, owendpercentmap.get(oweduser));
                        userspecificmap.get(oweduser).setBalance(inital_bal + perct_val);
                    } else {
                        OwedUser ownedusernobj = new OwedUser(oweduser, getpercenateamt(totalamt, owendpercentmap.get(oweduser)));
                        userspecificmap.put(oweduser, ownedusernobj);
                    }
                }
            }
            expensesMap.put(user.get(), userspecificmap);
        } else
            System.out.println("User is not in the provided input");
    }

    public void showexpenses(String username) {
        if (username.isEmpty() && expensesMap.size() == 0) {
            System.out.println("No Balance");
            return;
        }
        if (username.isEmpty()) {
            showallusersdata();
            return;
        }
        AtomicBoolean isuserhasdata = new AtomicBoolean(false);
        Optional<User> showuser = getUser(username);
        if (showuser.isPresent()) {
            if (expensesMap.get(showuser.get()) != null) {
                HashMap<User, OwedUser> owedusers = expensesMap.get(showuser);
                for (Map.Entry<User, OwedUser> entry : owedusers.entrySet()) {
                    isuserhasdata.set(true);
                    if (!entry.getKey().getName().equals(showuser.get().getName())) {
                        System.out.println(entry.getKey() + " Owes " + showuser.get().getName() + ":" + entry.getValue().getBalance());
                    }
                }
            }
            //I am responsible to pay
            userList.forEach(user -> {
                if (expensesMap.get(user) != null && user != showuser.get()) {
                    expensesMap.get(user).forEach(
                            (user1, oweduser) -> {
                                if (user1.getName().equals(showuser.get().getName()) ||
                                        oweduser.getUser().getName().equals(showuser.get().getName())) {
                                    isuserhasdata.set(true);
                                    System.out.println(oweduser.getUser().getName()+" Owes " + user.getName() + ": " + oweduser.getBalance());
                                }
                            }
                    );
                }

            });
            if (!isuserhasdata.get())
                System.out.println("No Balance");
            isuserhasdata.set(false);
        } else
            System.out.println("No User present");

    }

    private void showallusersdata() {
        userList.forEach(
                user -> {
                    if (expensesMap.get(user) != null) {
                        expensesMap.get(user).forEach(
                                (user1, owedUser) -> {
                                    if (!owedUser.getUser().getName().equals(user1.getName()))
                                        System.out.println(owedUser.getUser().getName() + " Owes " + user.getName() + " :" + owedUser.getBalance());
                                }
                        );
                    }
                }
        );
    }

    private double getpercenateamt(double amt, int percent) {
        return (amt * percent) / 100;
    }

    private Optional<User> getUser(String username) {
        for (User user : userList) {
            if (user.getName().equals(username.trim())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

}
