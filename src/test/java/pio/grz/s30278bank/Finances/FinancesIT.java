package pio.grz.s30278bank.Finances;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pio.grz.s30278bank.Account.AccountService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FinancesIT {

    @Autowired
    private FinancesService financesService;

    @Autowired
    private AccountService accountService;

    @Test
    void shouldDepositMoney() {
        int id = 1;
        Long amount = 50L;
        Long initialSaldo = accountService.getUserSaldo(id);

        financesService.deposit(id, amount);

        Long finalSaldo = accountService.getUserSaldo(id);
        assertEquals(initialSaldo + amount, finalSaldo);
    }

    @Test
    void shouldRegisterAndWithdrawMoney() {
        int id = 99;
        Long initialSaldo = 200L;
        Long withdrawAmount = 50L;

        accountService.registerAccount(id, initialSaldo);
        financesService.transfer(id, withdrawAmount);

        assertEquals(initialSaldo - withdrawAmount, financesService.getBalance(id));
    }

    @Test
    void shouldReturnProperValue() {
        int id = 2;

        financesService.deposit(id, 100L);
        financesService.transfer(id, 50L);
        financesService.deposit(id, 10L);

        assertEquals(260L, financesService.getBalance(id));
    }
}


