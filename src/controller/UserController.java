package controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
		String error = request.getParameter("error");
		request.setAttribute("error", error);
		if(code != null) {
//			동의를 했다는거.
			KakaoAPI kakao = new KakaoAPI();
			String access_Token = kakao.getAccessToken(code);
			System.out.println("토큰: " + access_Token);
			HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
			
//			이부분은 다시 체크해보도록 한다.
			if (userInfo.get("email") != null) {
		    	HttpSession session = request.getSession();
		        session.setAttribute("userId", userInfo.get("email"));
		        session.setAttribute("access_Token", access_Token);
		    }
		}
		
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
	    kakao.kakaoLogout((String)session.getAttribute("access_Token"));
	    session.removeAttribute("access_Token");
	    session.removeAttribute("userId");
	    return "/WEB-INF/view/user/loginForm.jsp";
	}
}

