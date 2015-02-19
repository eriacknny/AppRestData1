package AppRestData;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.fluttercode.datafactory.impl.DataFactory;
import org.zkoss.json.JSONArray;
import org.zkoss.json.JSONObject;
import org.zkoss.zhtml.Button;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.ext.Disable;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import AppRestData.TestCase;
import Dao.headerDao;
import Dao.requestDao;
import Dao.response_expectedDao;
import Dao.response_recevedDao;
import Modelo.header;
import Modelo.request;
import Modelo.response_expected;
import Modelo.response_receved;

public class AppRestDataController extends SelectorComposer<Component> {

	@Wire
	private Listbox setData;
	@Wire
	private Listbox set_headers;
	@Wire
	private Listbox set_response_expected;
	@Wire
	private Button add_item;
	@Wire
	private Button detete_item;
	@Wire
	private Button button_generate;
	@Wire
	private Button button_cancel;
	@Wire
	private Button button_result;
	@Wire
	private Textbox text_delimiter;
	@Wire
	private Textbox text_path;
	@Wire
	private Textbox text_row;
	@Wire
	private Textbox textFieldName;
	@Wire
	private Combobox comboType;
	@Wire
	private Textbox textValue;
	@Wire
	private Checkbox checkRequired;
	@Wire
	private Datebox dateBox;
	@Wire
	private Listbox listboxLenth;
	@Wire
	private Intbox intboxMin;
	@Wire
	private Intbox intboxMax;
	@Wire
	private Tabbox tabbox;
	@Wire
	private Tab tab_csv;
	@Wire
	private Tab tab_exel;
	@Wire
	private Tab tab_json;
	@Wire
	private Textbox text_header;
	@Wire
	private Textbox text_value;
	@Wire
	private Button add_item_header;
	@Wire
	private Button detele_item_header;
	@Wire
	private Textbox text_url;
	@Wire
	private Textbox text_nameService;
	@Wire
	private Textbox text_name_response;
	@Wire
	private Textbox text_json_response;
	@Wire
	private Button add_item_response;
	@Wire
	private Button detele_item_response;
	
	//------------------------------------------------------------

	

	// Metodo para bloquear el campo longitud que el caso de que el tipo de
	// valor no amerite longitud
	@Listen("onSelect = #comboType")
	public void selectCamposSinLongitud() {
		if ((comboType.getValue().equals("FirstName"))
				|| (comboType.getValue().equals("LastName"))
				|| (comboType.getValue().equals("Email"))
				|| (comboType.getValue().equals("BirthData"))
				|| (comboType.getValue().equals("Address"))
				|| (comboType.getValue().equals("City"))) {

			textValue.setDisabled(false);
			// textLength.setValue("null");
			// textLength.setDisabled(true);
			dateBox.setDisabled(true);
			listboxLenth.setVisible(false);

		} else {

			if (comboType.getValue().equals("Fecha DD/MM/YYYY")
					|| comboType.getValue().equals("Fecha DD-MM-YYYY")
					|| comboType.getValue().equals("Fecha MM/DD/YYYY")
					|| comboType.getValue().equals("Fecha MM-DD-YYYY")
					|| comboType.getValue().equals("Fecha YYYY/MM/DD")
					|| comboType.getValue().equals("Fecha YYYY-MM-DD")) {
				dateBox.setDisabled(false);
				textValue.setDisabled(true);
				// textLength.setDisabled(true);
				// textLength.setValue("null");
				listboxLenth.setVisible(false);

				if (comboType.getValue().equals("Fecha MM/DD/YYYY"))
					dateBox.setFormat("MM/dd/yyyy");
				if (comboType.getValue().equals("Fecha DD-MM-YYYY"))
					dateBox.setFormat("MM-dd-yyyy");
				if (comboType.getValue().equals("Fecha YYYY/MM/DD"))
					dateBox.setFormat("yyyy/MM/dd");
				if (comboType.getValue().equals("Fecha YYYY-MM-DD"))
					dateBox.setFormat("yyyy-MM-dd");
				if (comboType.getValue().equals("Fecha DD/MM/YYYY"))
					dateBox.setFormat("dd/MM/yyyy");
				if (comboType.getValue().equals("Fecha DD-MM-YYYY"))
					dateBox.setFormat("dd/MM/yyyy");
			} else {
				listboxLenth.setVisible(true);
				// textLength.setValue("null");
				// textLength.setDisabled(true);
				dateBox.setDisabled(true);
				textValue.setDisabled(false);
			}
		}

	}


