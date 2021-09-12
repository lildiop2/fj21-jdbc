package br.com.caelum.jdbc.teste;

import java.util.Calendar;

import javax.swing.JOptionPane;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaInsere {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		// pronto para gravar
//		Contato contato = new Contato();
//		contato.setNome("Abdul Kevin Alexis");
//		contato.setEmail("lildiop2@gmail.com");
//		contato.setEndereco("Rua Luiz Chagas de carvalho, 15");
//		contato.setDataNascimento(Calendar.getInstance());
		// grave nessa conexão!!!
		ContatoDao dao = new ContatoDao();
		// método elegante
//		dao.adiciona(contato);
		
//		System.out.println("Gravado!");
		
		
		JOptionPane.showMessageDialog(null, dao.pesquisar(3));
		
		
	}
	
	
	

}
