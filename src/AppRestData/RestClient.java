package AppRestData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

import org.zkoss.json.JSONArray;
import org.zkoss.json.JSONObject;

public class RestClient {
	
	public ArrayList<String> generateUrlQueryString(ArrayList<String> listCaso,
			ArrayList<String> listCampo, String url){
		ArrayList<String> listUrlCase = new ArrayList<>();
		int i=0;
		
		while(i<listCaso.size()){
			String query = "";
			for(int j=0; j<listCampo.size();++j){
				try {
					query += URLEncoder.encode(listCampo.get(j), "UTF-8");
					query += "=";
					if(listCaso.get(i).equals("  "))
						query += "  ";
					else
						query += URLEncoder.encode(listCaso.get(i), "UTF-8");
					if (j != listCampo.size() - 1)
						query += "&";
					++i;
				} catch (UnsupportedEncodingException ex) {
					throw new RuntimeException(
							"Broken VM does not support UTF-8");
				}
			}
			listUrlCase.add(url+query);
		}
		return listUrlCase;
	}
	

	public ArrayList<String> generateUrlOtro(ArrayList<String> listCaso,
			ArrayList<String> listCampo, String url){
		ArrayList<String> listUrlCase = new ArrayList<>();
		int i=0;
		
		while(i<listCaso.size()){
			String query = "";
			for(int j=0; j<listCampo.size();++j){
				try {
					query += URLEncoder.encode(listCampo.get(j), "UTF-8");
					query += "/";
					if(listCaso.get(i).equals("  "))
						query += "  ";
					else
						query += URLEncoder.encode(listCaso.get(i), "UTF-8");
					++i;
				} catch (UnsupportedEncodingException ex) {
					throw new RuntimeException(
							"Broken VM does not support UTF-8");
				}
			}
			listUrlCase.add(url+query);
		}
		return listUrlCase;
	}
	
	

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
						sb.append(line);
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
					BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "utf-8"));
					String line = null;
					while ((line = br.readLine()) != null) {
						sb.append(line);
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

	public void get(ArrayList<String> listUrlCase,
			ArrayList<String> listHeader, ArrayList<String> listValueHeader ,ArrayList<Integer> listStatus,
			ArrayList<String> listResponse, ArrayList<String> listRequestSend,
			ArrayList<Integer> listTimeConnection,ArrayList<String> listResponseMessage ) {

		for (int i = 0; i < listUrlCase.size(); ++i) {
			String targetURL = listUrlCase.get(i);
			try {

				URL restServiceURL = new URL(targetURL);

				HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
				httpConnection.setRequestMethod("GET");

				for (int p = 0; p < listHeader.size(); ++p) 
					httpConnection.setRequestProperty(listHeader.get(p),listValueHeader.get(p));
								
				int httpResult = httpConnection.getResponseCode();
				StringBuilder sb = new StringBuilder();
				long time = httpConnection.getDate();
				Integer timeConnection = (int) (long) time;
				listRequestSend.add(listUrlCase.get(i));
				
				if (httpConnection.getResponseCode() != 200) {
					
					throw new RuntimeException("HTTP GET Request Failed with Error code : " + httpConnection.getResponseCode());


				/*	BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "utf-8"));
					String line = null;
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
					br.close();
					System.out.println(httpResult + " "	+ httpConnection.getResponseMessage());
					System.out.println(sb.toString()); */
					
				} else {
					BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "utf-8"));
					String line = null;
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
					br.close();
					System.out.println(httpResult + " "+ httpConnection.getResponseMessage());
					System.out.println(sb.toString());
					listStatus.add(httpResult);
					listResponse.add(sb.toString());
					listTimeConnection.add(timeConnection);
					listResponseMessage.add(httpConnection.getResponseMessage());

				}
				
				httpConnection.disconnect();

			} catch (MalformedURLException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}
		}
	}

}
