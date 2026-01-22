package services;

import dao.CategoryDAO;
import dao.ManufacturerDAO;
import dao.ProductDAO;
import entities.Category;
import entities.Manufacturer;
import entities.Product;
import exceptions.DomainException;

public class ProductService {

	final public ProductDAO productDAO = new ProductDAO();
	final public ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
	final public CategoryDAO categoryDAO = new CategoryDAO();

	public void createProduct(Product product) {

		if (product == null) {
			throw new IllegalArgumentException("Produto não pode ser nulo.");
		}

		if (product.getName() == null || product.getName().trim().isEmpty()) {
			throw new DomainException("O nome do produto é obrigatorio.");
		}

		if (product.getPrice() == null) {
			throw new DomainException("O preco do produto é obrigatorio.");
		}

		if (product.getPrice() < 0.0) {
			throw new DomainException("O preço do produto não pode ser negativo.");
		}

		if (product.getStock() == null) {
			throw new DomainException("O estoque é obrigatorio.");
		}

		if (product.getStock() < 0) {
			throw new DomainException("O estoque não pode ser negativo.");
		}

		if (product.getDescription() == null || product.getDescription().trim().isEmpty()) {
			throw new DomainException("A descrição é obrigatoria.");
		}

		if (product.getManufacturer() == null) {
			throw new DomainException("O fornecerdor é obrigatorio.");
		}

		Manufacturer manufacturerFromDb = manufacturerDAO.findById(product.getManufacturer().getId());

		if (manufacturerFromDb == null) {
			throw new DomainException("Fornecedor não encontrado para o ID: " + product.getManufacturer().getId());
		}

		if (!manufacturerFromDb.getActive()) {
			throw new DomainException("O fornecedor está inativo.");
		}

		product.setManufacturer(manufacturerFromDb);

		if (product.getCategory() == null) {
			throw new DomainException("A categoria é obrigatória.");
		}

		Category categoryFromDb = categoryDAO.findById(product.getCategory().getId());

		if (categoryFromDb == null) {
			throw new DomainException("Categoria não encontrada para o ID: " + product.getCategory().getId());
		}

		if (!categoryFromDb.getActive()) {
			throw new DomainException("A Categoria está inativa.");
		}

		product.setCategory(categoryFromDb);	
		productDAO.save(product);
	}
}
