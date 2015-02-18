package Dao;

import Modelo.request;
import Modelo.response_expected;

public class response_expectedDao {
	public boolean registrarResponseExpected(request request,response_expected response_exp) {
		try {

			String sql = "insert into response_expected (id_request,name,json,status) values((select id from request where name = '"
					+ request.getName()
					+ "'),'"
					+ response_exp.getName()
					+ "','"
					+ response_exp.getJson_response_expected()
					+ "','"
					+ response_exp.getStatus() + "')";
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
