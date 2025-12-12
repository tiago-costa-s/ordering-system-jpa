package services;

import dao.CategoryDAO;
import entities.Category;

public class CategoryService {

	public void createCategory(Category category) {

		CategoryDAO categoryDAO = new CategoryDAO();

		Category categoryValidate = categoryDAO.findCategory(category.getId());
		
		if(categoryValidate.getName().equals(category)){
			throw new IllegalArgumentException("já existe uma categoria com esse nome.");
		}

		if (category.getName() == null) {
			throw new IllegalArgumentException("O nome não pode ser nulo");
		}

		if (category.getName()) {
		}

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
