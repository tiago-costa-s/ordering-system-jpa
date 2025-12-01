package entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean active;
	private String name;
	private Double price;
	private Integer stock;
	private String description;

	@ManyToOne
	@JoinColumn(name = "manufacturer_id")
	private Manufacturer manufacturer;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Product() {
		setActive(true);
	}

	public Product(Boolean active, String name, Double price, Integer stock, String description,
			Manufacturer manufacturer, Category category) {
		super();
		setActive(active);
		setName(name);
		setPrice(price);
		setStock(stock);
		setDescription(description);
		setManufacturer(manufacturer);
		setCategory(category);
	}

	public Long getId() {
		return id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		if (active == null) {
			throw new IllegalArgumentException("O status 'active' não pode ser nulo.");
		}

		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("O nome do produto não pode estar vazio.");
		}

		this.name = name.replaceAll("[^a-zA-Z0-9À-ÿ\\s]", "");
	}

	public Double getPrice() {
		return price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {

		if (stock == null) {
			throw new IllegalArgumentException("O estoque não pode ser nulo.");
		}

		if (stock < 0) {
			throw new IllegalArgumentException("O Estoque não pode ser negativo.");
		}

		this.stock = stock;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null || description.trim().isEmpty()) {
			throw new IllegalArgumentException("A descrição não pode ser vazia.");
		}

		description = description.trim();

		if (description.length() > 255) {
			throw new IllegalArgumentException("A descrição não pode ter mais de 255 caracteres.");
		}

		this.description = description;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		if (manufacturer == null) {
			throw new IllegalArgumentException("O fabricante não pode ser nulo.");
		}

		this.manufacturer = manufacturer;
	}

	public void setPrice(Double price) {
		if (price == null || price <= 0.0) {
			throw new IllegalArgumentException("O preço deve ser maior que R$ 0.00.");
		}

		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		if (category == null) {
			throw new IllegalArgumentException("A categoria não pode ser nula.");
		}

		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", active=" + active + ", name=" + name + ", price=" + price + ", stock=" + stock
				+ ", manufacturerId=" + (manufacturer != null ? manufacturer.getId() : null) + ", categoryId="
				+ (category != null ? category.getId() : null) + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

}
