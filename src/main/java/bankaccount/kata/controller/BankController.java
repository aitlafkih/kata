package bankaccount.kata.controller;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bankaccount.kata.model.BankAccount;
import bankaccount.kata.model.OperationRequest;
import bankaccount.kata.service.BankService;


@RestController
@RequestMapping("/bankapi/accounts")
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	@PutMapping(value = "{accountId}/deposit")
    public BankAccount deposit(@PathVariable String accountId,
                        @RequestBody OperationRequest amount) throws AccountNotFoundException {
       return bankService.deposit(accountId,amount.getAmount());
    }
	
	@PutMapping(value = "{accountId}/withdrawal")
    public BankAccount withdrawal(@PathVariable String accountId,
                        @RequestBody OperationRequest amount) throws AccountNotFoundException {
       return bankService.withdrawal(accountId,amount.getAmount());
    }

}
