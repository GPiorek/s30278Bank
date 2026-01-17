package pio.grz.s30278bank.Finances;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pio.grz.s30278bank.Account.Account;
import pio.grz.s30278bank.Account.AccountService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FinancesServiceTest {

    @Mock
    private AccountService accountService;

    @Mock
    private Finances finances;

    @InjectMocks
    private FinancesService financesService;

    @Test
    void shouldDepositMoney() {
        int id = 1;
        Long amount = 50L;
        Account account = new Account(id, 100L);

        when(accountService.getUsersAccount(id)).thenReturn(account);
        financesService.deposit(id, amount);

        assertEquals(150L, account.getSaldo());
    }

    @Test
    void shouldTransferMoney() {
        int id = 1;
        Long amount = 50L;
        Account account = new Account(id, 100L);
        when(accountService.getUsersAccount(id)).thenReturn(account);

        financesService.transfer(id, amount);

        assertEquals(50L, account.getSaldo());
    }

    @Test
    void shouldThrowExceptionWhenTransferInsufficientFunds() {
        int id = 1;
        Long amount = 150L;
        Account account = new Account(id, 100L);

        when(accountService.getUsersAccount(id)).thenReturn(account);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> financesService.transfer(id, amount));
        assertEquals("Insufficient funds on account " + id, exception.getMessage());
        assertEquals(100L, account.getSaldo());
    }

    @Test
    void shouldGetBalance() {
        int id = 1;
        Long expectedSaldo = 100L;

        when(accountService.getUserSaldo(id)).thenReturn(expectedSaldo);

        Long actualSaldo = financesService.getBalance(id);
        assertEquals(expectedSaldo, actualSaldo);
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound() {
        int id = 1;

        when(accountService.getUsersAccount(id)).thenThrow(new RuntimeException("Account not found"));

        assertThrows(RuntimeException.class, () -> financesService.deposit(id, 50L));
    }
}