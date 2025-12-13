package services;

import dao.CategoryDAO;
import entities.Category;

public class CategoryService {

	public void createCategory(Category newCategory) {

		if (newCategory == null) {
			throw new IllegalArgumentException("Categoria não pode ser nula.");
		}

		CategoryDAO categoryDAO = new CategoryDAO();

		Category categoryValidate = categoryDAO.findByName(newCategory.getName());

		if (categoryValidate != null) {
			throw new IllegalArgumentException("já existe uma categoria com esse nome.");
		}

		categoryDAO.insertCategory(newCategory);
	}

	public Category findCategory(Long id) {

	}

	public Category findCategories() {
	}

	public void updateCategory(Long id, Category newData) {
	}

	public void deleteCategory(Long id) {
	}
}
