package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionAnnotation;
import action.RequestMapping;
import action.RequestMapping.RequestMethod;
import util.KakaoAPI;
import util.NaverAPI;

@SuppressWarnings("serial")
public class BoardController extends ActionAnnotation {

	@Override
	public void initProcess(HttpServletRequest request, HttpServletResponse response) {
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		
		
		return "/WEB-INF/view/user/productForm.jsp";
	}

}
