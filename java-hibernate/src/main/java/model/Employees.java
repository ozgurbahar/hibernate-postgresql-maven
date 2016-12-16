package model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Employees
 *
 */
@Entity
@Table(name = "employees")
@NamedQueries({
	@NamedQuery(name="Employees.findById", query="Select e from Employees e where e.id=:id"),
	@NamedQuery(name="Employees.findByName", query="Select e from Employees e where e.firstName LIKE :firstName"),
	@NamedQuery(name="Employees.findByIds", query="Select e from Employees e where e.id IN :idList")
})
public class Employees implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	public Employees() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
