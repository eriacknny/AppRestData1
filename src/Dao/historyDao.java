package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.sun.xml.internal.fastinfoset.util.CharArray;

import Modelo.request;

public class historyDao {

public void obtenerRequest(ArrayList<request> listRequest){
		
		ArrayList<Integer> list_id = new ArrayList<>();
		ArrayList<String> list_name = new ArrayList<>();
		String sql = "select id,name,status from request where status = 'A'";
		Cdao ocado = new Cdao();
		
		ResultSet resultado = ocado.retorna_sql(sql);
		try {
			while(resultado.next())
				list_id.add(resultado.getInt("id"));
				list_name.add(resultado.getString("name"));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		for(int i=0; i<list_id.size();++i){
			request request = new request();
			request.setId_request(list_id.get(i));
			request.setName(list_name.get(i));
			request.setJson_request(null);
			request.setStatus('A');
			request.setTime(null);
			request.setUrl(null);
			listRequest.add(request);
		}
	
	}
}
