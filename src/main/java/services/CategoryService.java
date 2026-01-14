package services;

import java.util.List;
import java.util.stream.Collectors;

import dao.CategoryDAO;
import entities.Category;

public class CategoryService {

	private final CategoryDAO categoryDAO = new CategoryDAO();

	public void createCategory(Category newCategory) {

		if (newCategory == null) {
			throw new IllegalArgumentException("Categoria não pode ser nula.");
		}

		if (newCategory.getName() == null || newCategory.getName().trim().isEmpty()) {
			throw new IllegalArgumentException("O nome da categoria é obrigatório.");
		}

		List<Category> categoryValidate = categoryDAO.findByName(newCategory.getName());

		if (!categoryValidate.isEmpty()) {
			throw new IllegalArgumentException("já existe uma categoria com esse nome.");
		}

		categoryDAO.save(newCategory);
	}

	public Category findCategoryById(Long id) {

		if (id == null) {
			throw new IllegalArgumentException("O id da categoria não pode ser nulo.");
		}

		Category category = categoryDAO.findById(id);

		if (category == null) {
			throw new IllegalArgumentException("Categoria não encontrada para o ID: " + id);
		}

		return category;
	}

	public List<Category> findAllCategories() {

		List<Category> listCategories = categoryDAO.findAll();

		return listCategories;
	}

	public void updateCategory(Long id, Category newData) {

		if (id == null) {
			throw new IllegalArgumentException("O id não pode ser nulo.");
		}

		if (newData == null) {
			throw new IllegalArgumentException("Dados para atualização não podem ser nulos.");
		}

		Category category = categoryDAO.findById(id);

		if (category == null) {
			throw new IllegalArgumentException("Categoria não encontrada para o ID: " + id);
		}

		if (newData.getName() != null) {

			List<Category> categoriesWithSameName = categoryDAO.findByName(newData.getName());

			if (!categoriesWithSameName.isEmpty() && !categoriesWithSameName.get(0).getId().equals(category.getId())) {
				throw new IllegalArgumentException("Já existe uma categoria com esse nome.");
			}

			category.setName(newData.getName());
		}

		categoryDAO.update(category);
	}

	public void deactiveCategory(Long id) {

		if (id == null) {
			throw new IllegalArgumentException("O id não pode ser nulo.");
		}

		Category category = categoryDAO.findById(id);

		if (category == null) {
			throw new IllegalArgumentException("Categoria não encontrada para o ID: " + id);
		}

		if (category.getActive() != true) {
			throw new IllegalArgumentException("Categoria ja esta inativa.");
		}

		if (!category.getProducts().isEmpty()) {
			throw new IllegalArgumentException(
					"Categoria não pode ser desativada pois possui produto(s) associado(s).");
		}

		category.setActive(false);

		categoryDAO.update(category);
	}

	public void activateCategory(Long id) {

		if (id == null) {
			throw new IllegalArgumentException("O id não pode ser nulo.");
		}

		Category category = categoryDAO.findById(id);

		if (category == null) {
			throw new IllegalArgumentException("Categoria não encontrada para o ID: " + id);
		}

		if (category.getActive()) {
			throw new IllegalArgumentException("A categoria já está ativa.");
		}

		category.setActive(true);
		categoryDAO.update(category);
	}
}
