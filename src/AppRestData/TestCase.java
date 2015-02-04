package AppRestData;

import java.util.ArrayList;
import java.util.Random;

import javax.management.modelmbean.RequiredModelMBean;

import org.apache.commons.lang3.RandomStringUtils;
import org.fluttercode.datafactory.impl.DataFactory;

import com.sun.javafx.scene.control.behavior.ListViewBehavior;

public class TestCase {

	RandomStringUtils rsu = new RandomStringUtils();
	DataFactory df = new DataFactory();

	// Para generar casos de prueba por opciones

	public ArrayList<String> combinaciones(ArrayList<String> listString) {
		ArrayList<String> listCases = new ArrayList<String>();
		String combV = "";
		String combI = "";
		String combE = "";
		String comb = "";
		int nroOpciones = 3;
		Random r = new Random();
		boolean valor = false;

		for (int v = 0; v < listString.size(); ++v) {
			combV = "V";
			listCases.add(combV);
		}

		for (int i = 0; i < listString.size(); ++i) {
			combI = "I";
			listCases.add(combI);
		}

		for (int e = 0; e < listString.size(); ++e) {
			combE = "E";
			listCases.add(combE);
		}

		for (int i = 0; i < listString.size(); ++i) {
			combV = "V";
			combI = "I";
			combE = "E";

			for (int op = 0; op < listString.size(); ++op) {
				if (i == op)
					listCases.add(combV);
				else {
					valor = r.nextBoolean();
					if (valor == true)
						comb = combI;
					else
						comb = combE;
					listCases.add(comb);
				}
			}
		}
		System.out.println(listCases);
		return listCases;
	}

	public ArrayList<String> tupla(ArrayList<String> listString,
			ArrayList<String> listValuesValidate,
			ArrayList<Integer> listLengthMin, ArrayList<Integer> listLengthMax) {
		ArrayList<String> listTupla = new ArrayList<String>();
		ArrayList<String> listCombinaciones = combinaciones(listString);
		String cadena = "";

		int i = 0;
		while (i < listCombinaciones.size()) {
			for (int j = 0; j < listString.size(); ++j) {
				if (listCombinaciones.get(i).equals("V")) {
					listTupla.add(listValuesValidate.get(j));
					++i;
				} else {
					if (listCombinaciones.get(i).equals("I")) {
						cadena = invalido(listString, listLengthMin,
								listLengthMax, j);
						listTupla.add(cadena);
						++i;
					} else {
						listTupla.add("Vacio");
						++i;
					}
				}
			}
		}

		return listTupla;
	}

