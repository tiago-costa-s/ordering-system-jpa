package aplication;

import java.util.List;

import entities.Category;
import services.CategoryService;

public class Program {

	public static void main(String[] args) {

		CategoryService categoryService = new CategoryService();
	
		Category category = new Category(false, "eletronico importado");

		categoryService.deactive(1L);	
	
	}
}
