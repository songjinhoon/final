package controller;

import java.io.PrintWriter;
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

import action.ActionAnnotation;
import action.RequestMapping;
import action.RequestMapping.RequestMethod;
import model.User;
import repository.MybatisUserDao;
import util.Gmail;
import util.KakaoAPI;
import util.SHA256;

@SuppressWarnings("serial")
public class UserController extends ActionAnnotation {

	@Override
	public void initProcess(HttpServletRequest request, HttpServletResponse response) {

	}
	@RequestMapping(value = "kakaoLoginForm", method = RequestMethod.GET)
	public String kakaoLoginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		if -> 카카오 계정으로 로그인했을 때, 해당 계정과 일치하는 userId가 존재하는지 체크 후 존재하지 않으면 아래 절차를 받고 존재하면(기존에 가입을 했다면) 바로 세션에 저장후 메인으로 보내줌.
		String code = request.getParameter("code");
		String error = request.getParameter("error"); // 나중에 활용할 수 있음
		request.setAttribute("error", error);
		if(code != null) {
			KakaoAPI kakao = new KakaoAPI();
			String access_Token = kakao.getAccessToken(code);
			HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
			if (userInfo.get("nickname") != null) {
				request.setAttribute("userId", userInfo.get("email"));
		    	request.setAttribute("userName", userInfo.get("nickname"));
		    }
		}
		
		return "/WEB-INF/view/user/kakaoLoginForm.jsp";
	}
	

	@RequestMapping(value = "loginForm", method = RequestMethod.GET)
	public String loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "/WEB-INF/view/user/loginForm.jsp";
	}

	@RequestMapping(value = "loginPro", method = RequestMethod.POST)
	public String loginPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "/WEB-INF/view/member/loginPro.jsp";
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
		String userAddress = request.getParameter("userAddress");

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
			return "redirect:/main/main";
		}

		return "redirect:/main/main";
	}
}
