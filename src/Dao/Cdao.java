package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Cdao {

	Connection cn;

	protected Connection Conectar() {

		String strHost;
		String strPort;
		String strDatabase;
		String strUser;
		String strPassword;
		String strCadenaConexion;

		try {
			strHost = "localhost";
			strPort = "5432";
			strDatabase = "rest";
			strUser = "postgres";
			strPassword = "1234";
			strCadenaConexion = "jdbc:postgresql://" + strHost + ":" + strPort
					+ "/" + strDatabase;
			Class.forName("org.postgresql.Driver");
			cn = DriverManager.getConnection(strCadenaConexion, strUser,
					strPassword);
			System.out.println("se entrego el driver");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return cn;
	}

	
	protected ResultSet retorna_sql(String COMANDO_SQL) {
		//PARA LISTAR
		Connection cn;
		Statement st;
		cn = Conectar();
		ResultSet resultado;
		try {
			st = cn.createStatement();
			resultado = st.executeQuery(COMANDO_SQL);
			return resultado;

		} catch (Exception e) {
			System.out.println("Error...." + e.toString());
			return null;
		}
	}
	
	
	protected boolean ejecuta_sql(String COMANDO_SQL){
        //PARA INSERTAR,MODIFICAR,ELIMINAR
        Connection cn;
        Statement st;
        try{
            cn = Conectar();
            st = cn.createStatement();
            st.executeUpdate(COMANDO_SQL);
            cn.close();
            st.close();
            return true;
        }catch(Exception ex){
            System.out.println("Error...."+ ex.toString());
            return false;
        }
    }
	
	
	 protected boolean ejecuta_transaccion(List COMANDO_SQL_TRANSACCION){
	        //PARA INSERTAR,MODIFICAR,ELIMINAR
	        Connection cn;
	        Statement st;
	        cn = Conectar();
	        try{
	            cn.setAutoCommit(false);
	            st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	            for(int i = 0; i < COMANDO_SQL_TRANSACCION.size(); i++){
	                st.executeUpdate((String)COMANDO_SQL_TRANSACCION.get(i));
	            }
	            cn.commit();
	            cn.close();
	            st.close();
	            return true;
	        }catch(Exception ex){
	            System.out.println("Error...."+ ex.toString());
	            try {
	                cn.rollback();
	                cn.close();
	                System.out.println("Transaccion Fallida... No ay Registro en la Base de Datos");
	                return false;
	            }catch (SQLException se) {
	                se.printStackTrace();
	                return false;
	            }
	        }
	    }
	
	
}
