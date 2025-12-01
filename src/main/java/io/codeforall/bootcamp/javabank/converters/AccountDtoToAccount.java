package io.codeforall.bootcamp.javabank.converters;

import io.codeforall.bootcamp.javabank.dtos.AccountDto;
import io.codeforall.bootcamp.javabank.factories.AccountFactory;
import io.codeforall.bootcamp.javabank.model.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

/**
 * A {@link Converter} implementation that converts {@link AccountDto} objects to {@link Account} objects.
 */
@Component
public class AccountDtoToAccount implements Converter<AccountDto, Account> {

    private AccountFactory accountFactory;


    /**
     * @see Converter#convert(Object)
     */
    @Override
    public Account convert(AccountDto accountDto) {

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedNumber = decimalFormat.format(accountDto.getBalance());

        Account account = accountFactory.createAccount(accountDto.getAccountType());
        account.credit(accountDto.getBalance() != 0.0 ? Double.parseDouble(formattedNumber) : 0);

        return account;
    }

    /**
     * Set the account factory
     * @param accountFactory to set
     */
    @Autowired
    public void setAccountFactory(AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
    }
}

