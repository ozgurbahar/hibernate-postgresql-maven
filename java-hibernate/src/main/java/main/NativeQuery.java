/**
 * 
 */
package main;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Employees;

/**
 * @author Ozgur
 *
 */
public class NativeQuery {

	public static void main(String[] args) {
		EntityManager entityManager = Persistence.createEntityManagerFactory("postgresdb").createEntityManager();
		Employees employee;
		
		StringBuilder sbSql = new StringBuilder();
		sbSql.append("Select e.* from employees e where e.id = ?1");
		Query query = entityManager.createNativeQuery(sbSql.toString(), Employees.class);
		query.setParameter(1, 1L);
		employee = (Employees) query.getSingleResult();
		System.out.println(employee.getFirstName() + " " + employee.getLastName());
		
		entityManager.getTransaction().begin();
		
		sbSql.setLength(0);
		sbSql.append("Insert into employees(first_name, last_name) values('John', 'Smith')");
		query = entityManager.createNativeQuery(sbSql.toString(), Employees.class);
		query.executeUpdate();
		
		entityManager.getTransaction().commit();
		
		sbSql.setLength(0);
		sbSql.append("Select e.* from employees e where e.last_name like :lastName");
		query = entityManager.createNativeQuery(sbSql.toString(), Employees.class);
		query.setParameter("lastName", "Smith");
		employee = (Employees) query.getSingleResult();
		System.out.println(employee.getFirstName() + " " + employee.getLastName());
		
		entityManager.close();

	}

}
