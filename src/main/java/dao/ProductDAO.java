package dao;

import java.util.List;

import entities.Product;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

public class ProductDAO {

	// ----------------- CREATE -----------------
	public void insertProduct(Product product) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(product);
			em.getTransaction().commit();
			System.out.println("O produto foi criado com sucesso.");

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Erro ao criar o produto: " + e.getMessage());
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
	public void updateProduct(Long id, Product updatedProduct) {
		EntityManager em = JPAUtil.getEntityManager();

		try {

			if (id == null) {
				throw new IllegalArgumentException("O ID não pode ser nulo.");
			}

			em.getTransaction().begin();

			Product product = em.find(Product.class, id);

			if (product == null) {
				throw new IllegalArgumentException("Produto não encontrado para o ID: " + id);
			}

			if (updatedProduct == null) {
				throw new IllegalArgumentException("Os dados do produto não foram informados.");
			}

			if (updatedProduct.getName() != null) {
				product.setName(updatedProduct.getName());
			}

			if (updatedProduct.getPrice() != null) {
				product.setPrice(updatedProduct.getPrice());
			}

			if (updatedProduct.getStock() != null) {
				product.setStock(updatedProduct.getStock());
			}

			if (updatedProduct.getDescription() != null) {
				product.setDescription(updatedProduct.getDescription());
			}

			em.getTransaction().commit();
			System.out.println("Produto atualizado com sucesso. \n" + product);

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Erro ao atualizar o produto com o id: " + id + " - " + e.getMessage());
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
