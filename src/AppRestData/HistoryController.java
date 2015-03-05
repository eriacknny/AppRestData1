package AppRestData;

import java.util.ArrayList;

import org.zkoss.zhtml.Button;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import Dao.historyDao;
import Modelo.header;
import Modelo.parameter;
import Modelo.request;
import Modelo.response_expected;

public class HistoryController extends  GenericForwardComposer{
	
	private Listbox set_services;
	
	ArrayList<request> listRequest = new ArrayList<>();
	ArrayList<header> listHeader = new ArrayList<>();
	ArrayList<parameter> listParameter = new ArrayList<>();
	ArrayList<response_expected> listRe = new ArrayList<>();
	EventQueue qe = EventQueues.lookup("connection", true);
	EventQueue qe1 = EventQueues.lookup("connection1", true);
	EventQueue qe2 = EventQueues.lookup("connection2", true);
	EventQueue qe3 = EventQueues.lookup("connection3", true);
	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		historyDao historyDao = new historyDao();
		listRequest = historyDao.obtenerRequest();
		for(int i=0; i<listRequest.size();++i){
			Listitem item = new Listitem();
			Listcell cellService = new Listcell(listRequest.get(i).getName());
			item.appendChild(cellService);
			item.setStyle("background:#ffffff");
			set_services.appendChild(item);
		}
		
	}
	
	public void onSelect$set_services(Event e){
		int index = set_services.getSelectedIndex();
		historyDao historyDao = new historyDao();
		listHeader = historyDao.obtenerHeader(listRequest.get(index).getId_request());
		listParameter = historyDao.obtenerParametro(listRequest.get(index).getId_request());
		listRe = historyDao.obtenerResponseExpected(listRequest.get(index).getId_request());
		qe.publish(new Event("mensaje",null,listRequest.get(index)));
		qe1.publish(new Event("mensaje1",null,listHeader));
		qe2.publish(new Event("mensaje2",null,listParameter));
		qe3.publish(new Event("mensaje3",null,listRe));
	}
	
	
	
	
	
}

