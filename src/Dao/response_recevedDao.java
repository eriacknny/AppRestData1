package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.zkoss.json.JSONArray;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import Modelo.request;
import Modelo.response_receved;

public class response_recevedDao {
	public boolean registrarResponseReceved(request request,
			response_receved response_rec) {
		try {

			String sql = "insert into response_receved (id_request, json, json_send, status_response, message, duration,result,status,time) values((select id from request where name = '"
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
					+ response_rec.getResult()
					+ "','"
					+ response_rec.getStatus()
					+ "','"
					+ response_rec.getTime()
					+ "')";
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
			ArrayList<String> listMessage, ArrayList<String> listJsonR,
			ArrayList<String> listJsonSend, ArrayList<String> listname,
			ArrayList<String> listurl, ArrayList<String> listResult) {
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
		String sql1 = "select r.url, r.name, rr.status_response, rr.message,rr.result, rr.json,rr.json_send from request r join response_receved rr on rr.id_request = r.id where r.id="
				+ id + "";

		ResultSet resultado = dao.retorna_sql(sql1);

		try {

			while (resultado.next()) {
				listurl.add(resultado.getString("url"));
				listname.add(resultado.getString("name"));
				listStatus.add(resultado.getString("status_response"));
				listMessage.add(resultado.getString("message"));
				listResult.add(resultado.getString("result"));
				listJsonR.add(resultado.getString("json"));
				listJsonSend.add(resultado.getString("json_send"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
