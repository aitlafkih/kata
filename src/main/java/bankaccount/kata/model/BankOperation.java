package bankaccount.kata.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class BankOperation {
	@Id
	@Column
	private String id;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column
	private LocalDateTime dateOPeration;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "account_id")
	private BankAccount account;
	
	@Enumerated(EnumType.STRING)
	@Column
	private OperationType operationType;
	
	@Column
	private double amount;

}
