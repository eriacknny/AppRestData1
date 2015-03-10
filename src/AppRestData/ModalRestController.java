package AppRestData;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.zkoss.json.JSONArray;
import org.zkoss.zhtml.Button;
import org.zkoss.zhtml.I;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import Dao.Cdao;
import Dao.historyDao;
import Dao.response_expectedDao;
import Dao.response_recevedDao;
import Modelo.header;
import Modelo.request;
import Modelo.response_expected;
import Modelo.response_receved;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class ModalRestController extends GenericForwardComposer {
	
	private Listbox set_status;
	private Listcell listcell_request;
	private Listcell listcell_response;
	private Button button_exist;
	private Window window;
	private Textbox text_request;
	private Textbox text_response;
	private Label labelUrl;
	private Label labelName;
	private Label labelResult;
	private Label labelNameResponse;
	private Listitem item_result;
	private Textbox text_response_expected;
	

	private EventQueue qe5 = EventQueues.lookup("connection5", true);

	ArrayList<response_receved> listRR = new ArrayList<>();
	ArrayList<response_expected> listRe = new ArrayList<>();
	request request;
	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	
		qe5.subscribe(new EventListener() {
			public void onEvent(Event event) throws Exception {
				if(event.getName().equals("mensaje5")){	
					request = new request();
					request = (request) event.getData();
					labelUrl.setValue(request.getUrl());
					labelName.setValue(request.getName());
					historyDao historyDao = new historyDao();
					listRR = historyDao.obtenerResponseReceived(request.getId_request());
					listRe = historyDao.obtenerResponseExpected(request.getId_request());
					for(int i=0; i<listRR.size();++i){	
						Listitem item = new Listitem();
						Listcell cellStatus = new Listcell(listRR.get(i).getStatus_response() + " " + listRR.get(i).getMessage());
						if(listRR.get(i).getStatus_response().equals("200"))
							item.setStyle("background-color: #c3ffb1");
						else
							item.setStyle("background-color: #ffeac2");
						item.appendChild(cellStatus);
						set_status.appendChild(item);
					}
				}
				
			}
		});
		
				
		
		/*	
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
		*/
		
	}

	

	public void onSelect$set_status(){
		text_request.setValue("");
		text_response.setValue("");
		int index = set_status.getSelectedIndex();
		text_request.setValue(listRR.get(index).getJson_request());
		text_response.setValue(listRR.get(index).getJson_response_receved());
		labelResult.setValue(listRR.get(index).getResult());
		if(listRR.get(index).getResult().equals("Failed"))
			item_result.setStyle("background-color: #ffeac2");
		else
			item_result.setStyle("background-color: #c3ffb1");
	
		for(int i=0; i<listRe.size();++i){
			if(listRe.get(i).getCod_status().equals(listRR.get(index).getStatus_response())){
				text_response_expected.setValue(listRe.get(i).getJson_response_expected());
				labelNameResponse.setValue(listRe.get(i).getName());
			}
		}
	
	}
	

	public void onClick$button_exist() {
		window.detach();
	}
	

}
