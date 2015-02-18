package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.zkoss.json.JSONArray;

import Modelo.request;
import Modelo.response_receved;

public class response_recevedDao {
	public boolean registrarResponseReceved(request request,
			response_receved response_rec) {
		try {

			String sql = "insert into response_receved (id_request, json, json_send, status_response, message, duration, status) values((select id from request where name = '"
					+ request.getName()
					+ "'),'"
					+ response_rec.getJson_response_receved()
					+ "','"
					+ response_rec.getJson_request()
					+ "','"
					+ response_rec.getStatus_response()
					+ "','"
					+ response_rec.getMessage()
					+ "','"
					+ response_rec.getDuration()
					+ "','"
					+ response_rec.getStatus() + "')";
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

	public void obtenerResponseReceved(ArrayList<String> listStatus,
			ArrayList<String> listMessage, ArrayList<String> listJsonR, ArrayList<String> listJsonSend) {
		String sql = "select rr.status_response, rr.message, rr.json,rr.json_send from request r join response_receved rr on rr.id_request = r.id where r.name = 'GetCountries'";
		Cdao dao = new Cdao();
		ResultSet resultado = dao.retorna_sql(sql);

		try {
			while (resultado.next()) {
				listStatus.add(resultado.getString("status_response"));
				listMessage.add(resultado.getString("message"));
				listJsonR.add(resultado.getString("json"));
				listJsonSend.add(resultado.getString("json_send"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}