	@Listen("onClick = #add_item_header ")
	public void addItemHeader() {
		Listitem item = new Listitem();
		Listcell cellHeader = new Listcell(text_header.getValue());
		Listcell cellValue = new Listcell(text_value.getValue());
		item.appendChild(cellHeader);
		item.appendChild(cellValue);
		set_headers.appendChild(item);
		set_headers.setSelectedItem(item);
		limpiar1();
	}

	@Listen("onClick = #detele_item_header ")
	public void deleteItemHeader() {
		int index = set_headers.getSelectedIndex();
		System.out.println(index);
		if (index >= 0) {
			// remove the selected item
			set_headers.removeItemAt(index);
		} else {
			// a easy way to show a message to user
			alert("Please select an item first!");
		}
	}
	
	@Listen("onClick = #add_item_response")
	public void addItemResponse(){
		Listitem item = new Listitem();
		Listcell cellresponse = new Listcell(text_name_response.getValue());
		Listcell celljson = new Listcell(text_json_response.getValue());
		item.appendChild(cellresponse);
		item.appendChild(celljson);
		set_response_expected.appendChild(item);
		set_response_expected.setSelectedItem(item);
		limpiar2();
	}
	
	@Listen("onClick = #detele_item_response")
	public void deleteItemResponse(){
		int index = set_response_expected.getSelectedIndex();
		System.out.println(index);
		if (index >= 0) {
			// remove the selected item
			set_response_expected.removeItemAt(index);
		} else {
			// a easy way to show a message to user
			alert("Please select an item first!");
		}
	}
	
	

	// Metodo para agragar una fila a la tabla
	@Listen("onClick = #add_item")
	public void addItem() {

		if ((comboType.getValue().equals("FirstName"))
				|| (comboType.getValue().equals("LastName"))
				|| (comboType.getValue().equals("Email"))
				|| (comboType.getValue().equals("BirthData"))
				|| (comboType.getValue().equals("Address"))
				|| (comboType.getValue().equals("City"))
				|| comboType.getValue().equals("Fecha DD/MM/YYYY")
				|| comboType.getValue().equals("Fecha DD-MM-YYYY")
				|| comboType.getValue().equals("Fecha MM/DD/YYYY")
				|| comboType.getValue().equals("Fecha MM-DD-YYYY")
				|| comboType.getValue().equals("Fecha YYYY/MM/DD")
				|| comboType.getValue().equals("Fecha YYYY-MM-DD")) {
			Date fecha = dateBox.getValue();
			DateFormat dateFormat = new SimpleDateFormat(dateBox.getFormat());
			String valorFecha = "";
			Listitem item = new Listitem();
			Listcell cellname = new Listcell(textFieldName.getValue());
			Listcell celltype = new Listcell(comboType.getValue());
			Listcell cellvalue = null;
			if (textValue.getValue().equals("")) {
				valorFecha = dateFormat.format(fecha);
				cellvalue = new Listcell(valorFecha);
			} else
				cellvalue = new Listcell(textValue.getValue());

			Listcell celllengthMin = new Listcell("null");
			Listcell celllengthMax = new Listcell("null");
			boolean required = checkRequired.isChecked();
			String strRequired = Boolean.toString(required);
			Listcell cellrequired = new Listcell(strRequired);
			item.appendChild(cellname);
			item.appendChild(celltype);
			item.appendChild(cellvalue);
			item.appendChild(celllengthMin);
			item.appendChild(celllengthMax);
			item.appendChild(cellrequired);
			setData.appendChild(item);
			setData.setSelectedItem(item);
			limpiar();
		} else {
			Listitem item = new Listitem();
			Listcell cellname = new Listcell(textFieldName.getValue());
			Listcell celltype = new Listcell(comboType.getValue());
			Listcell cellvalue = new Listcell(textValue.getValue());
			Listcell celllengthMin = null;
			Listcell celllengthMax = null;
			if (intboxMin.getValue() != null && intboxMax.getValue() != null) {
				celllengthMin = new Listcell(intboxMin.getValue().toString());
				celllengthMax = new Listcell(intboxMax.getValue().toString());
			} else {
				if (intboxMin.getValue() == null
						&& intboxMax.getValue() != null) {
					celllengthMin = new Listcell("null");
					celllengthMax = new Listcell(intboxMax.getValue()
							.toString());
				} else {
					celllengthMin = new Listcell(intboxMin.getValue()
							.toString());
					celllengthMax = new Listcell("null");
				}
			}

			boolean required = checkRequired.isChecked();
			String strRequired = Boolean.toString(required);
			Listcell cellrequired = new Listcell(strRequired);
			item.appendChild(cellname);
			item.appendChild(celltype);
			item.appendChild(cellvalue);
			item.appendChild(celllengthMin);
			item.appendChild(celllengthMax);
			item.appendChild(cellrequired);
			setData.appendChild(item);
			setData.setSelectedItem(item);
			limpiar();
		}

	}

