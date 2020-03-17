package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator{

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		//메일을 보내기 위해서 관리자의 이메일 주소가 필요하다.
		//"oakNutSpring@gmail.com", "oak_nut!" : id,password이다.
		return new PasswordAuthentication("oakNutSpring@gmail.com", "oak_nut!");
	}
	
	
}
