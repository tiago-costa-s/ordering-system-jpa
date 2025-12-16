package aplication;

import java.util.List;

import dao.CategoryDAO;
import dao.ClientDAO;
import dao.ManufacturerDAO;
import dao.ProductDAO;
import entities.Category;
import entities.Client;
import entities.Manufacturer;
import entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Program {

	public static void main(String[] args) {

//		##### CLIENTE #####

//		Inserir Cliente
//		ClientDAO cd = new ClientDAO();
//		Client c = new Client(null, true, "Marvil Lucio", "313398-2020", "Marvil@gmail.com");
//		cd.insertClient(c);

//		Buscar por varios clientes
//		List<Client> listClients = (List<Client>) clientCrud.findClients();
//
//		for (Client x : listClients) {
//			System.out.println(x);
//		}
//      
//	    Buscar por um cliente
//		Client client = clientCrud.findClient(1L);//		
//		System.out.println(client);
//		
//		Atualizar cliente
//		Client newData = new Client();
//		newData.setName("Marvil Lucio");
//		
//		ClientDAO cd = new 	ClientDAO();
//		
//		Client update = cd.updateClient(8L, newData);
//		System.out.println(update);

//		Deletar cliente
//		ClientDAO cd = new ClientDAO();
//		cd.deleteClient(8L);

//		##### PRODUTO #####
		Product p = new Product();
		ProductDAO pd = new ProductDAO();

//		INSERIR PRODUTO
//		p.setId(null);
//		p.setName("Pendrive 64gb");
//		p.setPrice(25.00);
//		p.setStock(5);
//		p.setDescription("Alta taxa de transferencia de dados 280mb por segundo.");
//		ManufacturerDAO md = new ManufacturerDAO();
//		Manufacturer manufacturer = md.findManucturer(1L);
//		p.assignManufacturer(manufacturer);
//
//		pd.insertProduct(p);

//		BUSCAR PRODUTO
//		ProductDAO pd = new ProductDAO();
//		Product p = pd.findProduct(1L);
//		System.out.println(p);

//      ATUALIZAR PRODUTO
//		ProductDAO pd = new ProductDAO();
//		Product p = new Product();
//		p.setName("Pendrive");
//		pd.updateProduct(1L, p);
//		
//		DELETE PRODUTO		
//		ProductDAO pd = new ProductDAO();		
//		pd.deleteProduct(1L);

//		CRIAR FORNCEDOR

//
//		manufacturer.setActive(true);
//		manufacturer.setName("Tiago Costa$");
//		manufacturer.setCnpj(" 10000000000000000");
//		manufacturer.setPhone("3133982450");
//
//		manufacturerDAO.insertManufacturer(manufacturer);

//		BUSCAR FORNECEDOR
//		manufacturerDAO.findManucturer(1L);

//		BUSCAR VARIOS FORNERCEDORES
//		ManufacturerDAO md = new ManufacturerDAO();
//
//		List<Manufacturer> m = md.findManufacturer();
//
//		for (Manufacturer mList : m) {
//			System.out.println(mList); 
//		}

//		ATUALIZAR FORNECEDOR
//		ManufacturerDAO md = new ManufacturerDAO();
//		Manufacturer m = new Manufacturer();
//		m.setName("SUSUNG");
//		
//		md.updateManufacturer(1L, m);

//		DELETAR FORNERCEDOR
//		ManufacturerDAO md = new ManufacturerDAO();
//		Manufacturer m = new Manufacturer();
//		md.deleteManufacturer(7L);

//		BUSCAR CATEGORIA
		CategoryDAO cd = new CategoryDAO();
		Category c = new Category();
		List<Category> cList = cd.findByCategories();
		
//		BUSCAR CATEGORIAS
//		c.setName("Eletrodomestico");
//		c.setActive(true);
//		cd.insertCategory(c);
//		for(Category cat : cList) {
//			System.out.println(cat);
//		}
		
//		ATUALIZAR CATEGORIAS
		
//		c.setName("Eletronico");
//		cd.updateCategory(1L, c);
		
		
	}
}
