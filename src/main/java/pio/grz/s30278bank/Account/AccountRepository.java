package pio.grz.s30278bank.Account;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {

    List<Account> accounts;

    public AccountRepository() {
        this.accounts = new ArrayList<>();
        accounts.add(new Account(1, 100L));
        accounts.add(new Account(2, 200L));
        accounts.add(new Account(3, 300L));
        accounts.add(new Account(4, 400L));
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Optional<Account> findById(int id) {
        return accounts.stream()
                .filter(account -> account.getId() == id)
                .findFirst();
    }
}