	public String invalido(ArrayList<String> listString, ArrayList<Integer> listLengthMin, ArrayList<Integer> listLengthMax,int posicion) {
		
		String cadena = "";
		Random r = new Random();
		boolean emailBoolean = false;
		boolean lengthBoolean = false;
		boolean randomBoolean = false;
	
		
		if (listLengthMin.get(posicion) != null
				&& listLengthMax.get(posicion) != null) {
			randomBoolean = r.nextBoolean();
			if (randomBoolean == true) {
				if (listString.get(posicion) != "Numeric"
						&& listString.get(posicion) != "Email") {
					cadena = "86...*";
				} else {
					if (listString.get(posicion).equals("Numeric")) {
						cadena = "abcde";
					}
					if (listString.get(posicion).equals("Email")) {
						emailBoolean = r.nextBoolean();
						if (emailBoolean == true)
							cadena = "email@gmail";
						else
							cadena = "email.com";
					}
				}

			} else {
				if (listLengthMax.get(posicion) != null
						&& listLengthMin == null) {
					if (listString.get(posicion).equals("RandomText"))
						cadena = df
								.getRandomText(listLengthMax.get(posicion) + 1);
					if (listString.get(posicion).equals("RandomChars"))
						cadena = df
								.getRandomChars(listLengthMax.get(posicion) + 1);
					if (listString.get(posicion).equals("RandomWord"))
						cadena = df
								.getRandomWord(listLengthMax.get(posicion) + 1);
					if (listString.get(posicion).equals("Alphanumeric"))
						cadena = rsu.randomAlphanumeric(listLengthMax
								.get(posicion) + 1);
					if (listString.get(posicion).equals("Numeric"))
						cadena = rsu
								.randomNumeric(listLengthMax.get(posicion) + 1);
				} else {
					if (listLengthMin.get(posicion) != null
							&& listLengthMax == null) {
						if (listString.get(posicion).equals("RandomText"))
							cadena = df.getRandomText(listLengthMin
									.get(posicion) - 1);
						if (listString.get(posicion).equals("RandomChars"))
							cadena = df.getRandomChars(listLengthMin
									.get(posicion) - 1);
						if (listString.get(posicion).equals("RandomWord"))
							cadena = df.getRandomWord(listLengthMin
									.get(posicion) - 1);
						if (listString.get(posicion).equals("Alphanumeric"))
							cadena = rsu.randomAlphanumeric(listLengthMin
									.get(posicion) - 1);
						if (listString.get(posicion).equals("Numeric"))
							cadena = rsu.randomNumeric(listLengthMin
									.get(posicion) - 1);
					} else {
						if (listLengthMax.get(posicion) != null
								&& listLengthMin.get(posicion) != null) {
							lengthBoolean = r.nextBoolean();
							if (lengthBoolean == true) {
								if (listString.get(posicion).equals(
										"RandomText"))
									cadena = df.getRandomText(listLengthMax
											.get(posicion) + 1);
								if (listString.get(posicion).equals(
										"RandomChars"))
									cadena = df.getRandomChars(listLengthMax
											.get(posicion) + 1);
								if (listString.get(posicion).equals(
										"RandomWord"))
									cadena = df.getRandomWord(listLengthMax
											.get(posicion) + 1);
								if (listString.get(posicion).equals(
										"Alphanumeric"))
									cadena = rsu
											.randomAlphanumeric(listLengthMax
													.get(posicion) + 1);
								if (listString.get(posicion).equals("Numeric"))
									cadena = rsu.randomNumeric(listLengthMax
											.get(posicion) + 1);
							} else {
								if (listString.get(posicion).equals(
										"RandomText"))
									cadena = df.getRandomText(listLengthMin
											.get(posicion) - 1);
								if (listString.get(posicion).equals(
										"RandomChars"))
									cadena = df.getRandomChars(listLengthMin
											.get(posicion) - 1);
								if (listString.get(posicion).equals(
										"RandomWord"))
									cadena = df.getRandomWord(listLengthMin
											.get(posicion) - 1);
								if (listString.get(posicion).equals(
										"Alphanumeric"))
									cadena = rsu
											.randomAlphanumeric(listLengthMin
													.get(posicion) - 1);
								if (listString.get(posicion).equals("Numeric"))
									cadena = rsu.randomNumeric(listLengthMin
											.get(posicion) - 1);
							}
						}
					}

				}
			}

		} else {
			if (listString.get(posicion) != "Numeric"
					&& listString.get(posicion) != "Email"
					&& listString.get(posicion) != "Fecha DD-MM-YYYY"
					&& listString.get(posicion) != "Fecha MM/DD/YYYY"
					&& listString.get(posicion) != "Fecha MM-DD-YYYY"
					&& listString.get(posicion) != "Fecha YYYY/MM/DD"
					&& listString.get(posicion) != "Fecha YYYY-MM-DD") {

				cadena = "86...*_34";
			} else {
				if (listString.get(posicion).equals("Numeric")) {
					cadena = "abcde";
				}
				if (listString.get(posicion).equals("Email")) {
					emailBoolean = r.nextBoolean();
					if (emailBoolean == true)
						cadena = "email@gmail";
					else
						cadena = "email.com";
				}
				if (listString.get(posicion).equals("Fecha DD-MM-YYYY")
						|| listString.get(posicion).equals("Fecha MM/DD/YYYY")
						|| listString.get(posicion).equals("Fecha MM-DD-YYYY")
						|| listString.get(posicion).equals("Fecha YYYY/MM/DD")
						|| listString.get(posicion).equals("Fecha YYYY-MM-DD")) {
					cadena = casesFecha(listString, posicion);
				}
			}
		
		}
		return cadena;
	}

	public String casesFecha(ArrayList<String> listString, int posicion) {
		String cadena = "";
		String op = "";
		boolean caso = false;
		Random r = new Random();

		for (int i = 0; i < listString.size(); ++i) {
			op = listString.get(i);
			switch (op) {
			case "Fecha DD-MM-YYYY":
				caso = r.nextBoolean();
				if (caso == true)
					cadena = "21-01-YYYY";
				else
					cadena = "21-13-2015";
				break;
			case "Fecha MM/DD/YYYY":
				caso = r.nextBoolean();
				if (caso == true)
					cadena = "21/01/YYYY";
				else
					cadena = "21/13/2015";
				break;
			case "Fecha MM-DD-YYYY":
				if (caso == true)
					cadena = "01-21-YYYY";
				else
					cadena = "13-21-2015";
				break;
			case "Fecha YYYY/MM/DD":
				if (caso == true)
					cadena = "YYYY/01/21";
				else
					cadena = "2015/13/21";
				break;
			case "Fecha YYYY-MM-DD":
				if (caso == true)
					cadena = "YYYY/01/21";
				else
					cadena = "2015/13/21";
				break;

			default:
				break;
			}
		}

		return cadena;
	}
}
