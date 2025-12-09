package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
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

	@Column(nullable = false)
	private Boolean active;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true, length = 14)
	private String cnpj;

	@Column(nullable = false)
	private String phone;

	@OneToMany(mappedBy = "manufacturer")
	List<Product> products = new ArrayList<>();

	public Manufacturer() {
		super();
		this.active = true;
	}

	public Manufacturer(Boolean active, String name, String cnpj, String phone) {
		super();
		this.active = (active != null ? active : true);
		setName(name);
		setCnpj(cnpj);
		setPhone(phone);
	}

	public Long getId() {
		return id;
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
			throw new IllegalArgumentException("O nome do fornecedor não pode estar vazio.");
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

		cnpj = cnpj.replaceAll("\\D", "");

		if (cnpj.length() != 14) {
			throw new IllegalArgumentException("O CNPJ deve conter 14 dígitos numéricos.");
		}

		this.cnpj = cnpj;
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
