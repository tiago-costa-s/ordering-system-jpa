package dao;

import java.util.List;

import entities.Manufacturer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import util.JPAUtil;

public class ManufacturerDAO {

	// ----------------- CREATE -----------------
	public void save(Manufacturer manufacturer) {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(manufacturer);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			em.close();
		}
	}

// ----------------- READ -------------------
	public Manufacturer findById(Long id) {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			return em.find(Manufacturer.class, id);
		} finally {
			em.close();
		}
	}

	public List<Manufacturer> findAll() {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			return em.createQuery("SELECT m FROM Manufacturer m", Manufacturer.class).getResultList();
		} finally {
			em.close();
		}
	}

	public Manufacturer findByName(String name) {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			return em.createQuery("SELECT m FROM Manufacturer m WHERE m.name = :name", Manufacturer.class)
					.setParameter("name", name).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

// ----------------- UPDATE -----------------
	public void update(Manufacturer manufacturer) {
		
		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(manufacturer);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			em.close();
		}
	}
}
