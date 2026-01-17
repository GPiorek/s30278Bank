package pio.grz.s30278bank.Account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pio.grz.s30278bank.Finances.Finances;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private Finances finances;

    @InjectMocks
    private AccountService accountService;

    @Test
    void shouldGetUserSaldo() {
        int id = 1;
        Account account = new Account(id, 100L);
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));
        Long saldo = accountService.getUserSaldo(id);

        assertEquals(100L, saldo);
    }

    @Test
    void shouldRegisterAccount() {
        int id = 5;
        Long saldo = 300L;
        accountService.registerAccount(id, saldo);

        assertThat(accountRepository.findById(5).isPresent());
    }
}