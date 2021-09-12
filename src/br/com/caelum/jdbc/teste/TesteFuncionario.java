package br.com.caelum.jdbc.teste;

import javax.swing.JOptionPane;

import br.com.caelum.jdbc.dao.FuncionarioDao;
import br.com.caelum.jdbc.modelo.Funcionario;

public class TesteFuncionario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Funcionario f= new Funcionario();
		
		f.setNome("Kevin");
		f.setSenha("ABC123outra");
		f.setId(3L);
		
		FuncionarioDao dao=new FuncionarioDao();
		
		//dao.adicionar(f);
	
		
		JOptionPane.showMessageDialog(null,dao.listar().toString() ,"Informção", JOptionPane.INFORMATION_MESSAGE);
		dao.atualizar(f);
		JOptionPane.showMessageDialog(null,dao.pesquisar(3) ,"Informção", JOptionPane.INFORMATION_MESSAGE);

	}

}
