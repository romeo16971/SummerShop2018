package com.shosu.webSrvice;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class BaseController {
	protected static final Gson gson= new Gson();

	public HttpServletRequest getHTTPRequest() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		return request;
	}
}