	// Metodo para eliminar una fila
	@Listen("onClick = #detete_item")
	public void deleteItem() {
		int index = setData.getSelectedIndex();
		if (index >= 0) {
			// remove the selected item
			setData.removeItemAt(index);
		} else {
			// a easy way to show a message to user
			alert("Please select an item first!");
		}
	}
	
	@Listen("onClick = #button_cancel")
	public void Cancel(){
		limpiar();
		limpiar1();
		limpiar2();
		limpiar3();
		limpiarListas();
	}
	
	@Listen ("onClick = #button_result")
	public void Results(){
		 Window window = (Window)Executions.createComponents("modalRest.zul", null, null);
	        window.doModal();
	}
	

	// Método para generar los casos de prueba
	@Listen("onClick = #button_generate")
	public void Generate() {
		
		
		Messagebox mensaje = new Messagebox();
		mensaje.show("Are you sure?", "Question", Messagebox.OK
				| Messagebox.CANCEL, Messagebox.QUESTION,
				new EventListener<Event>() {
					@Override
					public void onEvent(Event event) throws Exception {
						if ("onCancel".equals(event.getName())) {
		
						} else {
							ArrayList<String> listString = new ArrayList<>();
							ArrayList<String> listValuesValidate = new ArrayList<>();
							ArrayList<String> listValuesText = new ArrayList<>();
							ArrayList<String> listCampo = new ArrayList<>();
							ArrayList<Integer> listLengthMin = new ArrayList();
							ArrayList<Integer> listLengthMax = new ArrayList<>();
							ArrayList<Boolean> listRequired = new ArrayList<>();
							ArrayList<String> listHeader = new ArrayList<>();
							ArrayList<String> listValueHeader = new ArrayList<>();
							ArrayList<Integer> listStatus = new ArrayList<>();
							ArrayList<String> listResponse = new ArrayList<>();
							ArrayList<String> listRequestSend = new ArrayList<>();
							ArrayList<Integer> listTimeConnection = new ArrayList<>();
							ArrayList<String> listResponseMessage = new ArrayList<>();
							ArrayList<String> listNameResponse = new ArrayList<>();
							ArrayList<String> listJsonResponse = new ArrayList<>();
							List<Listitem> lista = setData.getItems();
							List<Listitem> lista1 = set_headers.getItems();
							List<Listitem> lista2 = set_response_expected.getItems();
							String urlService = text_url.getValue();
							String nameService = text_value.getValue();

							ordenarParametros(listString, lista, listValuesValidate, listLengthMin,listLengthMax, listRequired, listCampo);
							obtenerHeaders(listHeader, listValueHeader, lista1);
							obtenerResponses(listNameResponse, listJsonResponse, lista2);
							
							//int lineas = Integer.parseInt(text_row.getValue());
							//int lineas_min = (3 + listString.size());
							//int row = setData.getItemCount();

							
							 TestCase caso = new TestCase(); 
							 ArrayList<String> listCaso = caso.tupla(listString, listValuesValidate, listLengthMin,listLengthMax); 
							 System.out.println(listCaso); 
							 RestClient rest = new RestClient(); 
							 JSONArray listJson = rest.gerenateJson(listCaso,listCampo); 
							 rest.post(listJson, listStatus, listResponse, listRequestSend, listTimeConnection, listHeader, listValueHeader,listResponseMessage,urlService,nameService);
							 
							 Date date= new Date(); 
							 Timestamp time = new Timestamp(date.getTime());
							 request request = new request();
							 request.setUrl(text_url.getValue());
							 request.setJson_request(listJson);
							 request.setName(text_nameService.getValue());
							 request.setTime(time);
							 request.setStatus('A');
							 
							
							 requestDao requestDao = new requestDao();
							 boolean service_name = requestDao.obtenerRequest(request);
							 if(service_name == false){
								 Boolean registro = null;
								 registro = requestDao.registrarRequest(request);
								 System.out.println("Registro:" + registro);
								 
								 for(int h=0; h<listHeader.size();++h){
									 header header = new header();
									 header.setName(listHeader.get(h));
									 header.setValue(listValueHeader.get(h));
									 header.setStatus('A');
									 headerDao headerDao = new headerDao();
									 Boolean registroH = headerDao.registrarHeader(header, request);
									 System.out.println("Registro header:" + registroH);
								 }
								 for(int r =0; r<listNameResponse.size();++r){
									 response_expected response_exp = new response_expected();
									 response_exp.setName(listNameResponse.get(r));
									 response_exp.setJson_response_expected(listJsonResponse.get(r));
									 response_exp.setStatus('A');
									 response_expectedDao response_expDao = new response_expectedDao();
									 Boolean resgistroRE = response_expDao.registrarResponseExpected(request, response_exp);
									 System.out.println("Resgistro response_expected:" + resgistroRE);
								 }
								 for(int re=0; re<listStatus.size();++re){
									 response_receved response_rec = new response_receved();
									 String valor = Integer.toString(listStatus.get(re)); 
									 response_rec.setStatus_response(valor);
									 response_rec.setMessage(listResponseMessage.get(re));
									 response_rec.setJson_request(listRequestSend.get(re));
									 response_rec.setJson_response_receved(listResponse.get(re));
									 response_rec.setDuration(listTimeConnection.get(re));
									 response_rec.setStatus('A');
									 response_recevedDao response_recDao = new response_recevedDao();
									 Boolean registroRR = response_recDao.registrarResponseReceved(request, response_rec);
									 System.out.println("Registro response_receved:" +registroRR);
								 }
								 //limpiarListas();
							 }else{
								 Messagebox mensaje1 = new Messagebox();
								 mensaje1.show("This name has been used, please specify!");
							 }
						
						}
					}
				});
		
		 //GenerateTXT(row, lineas, listCaso);

	}
	
