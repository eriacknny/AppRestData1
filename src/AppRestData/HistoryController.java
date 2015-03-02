package AppRestData;

import java.util.ArrayList;

import org.zkoss.zhtml.Button;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import Dao.historyDao;
import Modelo.request;

public class HistoryController extends SelectorComposer<Component> {
	@Wire
	private Button button_load1;
	@Wire
	private Listbox set_services;

	
	@Listen("onClick = #button_load1")
	private void load(){
		System.out.println("entreeeee");
	/*	historyDao historyDao = new historyDao();
		historyDao.obtenerRequest(listRequest);
		for(int i=0; i<listRequest.size();++i){
			Listitem item = new Listitem();
			Listcell cellService = new Listcell(listRequest.get(i).getName());
			item.appendChild(cellService);
			set_services.appendChild(item);
		}*/
	}

}
