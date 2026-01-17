package pio.grz.s30278bank.Account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pio.grz.s30278bank.Finances.Finances;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

}