package services;

import java.util.List;

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

	public Product findProductById(Long id) {

		if (id == null) {
			throw new DomainException("O id do produto não pode ser nulo.");
		}

		Product productFromDb = productDAO.findById(id);

		if (productFromDb == null) {
			throw new DomainException("Produto não encontrado para o ID: " + id);
		}

		return productFromDb;
	}

	public List<Product> findProductAll() {

		return productDAO.findAll();
	}

	public void updateProduct(Long id, Product product) {

		if (id == null) {
			throw new DomainException("O ID não pode ser nulo.");
		}

		if (product == null) {
			throw new DomainException("Dados para atualização não podem ser nulos.");
		}

		Product productFromDb = productDAO.findById(id);

		if (productFromDb == null) {
			throw new DomainException("Produto não encontrado para o ID: " + id);
		}

		if (!productFromDb.getActive()) {
			throw new DomainException("O produto está inativo.");
		}

		if (product.getName() != null) {
			productFromDb.setName(product.getName());
		}

		if (product.getPrice() != null) {

			if (product.getPrice() < 0.0) {
				throw new DomainException("O preço não pode ser negativo.");
			}

			productFromDb.setPrice(product.getPrice());
		}

		if (product.getStock() != null) {

			if (product.getStock() < 0) {
				throw new DomainException("O estoque não pode ser nulo.");
			}

			productFromDb.setStock(product.getStock());
		}

		if (product.getDescription() != null) {
			productFromDb.setDescription(product.getDescription());
		}

		if (product.getManufacturer() != null) {

			Manufacturer manufacturerFromDb = manufacturerDAO.findById(product.getManufacturer().getId());

			if (manufacturerFromDb == null) {
				throw new DomainException("Fornecedor não encontrado.");
			}

			if (!manufacturerFromDb.getActive()) {
				throw new DomainException("Fornecedor está inativo.");
			}

			productFromDb.setManufacturer(manufacturerFromDb);
		}

		if (product.getCategory() != null) {

			Category categoryFromDb = categoryDAO.findById(product.getCategory().getId());

			if (categoryFromDb == null) {
				throw new DomainException("Categoria não encontrada.");
			}

			if (!categoryFromDb.getActive()) {
				throw new DomainException("Categoria está inativa.");
			}

			productFromDb.setCategory(categoryFromDb);
		}

		productDAO.update(productFromDb);
	}
}
