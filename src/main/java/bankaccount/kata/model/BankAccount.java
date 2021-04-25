package bankaccount.kata.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BankAccount {
	
	@Id
	@Column
	private String id;
	
	@Column
	private double balance;
	
	@Column
	@OneToMany(mappedBy = "account")
    public List<BankOperation> operations = new ArrayList<>();

}
