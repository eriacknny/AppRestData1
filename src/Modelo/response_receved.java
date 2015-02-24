package Modelo;

import java.util.Date;
import org.zkoss.json.JSONArray;

public class response_receved {
	
	int id_response_receved;
	int id_request;
	String json_request;
	String json_response_receved;
	int duration;
	String status_response;
	String message;
	String result;
	char status;
	
	
	public int getId_response_receved() {
		return id_response_receved;
	}
	public void setId_response_receved(int id_response_receved) {
		this.id_response_receved = id_response_receved;
	}
	public int getId_request() {
		return id_request;
	}
	public void setId_request(int id_request) {
		this.id_request = id_request;
	}

	public String getStatus_response() {
		return status_response;
	}
	public void setStatus_response(String status_response) {
		this.status_response = status_response;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getJson_response_receved() {
		return json_response_receved;
	}
	public void setJson_response_receved(String json_response_receved) {
		this.json_response_receved = json_response_receved;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getJson_request() {
		return json_request;
	}
	public void setJson_request(String json_request) {
		this.json_request = json_request;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	
}
