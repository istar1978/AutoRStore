package com.zhaozhy.autorstore.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 
 * @Title				RequestFilter.java
 * @Package		com.zhaozhy.autorstore.filter
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-7-20   下午12:34:13
 * @Desc				处理session失效，页面跳转的filter
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class RequestFilter implements Filter {
	private static final Log log=LogFactory.getLog(RequestFilter.class);
	public void destroy() {
		// TODO Auto-generated method stub  
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpRes = (HttpServletResponse) res;
		HttpSession httpSession = httpReq.getSession();
//		log.info("path=======	"+httpReq.getServletPath());
		if (!httpReq.getServletPath().endsWith("/login.do")&&!httpReq.getServletPath().endsWith("/login.jsp")) {
			//判断登陆页         
			if (httpSession.getAttribute("userContext") == null) {
				log.info("session 失效。。。");
				//httpRes.sendRedirect("admin_login.jsp");
				httpSession.invalidate();
				httpRes.setContentType("text/html;charset=utf-8");
				PrintWriter out = httpRes.getWriter();
				out.println("<script language='javascript' type='text/javascript'>");
				out.println("alert('由于您长时间没有操作导致用户登录信息失效，请您重新登录');window.parent.location.href='" + httpReq.getContextPath() + "/login.jsp'");
				out.println("</script>");
			} else {
				chain.doFilter(req, res);
			}
		} else {
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
}