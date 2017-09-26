package com.zhaozhy.autorstore.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zhaozhy.autorstore.entity.Branch;
import com.zhaozhy.autorstore.entity.Department;
import com.zhaozhy.autorstore.entity.Staffer;
import com.zhaozhy.autorstore.exception.DataNotFoundException;
import com.zhaozhy.autorstore.exception.MannulSuccessException;
import com.zhaozhy.autorstore.exception.PasswordErrorException;
import com.zhaozhy.autorstore.exception.PasswordNotSameException;
import com.zhaozhy.autorstore.exception.SystemErrorException;
import com.zhaozhy.autorstore.form.ValidatorEditPwdForm;
import com.zhaozhy.autorstore.form.ValidatorLoginForm;
import com.zhaozhy.autorstore.sysadmin.DynaMenu;
import com.zhaozhy.autorstore.sysadmin.UserContext;
import com.zhaozhy.autorstore.util.MD5Util;

/**
 * 系统action，包括管理员登陆、登出操作，修改密码操作等
 * 
 * @author zhaozy
 * 
 */
public class SysAction extends BaseAction<Staffer> {
	private static final Log log = LogFactory.getLog(SysAction.class);
	/**
	 * 处理登陆操作
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ValidatorLoginForm loginForm = (ValidatorLoginForm) form;
		String username = loginForm.getUsername();
		String password = loginForm.getPassword();
		Staffer staffer = this.stafferService.findById(username);
		if (staffer == null) {
			System.out.println("staffer is null");
			throw new DataNotFoundException("");
//		} else if (!staffer.getStaPwd().equals(password)) {
			//20170721 加入md5解密验证
		}else if(!MD5Util.validPassword(password, staffer.getStaPwd())){
			throw new PasswordErrorException("");
		}

		// 将当前登陆的职员重新封装成UserContext对象，放到Session中
		UserContext userContext = new UserContext();
		userContext.setStafferId(staffer.getStaId());
		userContext.setStafferName(staffer.getStaName());
		userContext.setStafferLevel(staffer.getStaLevel());
		userContext.setStafferPosition(staffer.getStaPosition());

		userContext.setStafferDepId(staffer.getDepId());

		Department department = departmentService.findById(staffer.getDepId());
		if (department == null) {
			log.error("department is null");
			throw new SystemErrorException("");
		}
		userContext.setStafferDepName(department.getDepName());

		Branch branch = this.branchService.findById(staffer.getBraId().trim());
		if (branch == null) {
			log.error("branch is null");
			throw new SystemErrorException("");
		}
		userContext.setStafferBranchId(branch.getBraId());
		userContext.setStafferBranchName(branch.getBraName());

		//新增前，先把当前sesion中的userContext删除
		this.clearSessionByPro(request, "userContext");
		request.getSession().setAttribute("userContext", userContext);
		
		//清空consume_cart表
		this.consumeCartService.deleteAll();

		return mapping.findForward("success");
	}

	/**
	 * 处理登出操作
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if (request.getSession().getAttribute("userContext") != null) {
			request.getSession().removeAttribute("userContext");
		}

		return mapping.findForward("success");
	}

	/**
	 * 
	 * @CreateDate	2017年9月25日  下午9:50:21
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					当前登录用户修改密码转向实际页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editPwdInit(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		return mapping.findForward("success");
	}
	/**
	 * 处理修改密码操作
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editPwd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ValidatorEditPwdForm vepForm = (ValidatorEditPwdForm) form;
		String oldPassword = vepForm.getOldPassword().trim();
		String newPassword1 = vepForm.getNewPassword1().trim();
		String newPassword2 = vepForm.getNewPassword2().trim();
		// 用于判断操作是否成功的标志
		int num = 0;

		UserContext userContext = (UserContext) request.getSession()
				.getAttribute("userContext");
		String stafferId = userContext.getStafferId().trim();

		Staffer staffer = this.stafferService.findById(stafferId);
		if (staffer == null) {
			// Session中的职员 有问题
			throw new SystemErrorException("");
		}

		if (!newPassword1.equals(newPassword2)) {
			// 输入的两次新密码不一致
			throw new PasswordNotSameException("");
		}
//20170721 增加md5判断
//		if (!oldPassword.equals(staffer.getStaPwd().trim())) {
		if(!MD5Util.validPassword(oldPassword, staffer.getStaPwd())){
			// 输入的旧密码错误
			throw new PasswordErrorException("");
		}
		String password=MD5Util.getEncryptedPwd(newPassword1);
		/* 20170721 密码存入前，进行md5加密*/
		staffer.setStaPwd(password);
		this.stafferService.update(staffer);
		num = 1;

		if (num > 0) {
			// 操作成功，给出消息提示
			throw new MannulSuccessException("");
		}

		return mapping.findForward("success");
	}

	/**
	 * 处理登陆成功后顶部内容显示的请求
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward topFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("success");
	}

	/**
	 * 处理登陆成功后左侧动态菜单的维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dynaMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaMenu dynaMenu = new DynaMenu(depMenuService, menuService);

		UserContext userContext = (UserContext) request.getSession()
				.getAttribute("userContext");

		String menuStr = dynaMenu.menu(userContext.getStafferDepId());

		request.setAttribute("menuStr", menuStr);

		return mapping.findForward("success");
	}

}
