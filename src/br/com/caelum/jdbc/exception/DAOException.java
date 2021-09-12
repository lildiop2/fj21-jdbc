package br.com.caelum.jdbc.exception;

import java.sql.SQLException;

public class DAOException extends RuntimeException {

	 /**
     * importante caso a exceção seja serializada
     */
    private static final long serialVersionUID = 1149241039409861914L;
    
	public DAOException(SQLException e) {
		super("Erro no script SQL;",e);
	}
	
	
	

}
