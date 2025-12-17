package services;

import dao.ManufacturerDAO;
import entities.Manufacturer;

public class ManufacturerService {

	private final ManufacturerDAO manufacturerDAO = new ManufacturerDAO();

	public void createManufacturer(Manufacturer newManufacturer) {

		if (newManufacturer == null) {
			throw new IllegalArgumentException("Fornecedor não pode ser nulo");
		}

		if (newManufacturer.getName() == null || newManufacturer.getName().trim().isEmpty()) {
			throw new IllegalArgumentException("O nome da fabricante é obrigatório.");
		}

		Manufacturer manufacturerValidate = manufacturerDAO.findByName(newManufacturer.getName());

		if (manufacturerValidate != null) {
			throw new IllegalArgumentException("já existe uma fornecedor com esse nome.");
		}

		manufacturerDAO.insertManufacturer(newManufacturer);
	}

}
