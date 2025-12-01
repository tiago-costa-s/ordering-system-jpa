package entities;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean active;
	private String name;

	@OneToMany(mappedBy = "category")
	private List<Product> products;

	public Category() {
		this.active = true;
	}

	public Category(String name) {
		setName(name);
	}

	public Category(Boolean active, String name) {
		super();
		setActive(active);
		setName(name);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
			throw new IllegalArgumentException("O nome da categoria não pode estar vazio.");
		}

		this.name = name.replaceAll("[^a-zA-ZÀ-ÿ\\s]", "");
	}

	public List<Product> getProducts() {
		return products;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", active=" + active + ", name=" + name +"]";
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
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}

}
