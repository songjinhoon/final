package controller;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMessages;

import action.ActionAnnotation;
import action.RequestMapping;
import action.RequestMapping.RequestMethod;
import model.User;
import repository.MybatisUserDao;
import util.Gmail;
import util.KakaoAPI;
import util.NaverAPI;
import util.SHA256;

@SuppressWarnings("serial")
public class UserController extends ActionAnnotation {

	@Override
	public void initProcess(HttpServletRequest request, HttpServletResponse response) {

	}
	
	@RequestMapping(value = "selectJoinForm", method = RequestMethod.GET)
	public String selectJoinForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String naverApiUrl = NaverAPI.getApiUrl();
	    String kakaoApiUrl = KakaoAPI.getApiUrl();
	    request.setAttribute("naverApiUrl", naverApiUrl);
		request.setAttribute("kakaoApiUrl", kakaoApiUrl);
		
		return "/WEB-INF/view/user/selectJoinForm.jsp";
	}
	
	@RequestMapping(value = "kakaoLoginForm", method = RequestMethod.GET)
	public String kakaoLoginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String code = request.getParameter("code");
		String error = request.getParameter("error");
		request.setAttribute("error", error);
		if(code != null) {
			KakaoAPI kakao = new KakaoAPI();
			String access_Token = kakao.getAccessToken(code);
			HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
			if (userInfo.get("nickname") != null) {
				request.setAttribute("userId", userInfo.get("email"));
		    	request.setAttribute("userName", userInfo.get("nickname"));
//		    	여기서 디비를 통해 id체크
		    }
		}
		
		return "/WEB-INF/view/user/apiLoginForm.jsp";
	}
	
	@RequestMapping(value = "naverLoginForm", method = RequestMethod.GET)
	public String naverLoginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		NaverAPI naverAPI = new NaverAPI();
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String error = request.getParameter("error");
		String error_description = request.getParameter("error_description");
