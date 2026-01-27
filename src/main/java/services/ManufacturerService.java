package services;

import java.util.List;

import dao.ManufacturerDAO;
import entities.Manufacturer;
import exceptions.DomainException;
import jakarta.persistence.EntityNotFoundException;

public class ManufacturerService {

	private final ManufacturerDAO manufacturerDAO = new ManufacturerDAO();

	public void createManufacturer(Manufacturer newManufacturer) {

		if (newManufacturer == null) {
			throw new IllegalArgumentException("Fabricante não pode ser nulo");
		}

		if (newManufacturer.getName() == null || newManufacturer.getName().trim().isEmpty()) {
			throw new DomainException("O nome da fabricante é obrigatório.");
		}

		Manufacturer manufacturerValidate = manufacturerDAO.findByName(newManufacturer.getName());

		if (manufacturerValidate != null) {
			throw new DomainException("já existe um Fabricante com esse nome.");
		}

		manufacturerDAO.save(newManufacturer);
	}

	// ----------------- READ -------------------
	public Manufacturer findManufacturerById(Long id) {

		if (id == null) {
			throw new IllegalArgumentException("O id do fabricante não pode ser nulo.");
		}

		Manufacturer manufacturer = manufacturerDAO.findById(id);

		if (manufacturer == null) {
			throw new IllegalArgumentException("Fabricante não encontrado para o ID: " + id);
		}

		return manufacturer;
	}

	public List<Manufacturer> findAllManufacturers() {
		return manufacturerDAO.findAll();
	}

	public Manufacturer findManufacturerByName(String name) {

		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("O nome não pode ser nulo.");
		}

		return manufacturerDAO.findByName(name);
	}

	public void updateManufacturer(Long id, Manufacturer manufacturer) {

		if (id == null) {
			throw new IllegalArgumentException("O id não pode ser nulo.");
		}

		if (manufacturer == null) {
			throw new IllegalArgumentException("Dados para atualização não podem ser nulos.");
		}

		Manufacturer manufacturerFromDb = manufacturerDAO.findById(id);

		if (manufacturerFromDb == null) {
			throw new DomainException("Fabricante não encontrado para o ID: " + id);
		}

		if (manufacturer.getName() != null) {
			manufacturerFromDb.setName(manufacturer.getName());
		}

		if (manufacturer.getCnpj() != null) {
			manufacturerFromDb.setCnpj(manufacturer.getCnpj());
		}

		if (manufacturer.getPhone() != null) {
			manufacturerFromDb.setPhone(manufacturer.getPhone());
		}

		manufacturerDAO.update(manufacturerFromDb);
	}

	public void deactivate(Long id) {

		if (id == null) {
			throw new IllegalArgumentException("O id não pode ser nulo.");
		}

		Manufacturer manufacturer = manufacturerDAO.findById(id);

		if (manufacturer == null) {
			throw new DomainException("Fabricante não encontrado para o ID: " + id);
		}

		if (!manufacturer.getActive()) {
			throw new DomainException("O fabricante ja esta inativado.");
		}

		if (!manufacturer.getProducts().isEmpty()) {
			throw new DomainException("Fabricante não pode ser desativado pois possui produto(s) associado(s).");
		}

		manufacturer.setActive(false);
		manufacturerDAO.update(manufacturer);
	}

	public void activate(Long id) {

		if (id == null) {
			throw new IllegalArgumentException("O id não pode ser nulo.");
		}

		Manufacturer manufacturer = manufacturerDAO.findById(id);

		if (manufacturer == null) {
			throw new DomainException("Fabricante não foi encontrado para o ID: " + id);
		}

		if (manufacturer.getActive()) {
			throw new DomainException("Fabricante já esta ativo.");
		}

		manufacturer.setActive(true);
		manufacturerDAO.update(manufacturer);
	}
}
