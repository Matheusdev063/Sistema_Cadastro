package edu.mtdev00.sistemapedido.service;

import java.util.List;

import javax.transaction.Transactional;

import edu.mtdev00.sistemapedido.service.treatmenterros.DataIntegrityException;
import edu.mtdev00.sistemapedido.repository.AddressRepository;
import edu.mtdev00.sistemapedido.repository.ClientRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import edu.mtdev00.sistemapedido.dto.ClientDTO;
import edu.mtdev00.sistemapedido.dto.ClientDTOComplet;
import edu.mtdev00.sistemapedido.domain.Address;
import edu.mtdev00.sistemapedido.domain.Client;
import edu.mtdev00.sistemapedido.domain.TypeClient;

@Service
public class ClientService {
	@Autowired
	ClientRepository clientRepo;

	@Autowired
	AddressRepository addresRepo;

	public Client findClient(Long id) {
		clientRepo.findById(id);
		return clientRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Client not found"));
	}

	public Page<Client> findPageClient(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepo.findAll(pageRequest);
	}

	@Transactional
	public Client insert(Client obj) {
		obj = clientRepo.save(obj);
		addresRepo.saveAll(obj.getAddress());
		return obj;
	}

	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), TypeClient.toEnum(objDto.getType()),
				objDto.getCpf(), objDto.getCnpj());
	}

	public List<Client> findAll() {
		return clientRepo.findAll();
	}

	public Client fromDTO(ClientDTOComplet objDto) {
		TypeClient type = TypeClient.toEnum(objDto.getType());
		String cpf = type.equals(TypeClient.FISICA) ? objDto.getCpf() : null;
		String cnpj = type.equals(TypeClient.JURIDICA) ? objDto.getCnpj() : null;
		Client cli = new Client(null, objDto.getName(), objDto.getEmail(), type, cpf, cnpj);

		Address addr = new Address(null, objDto.getStreet(), objDto.getAddressSupplement(), objDto.getCep(),
				objDto.getCity(), cli);
		cli.getAddress().add(addr);

		cli.getTelephone().add(objDto.getTelephone1());

		return cli;
	}

	public Client update(Client obj) {
		Client newObj = findClient(obj.getId());
		updateData(newObj, obj);
		return clientRepo.save(newObj);
	}

	@Transactional
	public void delete(Long id) {
		findClient(id);
		try {
			clientRepo.deleteById(id);
		} catch (DataIntegrityException e) {
			throw new DataIntegrityException("Não é possivel Excluir um Cliente que possui Pedidos Relacionados");
		}
	}

	public Client updatePatch(Client obj) {

		Client existingClient = clientRepo.findById(obj.getId()).orElse(null);

		if (existingClient == null) {
			return null;
		}
		if (obj.getName() != null) {
			existingClient.setName(obj.getName());
		}
		if (obj.getEmail() != null) {
			existingClient.setEmail(obj.getEmail());
		}
		if (obj.getCpf() != null) {
			existingClient.setCpf(obj.getCpf());
		}
		if (obj.getCnpj() != null) {
			existingClient.setCnpj(obj.getCnpj());
		}
		if (obj.getType() != null) {
			existingClient.setType(obj.getType());
		}
		return clientRepo.save(existingClient);
	}

	private void updateData(Client newObj, Client objDto) {
		newObj.setName(objDto.getName());
		newObj.setEmail(objDto.getEmail());
		newObj.setType(objDto.getType());
		newObj.setCnpj(objDto.getCnpj());
		newObj.setCpf(objDto.getCpf());
	}

	public Address findAndress(List<Address> addresses, Integer clientId) {
		return addresses.stream().filter(address -> address.getId().equals(clientId)) // Filtra endereços pelo ID do
																						// cliente
				.findFirst().orElse(null); // Retorna o primeiro endereço encontrado ou null se não houver
	}

}