//		네이버 로그인 정보와 정보제공 동의 과정 완료 및 실패
		if(code != null && state != null){
			String access_token = naverAPI.getAccessToken(code, state);
			HashMap<String, String> userInfo = naverAPI.getUserInfo(access_token);
			request.setAttribute("userId", userInfo.get("userId"));
			request.setAttribute("userName", userInfo.get("userName"));
//			토큰 삭제
			String result = naverAPI.deleteAccessToken(access_token);
			if(result.equals("success")){
				System.out.println("- 토큰 삭제 성공 -");
			}else{
				System.out.println("- 토큰 삭제 실패 -");
			}
//			여기서 디비를 통해 ID체크하고 기존에 가입을 했었던 사람이라면 메인으로 보내주고 아니면 apiloginform ㅇㅋ?
			MybatisUserDao service = MybatisUserDao.getInstance();
			System.out.println("userId: " + userInfo.get("userId"));
			System.out.println("userName: " + userInfo.get("userName"));
			int check = service.getUserIdCheck(userInfo.get("userId"));
//			기존 아이디가 존재하지않는다면
			if(check == 0){
				return "/WEB-INF/view/user/apiLoginForm.jsp";
			}else{
				return "redirect:/main/main";
			}
		}else{
			System.out.println("네이버 아이디 로그인 인증 실패");
			System.out.println("에러코드: " + error);
			System.out.println("에러메시지: " + error_description);
			
			return "redirect:/main/main";
		}
	}

	@RequestMapping(value = "loginForm", method = RequestMethod.GET)
	public String loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String naverApiUrl = NaverAPI.getApiUrl();
	    String kakaoApiUrl = KakaoAPI.getApiUrl();
	    request.setAttribute("naverApiUrl", naverApiUrl);
		request.setAttribute("kakaoApiUrl", kakaoApiUrl);
		
		return "/WEB-INF/view/user/loginForm.jsp";
	}

	//로그인 처리
	@RequestMapping(value = "loginPro", method = RequestMethod.POST)
	public String loginPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		User user = new User();
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");
		String userPasswd = request.getParameter("userPasswd");
		user.setUserId(userId);
		user.setUserPasswd(userPasswd);
		
		MybatisUserDao service = MybatisUserDao.getInstance();
		userId = service.Login(user);
		
		request.setAttribute("userId", userId);
		
		PrintWriter script = response.getWriter();
		
		if(userId == null) {
			script.println("<script>");
			script.println("alert('로그인에 실패하셨습니다. \\n다시 로그인해주세요.');");
			script.println("location.href = '/zSpringProject/user/loginForm'");
			script.println("</script>");
			script.close();		
		}else if(userId != null) {
			session.setAttribute("userId", userId);
			script.println("<script>");
			script.println("alert('로그인되었습니다.');");
			script.println("location.href = '/zSpringProject/main/main'");
			script.println("</script>");
			script.close();
		}
		return "";
	}

	@RequestMapping(value = "logoutForm", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		KakaoAPI kakao = new KakaoAPI();
		kakao.kakaoLogout((String) session.getAttribute("access_Token"));
		session.removeAttribute("access_Token");
		session.removeAttribute("userId");
		return "/WEB-INF/view/user/loginForm.jsp";
	}

	// 회원가입 폼
	@RequestMapping(value = "joinForm", method = RequestMethod.GET)
	public String joinForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "/WEB-INF/view/user/joinForm.jsp";
	}

	// 회원가입 처리 (이메일 인증)
	@RequestMapping(value = "joinPro", method = RequestMethod.POST)
	public String joinPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");
		String userPasswd = request.getParameter("userPasswd");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		String userEmailHash = SHA256.getSHA256(userEmail);
		int userEmailCheck = 0;
		String userPhone = request.getParameter("phone1") + request.getParameter("phone2")
				+ request.getParameter("phone3");
		String userAddress = request.getParameter("userAddress") + " " +request.getParameter("detailAddress");

		MybatisUserDao service = MybatisUserDao.getInstance();
		User user = new User();
		user.setUserId(userId);
		user.setUserPasswd(userPasswd);
		user.setUserName(userName);
		user.setUserEmail(userEmail);
		user.setUserEmailHash(userEmailHash);
		user.setUserEmailCheck(userEmailCheck);
		user.setUserPhone(userPhone);
		user.setUserAddress(userAddress);
		service.joinUser(user);
		session.setAttribute("userId", userId);

		return "redirect:/user/joinSendEmail";
	}

	// 인증메일 보내기
	@RequestMapping(value = "joinSendEmail", method = RequestMethod.GET)
	public String joinSendEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		MybatisUserDao service = MybatisUserDao.getInstance();
		HttpSession session = request.getSession();

		String userId = null;

		// 세션에 저장된 id가 null이 아니라면 값 저장
		if (session.getAttribute("userId") != null) {
			userId = (String) session.getAttribute("userId");
		}

		if (userId == null) {
			// userId가 없다면 로그인폼으로 이동
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 해주세요.');");
			script.println("location.href = '/zSpringProject/user/loginForm'");
			script.println("</script>");
			script.close();

			return "redirect:/user/loginForm";
		}

		int emailChecked = service.getUserEmailChecked(userId);
		
		if (emailChecked == 1) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 인증 된 회원입니다.');");
			script.println("location.href = '/zSpringProject/main/main'");
			script.println("</script>");
			script.close();

			return "redirect:/main/main";
		}else if (emailChecked == 0) {
			// 사용자에게 보낼 이메일 내용을 입력
			String host = "http://localhost:8080/zSpringProject/user/";
			String from = "oakNutSpring@gmail.com";
			String to = service.getUserEmail(userId);

			String subject = "도토리마켓 회원가입 이메일 인증메일입니다!";
			String content = "다음 링크에 접속하여 이메일 확인을 진행해주세요:D" +

					"<a href='" + host + "joinEmailCheckPro?code=" + new SHA256().getSHA256(to) + "'>이메일 인증하기</a>";

			// SMTP에 접속하기 위한 정보를 입력하는 부분
			Properties p = new Properties();
			p.put("mail.smtp.user", from);
			p.put("mail.smtp.host", "smtp.googlemail.com");
			p.put("mail.smtp.port", "465");
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.debug", "true");
			p.put("mail.smtp.socketFactory.port", "465");
			p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.socketFactory.fallback", "false");

			try {

				Authenticator auth = new Gmail();
				Session ses = Session.getInstance(p, auth);
				ses.setDebug(true);
				MimeMessage msg = new MimeMessage(ses);
				msg.setSubject(subject);
				Address fromAddr = new InternetAddress(from);
				msg.setFrom(fromAddr);
				Address toAddr = new InternetAddress(to);
				msg.addRecipient(Message.RecipientType.TO, toAddr);
				msg.setContent(content, "text/html;charset=UTF-8");
				Transport.send(msg);
			} catch (Exception e) {

				e.printStackTrace();
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('오류가 발생했습니다..');");
				script.println("history.back();");
				script.println("</script>");
				script.close();

				return "redirect:/user/joinForm";

			}
			return "/WEB-INF/view/user/joinSendEmail.jsp";
		}
		return "/WEB-INF/view/user/joinSendEmail.jsp";
	}
	
	
	//메일 인증 확인
	@RequestMapping(value="joinEmailCheckPro", method=RequestMethod.GET)
	public String joinEmailCheckPro(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		String code = request.getParameter("code");
		
		System.out.println(code);
		
		String userId = null;
		MybatisUserDao service = MybatisUserDao.getInstance();
		
		if(session.getAttribute("userId") != null) {
			userId = (String)session.getAttribute("userId");
		}else if(userId == null) {

			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 해주세요.');");
			script.println("location.href = '/zSpringProject/user/loginForm'");
			script.println("</script>");
			script.close();
		}
		
		String userEmail = service.getUserEmail(userId);
		
		//인증코드와 디비에 저장된 코드 확인
		boolean rightCode = (new SHA256().getSHA256(userEmail).equals(code)) ? true : false;
		System.out.println(rightCode);
		if(rightCode == true) {
			service.setUserEmailChecked(userId);
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('인증에 성공했습니다.');");
			script.println("location.href = '/zSpringProject/main/main'");
			script.println("</script>");
			script.close();		
		}else {
			
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 코드입니다.');");
			script.println("location.href = '/zSpringProject/main/main'");
			script.println("</script>");
			script.close();		
		}

		return "redirect:/main/main";
	}
	
	//ID 중복체크 창
	@RequestMapping(value="confirmId", method = RequestMethod.GET)
	public String confirmId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		String userId = request.getParameter("userId");
		//System.out.println(userId);
		MybatisUserDao service = MybatisUserDao.getInstance();
		
		int userIdChecked = service.getUserIdCheck(userId);
		System.out.println(userIdChecked+"-------------------Controller");
		
		//el로 사용할 수 있게 보냄
		request.setAttribute("userIdChecked", userIdChecked);
		request.setAttribute("userId", userId);
		return "/WEB-INF/view/user/confirmId.jsp";
	}
}
