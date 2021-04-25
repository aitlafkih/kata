package bankaccount.kata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OperationType {
	DEPOSIT("DEPOSIT"), WITHDRAWAL("WITHDRAWAL");
	@Getter
	String operationType;

}
