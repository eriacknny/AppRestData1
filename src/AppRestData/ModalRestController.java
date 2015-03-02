package AppRestData;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.zkoss.json.JSONArray;
import org.zkoss.zhtml.Button;
import org.zkoss.zhtml.I;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import Dao.Cdao;
import Dao.response_expectedDao;
import Dao.response_recevedDao;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class ModalRestController extends SelectorComposer<Component> {
	@Wire
	private Listbox set_status;
	@Wire
	private Listcell listcell_request;
	@Wire 
	private Listcell listcell_response;
	@Wire
	private Button button_load;
	@Wire
	private Button button_exist;
	@Wire
	private Window window;
	@Wire
	private Textbox text_request;
	@Wire
	private Textbox text_response;
	@Wire
	private Label labelUrl;
	@Wire
	private Label labelName;
	@Wire
	private Label labelResult;
	@Wire
	private Label labelNameResponse;
	@Wire
	private Listitem item_result;
	@Wire
	private Textbox text_response_expected;
	
	ArrayList<String> listStatus = new ArrayList<>();
	ArrayList<String> listMessage = new ArrayList<>();
	ArrayList<String> listJsonR = new ArrayList<>();
	ArrayList<String> listJsonSend = new ArrayList<>();
	ArrayList<String> listname = new ArrayList<>();
	ArrayList<String> listurl = new ArrayList<>();
	ArrayList<String> listResult = new ArrayList<>();
	ArrayList<String> listNameResponse = new ArrayList<>();
	ArrayList<String> listJsonResponse_exp = new ArrayList<>();
	ArrayList<String> listCodStatus = new ArrayList<>();
	

	public void show(){
		window = (Window)Executions.createComponents("modalRest.zul",null,null);
		window.doModal();
	}
	
	@Listen("onClick = #button_load")
	public void load() {
	
		response_recevedDao rrDao = new response_recevedDao();
		response_expectedDao reDao = new response_expectedDao();
		rrDao.obtenerResponseReceved(listStatus,listMessage, listJsonR,listJsonSend,listname,listurl,listResult);
		reDao.obtenerResponseExpected(listNameResponse, listJsonResponse_exp, listCodStatus);
		
		labelUrl.setValue(listurl.get(0));
		labelName.setValue(listname.get(0));
		
	
		for(int i=0; i<listStatus.size();++i){
			Listitem item = new Listitem();
			Listcell cellStatus = new Listcell(listStatus.get(i) + " " + listMessage.get(i));
			if(listStatus.get(i).equals("200"))
				item.setStyle("background-color: #c3ffb1");
			else
				item.setStyle("background-color: #ffeac2");
			
				item.appendChild(cellStatus);
				set_status.appendChild(item);
		}
		
		
	
	}
	
	
	
	
	@Listen("onSelect = #set_status")
	public void selectStatus(){
		text_request.setValue("");
		text_response.setValue("");
		int index = set_status.getSelectedIndex();
		System.out.println(index);
		System.out.println(listJsonR.get(index));
		text_request.setValue(listJsonSend.get(index));
		text_response.setValue(listJsonR.get(index));
		labelResult.setValue(listResult.get(index));
		if(listResult.get(index).equals("Failed"))
			item_result.setStyle("background-color: #ffeac2");
		else
			item_result.setStyle("background-color: #c3ffb1");
		
		for(int i=0; i<listCodStatus.size();++i){
			if(listCodStatus.get(i).equals(listStatus.get(index))){
				text_response_expected.setValue(listJsonResponse_exp.get(i));
				labelNameResponse.setValue(listNameResponse.get(i));
			}
		}
		
	}
	
	@Listen("onClick = #button_exist")
	public void exist() {
		window.detach();
	}
	

}
