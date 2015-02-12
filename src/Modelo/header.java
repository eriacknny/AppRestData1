package Modelo;

public class header {
	
	int id_header;
	int id_request;
	String name;
	String value;
	char status;
	
	public int getId_header() {
		return id_header;
	}
	public void setId_header(int id_header) {
		this.id_header = id_header;
	}
	public int getId_request() {
		return id_request;
	}
	public void setId_request(int id_request) {
		this.id_request = id_request;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
	
}
