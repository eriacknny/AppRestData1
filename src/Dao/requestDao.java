package Dao;

import java.sql.ResultSet;

import Modelo.request;

public class requestDao {

	public boolean registrarRequest(request request) {
		try {
			String sql = "insert into request(url,json,name,time,status,type) values('"
					+ request.getUrl() + "','" + request.getJson_request()
					+ "','" + request.getName() + "','" + request.getTime()
					+ "','" + request.getStatus() + "','"+request.getType()+"')";
			Cdao ocado = new Cdao();
			if (ocado.ejecuta_sql(sql)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			System.out.println("" + ex.getStackTrace());
			return false;
		}
	}
	
	public Boolean obtenerRequest(request request){
		
		String sql = "select id from request where name ='"+request.getName()+"'";
		Cdao ocado = new Cdao();
		int row=0;
		ResultSet resultado = ocado.retorna_sql(sql);
		try {
			while(resultado.next())
				row = resultado.getRow();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(row !=0)
			return true;
		else
			return false;
			
	}
	
	
}
