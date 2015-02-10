package Modelo;

import java.util.Date;
import org.zkoss.json.JSONArray;

public class response_receved {
	
	int id_response_receved;
	int id_request;
	JSONArray json_response_receved;
	Date time_response_receved;
	Float duration;
	
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
	public JSONArray getJson_response_receved() {
		return json_response_receved;
	}
	public void setJson_response_receved(JSONArray json_response_receved) {
		this.json_response_receved = json_response_receved;
	}
	public Date getTime_response_receved() {
		return time_response_receved;
	}
	public void setTime_response_receved(Date time_response_receved) {
		this.time_response_receved = time_response_receved;
	}
	public Float getDuration() {
		return duration;
	}
	public void setDuration(Float duration) {
		this.duration = duration;
	} 
	
	
}
