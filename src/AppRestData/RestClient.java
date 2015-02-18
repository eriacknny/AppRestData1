package AppRestData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

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

	public void post(JSONArray listJson, ArrayList<Integer> listStatus,
			ArrayList<String> listResponse, ArrayList<String> listRequestSend,
			ArrayList<Integer> listTimeConnection,
			ArrayList<String> listHeader, ArrayList<String> listValueHeader, ArrayList<String> listResponseMessage,
			String urlService, String nameService) {

		final String targetURL = urlService;
		try {

			for (int i = 0; i < listJson.size(); ++i) {
				URL targetUrl = new URL(targetURL);
				HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
				httpConnection.setDoOutput(true);
				httpConnection.setDoInput(true);
				httpConnection.setRequestMethod("POST");
				
				for (int p = 0; p < listHeader.size(); ++p) {
					httpConnection.setRequestProperty(listHeader.get(p),listValueHeader.get(p));
				}

				OutputStreamWriter wr = new OutputStreamWriter(httpConnection.getOutputStream());
				wr.write(listJson.get(i).toString());
				listRequestSend.add(listJson.get(i).toString());
				wr.flush();
				int httpResult = httpConnection.getResponseCode();
				long time = httpConnection.getDate();
				Integer timeConnection = (int) (long) time;
				StringBuilder sb = new StringBuilder();

				if (httpResult == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "utf-8"));
					String line = null;
					while ((line = br.readLine()) != null) {
						sb.append(line + "/n");
					}
					br.close();
					System.out.println(httpResult + " "+ httpConnection.getResponseMessage());
					System.out.println(sb.toString());
					System.out.println(timeConnection);
					listStatus.add(httpResult);
					listResponse.add(sb.toString());
					listTimeConnection.add(timeConnection);
					listResponseMessage.add(httpConnection.getResponseMessage());

				} else {
					BufferedReader br = new BufferedReader(
							new InputStreamReader(httpConnection.getInputStream(), "utf-8"));
					String line = null;
					while ((line = br.readLine()) != null) {
						sb.append(line + "/n");
					}
					br.close();
					System.out.println(httpResult + " "	+ httpConnection.getResponseMessage());
					System.out.println(sb.toString());
					System.out.println(timeConnection);
					listStatus.add(httpResult);
					listResponse.add(sb.toString());
					listTimeConnection.add(timeConnection);
					listResponseMessage.add(httpConnection.getResponseMessage());

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void get() {
		final String targetURL = "";
		try {

			URL restServiceURL = new URL(targetURL);

			HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Accept", "application/json");

			int httpResult = httpConnection.getResponseCode();

			if (httpConnection.getResponseCode() != 200) {
				throw new RuntimeException(
						"HTTP GET Request Failed with Error code : "
								+ httpConnection.getResponseCode());
			}

			BufferedReader responseBuffer = new BufferedReader(
					new InputStreamReader((httpConnection.getInputStream())));

			String output;

			System.out.println(httpResult + " "
					+ httpConnection.getResponseMessage());

			while ((output = responseBuffer.readLine()) != null) {
				System.out.println(output);
			}

			httpConnection.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