	public void habilitarButton(){
		
	}

	// Método para encontrar el orden de los parametros indicados
	public void ordenarParametros(ArrayList<String> listString,
			List<Listitem> lista, ArrayList<String> listValuesValidate,
			ArrayList<Integer> listLengthMin, ArrayList<Integer> listLengthMax,
			ArrayList<Boolean> listRequired, ArrayList<String> listCampo) {
		for (Listitem currentList : lista) {
			List<Component> com = currentList.getChildren();

			for (Component currentComp : com) {

				Listcell lc = (Listcell) currentComp;

				String cadena = "";
				if (lc.getColumnIndex() == 0) {
					listCampo.add(lc.getLabel());
				}
				if (lc.getColumnIndex() == 1) {
					if (lc.getLabel().equals("FirstName"))
						listString.add("FirstName");
					if (lc.getLabel().equals("LastName"))
						listString.add("LastName");
					if (lc.getLabel().equals("Email"))
						listString.add("Email");
					if (lc.getLabel().equals("BirthData"))
						listString.add("BirthData");
					if (lc.getLabel().equals("Address"))
						listString.add("Address");
					if (lc.getLabel().equals("City"))
						listString.add("City");
					if (lc.getLabel().equals("RandomText"))
						listString.add("RandomText");
					if (lc.getLabel().equals("RandomChars"))
						listString.add("RandomChars");
					if (lc.getLabel().equals("RandomWord"))
						listString.add("RandomWord");
					if (lc.getLabel().equals("Alphanumeric"))
						listString.add("Alphanumeric");
					if (lc.getLabel().equals("Numeric"))
						listString.add("Numeric");
					if (lc.getLabel().equals("Fecha DD/MM/YYYY"))
						listString.add("Fecha DD/MM/YYYY");
					if (lc.getLabel().equals("Fecha DD-MM-YYYY"))
						listString.add("Fecha DD-MM-YYYY");
					if (lc.getLabel().equals("Fecha MM/DD/YYYY"))
						listString.add("Fecha MM/DD/YYYY");
					if (lc.getLabel().equals("Fecha MM-DD-YYYY"))
						listString.add("Fecha MM-DD-YYYY");
					if (lc.getLabel().equals("Fecha YYYY/MM/DD"))
						listString.add("Fecha YYYY/MM/DD");
					if (lc.getLabel().equals("Fecha YYYY-MM-DD"))
						listString.add("Fecha YYYY-MM-DD");

				}
				if (lc.getColumnIndex() == 2) {
					listValuesValidate.add(lc.getLabel());
				}
				if (lc.getColumnIndex() == 3) {
					if (lc.getLabel().equals("null")) {
						listLengthMin.add(null);
					} else {
						listLengthMin.add(Integer.parseInt(lc.getLabel()));
					}
				}
				if (lc.getColumnIndex() == 4) {
					if (lc.getLabel().equals("null"))
						listLengthMax.add(null);
					else
						listLengthMax.add(Integer.parseInt(lc.getLabel()));
				}
				if (lc.getColumnIndex() == 5) {
					listRequired.add(Boolean.parseBoolean(lc.getLabel()
							.toString()));
				}

			}
		}
	}

