package br.com.xmacedo.multlayercache.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;

@RestController
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountId}/balance")
    public ResponseEntity<?> getAccountBalance(@PathVariable String accountId) {
        long start = System.currentTimeMillis();
        AccountDTO accountDTO = accountService.getAccountBalance(accountId);
        long end = System.currentTimeMillis();
        System.out.println("Response time: " + (end - start) + "ms");
        return new ResponseEntity(accountDTO, HttpStatus.OK);
    }

    @PutMapping("/{accountId}/balance")
    public ResponseEntity<?> updateAccountBalance(@PathVariable String accountId, @RequestBody BigDecimal newBalance) {

        accountService.updateAccountBalance(accountId, newBalance);

        return new ResponseEntity(accountService.getAccountBalance(accountId), HttpStatus.ACCEPTED);
    }

}
