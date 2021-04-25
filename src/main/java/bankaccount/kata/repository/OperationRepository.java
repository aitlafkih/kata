package bankaccount.kata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bankaccount.kata.model.BankOperation;


@Repository
public interface OperationRepository extends JpaRepository<BankOperation, String> {

}
