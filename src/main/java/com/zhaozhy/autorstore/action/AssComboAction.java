package com.zhaozhy.autorstore.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ecside.table.limit.FilterSet;
import org.ecside.table.limit.Limit;
import org.ecside.table.limit.Sort;
import org.ecside.util.RequestUtils;

import com.zhaozhy.autorstore.entity.AssCombo;
import com.zhaozhy.autorstore.entity.Associator;
import com.zhaozhy.autorstore.entity.DicData;
import com.zhaozhy.autorstore.entity.DicDataId;
import com.zhaozhy.autorstore.entity.RepairItem;
import com.zhaozhy.autorstore.exception.AssociatorNotInUseException;
import com.zhaozhy.autorstore.exception.DataNotFoundException;
import com.zhaozhy.autorstore.exception.MannulSuccessException;
import com.zhaozhy.autorstore.form.ValidatorAssComboForm;
import com.zhaozhy.autorstore.sysadmin.DictionaryViews;
import com.zhaozhy.autorstore.sysadmin.ViewBean4;
import com.zhaozhy.autorstore.util.DicDataUtil;
import com.zhaozhy.autorstore.util.ExceptionUtil;

/**
 * 
 * @Title AssComboAction.java
 * @Package com.zhaozhy.autorstore.action
 * @Created zhaozhy (zhongyong@qq.com)
 * @Date 2017-6-6 下午08:48:21
 * @Desc TODO
 * @Version V1.0
 * 
 * @Modified
 * @Date
 * @Desc
 */
public class AssComboAction extends BaseAction<AssCombo> {
	private static final Log log = LogFactory.getLog(AssComboAction.class);
	

	/**
	 * 
	 * @CreateDate	2017-6-6  下午08:55:03
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					TODO
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ValidatorAssComboForm vform=(ValidatorAssComboForm)form;
		DictionaryViews dv=new DictionaryViews();
		String ass_id=vform.getAss_id();
		Associator ass=this.associatorService.findById(ass_id);
		if(ass==null){
//			System.out.println("===associator is null=====");
			log.error("===associator is null=====");
			throw new DataNotFoundException("");
		}
		//必须激活
		if(ass.getAssStat().equals(DicDataUtil.DICDATA_000001)){
//			System.out.println("====associator is not jihuo====");
			log.error("====associator is not jihuo====");
			throw new AssociatorNotInUseException("");
		}
		
//		request.setAttribute("ass", ass);
		request.getSession().setAttribute("ass", ass);
		//所属项目列表,有效
		List repList=this.repairItemService.findByRepStat(DicDataUtil.DICDATA_000000);
		request.getSession().setAttribute("cRepIdList", dv.getRepairItemSelect(repList));
		//套餐类别
		DicDataId did = new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0008);
		List<DicData> ddList = this.dicDataService.findByIdProperty(did);
		List dCategoryList = dv.getSelectList(ddList);

		request.setAttribute("cClassifyList", dCategoryList);
		request.getSession().setAttribute("cClassifyList", dCategoryList);
		return mapping.findForward("success");
	}

	/**
	 * 
	 * @CreateDate	2017-6-6  下午08:55:08
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					TODO
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward assComboAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ValidatorAssComboForm vform=(ValidatorAssComboForm)form;
		//com_id 和com_name不再页面填写，由系统自动生成；com_name暂取维修项目名称
		int num=this.assComboService.addAssCombo(vform);
		
		if(num>0){
//			System.out.println("===mannul ok===");
			log.error("AssComboAction add ok!");
			throw new MannulSuccessException("");
		}
		return mapping.findForward("success");
	}

	/**
	 * 
	 * @CreateDate	2017-6-10  下午07:37:51
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					TODO
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward assComboQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ValidatorAssComboForm vform=(ValidatorAssComboForm)form;
		String ass_id=vform.getAss_id();
		
		List<AssCombo> acList=this.assComboService.getAllComboByAssid(ass_id);
		List<ViewBean4> dataList=new ArrayList<ViewBean4>();
		for(AssCombo ac:acList){
			dataList.add(this.transformAssCombo(ac));
		}
		
		int currentPage = 1;
		int lineSize = 10;// 每页显示的记录数
		int allRecorders = 0;// 数据库中总的记录数

		Limit limit = RequestUtils.getLimit(request);
		Sort sort = limit.getSort();

		Map sortValueMap = sort.getSortValueMap();
		FilterSet filterSet = limit.getFilterSet();

		Map filterPropertyMap = filterSet.getPropertyValueMap();
		allRecorders=acList.size();
		int[] rowStartEnd = RequestUtils.getRowStartEnd(request, allRecorders,
				lineSize);
		if (lineSize > allRecorders) {
			currentPage = 1;
		}
		
		request.setAttribute("data", dataList);
		return mapping.findForward("success");
	}

	/**
	 * 
	 * @CreateDate	2017-6-10  下午08:44:05
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					组装在页面展现的asscombo
	 * @param assCombo
	 * @return
	 */
	private ViewBean4 transformAssCombo(AssCombo assCombo){
		//data1:comId 套餐编号        data2:comName 套餐名称          data3:repId 维修项目编号
		//data4:assId 会员卡号          data5:comSdate 起始日期			data6:comEdate 结束日期
		//data7:comDate 办理日期	data8:comTime 剩余套餐次数	data9:comDesc 优惠套餐详情
		//data10:comItem 优惠套餐大类		data11:comPrice 购买金额		data12:comStat 状态
		
		//data13:repName 维修项目名称		data14:assPhone 会员电话号码		data15:起始日期(2010-01-01)
		//data16:结束日期(2010-01-01)	data17:办理日期(2010-01-01)	data18:大类名称
		//data19:状态(中文)
		String data1=assCombo.getComId();
		String data2=assCombo.getComName();
		String data3=assCombo.getRepId();
		String data4=assCombo.getAssId();
		Date data5=assCombo.getComSdate();
		Date data6=assCombo.getComEdate();
		Date data7=assCombo.getComDate();
		Integer data8=assCombo.getComTime();
		String data9=assCombo.getComDesc();
		String data10=assCombo.getComItem();
		BigDecimal data11=assCombo.getComPrice();
		String data12=assCombo.getComStat();
		
		RepairItem rt=this.repairItemService.findById(data3);
		ExceptionUtil.throwDataNotFoundException(rt, "RepairItem not found", log);
		String data13=data3+":"+rt.getRepName();
		
		Associator ass=this.associatorService.findById(data4);
		ExceptionUtil.throwDataNotFoundException(ass, "associator not found", log);
		String data14=ass.getAssPhone();
		
		String data15=DateFormatUtils.format(data5, "yyyy-MM-dd");
		String data16=DateFormatUtils.format(data6, "yyyy-MM-dd");
		String data17=DateFormatUtils.format(data7, "yyyy-MM-dd");
		String data18=this.dicDataService.findById(new DicDataId(DicDataUtil.DICDATA_0008,data10)).getDicName().trim();
		String data19=this.dicDataService.findById(new DicDataId(DicDataUtil.DICDATA_0000,data12)).getDicName().trim();
		
		return new ViewBean4(data1,data2,data3,data4,data5.toString(),data6.toString(),data7.toString(),
				data8.toString(),data9,data10,data11.toString(),data12,data13,data14,data15,data16,data17,data18,data19);
		
	}
	
}
