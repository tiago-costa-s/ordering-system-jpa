package dao;

import java.util.List;

import entities.Product;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

public class ProductDAO {

	// ----------------- CREATE -----------------
	public void save(Product product) {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(product);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			em.close();
		}
	}

	// ----------------- READ -----------------
	public Product findById(Long id) {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			return em.find(Product.class, id);
		} finally {
			em.close();
		}
	}

	public List<Product> findAll() {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
		} finally {
			em.close();
		}
	}

	// ----------------- UPDATE -----------------
	public void update(Product product) {
		
		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(product);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			em.close();
		}
	}	
}
