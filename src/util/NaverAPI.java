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
//		네이버 아이디로 로그인 인증 요청
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
	
    public String getAccessToken (String param1, String param2) throws Exception {
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
		String access_token = ""; // 접근 토큰
		String refresh_token = ""; // 갱신 토큰
				
		try {
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();
			BufferedReader br;
			if(responseCode == 200) { // 정상
			    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else { // 에러
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
    
    public String deleteAccessToken (String access_token) throws Exception {
    	access_token = URLEncoder.encode(access_token,"UTF-8"); // 특수문자가 포함되어 있기에 GET으로 데이터 전달할 경우는 encode가 필요함
	    String apiUrl;
	    apiUrl = "https://nid.naver.com/oauth2.0/token?grant_type=delete&";
	    apiUrl += "client_id=" + clientId;
	    apiUrl += "&client_secret=" + clientSecret;
	    apiUrl += "&access_token=" + access_token;
	    apiUrl += "&service_provider=NAVER";
	    String result = null;
	    
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();
			BufferedReader br;
			if(responseCode == 200) { // 정상
			    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else { // 에러
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
		        result = jsonObj.getAsJsonObject().get("result").getAsString();
			}
		}catch (Exception e) {
			e.printStackTrace();
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
