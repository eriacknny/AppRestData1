package Modelo;

import java.util.ArrayList;
import java.util.Date;

import org.zkoss.json.JSONArray;

public class request {
	int id_request;
	String url;
	JSONArray json_request;
	String name;
	Date time;
	
	public int getId_request() {
		return id_request;
	}
	public void setId_request(int id_request) {
		this.id_request = id_request;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public JSONArray getJson_request() {
		return json_request;
	}
	public void setJson_request(JSONArray json_request) {
		this.json_request = json_request;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
		
	
}
