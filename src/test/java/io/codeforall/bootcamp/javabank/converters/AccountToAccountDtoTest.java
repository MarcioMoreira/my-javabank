package io.codeforall.bootcamp.javabank.converters;

import io.codeforall.bootcamp.javabank.command.AccountDto;
import io.codeforall.bootcamp.javabank.persistence.model.account.Account;
import io.codeforall.bootcamp.javabank.persistence.model.account.CheckingAccount;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class AccountToAccountDtoTest {

    private AccountToAccountDto accountToAccountDto;

    @Before
    public void setup() {
        accountToAccountDto = new AccountToAccountDto();
    }

    @Test
    public void testConvert() {

        // setup
        int fakeAccountId = 9999;
        double fakeAccountBalance = 1000.00;

        Account fakeAccount = Mockito.spy(CheckingAccount.class);
        fakeAccount.setId(fakeAccountId);
        fakeAccount.credit(fakeAccountBalance);

        // exercise
        AccountDto accountDto = accountToAccountDto.convert(fakeAccount);

        // verify
        verify(fakeAccount).getId();
        verify(fakeAccount).getAccountType();
        verify(fakeAccount).getBalance();

        assertTrue(fakeAccountId == accountDto.getId());
        TestCase.assertEquals(fakeAccount.getAccountType(), accountDto.getType());
        assertEquals(fakeAccountBalance, accountDto.getBalance());
    }
}
