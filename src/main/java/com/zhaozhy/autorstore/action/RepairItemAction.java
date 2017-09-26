package com.zhaozhy.autorstore.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ecside.util.ServletUtils;

import com.zhaozhy.autorstore.entity.DicData;
import com.zhaozhy.autorstore.entity.DicDataId;
import com.zhaozhy.autorstore.entity.RepairItem;
import com.zhaozhy.autorstore.exception.DataAlreadyExistException;
import com.zhaozhy.autorstore.exception.MannulSuccessException;
import com.zhaozhy.autorstore.form.ValidatorRepairItemAddForm;
import com.zhaozhy.autorstore.sysadmin.DictionaryViews;
import com.zhaozhy.autorstore.sysadmin.UserContext;
import com.zhaozhy.autorstore.sysadmin.ViewBean4;
import com.zhaozhy.autorstore.util.DicDataUtil;

/**
 * 
 * @Title RepairItemAction.java
 * @Package com.zhaozhy.autorstore.action
 * @Created zhaozhy
 * @Date 2017-6-6 下午02:35:47
 * @Desc TODO
 * @Version V1.0
 * 
 * @Modified
 * @Date
 * @Desc
 * 
 * @Email zhongyong@qq.com
 */
public class RepairItemAction extends BaseAction<RepairItem> {

	private static final Log log = LogFactory.getLog(RepairItem.class);
	/**
	 * 
	 * @CreateDate 2017-6-6 下午02:37:32
	 * @Author zhaozhy zhongyong@qq.com
	 * @Desc TODO
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward repairItemAddInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		this.addJspSelectInit(request);

		return mapping.findForward("success");
	}

	/**
	 * 
	 * @CreateDate 2017-6-6 下午02:36:46
	 * @Author zhaozhy zhongyong@qq.com
	 * @Desc TODO
	 * @param vform
	 */
	private void clearForm(ValidatorRepairItemAddForm vform) {
		vform.setAction("");
		vform.setRep_id("");
		vform.setRep_name("");
		vform.setRep_classify("");
		vform.setRep_money("");
		vform.setRep_stat("");
	}

	/**
	 * 
	 * @CreateDate	2017-6-29  下午10:54:51
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					使用ecside列表展示RepairItem
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward repairItemList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<RepairItem> srcList=this.repairItemService.findAll();
		
		List<ViewBean4> dataList=new ArrayList<ViewBean4>();
		for(RepairItem ri:srcList){
			dataList.add(this.transformRepairItem(ri));
		}
		request.setAttribute("dataList", dataList);
		return mapping.findForward("success");
	}
	
	/**
	 * 
	 * @CreateDate	2017-7-1  下午01:45:52
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					封装RepairItem类，用于页面展现
	 * @param ri
	 * @return
	 */
	private ViewBean4 transformRepairItem(RepairItem ri){
		//data1:repId项目编号		data2:repName项目名称				data3:repClassify分类
		//data4:repMoney价格		data5:repStat 状态
		//data6:分类名称					data7:状态(中文)
		String data1=ri.getRepId();
		String data2=ri.getRepName();
		String data3=ri.getRepClassify();
		String data4=ri.getRepMoney().toPlainString();
		String data5=ri.getRepStat();
		String data6=data3+":"+this.dicDataService.findById(new DicDataId(DicDataUtil.DICDATA_0008,data3)).getDicName();
		String data7=data5+":"+this.dicDataService.findById(new DicDataId(DicDataUtil.DICDATA_0000,data5)).getDicName();
		return new ViewBean4(data1,data2,data3,data4,data5,data6,data7);
	}

