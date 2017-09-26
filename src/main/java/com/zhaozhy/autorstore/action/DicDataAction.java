package com.zhaozhy.autorstore.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zhaozhy.autorstore.entity.DicData;
import com.zhaozhy.autorstore.entity.DicDataId;
import com.zhaozhy.autorstore.exception.DataAlreadyExistException;
import com.zhaozhy.autorstore.exception.DataNotFoundException;
import com.zhaozhy.autorstore.exception.MannulSuccessException;
import com.zhaozhy.autorstore.form.ValidatorDicDataForm;
import com.zhaozhy.autorstore.sysadmin.ViewBean4;
import com.zhaozhy.autorstore.util.DicDataUtil;
import com.zhaozhy.autorstore.util.PageState;
/**
 * 
 * @Title:			DicDataAction.java
 * @Package:		com.zhaozhy.autorstore.action
 * @Created：	zhaozhy
 * @Date：			2017-5-27   上午08:56:20
 * @Desc:			TODO
 * @Version: 		V1.0
 *
 * @Modified：
 * @Date：
 * @Desc：
 * 
 * @Email : 		zhongyong@qq.com
 */
public class DicDataAction extends BaseAction<DicData> {
	private static final Log log = LogFactory.getLog(DicData.class);
	/**
	 * 
	 * 创建时间：2017-5-27  上午08:57:22
	 * 	创建者：zhaozhy
	 *	方法说明：参数管理页面初始化
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward manageInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DicDataId did=new DicDataId();
		did.setDicLarge("*");
		this.addToRequest(request, did);
		ValidatorDicDataForm vbf = (ValidatorDicDataForm) form;
		this.clearForm(vbf);
		return mapping.findForward("success");
	}

	
	public void addToRequest(HttpServletRequest request,DicDataId did) {
//		DictionaryViews dirViews = new DictionaryViews();
		DicDataUtil dicDataUtil=new DicDataUtil(this.dicDataService);
		
		String footer = "";

		List dataList = dicDataUtil.queryPerPageById(did, new PageState(request));

		List list = new ArrayList();
		Iterator it = dataList.iterator();
		while (it.hasNext()) {
			DicData dic=(DicData)it.next();
			list.add(this.transformDicData(dic));
		}

		footer =dicDataUtil.getFooter();

		request.setAttribute("dataList", list);
		request.setAttribute("footer", footer);

	}
	
	/**
	 * 
	 * 创建时间：2017-6-2  下午02:06:54
	 * 	创建者：zhaozhy
	 *	方法说明：格式化 DicData对象
	 * @param dic
	 * @return
	 */
	public ViewBean4 transformDicData(DicData dic){
		//data1:dicLarge
		//data2:dicValue
		//data3:dicName
		//data4:dicText
		String data1=dic.getId().getDicLarge();
		String data2=dic.getId().getDicValue();
		String data3=dic.getDicName();
		String data4=dic.getDicText();
		ViewBean4 vb=new ViewBean4(data1,data2,data3,data4);
		return vb;
	}
	
	/**
	 * 
	 * 创建时间：2017-5-27  上午08:59:59
	 * 	创建者：zhaozhy
	 *	方法说明：清空form
	 * @param vbf
	 */
	public void clearForm(ValidatorDicDataForm vbf) {

		vbf.setDic_large("");
		vbf.setDic_value("");
		vbf.setDic_name("");
		vbf.setDic_text("");
	}
	
	/**
	 * 
	 * 创建时间：2017-6-2  下午02:17:12
	 * 	创建者：zhaozhy
	 *	方法说明：
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward manage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ValidatorDicDataForm vdf=(ValidatorDicDataForm)form;
		String action =vdf.getAction();
		DicDataId did=new DicDataId();
		did.setDicLarge(vdf.getDic_large());
		did.setDicValue(vdf.getDic_value());
		int num=0;
		if(action!=null){
			if("insert".equals(action)){
				DicData dic=this.dicDataService.findById(did);
				if(dic!=null){
					System.out.println("dicdata is already exist!");
					throw new DataAlreadyExistException("");
				}
				dic=new DicData();
				dic.setId(did);
				dic.setDicName(vdf.getDic_name());
				dic.setDicText(vdf.getDic_text());
				this.dicDataService.save(dic);
				
				num=1;
				this.clearForm(vdf);
			}else if("delete".equals(action)){
				DicData dic=this.dicDataService.findById(did);
				if(dic==null){
					System.out.println("dicdata is null!");
					throw new DataNotFoundException("");
				}
				this.dicDataService.delete(dic);
				
				num=1;
				this.clearForm(vdf);
			}else if("update".equals(action)){
				DicData dic=this.dicDataService.findById(did);
				if(dic==null){
					System.out.println("dicdata is null!");
					throw new DataNotFoundException("");
				}
				dic.setDicName(vdf.getDic_name());
				dic.setDicText(vdf.getDic_text());
				this.dicDataService.update(dic);
				
				num=1;
				this.clearForm(vdf);
			}else if("query".equals(action)){
				this.addToRequest(request, did);
			}
		}
		
		if(num>0){
			System.out.println("Mannul OK!");
			 did=new DicDataId();
			did.setDicLarge("*");
			this.addToRequest(request, did);
			throw new MannulSuccessException("");
		}
		
		return mapping.findForward("success");
	}
	
}
