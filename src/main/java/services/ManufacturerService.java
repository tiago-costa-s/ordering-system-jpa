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
			throw new IllegalArgumentException("O nome da fabricante é obrigatório.");
		}

		Manufacturer manufacturerValidate = manufacturerDAO.findByName(newManufacturer.getName());

		if (manufacturerValidate != null) {
			throw new IllegalArgumentException("já existe um Fabricante com esse nome.");
		}

		manufacturerDAO.insertManufacturer(newManufacturer);
	}

	// ----------------- READ -------------------
	public Manufacturer findById(Long id) {

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

	public void updateManufacturer(Long id, Manufacturer newData) {

		if (id == null) {
			throw new IllegalArgumentException("O id não pode ser nulo.");
		}

		if (newData == null) {
			throw new IllegalArgumentException("Dados para atualização não podem ser nulos.");
		}

		Manufacturer manufacturer = manufacturerDAO.findById(id);

		if (manufacturer == null) {
			throw new IllegalArgumentException("Fabricante não encontrada para o ID: " + id);
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

		manufacturerDAO.updateManufacturer(id, manufacturer);
	}

	public void deactivateManufacturer(Long id) {

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

	public void activateManufacturer(Long id) {

		if (id == null) {
			throw new IllegalArgumentException("O id não pode ser nulo.");
		}

		Manufacturer manufacturer = manufacturerDAO.findById(id);

		if (manufacturer == null) {
			throw new IllegalArgumentException("O Fabricante não foi encontrado para o ID: " + id);
		}

		if (manufacturer.getActive()) {
			throw new IllegalArgumentException("O fabricante já esta ativo.");
		}

		manufacturer.setActive(true);
		manufacturerDAO.updateManufacturer(id, manufacturer);
	}
}
