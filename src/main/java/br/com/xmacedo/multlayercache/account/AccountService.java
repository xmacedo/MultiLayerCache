package br.com.xmacedo.multlayercache.account;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class AccountService {

    @Cacheable(value = "accountBalance", key = "#accountId")
    public AccountDTO getAccountBalance(String accountId) {
        System.out.println("Fetching from DB for account: " + accountId);
        AccountDTO accountDTO = new AccountDTO(accountId,new BigDecimal("1234.56")); // simulate DB call
        return accountDTO;
    }

    @CacheEvict(value = "accountBalance", key = "#accountId")
    public void updateAccountBalance(String accountId, BigDecimal newBalance) {
        System.out.println("Updating balance in DB for: " + accountId);
        // simulate DB update
    }
}
