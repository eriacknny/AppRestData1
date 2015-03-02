package Dao;

import Modelo.parameter;
import Modelo.request;

public class parameterDao {
	public boolean registrarParameter(request request, parameter parameter) {

		try {

			String sql = "insert into parameter(id_request,field_value,type,value,length_min,length_max,required,status) values ((select id from request where name = '"
					+ request.getName()
					+ "'),'"
					+ parameter.getField_name()
					+ "','"
					+ parameter.getType()
					+ "','"
					+ parameter.getValue()
					+ "','"
					+ parameter.getLengthMin()
					+ "','"
					+ parameter.getLengthMax()
					+ "','"
					+ parameter.isRequired()
					+ "','"
					+ parameter.getStatus()
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
}
