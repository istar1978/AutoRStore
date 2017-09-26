package com.zhaozhy.autorstore.sysadmin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zhaozhy.autorstore.entity.Branch;
import com.zhaozhy.autorstore.entity.Department;
import com.zhaozhy.autorstore.entity.DicData;
import com.zhaozhy.autorstore.entity.Material;
import com.zhaozhy.autorstore.entity.RepairItem;
import com.zhaozhy.autorstore.service.BranchService;
import com.zhaozhy.autorstore.service.DepartmentService;
import com.zhaozhy.autorstore.service.MaterialService;

/**
 * 提供数据字典查询结果
 * 
 */
public class DictionaryViews  implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private BranchService branchService;
	private DepartmentService departmentService;

	/**
	 * 无参构造函数
	 */
	public DictionaryViews() {
		super();
	}

	/**
	 * 包含所有参数的构造函数
	 * 
	 * @param branchDAO
	 * @param departmentDAO
	 */
	public DictionaryViews(BranchService branchService, DepartmentService departmentService) {
		super();
		this.branchService = branchService;
		this.departmentService = departmentService;
	}

	/**
	 * 只包含branchDAO的构造函数
	 * 
	 * @param branchDAO
	 */
	public DictionaryViews(BranchService branchService) {
		super();
		this.branchService = branchService;
	}

	/**
	 * 只包含departmentDAO的构造函数
	 * 
	 * @param departmentDAO
	 */
	public DictionaryViews(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	/**
	 * 取得数据库中的机构列表(所有数据)
	 * 
	 * @return
	 */
	public static List getBranchList(List<Branch> list) {
		List arrayList = new ArrayList();
		DictionaryView dirView = new DictionaryView();
		dirView.setCode("");
		dirView.setName("------请选择-----");
		arrayList.add(dirView);

		Iterator it = list.iterator();
		while (it.hasNext()) {
			Branch branch = (Branch) it.next();
			dirView = new DictionaryView();
			dirView.setCode(branch.getBraId());
			dirView.setName(branch.getBraName());
			arrayList.add(dirView);

		}
		return arrayList;
	}
	/**
	 * 
	 * @CreateDate	2017-6-6  下午09:30:39
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					取得项目编号下拉列表
	 * @param repList
	 * @return
	 */
	public List getRepairItemSelect(List<RepairItem> repList){
		List dataList=new ArrayList();
		DictionaryView dirView=new DictionaryView();
		dirView.setCode("");
		dirView.setName("------请选择-----");
		dataList.add(dirView);
		
		for(RepairItem item:repList){
			dirView=new DictionaryView();
			dirView.setCode(item.getRepId());
			dirView.setName(item.getRepId()+":"+item.getRepName());
			dataList.add(dirView);
		}
		return dataList;
	}

	/**
	 * 取得数据库中的机构列表(除去当前登录职员所在的机构)
	 * 
	 * @param branchId
	 * @return
	 */
	public List getBranchListOthers(String branchId) {
		List arrayList = new ArrayList();
		DictionaryView dirView = new DictionaryView();
		dirView.setCode("");
		dirView.setName("------请选择-----");
		arrayList.add(dirView);

		List list = this.branchService.findAllOthers(branchId);

		Iterator it = list.iterator();
		while (it.hasNext()) {
			Branch branch = (Branch) it.next();
			dirView = new DictionaryView();
			dirView.setCode(branch.getBraId());
			dirView.setName(branch.getBraName());
			arrayList.add(dirView);

		}
		return arrayList;
	}

	/**
	 * 取出数据库中所有状态为正常的机构列表
	 * 
	 * @return
	 */
	public List getBranchListInUsing() {
		ArrayList arrayList = new ArrayList();
		DictionaryView dirView = new DictionaryView();
		dirView.setCode("");
		dirView.setName("------请选择------");
		arrayList.add(dirView);

		List list = branchService.findAllInUsing();
		Iterator it = list.iterator();

		while (it.hasNext()) {
			Branch branch = (Branch) it.next();
			dirView = new DictionaryView();
			dirView.setCode(branch.getBraId());
			dirView.setName(branch.getBraName());
			arrayList.add(dirView);
		}

		return arrayList;
	}

	/**
	 * 取得状态为正常的药店部门列表(除去管理员组)
	 * 
	 * @return
	 */
	public List getDepartmentListInUsing() {
		ArrayList arrayList = new ArrayList();
		DictionaryView dirView = new DictionaryView();
		dirView.setCode("");
		dirView.setName("------请选择------");
		arrayList.add(dirView);

		List list = departmentService.findAllInUsingN();

		Iterator it = list.iterator();
		while (it.hasNext()) {
			Department sysGrp = (Department) it.next();
			dirView = new DictionaryView();
			dirView.setCode(sysGrp.getDepId());
			dirView.setName(sysGrp.getDepName());
			arrayList.add(dirView);
		}

		return arrayList;
	}

	/**
	 * 取出数据库中所有的部门列表（全部）
	 * 
	 * @return
	 */
	public List getDepartmentList() {
		List data = new ArrayList();
		DictionaryView dirView = new DictionaryView();
		dirView.setCode("");
		dirView.setName("-----请选择-----");
		data.add(dirView);

		List list = departmentService.findAll();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Department department = (Department) it.next();
			dirView = new DictionaryView();
			dirView.setCode(department.getDepId());
			dirView.setName(department.getDepName());
			data.add(dirView);
		}
		return data;

	}

	/**
	 * 
	 * 创建时间：2017-5-24  上午10:59:10
	 * 	创建者：zhaozhy
	 *	方法说明：取得员工岗位列表
	 * @param dList
	 * @return
	 */
	public List getPositionList(List<DicData> dList){
		List dataList=new ArrayList();
		DictionaryView dirView=new DictionaryView();
		dirView.setCode("");
		dirView.setName("-----请选择-----");
		dataList.add(dirView);
		
		for(DicData d:dList){
			dirView=new DictionaryView();
			dirView.setCode(d.getId().getDicValue());
			dirView.setName(d.getDicName());
			dataList.add(dirView);
		}
		return dataList;
	}
	/**
	 * 取得状态列表
	 * 
	 * @return
	 */
	public static List getStatList(List<DicData> sList) {
		List dataList = new ArrayList();
		DictionaryView dirView = new DictionaryView();
		dirView.setCode("");
		dirView.setName("-----请选择-----");
		dataList.add(dirView);
		for(DicData d:sList){
			dirView=new DictionaryView();
			dirView.setCode(d.getId().getDicValue());
			dirView.setName(d.getDicName());
			dataList.add(dirView);
		}
		return dataList;
	}

	/**
	 * 取得部门类型列表
	 * 
	 * @return
	 */
	public List getDTypeList(List<DicData> dList) {
		List dataList = new ArrayList();
		DictionaryView dirView = new DictionaryView();
		dirView.setCode("");
		dirView.setName("-----请选择-----");
		dataList.add(dirView);

		for(DicData d:dList){
			dirView=new DictionaryView();
			dirView.setCode(d.getId().getDicValue());
			dirView.setName(d.getDicName());
			dataList.add(dirView);
		}

		return dataList;
	}

	/**
	 * 取得药店职员级别列表
	 * 
	 * @return
	 */
	public List getSLevelList(List<DicData> sList) {
		List dataList = new ArrayList();
		DictionaryView dirView = new DictionaryView();
		dirView.setCode("");
		dirView.setName("-----请选择-----");
		dataList.add(dirView);
		for(DicData d:sList){
			dirView=new DictionaryView();
			dirView.setCode(d.getId().getDicValue());
			dirView.setName(d.getDicName());
			dataList.add(dirView);
		}
		return dataList;

	}
	
	/**
	 * 
	 * 创建时间：2017-5-24  下午12:21:16
	 * 	创建者：zhaozhy
	 *	方法说明：组装下拉列表通用工具
	 * @param dList
	 * @return
	 */
	public static List getSelectList(List<DicData> dList) {
		List dataList = new ArrayList();
		DictionaryView dirView = new DictionaryView();
		dirView.setCode("");
		dirView.setName("-----请选择-----");
		dataList.add(dirView);
		
		for(DicData d:dList){
			dirView=new DictionaryView();
			dirView.setCode(d.getId().getDicValue());
			dirView.setName(d.getDicName());
			dataList.add(dirView);
		}
		return dataList;
	}
	
	/**
	 * 
	 * @CreateDate	2017-6-6  下午07:32:20
	 * @Author				zhaozhy  zhongyong@qq.com
	 *	@Desc					组装下拉列表，和getSelectList不同的是没有（-----请选择-----）选项，有默认值
	 * @param dList
	 * @return
	 */
	public List getSelectListDefault(List<DicData> dList) {
		List dataList=new ArrayList();
		
		for(DicData d:dList){
			DictionaryView dirView=new DictionaryView();
			dirView.setCode(d.getId().getDicValue());
			dirView.setName(d.getDicName());
			dataList.add(dirView);
		}
		return dataList;
	}
	
	public List getSelectListDefault1(Map<String,String> dataMap){
		List rtnList=new ArrayList();
		Set<String> skey=dataMap.keySet();
		for(String key:skey){
			DictionaryView dirView=new DictionaryView();
			String svalue=dataMap.get(key);
			dirView.setCode(key);
			dirView.setName(svalue);
			rtnList.add(dirView);
		}
		return rtnList;
	}
	/**
	 * 
	 * @CreateDate	2017年8月1日  上午8:54:16
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					使用ecside显示下拉列表，列表数据需要封装到map中
	 * @param list
	 * @return
	 */
	public static Map<String,String> getSelectMap(List<DicData> list){
		Map<String,String> rtnMap=new LinkedHashMap<String, String>();
		rtnMap.put("", "------请选择------");
		for(DicData dd:list){
			rtnMap.put(dd.getId().getDicValue(), dd.getId().getDicValue()+":"+dd.getDicName());
		}
		return rtnMap;
	}

}
