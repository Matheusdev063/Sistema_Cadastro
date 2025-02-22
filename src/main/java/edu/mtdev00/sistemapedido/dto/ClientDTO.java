package edu.mtdev00.sistemapedido.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import edu.mtdev00.sistemapedido.domain.Client;

public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	@NotEmpty(message = "FIELD REQUIRED")
	private String name;
	@NotEmpty(message = "FIELD REQUIRED")
	@Email
	private String email;
	private Integer type;
	private String cpf;
	private String cnpj;
	private String descriptionType;

	public ClientDTO() {
	}

	public ClientDTO(Client obj, String description) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
		this.type = obj.getType();
		this.cpf = obj.getCpf();
		this.cnpj = obj.getCnpj();
		this.descriptionType = description;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDescriptionType() {
		return descriptionType;
	}

	public void setDescriptionType(String descriptionType) {
		this.descriptionType = descriptionType;
	}

}
