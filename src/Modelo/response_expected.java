package Modelo;

import org.zkoss.json.JSONArray;

public class response_expected {
	
	int id_request_expected;
	int id_request;
	String json_response_expected;
	String name;
	char status;
	
	public int getId_request_expected() {
		return id_request_expected;
	}
	public void setId_request_expected(int id_request_expected) {
		this.id_request_expected = id_request_expected;
	}
	public int getId_request() {
		return id_request;
	}
	public void setId_request(int id_request) {
		this.id_request = id_request;
	}	
	public String getJson_response_expected() {
		return json_response_expected;
	}
	public void setJson_response_expected(String json_response_expected) {
		this.json_response_expected = json_response_expected;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}	
}
