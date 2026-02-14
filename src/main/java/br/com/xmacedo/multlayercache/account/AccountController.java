package br.com.xmacedo.multlayercache.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountId}/balance")
    public ResponseEntity<?> getAccountBalance(@PathVariable String accountId) {
        AccountDTO accountDTO = accountService.getAccountBalance(accountId);

        return new ResponseEntity(accountDTO, HttpStatus.OK);
    }

}
