package entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean active;
	private String name;
	private String phone;
	private String email;

	public Client() {
		this.active = true;
	}

	public Client(Boolean active, String name, String phone, String email) {
		super();
		setActive(active);
		setName(name);
		setPhone(phone);
		setEmail(email);
	}

	public Long getId() {
		return id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("O nome do cliente não pode estar vazio.");
		}

		this.name = name.replaceAll("[^a-zA-ZÀ-ÿ\\s]", "");
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if (phone == null || phone.trim().isEmpty()) {
			throw new IllegalArgumentException("O telefone não pode estar vazio.");
		}

		this.phone = phone.replaceAll("\\D", "");

		if (phone.length() < 10 || phone.length() > 11) {
			throw new IllegalArgumentException("O telefone deve conter 10 ou 11 dígitos numéricos.");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("O e-mail não pode ser vazio.");
		}

		email = email.trim();

		if (!email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$")) {
			throw new IllegalArgumentException("O e-mail informado é inválido.");
		}

		this.email = email.toLowerCase();
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + "]";
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
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}
}
