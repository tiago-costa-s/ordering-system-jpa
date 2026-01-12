package dao;

import java.util.List;

import entities.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import util.JPAUtil;

public class CategoryDAO {
	// ----------------- CREATE -----------------
	public Category insertCategory(Category category) {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(category);
			em.getTransaction().commit();

			System.out.println("Categoria criada com sucesso.");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao criar a categoria: " + e.getMessage());
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
	public Category updateCategory(Long id, Category newData) {

		EntityManager em = JPAUtil.getEntityManager();
		Category category = null;

		try {
			em.getTransaction().begin();
			category = em.find(Category.class, id);

			if (newData.getActive() != null) {
				category.setActive(newData.getActive());
			}

			if (newData.getName() != null) {
				category.setName(newData.getName());
			}

			em.getTransaction().commit();

			System.out.println("Categoria atualizada com sucesso.");

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Erro ao buscar categorias: " + e.getMessage());
		} finally {
			em.close();
		}

		return category;
	}
}
