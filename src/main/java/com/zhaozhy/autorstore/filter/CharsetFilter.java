package com.zhaozhy.autorstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 *处理乱码问题的filter
 * 
 * @author zhaozy
 * 
 */
public class CharsetFilter implements Filter {

	// private Logger logger = Logger.getLogger(getClass());

	FilterConfig config;

	public CharsetFilter() {

	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		/*
		 * String charsetData = StringUtils.defaultString(config
		 * .getInitParameter("charset"), "GBK");
		 * req.setCharacterEncoding(charsetData); chain.doFilter(req, resp);
		 */
		
		/*
		 * //自己的方法,将所有的编码统一为GBK req.setCharacterEncoding("GBK");
		 * resp.setCharacterEncoding("GBK"); chain.doFilter(req, resp);
		 */
		
		//综合，原先的方法可以在配置文件中传入一个参数config
		String charsetData=StringUtils.defaultString(config.getInitParameter("chartset"), "UTF-8");
		req.setCharacterEncoding(charsetData);
		//resp.setCharacterEncoding(charsetData);
		
		chain.doFilter(req, resp);
	}

	public void destroy() {

	}
}
