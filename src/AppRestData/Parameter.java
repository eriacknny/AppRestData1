package AppRestData;

public class Parameter {
	private String field_name;
	private String data_type;
	private String value;
	private Integer length;
	private boolean required;
	
	
	public Parameter(String field_name, String data_type, String value, int length, boolean required) {
		this.field_name = field_name;
		this.data_type = data_type;
		this.value = value;
		this.length = length;
		this.required= required;
	}

	public String getField_name() {
		return field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
	
	
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	
	
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	
}
