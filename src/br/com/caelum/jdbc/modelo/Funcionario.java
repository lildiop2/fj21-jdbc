package br.com.caelum.jdbc.modelo;

public class Funcionario {
	
	//id (Long), nome, usuario e senha (String)
	private Long id;
	private String nome,senha;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Funcionario [ID=");
		builder.append(id);
		builder.append(", Nome=");
		builder.append(nome);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
