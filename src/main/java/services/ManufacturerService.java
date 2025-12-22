package services;

import java.util.List;

import dao.ManufacturerDAO;
import entities.Manufacturer;

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

	public Manufacturer findManufacturerById(Long id) {

		if (id == null) {
			throw new IllegalArgumentException("O id do faricante não pode ser nulo");
		}

		Manufacturer manufacturer = manufacturerDAO.findById(id);

		if (manufacturer == null) {
			throw new IllegalArgumentException("Fabricante não enncontrado para o ID: " + id);
		}

		return manufacturer;
	}

	public List<Manufacturer> findAllManufacturers() {

		List<Manufacturer> manufacturers = manufacturerDAO.findAll();

		return manufacturers;
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

	public void deactiveManufacturer(Long id) {

		if (id == null) {
			throw new IllegalArgumentException("O id não pode ser nulo.");
		}

		Manufacturer manufacturer = manufacturerDAO.findById(id);

		if (manufacturer == null) {
			throw new IllegalArgumentException("Fabricante não encontrada para o ID: " + id);
		}

		if (manufacturer.getActive() != true) {
			throw new IllegalArgumentException("O fabricante ja esta inativado.");
		}

		if (!manufacturer.getProducts().isEmpty()) {
			throw new IllegalArgumentException("Fabricante não pode ser removida pois possui produto(s) associado(s).");
		}
		
		manufacturer.setActive(false);
		manufacturerDAO.updateManufacturer(id, manufacturer);
	}
}
