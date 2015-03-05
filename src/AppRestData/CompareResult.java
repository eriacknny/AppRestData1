package AppRestData;

import java.util.ArrayList;

public class CompareResult {
	public ArrayList<String> obtenerResult(ArrayList<String> listJsonResponse,
			ArrayList<Integer> listCodStatus, ArrayList<String> listResponse,
			ArrayList<Integer> listStatus) {
		ArrayList<String> listResult = new ArrayList<>();
		String result = "";
		for (int i = 0; i < listStatus.size(); ++i) {
			if (listStatus.get(i) != 200) {
				for (int j = 0; j < listCodStatus.size(); ++j) {
					if (listCodStatus.get(j).equals(listStatus.get(i))) {
						if (listJsonResponse.get(j).equals(listResponse.get(i))) {
							result = "Passed";
							listResult.add(result);
							System.out.println(result);
						} else {
							result = "Failed";
							listResult.add(result);
							System.out.println(result);
						}
					}
				}
			} else {
				boolean jsonStringValid = JSONUtils.isJSONValid(listResponse.get(i));
				if (jsonStringValid) {
					result = "Passed";
					listResult.add(result);
					System.out.println(result);
				} else {
					result = "Failed";
					listResult.add(result);
					System.out.println(result);
				}
			}
		}
		return listResult;
	}

}
