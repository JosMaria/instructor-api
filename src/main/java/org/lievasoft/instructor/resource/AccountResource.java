package org.lievasoft.instructor.resource;

import org.lievasoft.instructor.dto.AccountRegisterDTO;
import org.lievasoft.instructor.dto.AccountRegisterResponse;
import org.lievasoft.instructor.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountResource {

    private final AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("register")
    public ResponseEntity<AccountRegisterResponse> register(@RequestBody AccountRegisterDTO accountRegisterDTO) {
        var accountRegisterResponse = accountService.register(accountRegisterDTO);
        return ResponseEntity.created(URI.create("/api/v1/accounts/register")).body(accountRegisterResponse);
    }
}
