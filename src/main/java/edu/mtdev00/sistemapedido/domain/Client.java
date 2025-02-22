package edu.mtdev00.sistemapedido.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Clients")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String name;
	@Column(unique = true)
	private String email;

	private Integer type;

	@Column(unique = true, nullable = true)
	private String cpf;
	@CNPJ
	@Column(unique = true, nullable = true)
	private String cnpj;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Address> address = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "Telephones")
	private Set<String> telephone = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();

	public Client() {

	}

	public Client(Long id, String name, String email, TypeClient type, String cpf, String cnpj) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.type = (type == null) ? null : type.getCod();
		this.cpf = cpf;
		this.cnpj = cnpj;
	}

	public Client(Long id, String name, String email, Integer type, String cpf, String cnpj, List<Address> address,
			Set<String> telephone, List<Order> orders) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.type = type;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.address = address;
		this.telephone = telephone;
		this.orders = orders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Set<String> getTelephone() {
		return telephone;
	}

	public void setTelephone(Set<String> telephone) {
		this.telephone = telephone;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrdes(List<Order> orders) {
		this.orders = orders;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
