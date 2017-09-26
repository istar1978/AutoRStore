package com.zhaozhy.autorstore.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.actions.MappingDispatchAction;

import com.zhaozhy.autorstore.form.ValidatorAssComboForm;
import com.zhaozhy.autorstore.form.ValidatorAssociatorForm;
import com.zhaozhy.autorstore.form.ValidatorSalesForm;
import com.zhaozhy.autorstore.service.AssComboService;
import com.zhaozhy.autorstore.service.AssConsumeService;
import com.zhaozhy.autorstore.service.AssRechargeService;
import com.zhaozhy.autorstore.service.AssociatorService;
import com.zhaozhy.autorstore.service.BranchService;
import com.zhaozhy.autorstore.service.ConsumeCartService;
import com.zhaozhy.autorstore.service.ConsumeListService;
import com.zhaozhy.autorstore.service.DepMenuService;
import com.zhaozhy.autorstore.service.DepartmentService;
import com.zhaozhy.autorstore.service.DicDataService;
import com.zhaozhy.autorstore.service.ItemMateService;
import com.zhaozhy.autorstore.service.MaterialService;
import com.zhaozhy.autorstore.service.MenuService;
import com.zhaozhy.autorstore.service.RepairItemService;
import com.zhaozhy.autorstore.service.SerialGenService;
import com.zhaozhy.autorstore.service.StafferService;
import com.zhaozhy.autorstore.service.Static2Service;
import com.zhaozhy.autorstore.service.Static3Service;
import com.zhaozhy.autorstore.service.Static4Service;
/**
 * 
 * @Title				BaseAction.java
 * @Package		com.zhaozhy.autorstore.action
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-16   下午04:47:47
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class BaseAction<T> extends MappingDispatchAction {

	protected AssComboService assComboService;
	protected AssConsumeService assConsumeService;
	protected AssociatorService associatorService;
	protected AssRechargeService assRechargeService;
	protected DicDataService dicDataService;
	protected BranchService branchService;
	protected ConsumeListService consumeListService;
	protected DepartmentService departmentService;
	protected DepMenuService depMenuService;
	protected ItemMateService itemMateService;
	protected MaterialService materialService;
	protected MenuService menuService;
	protected RepairItemService repairItemService;
	protected SerialGenService serialGenService;
	protected StafferService stafferService;
	protected Static2Service static2Service;
	protected Static3Service static3Service;
	protected Static4Service static4Service;
	protected ConsumeCartService consumeCartService;

	public ConsumeCartService getConsumeCartService() {
		return consumeCartService;
	}

	public void setConsumeCartService(ConsumeCartService consumeCartService) {
		this.consumeCartService = consumeCartService;
	}

	public AssRechargeService getAssRechargeService() {
		return assRechargeService;
	}

	public void setAssRechargeService(AssRechargeService assRechargeService) {
		this.assRechargeService = assRechargeService;
	}

	public Static2Service getStatic2Service() {
		return static2Service;
	}

	public void setStatic2Service(Static2Service static2Service) {
		this.static2Service = static2Service;
	}

	public Static3Service getStatic3Service() {
		return static3Service;
	}

	public void setStatic3Service(Static3Service static3Service) {
		this.static3Service = static3Service;
	}

	public Static4Service getStatic4Service() {
		return static4Service;
	}

	public void setStatic4Service(Static4Service static4Service) {
		this.static4Service = static4Service;
	}

	public StafferService getStafferService() {
		return stafferService;
	}

	public void setStafferService(StafferService stafferService) {
		this.stafferService = stafferService;
	}

	public SerialGenService getSerialGenService() {
		return serialGenService;
	}

	public void setSerialGenService(SerialGenService serialGenService) {
		this.serialGenService = serialGenService;
	}

	public RepairItemService getRepairItemService() {
		return repairItemService;
	}

	public void setRepairItemService(RepairItemService repairItemService) {
		this.repairItemService = repairItemService;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public MaterialService getMaterialService() {
		return materialService;
	}

	public void setMaterialService(MaterialService materialService) {
		this.materialService = materialService;
	}

	public ItemMateService getItemMateService() {
		return itemMateService;
	}

	public void setItemMateService(ItemMateService itemMateService) {
		this.itemMateService = itemMateService;
	}

	public DepMenuService getDepMenuService() {
		return depMenuService;
	}

	public void setDepMenuService(DepMenuService depMenuService) {
		this.depMenuService = depMenuService;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public ConsumeListService getConsumeListService() {
		return consumeListService;
	}

	public void setConsumeListService(ConsumeListService consumeListService) {
		this.consumeListService = consumeListService;
	}

	public BranchService getBranchService() {
		return branchService;
	}

	public void setBranchService(BranchService branchService) {
		this.branchService = branchService;
	}

	public DicDataService getDicDataService() {
		return dicDataService;
	}

	public void setDicDataService(DicDataService dicDataService) {
		this.dicDataService = dicDataService;
	}

	public AssociatorService getAssociatorService() {
		return associatorService;
	}

	public void setAssociatorService(AssociatorService associatorService) {
		this.associatorService = associatorService;
	}

	public AssComboService getAssComboService() {
		return assComboService;
	}

	public void setAssComboService(AssComboService assComboService) {
		this.assComboService = assComboService;
	}

	public AssConsumeService getAssConsumeService() {
		return assConsumeService;
	}

	public void setAssConsumeService(AssConsumeService assConsumeService) {
		this.assConsumeService = assConsumeService;
	}
	/**
	 * 
	 * @CreateDate	2017-6-19  下午12:32:53
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					判断当前session中是否存在名为pro的键值，若有，删除，为新增做准备
	 * @param request
	 * @param pro
	 */
	public void clearSessionByPro(HttpServletRequest request,String pro){
		Object sessObj=request.getSession().getAttribute(pro);
		if(sessObj!=null&&!sessObj.toString().equals("")){
			request.getSession().removeAttribute(pro);
		}
	}
	
	/**
	 * 
	 * @CreateDate	2017-6-22  下午06:17:58
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					判断session和request是否存在名为pro的键值，若有，删除，初始化数据
	 * @param request
	 * @param pro
	 */
	public void clearAllByPro(HttpServletRequest request,String pro){
		Object reqObj=request.getAttribute(pro);
		if(reqObj!=null&&!reqObj.toString().equals("")){
			request.removeAttribute(pro);
		}
		this.clearSessionByPro(request, pro);
	}
	/**
	 * 
	 * @CreateDate	2017-6-21  上午09:27:46
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					TODO
	 * @param vacf
	 */
	public void clearAssComboForm(ValidatorAssComboForm vacf){
		vacf.setCom_id("");
		vacf.setCom_name("");
		vacf.setRep_id("");
		vacf.setAss_id("");
		vacf.setCom_sdate("");
		vacf.setCom_edate("");
		vacf.setCom_date("");
		vacf.setCom_time("");
		vacf.setCom_desc("");
		vacf.setCom_item("");
		vacf.setCom_price("");
		vacf.setCom_stat("");
	}
	/**
	 * 
	 * @CreateDate	2017-6-21  上午09:31:07
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					TODO
	 * @param vaf
	 */
	public void clearAssociatorForm(ValidatorAssociatorForm vaf){
		vaf.setAction("");
		vaf.setA_id("");
		vaf.setA_password("");
		vaf.setA_addr("");
		vaf.setA_balance("");
		vaf.setA_birthday("");
		vaf.setA_carno("");
		vaf.setA_gender("");
		vaf.setA_level("");
		vaf.setA_name("");
		vaf.setA_no("");
		vaf.setA_pbalance("");
		vaf.setA_stat("");
		vaf.setA_tel("");
		vaf.setOldPassword("");
		vaf.setNewPassword1("");
		vaf.setNewPassword2("");
	}
	
	/**
	 * 
	 * @CreateDate	2017-7-3  下午08:43:24
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					TODO
	 * @param vform
	 */
	public void clearSalesForm(ValidatorSalesForm vform){
		vform.setAss_id("");
		vform.setCart_num("");
		vform.setCom_id("");
		vform.setMat_id("");
		vform.setRep_id("");
		vform.setYorn("");
	}
}
