package edu.mtdev00.sistemapedido.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String street;
	private String addressSupplement;
	private String cep;
	private String city;
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "clients_id")
	private Client client;
	@Transient
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "order_id") // Ajuste o nome da coluna se necessário
	private Order order;;

	public Address() {
	}

	public Address(String street, String addressSupplement, String cep, String city) {
	    this.street = street;
	    this.addressSupplement = addressSupplement;
	    this.setCep(cep);
	    this.city = city;
	}

	public Address(Object object, String street, String addressSupplement2, String cep2, String city2, Client cli) {
	    this.street = street;
	    this.addressSupplement = addressSupplement2;  
	    this.setCep(cep2);
	    this.city = city2;
	    this.client = cli;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getAddressSupplement() {
		return addressSupplement;
	}

	public void setAddressSupplement(String addressSupplement) {
		this.addressSupplement = addressSupplement;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClients() {
		return client;
	}

	public void setClients(Client clients) {
		this.client = clients;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
		Address other = (Address) obj;
		return Objects.equals(id, other.id);
	}

}
