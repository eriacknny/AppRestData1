package AppRestData;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.fluttercode.datafactory.impl.DataFactory;
import org.zkoss.zhtml.Button;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
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
import org.zkoss.zul.Textbox;
import AppRestData.TestCase;

public class AppRestDataController extends SelectorComposer<Component> {

	@Wire
	private Listbox setData;
	@Wire
	private Button add_item;
	@Wire
	private Button detete_item;
	@Wire
	private Button button_generate;
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
	private Textbox textLength;
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
				if (intboxMin.getValue() == null && intboxMax.getValue() != null) {
					celllengthMin = new Listcell("null");
					celllengthMax = new Listcell(intboxMax.getValue().toString());
				} else {
					celllengthMin = new Listcell(intboxMin.getValue().toString());
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

	// Método para generar los casos de prueba
	@Listen("onClick = #button_generate")
	public void Generate() {
		ArrayList<String> listString = new ArrayList<>();
		ArrayList<String> listValuesValidate = new ArrayList<>();
		ArrayList<String> listValuesText = new ArrayList<>();
		ArrayList<Integer> listLengthMin = new ArrayList();
		ArrayList<Integer> listLengthMax = new ArrayList<>();
		ArrayList<Boolean> listRequired = new ArrayList<>();
		List<Listitem> lista = setData.getItems();
		DataFactory df = new DataFactory();
		RandomStringUtils rsu = new RandomStringUtils();

		ordenarParametros(listString, lista, listValuesValidate, listLengthMin,
				listLengthMax, listRequired);
		int lineas = Integer.parseInt(text_row.getValue());
		int lineas_min = (3 + listString.size());
		System.out.println("lineas: " + lineas);
		System.out.println("lineas_min: " + lineas_min);
		if (lineas < lineas_min) {
			Messagebox mensaje = new Messagebox();
			mensaje.show("Are you sure?", "Question", Messagebox.OK
					| Messagebox.CANCEL, Messagebox.QUESTION,
					new EventListener<Event>() {
						@Override
						public void onEvent(Event event) throws Exception {
							if ("onCancel".equals(event.getName())) {
								text_row.setValue("");
							} else {
								ArrayList<String> listString = new ArrayList<>();
								ArrayList<String> listValuesValidate = new ArrayList<>();
								ArrayList<String> listValuesText = new ArrayList<>();
								ArrayList<Integer> listLengthMin = new ArrayList();
								ArrayList<Integer> listLengthMax = new ArrayList<>();
								ArrayList<Boolean> listRequired = new ArrayList<>();
								List<Listitem> lista = setData.getItems();
								int lineas = Integer.parseInt(text_row
										.getValue());
								ordenarParametros(listString, lista,
										listValuesValidate, listLengthMin,
										listLengthMax, listRequired);
								int row = setData.getItemCount();
								TestCase caso = new TestCase();
								ArrayList<String> listCaso = caso.tupla(
										listString, listValuesValidate,
										listLengthMin, listLengthMax);
								for (int d = 0; d < listCaso.size(); ++d) {
									listValuesText.add(listCaso.get(d));
								}
								System.out.println(listValuesText);

							}
						}
					});
		} else {
			int row = setData.getItemCount();

			TestCase caso = new TestCase();
			ArrayList<String> listCaso = caso.tupla(listString,
					listValuesValidate, listLengthMin, listLengthMax);
			// ArrayList<String> listCaso = caso.casosOpciones(listString,
			// listLength, listValuesValidate, listRequired);
			System.out.println(listCaso);
			generateTXT(row, lineas, listCaso);
		}

	}

	// Método para encontrar el orden de los parametros indicados
	public void ordenarParametros(ArrayList<String> listString,
			List<Listitem> lista, ArrayList<String> listValuesValidate,
			ArrayList<Integer> listLengthMin, ArrayList<Integer> listLengthMax,
			ArrayList<Boolean> listRequired) {
		for (Listitem currentList : lista) {
			List<Component> com = currentList.getChildren();

			for (Component currentComp : com) {

				Listcell lc = (Listcell) currentComp;

				String cadena = "";
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
					if(lc.getLabel().equals("Fecha DD/MM/YYYY"))
						listString.add("Fecha DD/MM/YYYY");
					if(lc.getLabel().equals("Fecha DD-MM-YYYY"))
						listString.add("Fecha DD-MM-YYYY");
					if(lc.getLabel().equals("Fecha MM/DD/YYYY"))
						listString.add("Fecha MM/DD/YYYY");
					if(lc.getLabel().equals("Fecha MM-DD-YYYY"))
						listString.add("Fecha MM-DD-YYYY");
					if(lc.getLabel().equals("Fecha YYYY/MM/DD"))
						listString.add("Fecha YYYY/MM/DD");
					if(lc.getLabel().equals("Fecha YYYY-MM-DD"))
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

}
