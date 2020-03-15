package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.Url;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class NaverAPI {
	private static String clientId = "yPQYyVMFjdlHoblG9T46";
	private static String clientSecret = "39GpBX6pCz"; 
	
	public static String getApiUrl() {
//		���̹� ���̵�� �α��� ���� ��û
		String apiUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    String redirectUri;
		try {
			redirectUri = URLEncoder.encode("http://localhost:8080/zSpringProject/user/naverLoginForm", "UTF-8");
		    SecureRandom random = new SecureRandom();
		    String state = new BigInteger(130, random).toString();
		    apiUrl += "&client_id=" + clientId;
		    apiUrl += "&redirect_uri=" + redirectUri;
		    apiUrl += "&state=" + state;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return apiUrl;
	}
	
	public JsonObject getJsonObject(String apiUrl){
		JsonObject jsonObj = null;
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if(responseCode == 200) { // ����
			    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // ����
			    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if(responseCode == 200){
				System.out.println("res: " + res.toString());
				JsonParser parsing = new JsonParser();
				Object obj = parsing.parse(res.toString());
				jsonObj = (JsonObject) obj;
				return jsonObj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObj;
	}
	
    public String getAccessToken (String param1, String param2) throws Exception {
		String code = param1;
		String state = param2;
		String redirectURI = URLEncoder.encode("http://localhost:8080/zSpringProject/user/naverLoginForm","UTF-8");
	    String apiUrl;
	    apiUrl = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiUrl += "client_id=" + clientId;
	    apiUrl += "&client_secret=" + clientSecret;
	    apiUrl += "&redirect_uri=" + redirectURI;
	    apiUrl += "&code=" + code;
	    apiUrl += "&state=" + state;
		String access_token = ""; // ���� ��ū
		String refresh_token = ""; // ���� ��ū
		
		JsonObject jsonObj = getJsonObject(apiUrl);
		if(jsonObj != null){
	        access_token = jsonObj.getAsJsonObject().get("access_token").getAsString();
	        refresh_token = jsonObj.getAsJsonObject().get("refresh_token").getAsString();
		}
		return access_token;
	}
    
    public String deleteAccessToken (String access_token) throws Exception {
    	access_token = URLEncoder.encode(access_token,"UTF-8"); // Ư�����ڰ� ���ԵǾ� �ֱ⿡ GET���� ������ ������ ���� encode�� �ʿ���
	    String apiUrl;
	    apiUrl = "https://nid.naver.com/oauth2.0/token?grant_type=delete&";
	    apiUrl += "client_id=" + clientId;
	    apiUrl += "&client_secret=" + clientSecret;
	    apiUrl += "&access_token=" + access_token;
	    apiUrl += "&service_provider=NAVER";
	    String result = null;
	    
	    JsonObject jsonObj = getJsonObject(apiUrl);
		if(jsonObj != null){
	        access_token = jsonObj.getAsJsonObject().get("access_token").getAsString();
	        result = jsonObj.getAsJsonObject().get("result").getAsString();
		}
		return result;
    }
    
    public HashMap<String, String> getUserInfo (String access_Token) {
    	HashMap<String, String> userInfo = new HashMap<>();
        String header = "Bearer " + access_Token;
        String apiURL = "https://openapi.naver.com/v1/nid/me";
        try {
        	URL url = new URL(apiURL);
        	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        	conn.setRequestMethod("GET");
        	conn.setRequestProperty("Authorization", header);
        	int responseCode = conn.getResponseCode();
        	BufferedReader br;
        	if(responseCode == 200) {
        		br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        	}else {
        		br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        	}
        	String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			JsonParser parsing = new JsonParser();
			Object obj = parsing.parse(res.toString());
			JsonObject jsonObj = (JsonObject) obj;
			JsonObject resObj = (JsonObject) jsonObj.get("response");
			
			String email = resObj.getAsJsonObject().get("email").getAsString();
			String name = resObj.getAsJsonObject().get("name").getAsString();
//			üũ
			System.out.println(email);
			System.out.println(name);
			
			userInfo.put("userId", email);
			userInfo.put("userName", name);
			
        }catch (Exception e) {
			e.printStackTrace();
		}
        
        return userInfo;
    }

}
