package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionAnnotation;
import action.RequestMapping;
import action.RequestMapping.RequestMethod;

@SuppressWarnings("serial")
public class MainController extends ActionAnnotation {

	@Override
	public void initProcess(HttpServletRequest request, HttpServletResponse response) {
	}
	
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response) {

		return "/WEB-INF/view/main/main.jsp";
	}
}
