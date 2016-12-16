package main;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Employees;

/**
 * @author Ozgur
 *
 */
public class NamedQuery {

	public static void main(String[] args) {
		EntityManager entityManager = Persistence.createEntityManagerFactory("postgresdb").createEntityManager();
		Query query;
		
		/*
		 * findby id
		 */
		query = entityManager.createNamedQuery("Employees.findById", Employees.class).setParameter("id", 1L);
		Employees employee = (Employees) query.getSingleResult();
		System.out.println(employee.getFirstName() + " " + employee.getLastName());

		/*
		 * findby firstname
		 */
		query = entityManager.createNamedQuery("Employees.findByName", Employees.class).setParameter("firstName",
				"Steve");
		employee = (Employees) query.getSingleResult();
		System.out.println(employee.getFirstName() + " " + employee.getLastName());

		/*
		 * findby ids
		 */
		query = entityManager.createNamedQuery("Employees.findByIds", Employees.class).setParameter("idList",
				Arrays.asList(1L, 2L));
		List<Employees> employeeList = query.getResultList();
		employeeList.stream().forEach(p -> System.out.println(p.getFirstName() + " " + p.getLastName()));
		
		entityManager.close();
	}

}
