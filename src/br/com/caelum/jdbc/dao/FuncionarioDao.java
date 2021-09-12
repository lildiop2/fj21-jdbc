package br.com.caelum.jdbc.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.exception.DAOException;

import br.com.caelum.jdbc.modelo.Funcionario;

public class FuncionarioDao {

	// a conexão com o banco de dados
	private Connection connection;

	public FuncionarioDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	// adiciona um funcionario no banco
	public void adicionar(Funcionario funcionario) {
		String sql = "insert into funcionarios (nome,senha) values (?,?)";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getSenha());
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	// Listar Funcionarios
	public List<Funcionario> listar() {
		try {
			List<Funcionario> funcionarios = new ArrayList<Funcionario>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from funcionarios");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto Contato
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getLong("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setSenha("****");
				// adicionando o objeto à lista
				funcionarios.add(funcionario);
			}
			rs.close();
			stmt.close();
			return funcionarios;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	// buscar um funcionario por id

	public Funcionario pesquisar(int id) {
		try {
			String sql = "select * from funcionarios where id=" + id;
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			// criando o objeto Funcionario
			Funcionario funcionario= new Funcionario();
			while (rs.next()) {

				funcionario.setId(rs.getLong("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setSenha(rs.getString("senha"));

			}
			rs.close();
			stmt.close();
			return funcionario;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	// Atualizar funcionario
	public void atualizar(Funcionario funcionario) {
		String sql = "update funcionarios set nome=?, senha=? where id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getSenha());
			stmt.setLong(3, funcionario.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	// remove Funcionario
		public void remover(Funcionario funcionario) {
			try {
				PreparedStatement stmt = connection.prepareStatement("delete from funcionarios where id=?");
				stmt.setLong(1, funcionario.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}

}
