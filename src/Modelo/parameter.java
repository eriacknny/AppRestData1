package Modelo;

public class parameter {
	
	int id_parameter;
	int id_request;
	String field_name;
	String type;
	String value;
	int lengthMin;
	int lengthMax;
	boolean required;
	char status;
	
	
	public int getId_parameter() {
		return id_parameter;
	}
	public void setId_parameter(int id_parameter) {
		this.id_parameter = id_parameter;
	}
	public int getId_request() {
		return id_request;
	}
	public void setId_request(int id_request) {
		this.id_request = id_request;
	}
	public String getField_name() {
		return field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getLengthMin() {
		return lengthMin;
	}
	public void setLengthMin(int lengthMin) {
		this.lengthMin = lengthMin;
	}
	public int getLengthMax() {
		return lengthMax;
	}
	public void setLengthMax(int lengthMax) {
		this.lengthMax = lengthMax;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
	
}
