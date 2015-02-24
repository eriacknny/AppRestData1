package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import Modelo.request;
import Modelo.response_expected;

public class response_expectedDao {
	public boolean registrarResponseExpected(request request,
			response_expected response_exp) {
		try {

			String sql = "insert into response_expected (id_request,name,cod_status,json,status) values((select id from request where name = '"
					+ request.getName()
					+ "'),'"
					+ response_exp.getName()
					+ "','"
					+ response_exp.getCod_status()
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

	public void obtenerResponseExpected(ArrayList<String> listName,
			ArrayList<String> listJsonResponse_exp, ArrayList<String> listCodStatus) {
		int id = 0;
		Cdao dao = new Cdao();
		String sql = "select max(id) from request";
		ResultSet resultId = dao.retorna_sql(sql);
		try {
			while (resultId.next())
				id = resultId.getInt("max");
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("id:" + id);
		String sql1= "select rr.name, rr.json, rr.cod_status from request r join response_expected rr on rr.id_request = r.id where r.id ="+id+"";
		ResultSet resultado = dao.retorna_sql(sql1);
		try {
			while(resultado.next()){
				listName.add(resultado.getString("name"));
				listJsonResponse_exp.add(resultado.getString("json"));
				listCodStatus.add(resultado.getString("cod_status"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