	public void obtenerHeaders(ArrayList<String> listHeader,ArrayList<String> listValueHeader, List<Listitem> lista1) {
		for (Listitem currentList : lista1) {
			List<Component> com = currentList.getChildren();
			for (Component currentComp : com) {
				Listcell lc = (Listcell) currentComp;
				if (lc.getColumnIndex() == 0) {
					listHeader.add(lc.getLabel());
				}
				if(lc.getColumnIndex()==1){
					listValueHeader.add(lc.getLabel());
				}
			}
		}
	}
	
	public void obtenerResponses(ArrayList<String> listNameResponse, ArrayList<String> listJsonResponse, List<Listitem> lista2){
		for (Listitem currentList : lista2) {
			List<Component> com = currentList.getChildren();
			for (Component currentComp : com) {
				Listcell lc = (Listcell) currentComp;
				if (lc.getColumnIndex() == 0) {
					listNameResponse.add(lc.getLabel());
				}
				if(lc.getColumnIndex()==1){
					listJsonResponse.add(lc.getLabel());
				}
			}
		}
	}
	
	// Metodo para crear el un archivo TXT
	public void generateTXT(int row, int lineas, ArrayList<String> listCaso) {
		PrintWriter salida = null;
		int sizeList = listCaso.size();
		try {

			salida = new PrintWriter(text_path.getValue());
			int variable = row;
			int valorPost = 0;
			for (int i = 0; i < lineas; ++i) {
				for (int a = valorPost; a < variable; ++a) {
					if (a == (variable - 1)) {
						salida.print(listCaso.get(a).toString());
					} else {
						salida.print(listCaso.get(a).toString()
								+ text_delimiter.getValue());
					}

					valorPost = a;
				}
				valorPost = valorPost + 1;
				variable = variable + row;
				salida.println();
			}
			salida.flush();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			salida.close();
		}
	}

	public void limpiar() {
		textFieldName.setValue("");
		comboType.setValue("");
		textValue.setValue("");
		checkRequired.setValue(null);
		dateBox.setValue(null);
		intboxMin.setValue(null);
		intboxMax.setValue(null);

	}

	public void limpiar1() {
		text_header.setValue("");
		text_value.setValue("");
	}
	
	public void limpiar2(){
		text_name_response.setValue("");
		text_json_response.setValue("");
	}
	public void limpiar3(){
		text_url.setValue("");
		text_nameService.setValue("");
	}

	public void limpiarListas(){
		int index = set_response_expected.getSelectedIndex();
		int index1 = setData.getSelectedIndex();
		int index2 = set_headers.getSelectedIndex();
		
		System.out.println(index);
		if (index >= 0) {
			for(int i=0; i<index;++i){
				set_response_expected.removeItemAt(i);
			}
		}
		if(index1 >=0){
			for(int i1=0; i1<index1;++i1){
				setData.removeItemAt(i1);
			}
		}
		if(index2 >=0){
			for(int i2=0; i2<index2;++i2){
				set_headers.removeItemAt(i2);
			}
		}
	}
	

	
	
}
