package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.ActionAnnotation;
import action.RequestMapping;
import action.RequestMapping.RequestMethod;

@SuppressWarnings("serial")
public class ChatController extends ActionAnnotation {

	@Override
	public void initProcess(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		boolean userIdCheck = false;
		if(session.getAttribute("userId") != null){
			userIdCheck = true;
			request.setAttribute("userIdCheck", userIdCheck);
			request.setAttribute("userName", session.getAttribute("userName")); 
		}else{
			request.setAttribute("userIdCheck", userIdCheck);
		}
	}
	
	@RequestMapping(value = "chatForm", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if((boolean) request.getAttribute("userIdCheck")){
			System.out.println("[ChartController] " + request.getAttribute("userName"));
			return "/WEB-INF/view/chat/chatForm.jsp";
		}else{
			return "/WEB-INF/view/user/loginForm.jsp";
		}
	}
	
}
