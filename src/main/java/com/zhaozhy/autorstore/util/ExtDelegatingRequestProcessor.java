package com.zhaozhy.autorstore.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;
import org.ecside.util.ECSideUtils;
import org.springframework.web.struts.DelegatingRequestProcessor;

import com.zhaozhy.autorstore.sysadmin.UserContext;

public class ExtDelegatingRequestProcessor extends DelegatingRequestProcessor {

	private static final Log log = LogFactory.getLog(ExtDelegatingRequestProcessor.class);

	@Override
	protected ActionForward processActionPerform(HttpServletRequest request, HttpServletResponse response, Action action, ActionForm form, ActionMapping mapping) throws IOException, ServletException {

		String methodname = "execute";
		//判断DispatchAction是否是所传过来的Action指向对象的父类
		//如果是，则根据请求参数，取得将要执行的方法名称，并且通过反射技术取得这个方法，采用AOP切入！
		//如果不是的话，则证明是执行的是Action的execute方法，返回execute的方法的对象。
		if (MappingDispatchAction.class.isAssignableFrom(action.getClass())) {
			//根据struts配置文件中dispatchAction的Parameter取得要执行的方法名称
//			methodname = request.getParameter(mapping.getParameter());
			methodname=mapping.getParameter();
		}
		String ipStr=request.getRemoteAddr();
		String uriStr=request.getRequestURI();
		String pathStr=request.getServletPath();
		Object obj=request.getSession().getAttribute("userContext");
		UserContext uc=new UserContext();
		if(obj!=null){
			uc=(UserContext)obj;
		}
		StringBuilder sb = new StringBuilder(">>>> DelegatingRequestProcessor logger \r\n");
		sb.append(" [start   ] : " + DateUtil.getDateString17() + " \r\n");
		sb.append(" [class   ] : " + action.getClass() + " \r\n");
		sb.append(" [method  ] : " + methodname + " \r\n");
		if(obj!=null){
			sb.append("[staff]:"+uc.getStafferId()+"--"+uc.getStafferName()+"\r\n");
		}
		sb.append(" [request ] : " + request + " \r\n");
		sb.append("[IP]:"+ipStr+"\r\n");
		sb.append("[URI]+"+uriStr+"\r\n");
		sb.append("[PATH]:"+pathStr+"\r\n");
		sb.append(" [response] : " + response + " \r\n");
		sb.append(" [end     ]: " +DateUtil.getDateString17()+ " <<<<");
		log.info(sb.toString());

		return super.processActionPerform(request, response, action, form, mapping);
	}
	
	
}
