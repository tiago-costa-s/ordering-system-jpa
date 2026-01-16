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
	public void update(Product newDate) {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(newDate);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			em.close();
		}
	}

	// ----------------- DELETE -----------------
	public void deleteProduct(Long id) {
		EntityManager em = JPAUtil.getEntityManager();

		if (id == null) {
			throw new IllegalArgumentException("O ID não poder nulo.");
		}

		try {
			Product product = em.find(Product.class, id);

			if (product == null) {
				throw new IllegalArgumentException("Produto não encontrado para o ID: " + id);
			}

			em.getTransaction().begin();
			em.remove(product);
			em.getTransaction().commit();
			System.out.println("Produto removido com sucesso.");

		} catch (Exception e) {
			System.out.println("Erro ao remover o produto" + e.getMessage());
		} finally {
			em.close();
		}
	}
}
