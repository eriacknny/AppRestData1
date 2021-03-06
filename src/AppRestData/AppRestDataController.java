package AppRestData;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdk.nashorn.internal.runtime.ListAdapter;

import org.apache.commons.lang3.RandomStringUtils;
import org.fluttercode.datafactory.impl.DataFactory;
import org.zkoss.idom.Item;
import org.zkoss.json.JSONArray;
import org.zkoss.json.JSONObject;
import org.zkoss.zhtml.Button;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.ext.Disable;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import AppRestData.TestCase;
import Dao.headerDao;
import Dao.parameterDao;
import Dao.requestDao;
import Dao.response_expectedDao;
import Dao.response_recevedDao;
import Modelo.header;
import Modelo.parameter;
import Modelo.request;
import Modelo.response_expected;
import Modelo.response_receved;

public class AppRestDataController extends GenericForwardComposer {

	
	private Listbox setData;
	private Listbox set_headers;
	private Listbox set_response_expected;
	private Button add_item;
	private Button detete_item;
	private Button button_generate;
	private Button button_cancel;
	private Button button_result;
	private Button button_delete;
	private Textbox text_delimiter;
	private Textbox text_path;
	private Textbox text_row;
	private Textbox textFieldName;
	private Combobox comboTypeRequest;
	private Combobox comboType;
	private Textbox textValue;
	private Checkbox checkRequired;
	private Datebox dateBox;
	private Listbox listboxLenth;
	private Intbox intboxMin;
	private Intbox intboxMax;
	private Tabbox tabbox;
	private Tab tab_csv;
	private Tab tab_exel;
	private Tab tab_json;
	private Textbox text_header;
	private Textbox text_value;
	private Button add_item_header;
	private Button detele_item_header;
	private Textbox text_url;
	private Textbox text_nameService;
	private Textbox text_name_response;
	private Textbox text_json_response;
	private Button add_item_response;
	private Button detele_item_response;
	private Intbox text_status;
	private Listitem itemRadioButton;
	private Radio rQueryString;
	private Radio rOtro;
	private EventQueue qe = EventQueues.lookup("connection", true);
	private EventQueue qe1 = EventQueues.lookup("connection1", true);
	private EventQueue qe2 = EventQueues.lookup("connection2", true);
	private EventQueue qe3 = EventQueues.lookup("connection3", true);

	
	ArrayList<parameter> listParameter;
	ArrayList<header> listHeader;
	ArrayList<response_expected> listRe;
	request request;
	
	
	EventQueue qe5 = EventQueues.lookup("connection5", true);
	
	//------------------------------------------------------------

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comboTypeRequest.setValue("GET");

		qe.subscribe(new EventListener() {
			public void onEvent(Event event) throws Exception {
				if(event.getName().equals("mensaje")){	
					request = new request();
					request = (request) event.getData();
					text_url.setValue(request.getUrl());
					text_nameService.setValue(request.getName());
					comboTypeRequest.setValue(request.getType());
				}
				
			}
		});
		qe1.subscribe(new EventListener() {
			public void onEvent(Event event) throws Exception {
				if(event.getName().equals("mensaje1")){	
				    listHeader = (ArrayList<header>) event.getData();
					set_headers.getItems().clear();
					for(int i=0; i<listHeader.size();++i){
						Listitem item = new Listitem();
						Listcell cellHeader = new Listcell(listHeader.get(i).getName());
						Listcell cellValue = new Listcell(listHeader.get(i).getValue());
						item.appendChild(cellHeader);
						item.appendChild(cellValue);
						set_headers.appendChild(item);
					}
				}
				
			}
		});
		
		qe2.subscribe(new EventListener() {
			public void onEvent(Event event) throws Exception {
				if(event.getName().equals("mensaje2")){	
					listParameter = (ArrayList<parameter>) event.getData();
					setData.getItems().clear();
					for(int i=0; i<listParameter.size();++i){
						Listitem item = new Listitem();
						Listcell cellname = new Listcell(listParameter.get(i).getField_name());
						Listcell celltype = new Listcell(listParameter.get(i).getType());
						Listcell cellvalue = new Listcell(listParameter.get(i).getValue());
						Listcell celllengthMin;
						Listcell celllengthMax;
						if(listParameter.get(i).getLengthMin()!=0)
							celllengthMin = new Listcell(Integer.toString(listParameter.get(i).getLengthMin()));
						else
							celllengthMin = new Listcell("null");
						if(listParameter.get(i).getLengthMax()!=0)
							celllengthMax = new Listcell(Integer.toString(listParameter.get(i).getLengthMax()));
						else
							celllengthMax = new Listcell("null");
						
						Listcell cellrequired = new Listcell(Boolean.toString(listParameter.get(i).isRequired()));
						item.appendChild(cellname);
						item.appendChild(celltype);
						item.appendChild(cellvalue);
						item.appendChild(celllengthMin);
						item.appendChild(celllengthMax);
						item.appendChild(cellrequired);
						setData.appendChild(item);
						
					}
				}
				
			}
		});
		
