package Dao;

import java.sql.ResultSet;

import Modelo.request;

public class requestDao {

	public boolean registrarRequest(request request) {
		try {
			String sql = "insert into request(url,json,name,time,status,type,listurl) values('"
					+ request.getUrl() + "','" + request.getJson_request()
					+ "','" + request.getName() + "','" + request.getTime()
					+ "','" + request.getStatus() + "','"+request.getType()+"','"+request.getListUrl()+"')";
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
	
	public boolean eliminarService(request request){
		try {
			String sql = "delete from response_receved where id_request = '"+request.getId_request()+"' ;delete from response_expected where id_request = '"+request.getId_request()+"';delete from parameter where id_request = '"+request.getId_request()+"';delete from header where id_request = '"+request.getId_request()+"';delete from request where id= '"+request.getId_request()+"'";
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
	
}