	/**
	 * 
	 * @CreateDate 2017-6-6 下午02:56:57
	 * @Author zhaozhy zhongyong@qq.com
	 * @Desc TODO
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward repairItemAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ValidatorRepairItemAddForm vform=(ValidatorRepairItemAddForm)form;
		String repName=vform.getRep_name();
		String repClassify=vform.getRep_classify();
		String repMoney=vform.getRep_money();
		String repStat=vform.getRep_stat();
		
		RepairItem item=new RepairItem();
		item.setRepName(repName);
		item.setRepClassify(repClassify);
		item.setRepMoney(new BigDecimal(repMoney));
		item.setRepStat(repStat);
		
		int num=this.repairItemService.addRepairItem(item);
		
		if (num > 0) {
			this.addJspSelectInit(request);
			throw new MannulSuccessException("");
		}
		
		this.addJspSelectInit(request);
//		this.clearForm(vform);
		return mapping.findForward("success");
	}
	
	private void addJspSelectInit(HttpServletRequest request){
		DictionaryViews dirViews = new DictionaryViews();

		DicDataId did = new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0008);
		List<DicData> ddList = this.dicDataService.findByIdProperty(did);
		List dCategoryList = dirViews.getSelectList(ddList);

		request.setAttribute("rClassifyList", dCategoryList);
		request.getSession().setAttribute("rClassifyList", dCategoryList);
		// 加入状态list
		did = new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0000);
		List<DicData> statList = this.dicDataService.findByIdProperty(did);
		request.setAttribute("rStateList", dirViews.getSelectListDefault(statList));
		request.getSession().setAttribute("rStateList", dirViews.getSelectListDefault(statList));
	}

	/**
	 * 
	 * @CreateDate	2017年8月7日  下午9:06:19
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					准备页面数据
	 * @param request
	 */
	public void addToRequestECSide(HttpServletRequest request){
		DicDataId did = new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0008);
		List<DicData> ddList = this.dicDataService.findByIdProperty(did);
		Map dCategoryMap = DictionaryViews.getSelectMap(ddList);

		request.setAttribute("repClassifyMap", dCategoryMap);

	}
	/**
	 * 
	 * @CreateDate	2017年8月7日  下午9:05:27
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					维修项目管理页面 数据初始化
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward repairItemManageInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.addToRequestECSide(request);
		
		return mapping.findForward("success");
	}
	
	/**
	 * 
	 * @CreateDate	2017年8月7日  下午9:07:30
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					维修项目管理 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward repairItemManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("RepairItemAction-->repairItemManage()");
		List list=ServletUtils.getParameterMaps(request);
		Map paramap=(Map)list.get(0);
		String action=(String)paramap.get("action");
		String repName=(String)paramap.get("rep_name");
		String repClassify=(String)paramap.get("rep_classify");
		String repMoney=(String)paramap.get("rep_money");
		
		BigDecimal rm=null;
		if(StringUtils.isNotBlank(repMoney)){
			rm=new BigDecimal(repMoney);
		}
		RepairItem ri=new RepairItem();
		ri.setRepClassify(repClassify);
		ri.setRepMoney(rm);
		ri.setRepName(repName);
		ri.setRepStat(DicDataUtil.DICDATA_000000);//默认有效
		
		int num=0;
		List<RepairItem> riList=null;
		if(action.equals("insert")){
			num=this.repairItemService.addRepairItem(ri);
			riList=this.repairItemService.findAll();
		}else if(action.equals("query")){
			riList=this.repairItemService.findAllByExample(ri);
		}else if(action.equals("update")){
			String repId=(String)paramap.get("repId");
			ri.setRepId(repId);
			this.repairItemService.update(ri);
			riList=this.repairItemService.findAll();
			num=1;
		}
		
		List dataList=new ArrayList();
		for(RepairItem tmp:riList){
			dataList.add(this.transformRepairItem(tmp));
		}
		request.setAttribute("dataList", dataList);
		this.addToRequestECSide(request);
		request.setAttribute("totalRows", dataList.size());
		if(num>0){
			log.error("mannul ok!");
			throw new MannulSuccessException("");
		}
		return mapping.findForward("success");
	}

	/**
	 * 
	 * @CreateDate	2017年8月7日  下午9:08:02
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					ECSide 列表删除列操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doAjaxRepairItemDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List list=ServletUtils.getParameterMaps(request);
		Map paramap=(Map)list.get(0);
		String recordKey=(String)paramap.get("recordKey");
		this.repairItemService.delete(this.repairItemService.findById(recordKey));
		
		ServletUtils.defaultAjaxResopnse(list, new int[]{1}, request, response);
		return null;
	}
}
