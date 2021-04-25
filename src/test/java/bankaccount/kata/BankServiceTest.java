package bankaccount.kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import bankaccount.kata.model.BankAccount;
import bankaccount.kata.model.BankOperation;
import bankaccount.kata.model.OperationType;
import bankaccount.kata.repository.AccountRepository;
import bankaccount.kata.repository.OperationRepository;
import bankaccount.kata.service.BankService;

@DataJpaTest
public class BankServiceTest {

	BankAccount account;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private OperationRepository operationRepository;

	@InjectMocks
	private BankService bankService;

	@BeforeEach
	public void init() {
		bankService = new BankService(accountRepository, operationRepository);
		account = new BankAccount("account1", 700, null);
		accountRepository.save(account);
	}

	@Test
	void should_return_detail_account() {
		BankAccount accountFound = bankService.accountDetail("account1");
		Assertions.assertEquals(accountFound.getId(), "account1");
		Assertions.assertEquals(accountFound.getBalance(), account.getBalance());

	}

	@Test
	void should_create_deposit_operation_in_account() {
		BankOperation operation = bankService.createOperation("account1", OperationType.DEPOSIT, 300);
		Assertions.assertEquals(operation.getAmount(), 300);
		Assertions.assertEquals(operation.getOperationType(), OperationType.DEPOSIT);
		Assertions.assertEquals(operation.getAccount().getBalance(), 1000);
		Assertions.assertEquals(operation.getAccount().getId(), "account1");
	}

	@Test
	void should_throw_exception_account_not_found_when_deposit() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			bankService.deposit("account3", 300);
		});
	}

	@Test
	void should_create_withdrawal_operation_in_account() {
		BankOperation operation = bankService.createOperation("account1", OperationType.WITHDRAWAL, 300);
		Assertions.assertEquals(operation.getAmount(), 300);
		Assertions.assertEquals(operation.getOperationType(), OperationType.WITHDRAWAL);
		Assertions.assertEquals(operation.getAccount().getBalance(), 400);
		Assertions.assertEquals(operation.getAccount().getId(),"account1"); 
	}
	
	@Test
	void should_throw_exception_account_not_found_when_withdrawal() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			bankService.withdrawal("account3", 300);
		});
	}

	@Test
	void should_throw_exception_amount_greater_than_balance() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			bankService.withdrawal("account1", 1200);
		});
	}
	
}
