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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class NaverAPI {
	
	public static String getApiUrl() {
		String clientId = "yPQYyVMFjdlHoblG9T46";
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    String redirectURI;
		try {
			redirectURI = URLEncoder.encode("http://localhost:8080/zSpringProject/user/naverLoginForm", "UTF-8");
		    SecureRandom random = new SecureRandom();
		    String state = new BigInteger(130, random).toString();
		    apiURL += "&client_id=" + clientId;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&state=" + state;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return apiURL;
	}
	
    public String getAccessToken (String param1, String param2) throws Exception {
		String clientId = "yPQYyVMFjdlHoblG9T46";
		String clientSecret = "39GpBX6pCz"; 
		String code = param1;
		String state = param2;
		String redirectURI = URLEncoder.encode("http://localhost:8080/zSpringProject/user/naverLoginForm","UTF-8");
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
		String access_token = "";
		String refresh_token = "";	//나중에 활용하자
				
		try {
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();
			BufferedReader br;
			if(responseCode == 200) { // 정상 호출
			    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else { 
			    br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if(responseCode == 200) {
				System.out.println(res.toString());
				JsonParser parsing = new JsonParser();
				Object obj = parsing.parse(res.toString());
				JsonObject jsonObj = (JsonObject) obj;
				
		        access_token = jsonObj.getAsJsonObject().get("access_token").getAsString();
		        refresh_token = jsonObj.getAsJsonObject().get("refresh_token").getAsString();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return access_token;
	}
    
    public HashMap<String, Object> getUserInfo (String access_Token) {
    	HashMap<String, Object> userInfo = new HashMap<>();
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
//			체크
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
