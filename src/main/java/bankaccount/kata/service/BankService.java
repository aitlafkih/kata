package bankaccount.kata.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bankaccount.kata.model.BankAccount;
import bankaccount.kata.model.BankOperation;
import bankaccount.kata.model.OperationType;
import bankaccount.kata.repository.AccountRepository;
import bankaccount.kata.repository.OperationRepository;


@Service
@Transactional
public class BankService {
	
	private AccountRepository accountRepository;

	
	private OperationRepository operationRepository;
	
	
	@Autowired
	public BankService(AccountRepository accountRepository, OperationRepository operationRepository) {
		this.accountRepository = accountRepository;
		this.operationRepository = operationRepository;
	}
	
    /**
     * get account details
     * @param accountId
     * @return information account
     */	
	public BankAccount accountDetail(String accountId)  {
		Optional<BankAccount> account = accountRepository.findById(accountId);
		if (!account.isPresent())
			throw new RuntimeException("Account not Found");
		
		return account.get();
	}
	
	
    /**
     * add account 
     * @param BankAccount(accountId, balance)
     * @return account created
     */	
	public BankAccount addAccount(BankAccount account)  {
		return accountRepository.save(account);
	}
	
	
    /**
     * deposit amount in account
     * @param accountId
     * @param amount to deposit
     * @return account information
     */	
	public BankAccount deposit(String accountId, double amount) {
		BankOperation operation = createOperation(accountId, OperationType.DEPOSIT, amount);
		BankAccount account = accountRepository.findById(accountId).get();
		account.getOperations().add(operation);
		return account;
	}
	
    /**
     * create deposit and withdrawal operations 
     * @param accountId
     * @param type of operation (deposit/withdrawal)
     * @return operation information
     */	
	public BankOperation createOperation(String accountId, OperationType type, double amount)  {
		Optional<BankAccount> optionnalAccount = accountRepository.findById(accountId);
		if (!optionnalAccount.isPresent())
			throw new RuntimeException("Account not Found");
		BankAccount account = optionnalAccount.get();
		account.setBalance(account.getBalance() +  amount);
		BankOperation opeartion = BankOperation.builder().id(UUID.randomUUID().toString())
				.dateOPeration(LocalDateTime.now()).operationType(type).account(account).amount(amount).build();
		return operationRepository.save(opeartion);
	}

}
