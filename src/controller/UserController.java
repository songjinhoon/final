package controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.ActionAnnotation;
import action.RequestMapping;
import action.RequestMapping.RequestMethod;
import model.User;
import repository.MybatisUserDao;
import util.KakaoAPI;
import util.SHA256;

@SuppressWarnings("serial")
public class UserController extends ActionAnnotation {

	@Override
	public void initProcess(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	@RequestMapping(value = "loginForm", method = RequestMethod.GET)
	public String loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String code = request.getParameter("code");
//		System.out.println("code: " + code);
		KakaoAPI kakao = new KakaoAPI();
		String access_Token = kakao.getAccessToken(code);
//      System.out.println("controller access_token : " + access_Token);
	    HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
	    System.out.println("login Controller : " + userInfo);
	    
	    if (userInfo.get("email") != null) {
	    	HttpSession session = request.getSession();
	        session.setAttribute("userId", userInfo.get("email"));
	        session.setAttribute("access_Token", access_Token);
	    }
		
		return "/WEB-INF/view/user/loginForm.jsp";
	}
	
	@RequestMapping(value = "loginPro", method = RequestMethod.POST)
	public String loginPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "/WEB-INF/view/member/loginPro.jsp";
	}
	
	@RequestMapping(value = "logoutForm", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		KakaoAPI kakao = new KakaoAPI();
	    kakao.kakaoLogout((String)session.getAttribute("access_Token"));
	    session.removeAttribute("access_Token");
	    session.removeAttribute("userId");
	    return "/WEB-INF/view/user/loginForm.jsp";
	}
	//회원가입 폼
	@RequestMapping(value = "joinForm", method = RequestMethod.GET)
	public String joinForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		  return "/WEB-INF/view/user/joinForm.jsp";
	}
	
	//회원가입 처리 (이메일 인증)
	@RequestMapping(value = "joinPro", method = RequestMethod.POST)
	public String joinPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String userId = request.getParameter("id");
		String userPasswd = request.getParameter("password");
		String userName = request.getParameter("name");
		String userEmail = request.getParameter("email");
		String userEmailHash = SHA256.getSHA256(userEmail);
		int userEmailCheck = 0;
		String userPhone =  request.getParameter("phone1") + request.getParameter("phone2") + request.getParameter("phone3");
		String userAddress = request.getParameter("address");
		
		MybatisUserDao service = MybatisUserDao.getInstance();
		
		User user = new User();
		
		user.setUserId(userId);
		user.setUserPassword(userPasswd);
		user.setUserName(userName);
		user.setUserEmail(userEmail);
		user.setUserEmailHash(userEmailHash);
		user.setUserEmailCheck(userEmailCheck);
		user.setUserPhone(userPhone);
		user.setUserAddress(userAddress);
		
		service.joinUser(user);
		session.setAttribute("userId", userId);
		session.setAttribute("userPasswd", userPasswd);
		
		  return "redirect:/user/joinSendEmail";
	}
	
	@RequestMapping(value="joinEmailSend", method = RequestMethod.GET)
	public String joinEmailSend(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		return "/WEB-INF/view/user/joinEmailSend.jsp";
	}
}

