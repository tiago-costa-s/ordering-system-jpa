package dao;

import java.util.List;

import javax.swing.JApplet;

import entities.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import util.JPAUtil;

public class ClientDAO {

	// ----------------- CREATE -----------------
	public Client insertClient(Client client) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(client);
			em.getTransaction().commit();
			System.out.println("Cliente criado com sucesso!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Erro ao criar cliente: " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			em.close();
		}
		return client;
	}

	// ----------------- READ -----------------
	public Client findById(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Client client = null;

		try {
			client = em.find(Client.class, id);
		} catch (Exception e) {
			System.out.println("Erro ao buscar o cliente: com ID: " + id + ": " + e.getMessage());
		} finally {
			em.close();
		}
		return client;
	}

	public List<Client> findAll() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Client> clientList = null;

		try {
			clientList = em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao buscar clientes:" + e.getMessage());
		} finally {
			em.close();
		}

		return clientList;
	}

	public Client findByName(String name) {
		EntityManager em = JPAUtil.getEntityManager();
		Client client = null;

		try {
			client = em.createQuery("SELECT c FROM Client c WHERE c.name = :name ", Client.class)
					.setParameter("name", name).getSingleResult();

		} catch (NoResultException e) {
			client = null;
		} finally {
			em.close();
		}
		return client;
	}

	public Client findByPhone(String phone) {
		EntityManager em = JPAUtil.getEntityManager();
		Client client = null;

		try {
			client = em.createQuery("SELECT c FROM Client c WHERE c.phone = :phone", Client.class)
					.setParameter("phone", phone).getSingleResult();
		} catch (NoResultException e) {
			client = null;
		} finally {
			em.close();
		}
		return client;
	}

	public Client findByEmail(String email) {
		EntityManager em = JPAUtil.getEntityManager();
		Client client = null;

		try {
			client = em.createQuery("SELECT c FROM Client c WHERE c.email = :email", Client.class)
					.setParameter("email", email).getSingleResult();
		} catch (NoResultException e) {
			client = null;
		} finally {
			em.close();
		}
		return client;
	}

	// ----------------- UPDATE -----------------
	public Client updateClient(Long id, Client newData) {
		EntityManager em = JPAUtil.getEntityManager();
		Client client = null;

		try {
			em.getTransaction().begin();
			client = em.find(Client.class, id);

			if (client == null) {
				throw new IllegalArgumentException("Cliente não informado para o ID: " + id);
			}

			if (newData.getName() != null) {
				client.setName(newData.getName());
			}

			if (newData.getPhone() != null) {
				client.setPhone(newData.getPhone());
			}

			if (newData.getEmail() != null) {
				client.setEmail(newData.getEmail());
			}

			em.getTransaction().commit();

			System.out.println("Cliente atualizado com sucesso.");

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Erro ao atualizar o cliente: " + e.getMessage());
		} finally {
			em.close();
		}

		return client;
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
