package controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.ActionAnnotation;
import action.RequestMapping;
import action.RequestMapping.RequestMethod;
import util.KakaoAPI;

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


}

