/**
 * 
 */
package main;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Employees;

/**
 * @author Ozgur
 *
 */
public class HqlQuery {

	public static void main(String[] args) {
		Query query;
		Employees employee;
		String hql;
		EntityManager entityManager = Persistence.createEntityManagerFactory("postgresdb").createEntityManager();

		/*
		 * findby id
		 */
		hql = "from Employees e where e.id =:id";
		query = entityManager.createQuery(hql);
		query.setParameter("id", 1L);
		employee = (Employees) query.getSingleResult();
		System.out.println(employee.getFirstName() + " " + employee.getLastName());

		/*
		 * findby firstname
		 */
		hql = "from Employees e where e.firstName LIKE :firstName";
		query = entityManager.createQuery(hql);
		query.setParameter("firstName", "Bill");
		employee = (Employees) query.getSingleResult();
		System.out.println(employee.getFirstName() + " " + employee.getLastName());

		/*
		 * findby ids
		 */
		hql = "from Employees e where e.id IN :ids";
		query = entityManager.createQuery(hql);
		query.setParameter("ids", Arrays.asList(1L, 2L));
		List<Employees> employeeList = query.getResultList();
		employeeList.stream().forEach(p -> System.out.println(p.getFirstName() + " " + p.getLastName()));
	}
}
