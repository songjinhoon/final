package util;

import java.security.MessageDigest;

public class SHA256 {

	public static String getSHA256(String input) {
		StringBuffer result = new  StringBuffer();
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			//salt는 해시데이터 생성시 원래의 데이터를 파악하기 어렵게 만들어준다.
			byte[] salt = "Final Spring Project!".getBytes();
			digest.reset();
			digest.update(salt);
			byte[] chars = digest.digest(input.getBytes("UTF-8"));
			for(int i=0; i<chars.length; i++) {
				String hex = Integer.toHexString(0xff & chars[i]);
				//8진수의 수가 한 글자라면 앞에 0을 붙여줌.
				if(hex.length() == 1) {
					result.append('0');
				}
				result.append(hex);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}
