package pio.grz.s30278bank.Account;

import org.springframework.stereotype.Service;
import pio.grz.s30278bank.Finances.Finances;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final Finances finances;

    public AccountService(AccountRepository accountRepository, Finances finances) {
        this.accountRepository = accountRepository;
        this.finances = finances;
    }

    public Long getUserSaldo(int id) {
        Account account = getUsersAccount(id);
        return account.getSaldo();
    }

    public Account getUsersAccount(int id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account with ID " + id + " not found"));
    }

    public void registerAccount(int id, Long saldo) {
        if (accountRepository.findById(id).isPresent()) {
            throw new RuntimeException("Account with ID " + id + " already exists");
        }
        Account account = new Account(id, saldo);
        accountRepository.getAccounts().add(account);
    }


}