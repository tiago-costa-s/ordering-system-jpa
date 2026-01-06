package services;

import dao.ClientDAO;
import entities.Client;

public class ClientService {

	private final ClientDAO clientDAO = new ClientDAO();

	public void createCliente(Client newClient) {

		if (newClient == null) {
			throw new IllegalArgumentException("Cliente não pode ser nulo.");
		}

		Client validateEmail = clientDAO.findByEmail(newClient.getEmail());

		if (validateEmail != null) {
			throw new IllegalArgumentException("Existe cliente cadastrado com o e-mail informado.");
		}

		clientDAO.insertClient(newClient);
	}
}
