package pio.grz.s30278bank.Finances;

import org.springframework.stereotype.Service;
import pio.grz.s30278bank.Account.Account;
import pio.grz.s30278bank.Account.AccountService;

@Service
public class FinancesService {

    private final Finances finances;
    private final AccountService accountService;

    public FinancesService(Finances finances, AccountService accountService) {
        this.finances = finances;
        this.accountService = accountService;
    }

    public Finances getFinances() {
        return finances;
    }

    public Status getStatus() {
        return finances.status;
    }

    public void setStatus(Status status) {
        finances.status = status;
    }

    public void deposit(int id, Long amount) {
        Account account = accountService.getUsersAccount(id);
        account.setSaldo(account.getSaldo() + amount);
        if(account.getId() == id){
            setStatus(Status.ACCEPTED);
        }else {
            setStatus(Status.DECLINED);
        }
    }

    public void transfer(int id, Long amount) {
        Account account = accountService.getUsersAccount(id);
        if (account.getSaldo() >= amount) {
            account.setSaldo(account.getSaldo() - amount);
            setStatus(Status.ACCEPTED);
        } else {
            setStatus(Status.DECLINED);
            throw new RuntimeException("Insufficient funds on account " + id);
        }
    }

    public Long getBalance(int id) {
        return accountService.getUserSaldo(id);
    }


}
