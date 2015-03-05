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

public class historyDao {

public ArrayList<request>  obtenerRequest(){
		ArrayList<request> listRequest = new ArrayList<>();
		String sql = "select r.id,r.name,r.url, r.time from request r where status ='A'";
		Cdao ocado = new Cdao();
		
		ResultSet resultado = ocado.retorna_sql(sql);
		
		try {
			
			while (resultado.next()) {
				request request = new request();
				request.setId_request(resultado.getInt(1));
				request.setName(resultado.getString(2));
				request.setUrl(resultado.getString(3));
				request.setJson_request(null);
				request.setStatus('A');
				request.setTime(null);
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

}
