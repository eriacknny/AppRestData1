package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.sun.xml.internal.fastinfoset.util.CharArray;

import Modelo.header;
import Modelo.parameter;
import Modelo.request;
import Modelo.response_expected;
import Modelo.response_receved;

public class historyDao {

public ArrayList<request>  obtenerRequest(){
		ArrayList<request> listRequest = new ArrayList<>();
		String sql = "select r.id,r.name,r.url, r.time,r.type from request r where status ='A'";
		Cdao ocado = new Cdao();
		
		ResultSet resultado = ocado.retorna_sql(sql);
		
		try {
			
			while (resultado.next()) {
				request request = new request();
				request.setId_request(resultado.getInt(1));
				request.setName(resultado.getString(2));
				request.setUrl(resultado.getString(3));
				request.setJson_request(null);
				request.setTime(resultado.getTimestamp(4));
				request.setType(resultado.getString(5));
				request.setStatus('A');				
				listRequest.add(request);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listRequest;
	}


public ArrayList<header> obtenerHeader(int id_request){
	ArrayList<header> listHeader = new ArrayList<>();
	
	String sql = "select id, id_request, name, value from header where id_request = "+id_request+" and status ='A'";
	Cdao ocado = new Cdao();
	ResultSet resultado = ocado.retorna_sql(sql);
	
	try {
		while (resultado.next()) {
			header header = new header();
			header.setId_header(resultado.getInt(1));
			header.setId_request(resultado.getInt(2));
			header.setName(resultado.getString(3));
			header.setValue(resultado.getString(4));
			header.setStatus('A');
			listHeader.add(header);
	
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return listHeader;
}

public ArrayList<parameter> obtenerParametro(int id_request){
	ArrayList<parameter> listParameter = new ArrayList<>();

	
	String sql = "select id, id_request,field_value,type,length_min,length_max,required,value from parameter where id_request = "+id_request+" and status ='A'";
	Cdao ocado = new Cdao();
	ResultSet resultado = ocado.retorna_sql(sql);
	try {
		
		while (resultado.next()) {
			parameter parameter = new parameter();
			parameter.setId_parameter(resultado.getInt(1));
			parameter.setId_request(resultado.getInt(2));
			parameter.setField_name(resultado.getString(3));
			parameter.setType(resultado.getString(4));
			parameter.setLengthMin(resultado.getInt(5));
			parameter.setLengthMax(resultado.getInt(6));
			parameter.setRequired(resultado.getBoolean(7));
			parameter.setValue(resultado.getString(8));
			parameter.setStatus('A');
			listParameter.add(parameter);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return listParameter;
}



	public ArrayList<response_expected> obtenerResponseExpected(int id_request) {
		ArrayList<response_expected> listResponseE = new ArrayList<>();
		String sql = "select id,id_request,name,json,cod_status from response_expected where id_request = "
				+ id_request + " and status ='A'";
		Cdao ocado = new Cdao();
		ResultSet resultado = ocado.retorna_sql(sql);

		try {

			while (resultado.next()) {
				response_expected re = new response_expected();
				re.setId_request_expected(resultado.getInt(1));
				re.setId_request(resultado.getInt(2));
				re.setName(resultado.getString(3));
				re.setJson_response_expected(resultado.getString(4));
				re.setCod_status(resultado.getString(5));
				re.setStatus('A');
				listResponseE.add(re);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listResponseE;
	}

	public ArrayList<response_receved> obtenerResponseReceived(int id_request) {
		ArrayList<response_receved> listRR = new ArrayList<>();
		String sql = "select id,id_request,status_response,message,json_send,json,result,time from response_receved where id_request = "
				+ id_request + " and status='A'";
		
		Cdao ocado = new Cdao();
		ResultSet resultado = ocado.retorna_sql(sql);
		
		try {

			while (resultado.next()) {
				response_receved rr = new response_receved();
				rr.setId_response_receved(resultado.getInt(1));
				rr.setId_request(resultado.getInt(2));
				rr.setStatus_response(resultado.getString(3));
				rr.setMessage(resultado.getString(4));
				rr.setJson_request(resultado.getString(5));
				rr.setJson_response_receved(resultado.getString(6));
				rr.setResult(resultado.getString(7));
				rr.setTime(resultado.getTimestamp(8));
				rr.setStatus('A');
				listRR.add(rr);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listRR;
	}
}
