package AppRestData;

import java.util.ArrayList;
import java.util.Random;

import javax.management.modelmbean.RequiredModelMBean;

import org.apache.commons.lang3.RandomStringUtils;
import org.fluttercode.datafactory.impl.DataFactory;

public class TestCase {

	RandomStringUtils rsu = new RandomStringUtils();
	DataFactory df = new DataFactory();

	// Para generar casos de prueba por opciones
	public ArrayList<String> casosOpciones(ArrayList<String> listString,
			ArrayList<Integer> listLength,
			ArrayList<String> listValuesValidate,
			ArrayList<Boolean> listRequired) {
		ArrayList<String> listCases = new ArrayList<String>();

		ArrayList<String> listOpciones = new ArrayList<>();
		listOpciones.add("Valido");
		listOpciones.add("InvalidoVacio");
		listOpciones.add("InvalidoType");
		listOpciones.add("InvalidoLength");

		for (int op = 0; op < listOpciones.size(); ++op) {
			if (listOpciones.get(op).equals("Valido")) {
				for (int v = 0; v < listValuesValidate.size(); ++v) {
					if (listRequired.get(v) == false)
						listCases.add(" ");
					else
						listCases.add(listValuesValidate.get(v));
				}
			}
			if (listOpciones.get(op).equals("Valido")) {
				for (int r = 0; r < listRequired.size(); ++r) {
					if (listRequired.get(r) == false) {
						for (int v = 0; v < listValuesValidate.size(); ++v) {
							if (r == v)
								listCases.add(listValuesValidate.get(v));
							else
								listCases.add(listValuesValidate.get(v));

						}
					} else {
						for (int v = 0; v < listValuesValidate.size(); ++v) {
							if (r == v)
								listCases.add(" ");
							else
								listCases.add(listValuesValidate.get(v));

						}
					}
				}
			}
			if (listOpciones.get(op).equals("InvalidoType")) {
				for (int t = 0; t < listString.size(); ++t) {
					if (listString.get(t) != "Numeric"
							&& listString.get(t) != "Email") {
						for (int v = 0; v < listValuesValidate.size(); ++v) {
							if (t == v)
								listCases.add("86...*");
							else
								listCases.add(listValuesValidate.get(v));
						}
					} else {
						if (listString.get(t).equals("Email")) {
							for (int v = 0; v < listValuesValidate.size(); ++v) {
								if (t == v)
									listCases.add("email.com");
								else
									listCases.add(listValuesValidate.get(v));
							}
						}
						if (listString.get(t).equals("Numeric")) {
							for (int v = 0; v < listValuesValidate.size(); ++v) {
								if (t == v)
									listCases.add("abcde");
								else
									listCases.add(listValuesValidate.get(v));
							}
						}
					}
				}
			}
			if (listOpciones.get(op).equals("InvalidoLength")) {
				for (int l = 0; l < listLength.size(); ++l) {
					if (listLength.get(l) != null) {
						for (int v = 0; v < listValuesValidate.size(); ++v) {
							if (l == v)
								listCases.add(df.getRandomText(listLength
										.get(l) + 1));
							else
								listCases.add(listValuesValidate.get(v));
						}

					}
				}
				for (int l = 0; l < listLength.size(); ++l) {
					if (listLength.get(l) != null) {
						for (int v = 0; v < listValuesValidate.size(); ++v) {
							if (l == v)
								listCases.add(df.getRandomText(listLength
										.get(l) - 1));
							else
								listCases.add(listValuesValidate.get(v));
						}

					}
				}

			}
		}
		return listCases;
	}

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
			ArrayList<String> listValuesValidate, ArrayList<Integer> listLength) {
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
						cadena = invalido(listString, listLength, j);
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

	public String invalido(ArrayList<String> listString,
			ArrayList<Integer> listLength, int posicion) {
		String cadena = "";
		Random r = new Random();
		boolean emailBoolean = false;
		boolean lengthBoolean = false;
		boolean randomBoolean = false;

		if (listLength.get(posicion) != null) {
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
				lengthBoolean = r.nextBoolean();
				if (lengthBoolean == true) {
					if (listString.get(posicion).equals("RandomText"))
						cadena = df.getRandomText(listLength.get(posicion) + 1);
					if (listString.get(posicion).equals("RandomChars"))
						cadena = df
								.getRandomChars(listLength.get(posicion) + 1);
					if (listString.get(posicion).equals("RandomWord"))
						cadena = df.getRandomWord(listLength.get(posicion) + 1);
					if (listString.get(posicion).equals("Alphanumeric"))
						cadena = rsu.randomAlphanumeric(listLength
								.get(posicion) + 1);
					if (listString.get(posicion).equals("Numeric"))
						cadena = rsu
								.randomNumeric(listLength.get(posicion) + 1);
				} else {
					if (listString.get(posicion).equals("RandomText"))
						cadena = df.getRandomText(listLength.get(posicion) - 1);
					if (listString.get(posicion).equals("RandomChars"))
						cadena = df
								.getRandomChars(listLength.get(posicion) - 1);
					if (listString.get(posicion).equals("RandomWord"))
						cadena = df.getRandomWord(listLength.get(posicion) - 1);
					if (listString.get(posicion).equals("Alphanumeric"))
						cadena = rsu.randomAlphanumeric(listLength
								.get(posicion) - 1);
					if (listString.get(posicion).equals("Numeric"))
						cadena = rsu
								.randomNumeric(listLength.get(posicion) - 1);
				}
			}

		} else {
			if (listString.get(posicion) != "Numeric"
					&& listString.get(posicion) != "Email") {
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
			}
		}

		return cadena;
	}
}
