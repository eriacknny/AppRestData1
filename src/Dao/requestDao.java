package Dao;

import Modelo.request;

public class requestDao {

	public boolean registrarRequest(request request) {
		try {
			String sql = "insert into request(url,json,name,time,status) values('"
					+ request.getUrl() + "','" + request.getJson_request()
					+ "','" + request.getName() + "','" + request.getTime()
					+ "','" + request.getStatus() + "')";
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
