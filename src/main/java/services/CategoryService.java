package services;

import java.util.List;

import dao.CategoryDAO;
import entities.Category;

public class CategoryService {
	CategoryDAO categoryDAO = new CategoryDAO();

	public void createCategory(Category newCategory) {

		if (newCategory == null) {
			throw new IllegalArgumentException("Categoria não pode ser nula.");
		}

		Category categoryValidate = categoryDAO.findByName(newCategory.getName());

		if (categoryValidate != null) {
			throw new IllegalArgumentException("já existe uma categoria com esse nome.");
		}

		categoryDAO.insertCategory(newCategory);
	}

	public Category findCategory(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("O id da categoria não pode ser nulo.");
		}

		Category category = categoryDAO.findByCategory(id);

		if (category == null) {
			throw new IllegalArgumentException("Categoria não encontrada para o ID: " + id);
		}

		return category;
	}

	public List<Category> findCategories() {
		List<Category> listCategories = null;

		listCategories = categoryDAO.findByCategories();

		if (listCategories.isEmpty()) {
			throw new IllegalArgumentException("Categorias não encotradas. ");
		}

		return listCategories;
	}

	public void updateCategory(Long id, Category newData) {

		if (id == null) {
			throw new IllegalArgumentException("O id não pode ser nulo.");
		}

		if (newData == null) {
			throw new IllegalArgumentException("Dados para atualização nã pdem ser nulos.");
		}

		Category category = categoryDAO.findByCategory(id);

		if (category == null) {
			throw new IllegalArgumentException("Categoria não encontrada para o ID: " + id);
		}

		if (newData.getName() != null) {
			category.setName(newData.getName());
		}

		categoryDAO.updateCategory(id, category);
	}

	public void deleteCategory(Long id) {
	}
}
