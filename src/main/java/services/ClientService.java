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
}
