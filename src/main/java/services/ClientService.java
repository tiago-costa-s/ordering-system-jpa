package services;

import java.util.List;

import dao.ClientDAO;
import entities.Client;

public class ClientService {

	private final ClientDAO clientDAO = new ClientDAO();

	public void createClient(Client newClient) {

		if (newClient == null) {
			throw new IllegalArgumentException("Cliente não pode ser nulo.");
		}

		Client validateEmail = clientDAO.findByEmail(newClient.getEmail());

		if (validateEmail != null) {
			throw new IllegalArgumentException("Existe cliente cadastrado com o e-mail informado.");
		}

		clientDAO.insertClient(newClient);
	}

	public Client findClientById(Long id) {

		if (id == null) {
			throw new IllegalArgumentException("O id não pode ser nulo.");
		}

		Client client = clientDAO.findById(id);

		if (client == null) {
			throw new IllegalArgumentException("Cliente não encontrado para o ID: " + id);
		}

		return client;
	}

	public List<Client> findAllClients() {

		List<Client> listClients = clientDAO.findAll();

		return listClients;
	}

	public List<Client> findClientByName(String name) {

		if (name == null || name.trim().isBlank()) {
			throw new IllegalArgumentException("O nome não pode ser nulo.");
		}

		List<Client> listClients = clientDAO.findByName(name);

		if (listClients == null) {
			throw new IllegalArgumentException("Client não encontrado para o nome informado.");
		}

		return listClients;
	}

	public List<Client> findClientByPhone(String phone) {

		if (phone == null || phone.trim().isBlank()) {
			throw new IllegalArgumentException("O telefone não pode ser nulo.");
		}

		List<Client> listClients = clientDAO.findByPhone(phone);

		if (listClients.isEmpty()) {
			throw new IllegalArgumentException("Cliente não encontrado para o telefone informado.");
		}

		return listClients;
	}
}