		qe3.subscribe(new EventListener() {
			public void onEvent(Event event) throws Exception {
				if(event.getName().equals("mensaje3")){	
					listRe = (ArrayList<response_expected>) event.getData();
					set_response_expected.getItems().clear();
					for(int i=0; i<listRe.size();++i){
						Listitem item = new Listitem();
						Listcell cellresponse = new Listcell(listRe.get(i).getJson_response_expected());
						Listcell cellCodStatus = new Listcell(listRe.get(i).getCod_status());
						Listcell celljson = new Listcell(listRe.get(i).getJson_response_expected());
						item.appendChild(cellresponse);
						item.appendChild(cellCodStatus);
						item.appendChild(celljson);
						set_response_expected.appendChild(item);
					}
				}
				
			}
		});
		
	}
	
	public void onSelect$comboTypeRequest(){
		if(comboTypeRequest.getValue().equals("GET"))
			itemRadioButton.setVisible(true);
		else
			itemRadioButton.setVisible(false);
	}
	
	

	// Metodo para bloquear el campo longitud que el caso de que el tipo de
	// valor no amerite longitud
	
	public void onSelect$comboType() {
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



	
	public void onClick$add_item_header() {
		Listitem item = new Listitem();
		Listcell cellHeader = new Listcell(text_header.getValue());
		Listcell cellValue = new Listcell(text_value.getValue());
		item.appendChild(cellHeader);
		item.appendChild(cellValue);
		set_headers.appendChild(item);
		set_headers.setSelectedItem(item);
		limpiar1();
	}


	public void onClick$detele_item_header() {
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
	
	
	public void onClick$add_item_response(){
		Listitem item = new Listitem();
		Listcell cellresponse = new Listcell(text_name_response.getValue());
		Listcell cellCodStatus = new Listcell(Integer.toString(text_status.getValue()));
		Listcell celljson = new Listcell(text_json_response.getValue());
		item.appendChild(cellresponse);
		item.appendChild(cellCodStatus);
		item.appendChild(celljson);
		set_response_expected.appendChild(item);
		set_response_expected.setSelectedItem(item);
		limpiar2();
	}
	
	

	public void onClick$detele_item_response(){
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
	public void onClick$add_item() {

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
	public void onClick$detete_item() {
		int index = setData.getSelectedIndex();
		if (index >= 0) {
			// remove the selected item
			setData.removeItemAt(index);
		} else {
			// a easy way to show a message to user
			alert("Please select an item first!");
		}
	}
	

	public void onClick$button_cancel(){
		limpiar();
		limpiar1();
		limpiar2();
		limpiar3();
		limpiarListas();
	}
	
	

	public void onClick$button_result(){
		 qe5.publish(new Event("mensaje5",null,request));
		 Window window = (Window)Executions.createComponents("modalRest.zul", null, null);
	        window.doModal();
	        
	       
	}
	
	public void onClick$button_delete(){
		Messagebox mensaje = new Messagebox();
		mensaje.show("Are you sure?", "Question", Messagebox.OK
				| Messagebox.CANCEL, Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(Event event) throws Exception {
						// TODO Auto-generated method stub
						if ("onCancel".equals(event.getName())) {

						} else {
							requestDao rDao = new requestDao();
							rDao.eliminarService(request);
							Messagebox mensaje2 = new Messagebox();
							mensaje2.show("Successful operation!");
						}
					}
			
				});

	}
	

	// M�todo para generar los casos de prueba
	public void onClick$button_generate() {

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
							ArrayList<Integer> listCodStatus = new ArrayList<>();
							ArrayList<String> listRequestSend = new ArrayList<>();
							ArrayList<Integer> listTimeConnection = new ArrayList<>();
							ArrayList<String> listResponseMessage = new ArrayList<>();
							ArrayList<String> listNameResponse = new ArrayList<>();
							ArrayList<String> listJsonResponse = new ArrayList<>();
							ArrayList<String> listResult = new ArrayList<>();

							List<Listitem> lista = setData.getItems();
							List<Listitem> lista1 = set_headers.getItems();
							List<Listitem> lista2 = set_response_expected.getItems();
							String urlService = text_url.getValue();
							String nameService = text_value.getValue();
							String result = "";

							ordenarParametros(listString, lista,listValuesValidate, listLengthMin,listLengthMax, listRequired, listCampo);
							obtenerHeaders(listHeader, listValueHeader, lista1);
							obtenerResponses(listNameResponse,listJsonResponse, listCodStatus, lista2);

							if (comboTypeRequest.getValue().equals("POST")) {
								TestCase caso = new TestCase();
								ArrayList<String> listCaso = caso.tupla(listString, listValuesValidate,listLengthMin, listLengthMax);
								System.out.println(listCaso);
								RestClient rest = new RestClient();
								JSONArray listJson = rest.gerenateJson(listCaso, listCampo);
								rest.post(listJson, listStatus, listResponse,listRequestSend, listTimeConnection,listHeader, listValueHeader,listResponseMessage, urlService,nameService);
								CompareResult compareResult = new CompareResult();
								listResult = compareResult.obtenerResult(listJsonResponse, listCodStatus,listResponse, listStatus);

								Date date = new Date();
								Timestamp time = new Timestamp(date.getTime());
								request = new request();
								request.setUrl(text_url.getValue());
								request.setJson_request(listJson);
								request.setName(text_nameService.getValue());
								request.setTime(time);
								request.setType(comboTypeRequest.getValue());
								request.setListUrl(null);
								request.setStatus('A');
								requestDao requestDao = new requestDao();
								boolean service_name = requestDao.obtenerRequest(request);
								if (service_name == false) {
									Boolean registro = null;
									registro = requestDao
											.registrarRequest(request);
									for (int h = 0; h < listHeader.size(); ++h) {
										header header = new header();
										header.setName(listHeader.get(h));
										header.setValue(listValueHeader.get(h));
										header.setStatus('A');
										headerDao headerDao = new headerDao();
										Boolean registroH = headerDao.registrarHeader(header,request);
									}
									for (int r = 0; r < listNameResponse.size(); ++r) {
										response_expected response_exp = new response_expected();
										response_exp.setName(listNameResponse.get(r));
										response_exp.setJson_response_expected(listJsonResponse.get(r));
										response_exp.setCod_status(Integer.toString(listCodStatus.get(r)));
										response_exp.setStatus('A');
										response_expectedDao response_expDao = new response_expectedDao();
										Boolean resgistroRE = response_expDao.registrarResponseExpected(request, response_exp);
									}
									for (int re = 0; re < listStatus.size(); ++re) {
										response_receved response_rec = new response_receved();
										Timestamp time1 = new Timestamp(date.getTime());
										String valor = Integer.toString(listStatus.get(re));
										response_rec.setStatus_response(valor);
										response_rec.setMessage(listResponseMessage.get(re));
										response_rec.setJson_request(listRequestSend.get(re));
										response_rec.setJson_response_receved(listResponse.get(re));
										response_rec.setDuration(listTimeConnection.get(re));
										response_rec.setResult(listResult.get(re));
										response_rec.setStatus('A');
										response_rec.setTime(time1);
										response_recevedDao response_recDao = new response_recevedDao();
										Boolean registroRR = response_recDao.registrarResponseReceved(request, response_rec);
									}

									for (int p = 0; p < listString.size(); ++p) {
										parameter parameter = new parameter();
										parameter.setField_name(listCampo.get(p));
										parameter.setType(listString.get(p));
										parameter.setValue(listValuesValidate.get(p));
										if (listLengthMin.get(p) == null && listLengthMax.get(p) == null) {
											parameter.setLengthMin(0);
											parameter.setLengthMax(0);
										}
										if (listLengthMin.get(p) != null && listLengthMax.get(p) != null) {
											parameter.setLengthMin(listLengthMin.get(p));
											parameter.setLengthMax(listLengthMax.get(p));
										}
										if (listLengthMin.get(p) == null && listLengthMax.get(p) != null) {
											parameter.setLengthMin(0);
											parameter.setLengthMax(listLengthMax.get(p));
										}
										if (listLengthMin.get(p) != null && listLengthMax.get(p) == null) {
											parameter.setLengthMin(listLengthMin.get(p));
											parameter.setLengthMax(0);
										}
										parameter.setRequired(listRequired.get(p));
										parameter.setStatus('A');
										parameterDao parameterDao = new parameterDao();
										boolean registroP = parameterDao.registrarParameter(request,parameter);

									}

									Messagebox mensaje2 = new Messagebox();
									mensaje2.show("Successful operation!");
								
								} else {
									Messagebox mensaje1 = new Messagebox();
									mensaje1.show("This name has been used, please specify!");
								}
							} else {
								if (comboTypeRequest.getValue().equals("GET")){
									TestCase caso = new TestCase();
									RestClient rest = new RestClient();
									ArrayList<String> listUrlCase = new ArrayList<>();
									ArrayList<String> listCaso = caso.tupla(listString, listValuesValidate,listLengthMin, listLengthMax);
									if(rQueryString.isSelected())
										listUrlCase = rest.generateUrlQueryString(listCaso, listCampo, text_url.getValue());
									if(rOtro.isSelected())
										listUrlCase = rest.generateUrlOtro(listCaso, listCampo, text_url.getValue());
									rest.get(listUrlCase, listHeader, listValueHeader, listStatus, listResponse, listRequestSend, listTimeConnection, listResponseMessage);
									CompareResult compareResult = new CompareResult();
									listResult = compareResult.obtenerResult(listJsonResponse, listCodStatus,listResponse, listStatus);
									Date date = new Date();
									Timestamp time = new Timestamp(date.getTime());
									request = new request();
									request.setUrl(text_url.getValue());
									request.setJson_request(null);
									request.setName(text_nameService.getValue());
									request.setTime(time);
									request.setType(comboTypeRequest.getValue());
									request.setListUrl(listUrlCase);
									request.setStatus('A');
									requestDao requestDao = new requestDao();
									boolean service_name = requestDao.obtenerRequest(request);
									
									if (service_name == false) {
										Boolean registro = null;
										registro = requestDao
												.registrarRequest(request);
										for (int h = 0; h < listHeader.size(); ++h) {
											header header = new header();
											header.setName(listHeader.get(h));
											header.setValue(listValueHeader.get(h));
											header.setStatus('A');
											headerDao headerDao = new headerDao();
											Boolean registroH = headerDao.registrarHeader(header,request);
										}
										for (int r = 0; r < listNameResponse.size(); ++r) {
											response_expected response_exp = new response_expected();
											response_exp.setName(listNameResponse.get(r));
											response_exp.setJson_response_expected(listJsonResponse.get(r));
											response_exp.setCod_status(Integer.toString(listCodStatus.get(r)));
											response_exp.setStatus('A');
											response_expectedDao response_expDao = new response_expectedDao();
											Boolean resgistroRE = response_expDao.registrarResponseExpected(request, response_exp);
										}
										for (int re = 0; re < listStatus.size(); ++re) {
											response_receved response_rec = new response_receved();
											Timestamp time1 = new Timestamp(date.getTime());
											String valor = Integer.toString(listStatus.get(re));
											response_rec.setStatus_response(valor);
											response_rec.setMessage(listResponseMessage.get(re));
											response_rec.setJson_request(listRequestSend.get(re));
											response_rec.setJson_response_receved(listResponse.get(re));
											response_rec.setDuration(listTimeConnection.get(re));
											response_rec.setResult(listResult.get(re));
											response_rec.setStatus('A');
											response_rec.setTime(time1);
											response_recevedDao response_recDao = new response_recevedDao();
											Boolean registroRR = response_recDao.registrarResponseReceved(request, response_rec);
										}

										for (int p = 0; p < listString.size(); ++p) {
											parameter parameter = new parameter();
											parameter.setField_name(listCampo.get(p));
											parameter.setType(listString.get(p));
											parameter.setValue(listValuesValidate.get(p));
											if (listLengthMin.get(p) == null && listLengthMax.get(p) == null) {
												parameter.setLengthMin(0);
												parameter.setLengthMax(0);
											}
											if (listLengthMin.get(p) != null && listLengthMax.get(p) != null) {
												parameter.setLengthMin(listLengthMin.get(p));
												parameter.setLengthMax(listLengthMax.get(p));
											}
											if (listLengthMin.get(p) == null && listLengthMax.get(p) != null) {
												parameter.setLengthMin(0);
												parameter.setLengthMax(listLengthMax.get(p));
											}
											if (listLengthMin.get(p) != null && listLengthMax.get(p) == null) {
												parameter.setLengthMin(listLengthMin.get(p));
												parameter.setLengthMax(0);
											}
											parameter.setRequired(listRequired.get(p));
											parameter.setStatus('A');
											parameterDao parameterDao = new parameterDao();
											boolean registroP = parameterDao.registrarParameter(request,parameter);

										}

										Messagebox mensaje2 = new Messagebox();
										mensaje2.show("Successful operation!");
								   }else {
										Messagebox mensaje1 = new Messagebox();
										mensaje1.show("This name has been used, please specify!");
									}
							   }
							};

						}
					}
				});
	}
	



	// M�todo para encontrar el orden de los parametros indicados
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
	
	public void obtenerResponses(ArrayList<String> listNameResponse, ArrayList<String> listJsonResponse,ArrayList<Integer> listCodStatus,List<Listitem> lista2){
		for (Listitem currentList : lista2) {
			List<Component> com = currentList.getChildren();
			for (Component currentComp : com) {
				Listcell lc = (Listcell) currentComp;
				if (lc.getColumnIndex() == 0) {
					listNameResponse.add(lc.getLabel());
				}
				if(lc.getColumnIndex()==1){
					listCodStatus.add(Integer.parseInt(lc.getLabel()));					
				}
				if(lc.getColumnIndex()==2){
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
