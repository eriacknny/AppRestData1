package AppRestData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.zkoss.json.JSONArray;
import org.zkoss.json.JSONObject;

public class RestClient {

	public JSONArray gerenateJson(ArrayList<String> listCaso,
			ArrayList<String> listCampo) {
		int i = 0;
		JSONArray listJson = new JSONArray();
		while (i < listCaso.size()) {
			JSONObject obj = new JSONObject();
			for (int j = 0; j < listCampo.size(); ++j) {
				obj.put(listCampo.get(j), listCaso.get(i));
				++i;
			}
			listJson.add(obj);
		}
		System.out.println(listJson);
		return listJson;
	}

	public void post(JSONArray listJson) {
		final String targetURL = "http://certificacion.educatablet.com:9090/WSLocation.svc/GetCountries";
		try {
			URL targetUrl = new URL(targetURL);
			HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type","application/json");
			httpConnection.setRequestProperty("X-Auth-device-token","mszrkhB3FUCD4ZP+2flTme6HJ2Os4dZz4hBAutg5DN/e7s4bdmDP7g==");
			httpConnection.setRequestProperty("X-Auth-key","zD0sXXcNEMeqZRvIOkfc6IM0qxesfrDO");
			
			OutputStreamWriter wr = new OutputStreamWriter(httpConnection.getOutputStream());
			BufferedReader br= null;
			for (int i = 0; i < listJson.size(); ++i) {
				System.out.println(i);
				
				wr.write(listJson.get(i).toString());
				wr.flush();
				int httpResult = httpConnection.getResponseCode();
				StringBuilder sb = new StringBuilder();
				
					if (httpResult == HttpURLConnection.HTTP_OK) {
						br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "utf-8"));
						String line = null;
							while ((line = br.readLine()) != null) {
								sb.append(line + "/n");
							}
						
						System.out.println(httpResult + " "+ httpConnection.getResponseMessage());
						System.out.println(sb.toString());
						sb = null;
						
				
					} else {
						System.out.println(httpResult +" "+ httpConnection.getResponseMessage());		
						
					}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
