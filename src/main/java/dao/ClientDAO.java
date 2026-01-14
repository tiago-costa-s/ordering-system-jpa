package dao;

import java.util.List;

import javax.swing.JApplet;

import entities.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import util.JPAUtil;

public class ClientDAO {

	// ----------------- CREATE -----------------
	public Client save(Client client) {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(client);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			em.close();
		}

		return client;
	}

	// ----------------- READ -----------------
	public Client findById(Long id) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			return em.find(Client.class, id);
		} finally {
			em.close();
		}
	}

	public List<Client> findAll() {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			return em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Client> findByName(String name) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			return em.createQuery("SELECT c FROM Client c WHERE c.name = :name ", Client.class)
					.setParameter("name", name).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Client> findByPhone(String phone) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			return em.createQuery("SELECT c FROM Client c WHERE c.phone = :phone", Client.class)
					.setParameter("phone", phone).getResultList();
		} finally {
			em.close();
		}
	}

	public Client findByEmail(String email) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			return em.createQuery("SELECT c FROM Client c WHERE c.email = :email", Client.class)
					.setParameter("email", email).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	// ----------------- UPDATE -----------------
	public void update(Client newData) {

		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		em.merge(newData);
		em.getTransaction().commit();
		em.close();
	}

	// ----------------- DELETE -----------------
	public void deleteClient(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Client client = em.find(Client.class, id);

		if (client == null) {
			throw new IllegalArgumentException("Cliente não informado para o ID: " + id);
		}

		try {
			em.getTransaction().begin();
			em.remove(client);
			em.getTransaction().commit();
			System.out.println("Cliente removido com sucesso.");
		} catch (IllegalArgumentException e) {
			em.getTransaction().rollback();
			System.out.println("Erro ao remover o cliente: " + e.getMessage());
		} finally {
			em.close();
		}
	}
}
