package Dao;

import Modelo.header;
import Modelo.request;

public class headerDao {
	public boolean registrarHeader(header header, request request) {
		try {

			String sql = "insert into header(id_request,name,value) values((select id from request where name = '"
					+ request.getName()
					+ "'),'"
					+ header.getName()
					+ "','"
					+ header.getValue() + "')";
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
