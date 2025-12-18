package dao;

import java.util.List;

import entities.Manufacturer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import util.JPAUtil;

public class ManufacturerDAO {

	// ----------------- CREATE -----------------
	public void insertManufacturer(Manufacturer manufacturer) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(manufacturer);
			em.getTransaction().commit();
			System.out.println("Fornecedor criado com sucesso.");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Erro ao criar fornceedor: " + e.getMessage());
		} finally {
			em.close();
		}
	}

// ----------------- READ -------------------
	public Manufacturer findById(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Manufacturer manufacturer = null;

		if (id == null) {
			throw new IllegalArgumentException("Fornecerdor não encontrado para o id associado.");
		}

		try {
			manufacturer = em.find(Manufacturer.class, id);
			System.out.println("" + manufacturer);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao buscar o forncedor: " + e.getMessage());
		} finally {
			em.close();
		}

		return manufacturer;
	}

	public List<Manufacturer> findAll() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Manufacturer> manufacturerList = null;

		try {
			manufacturerList = em.createQuery("SELECT m FROM Manufacturer m", Manufacturer.class).getResultList();

		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao buscar os forncedores: " + e.getMessage());

		} finally {
			em.close();
		}

		return manufacturerList;
	}

	public Manufacturer findByName(String name) {

		EntityManager em = JPAUtil.getEntityManager();
		Manufacturer manufacturer = null;

		try {
			manufacturer = em.createQuery("SELECT m FROM manufacturer m WHERE m.name = :name", Manufacturer.class)
					.setParameter("name", name).getSingleResult();
		} catch (NoResultException e) {
			manufacturer = null;
		} catch (Exception e) {
			System.out.println("Erro ao buscar fabricante pelo nome: " + e.getMessage());
		} finally {
			em.close();
		}

		return manufacturer;
	}

// ----------------- UPDATE -----------------
	public void updateManufacturer(Long id, Manufacturer newData) {
		EntityManager em = JPAUtil.getEntityManager();
		Manufacturer manufacturer = null;

		if (id == null) {
			throw new IllegalArgumentException("O ID não pode estar vazio.");
		}

		if (newData == null) {
			throw new IllegalArgumentException("Forncedor não pode estar vazio.");
		}

		try {
			em.getTransaction().begin();
			manufacturer = em.find(Manufacturer.class, id);

			if (manufacturer == null) {
				throw new IllegalArgumentException("Fornecerdor não encontrado para o ID " + id);
			}

			if (newData.getName() != null) {
				manufacturer.setName(newData.getName());
			}

			if (newData.getCnpj() != null) {
				manufacturer.setCnpj(newData.getCnpj());
			}

			if (newData.getPhone() != null) {
				manufacturer.setPhone(newData.getPhone());
			}

			em.getTransaction().commit();
			System.out.println("Fornercedor atualizado com sucesso.");

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao tentar atualizar o fornecedor: " + e.getLocalizedMessage());
		} finally {
			em.close();
		}
	}

// ----------------- DELETE -----------------
	public void deleteManufacturer(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Manufacturer manufacturer = null;

		try {
			em.getTransaction().begin();
			manufacturer = em.find(Manufacturer.class, id);
			em.remove(manufacturer);
			em.getTransaction().commit();

			System.out.println("Fornercedor deletado com sucesso.");

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Erro ao deletar o fornercedor: " + e.getMessage());
		} finally {
			em.close();
		}
	}
}
