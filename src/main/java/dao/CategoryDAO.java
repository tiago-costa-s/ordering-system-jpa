package dao;

import java.util.List;

import entities.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import util.JPAUtil;

public class CategoryDAO {
	// ----------------- CREATE -----------------
	public Category save(Category category) {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(category);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			em.close();
		}

		return category;
	}

	// ----------------- READ -----------------
	public Category findById(Long id) {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			return em.find(Category.class, id);
		} finally {
			em.close();
		}
	}

	public List<Category> findAll() {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Category> findByName(String name) {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			return em.createQuery("SELECT c FROM Category c WHERE c.name  = :name", Category.class)
					.setParameter("name", name).getResultList();
		} finally {
			em.close();
		}
	}

	// ----------------- UPDATE -----------------
	public void update(Category newData) {
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		em.merge(newData);
		em.getTransaction().commit();
		
		em.close();
	}
}
