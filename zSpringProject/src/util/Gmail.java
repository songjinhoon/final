package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator{

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		//������ ������ ���ؼ� �������� �̸��� �ּҰ� �ʿ��ϴ�.
		//"oakNutSpring@gmail.com", "oak_nut!" : id,password�̴�.
		return new PasswordAuthentication("oakNutSpring@gmail.com", "oak_nut!");
	}
	
	
}
