package AppRestData;

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
import org.zkoss.zul.Window;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class ModalRestController extends SelectorComposer<Component> {
	@Wire
	private Listbox set_status;
	@Wire
	private Listcell listcell_request;
	@Wire 
	private Listcell listcell_response;
	@Wire
	private Button button_run;
	@Wire
	private Button button_save;
	@Wire
	private Button button_exist;
	@Wire
	private Window window;

	private ArrayList<Integer> listStatusRequest = new ArrayList<>();;
	
	public void show(){
		window = (Window)Executions.createComponents("modalRest.zul",null,null);
		window.doModal();
	}
	
	@Listen("onClick = #button_run")
	public void run() {
		System.out.println("status:" +listStatusRequest);
		for(int i=0; i<30;++i){
			Listitem item = new Listitem();
			Listcell cellStatus = new Listcell("request");
			item.appendChild(cellStatus);
			set_status.appendChild(item);
		}
	}
	
	@Listen("onClick = #button_exist")
	public void exist() {
		window.detach();
	}
	
	@Listen("onClick = #button_save")
	public void save() {
		
	}
	
	public void cargarRequest(JSONArray listJson, ArrayList<Integer> listStatus, ArrayList<String> listResponse){
		listStatusRequest = listStatus;
	}
}
