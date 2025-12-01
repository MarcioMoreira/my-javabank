package io.codeforall.bootcamp.javabank.converters;

import io.codeforall.bootcamp.javabank.dtos.AccountDto;
import io.codeforall.bootcamp.javabank.model.account.Account;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;


/**
 * A concrete converter class that transforms an {@link Account} entity into an {@link AccountDto}.
 */
@Component
public class AccountToAccountDto extends AbstractConverter<Account, AccountDto> {

    /**
     * Convert account into accountDto
     * @param account to take the info out of
     * @return the accountDto
     */
    @Override
    public AccountDto convert(Account account) {

        AccountDto accountDto = new AccountDto();

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedNumber = decimalFormat.format(account.getBalance());

        accountDto.setId(account.getId());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBalance(Double.parseDouble(formattedNumber));

        return accountDto;
    }
}
