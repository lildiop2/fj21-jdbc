package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.exception.DAOException;
import br.com.caelum.jdbc.modelo.Contato;

public class ContatoDao {

	// a conexão com o banco de dados
	private Connection connection;

	public ContatoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	// adiciona um contato no banco
	public void adiciona(Contato contato) {
		String sql = "insert into contatos " + "(nome,email,endereco,dataNascimento)" + " values (?,?,?,?)";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	// retorna lista dos contatos
	public List<Contato> getLista() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from contatos");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto Contato
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				// montando a data através do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);
				// adicionando o objeto à lista
				contatos.add(contato);
			}
			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	// retorna lista dos contatos
	public List<Contato> getListaPersonalizada() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			Scanner scn = new Scanner(System.in);
			System.out.println("Escreva umas carateres que contem o que você está procurando: ");
			String sql = "select * from contatos where nome like '" + scn.next() + "%'";
			scn.close();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto Contato
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				// montando a data através do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);
				// adicionando o objeto à lista
				contatos.add(contato);
			}
			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	// retorna lista dos contatos
	public Contato pesquisar(int id) {
		try {
			String sql = "select * from contatos where id=" + id;
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			// criando o objeto Contato
			Contato contato = new Contato();
			while (rs.next()) {

				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				// montando a data através do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);

			}
			rs.close();
			stmt.close();
			return contato;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	// Altera contato
	public void altera(Contato contato) {
		String sql = "update contatos set nome=?, email=?," + "endereco=?, dataNascimento=? where id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			stmt.setLong(5, contato.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	// remove contato
	public void remove(Contato contato) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from contatos where id=?");
			stmt.setLong(1, contato.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
