package entities;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Manufacturer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean active;
	private String name;
	private String cnpj;
	private String phone;

	@OneToMany(mappedBy = "manufacturer")
	List<Product> products;

	public Manufacturer() {
		super();
	}

	public Manufacturer(Boolean active, String name, String cnpj, String phone) {
		super();
		this.active = active;
		this.name = name;
		this.cnpj = cnpj;
		this.phone = phone;
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
			throw new IllegalArgumentException("O status ativo não pode ser nulo");
		}
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {

		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("O nome do fornercedor não pode estar vazio.");
		}

		this.name = name.replaceAll("[^a-zA-ZÀ-ÿ\\s]", "");
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		if (cnpj == null || cnpj.trim().isEmpty()) {
			throw new IllegalArgumentException("O cnpj não pode estar vazio.");
		}
		this.cnpj = cnpj.replaceAll("\\D", "");
	}

	public String getPhone() {

		return phone;
	}

	public void setPhone(String phone) {
		if (phone == null || phone.trim().isEmpty()) {
			throw new IllegalArgumentException("O telefone não pode estar vazio");
		}
		this.phone = phone.replaceAll("\\D", "");
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Manufacturer [id=" + id + ", active=" + active + ", name=" + name + ", cnpj=" + cnpj + ", phone="
				+ phone + "]";
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
		Manufacturer other = (Manufacturer) obj;
		return Objects.equals(id, other.id);
	}

}
