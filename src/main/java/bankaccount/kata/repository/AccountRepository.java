package bankaccount.kata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bankaccount.kata.model.BankAccount;


@Repository
public interface AccountRepository extends JpaRepository<BankAccount, String>{
}
