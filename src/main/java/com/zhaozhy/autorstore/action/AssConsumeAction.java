package com.zhaozhy.autorstore.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ecside.table.limit.FilterSet;
import org.ecside.table.limit.Limit;
import org.ecside.table.limit.Sort;
import org.ecside.util.RequestUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;

import com.zhaozhy.autorstore.entity.AssConsume;
import com.zhaozhy.autorstore.entity.Associator;
import com.zhaozhy.autorstore.entity.Branch;
import com.zhaozhy.autorstore.entity.Material;
import com.zhaozhy.autorstore.entity.MaterialId;
import com.zhaozhy.autorstore.entity.Staffer;
import com.zhaozhy.autorstore.entity.Static3;
import com.zhaozhy.autorstore.entity.Static4;
import com.zhaozhy.autorstore.entity.Static4Id;
import com.zhaozhy.autorstore.exception.DateErrorException;
import com.zhaozhy.autorstore.exception.SystemErrorException;
import com.zhaozhy.autorstore.form.ValidatorTimeQueryForm;
import com.zhaozhy.autorstore.sysadmin.DictionaryViews;
import com.zhaozhy.autorstore.sysadmin.ViewBean4;
import com.zhaozhy.autorstore.util.AssConsumeUtil;
import com.zhaozhy.autorstore.util.PageState;

/**
 * 
 * @author zhaozy
 * 
 */
public class AssConsumeAction extends BaseAction<AssConsume> {

	private static final Log log = LogFactory.getLog(AssConsume.class);

	/**
	 * 药品统计显示初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward chartDrugInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ValidatorTimeQueryForm timeForm = (ValidatorTimeQueryForm) form;
		timeForm.setB_id("");
		timeForm.setD_id("");
		timeForm.setFromDate("");
		timeForm.setEndDate("");

		this.addToRequestDrugList(request);
		return mapping.findForward("success");
	}

	/**
	 * 列表显示利润
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward produceDrugChart(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ValidatorTimeQueryForm timeForm = (ValidatorTimeQueryForm) form;
		String fromDate = timeForm.getFromDate();
		String endDate = timeForm.getEndDate();
		String d_id = timeForm.getD_id();

		if (fromDate.length() == 10 && endDate.length() == 8) {
			String fromDateStr = fromDate.replace("-", "");
			String endDateStr = endDate.replace("-", "");

			Integer fromDateInt = Integer.parseInt(fromDateStr);
			Integer endDateInt = Integer.parseInt(endDateStr);

			if (fromDateInt.intValue() > endDateInt) {
//				System.out.println("fromDate >endDate");
				log.error("fromDate >endDate");
				this.addToRequestDrugList(request);
				throw new DateErrorException("");
			}
		}

		if ("".equals(fromDate.trim())) {
			fromDate = null;
		}
		if ("".equals(endDate.trim())) {
			endDate = null;
		}

		if ("".equals(d_id.trim())) {
			d_id = null;
		}

		AssConsumeUtil statisticUtil = new AssConsumeUtil(this.assConsumeService);
		String footer = "";

		List dataList = this.transformStatic1(statisticUtil.queryPerPage(
				fromDate, endDate, d_id, new PageState(request)));

		footer = statisticUtil.getFooter();

		int currentPage = 1;
		int lineSize = 10;// 每页显示的记录数
		int allRecorders = 0;// 数据库中总的记录数

		Limit limit = RequestUtils.getLimit(request);
		Sort sort = limit.getSort();

		Map sortValueMap = sort.getSortValueMap();
		FilterSet filterSet = limit.getFilterSet();

		Map filterPropertyMap = filterSet.getPropertyValueMap();

		// allRecorders = sysOptDAO.getAllCount(sortValueMap,
		// filterPropertyMap);
		allRecorders = this.associatorService.findAll().size();

		int[] rowStartEnd = RequestUtils.getRowStartEnd(request, allRecorders,
				lineSize);
		if (lineSize > allRecorders) {
			currentPage = 1;
		}

		request.setAttribute("dataList", dataList);
		request.setAttribute("footer", footer);

		// 求得总利润
		double sumJing = 0;
		Iterator it = dataList.iterator();
		while (it.hasNext()) {
			ViewBean4 viewBean4 = (ViewBean4) it.next();

			String jingStr = viewBean4.getData7();

			sumJing += Double.parseDouble(jingStr);
		}

		request.setAttribute("sumJing", sumJing);
		request.setAttribute("fromDate", fromDate);
		request.setAttribute("endDate", endDate);

		return mapping.findForward("success");
	}

	/**
	 * 初始化request
	 * 
	 * @param request
	 */
	public void addToRequestDrugList(HttpServletRequest request) {
//		DictionaryViews dirViews = new DictionaryViews(this.materialService);
//
//		List drugIdList = dirViews.getDIdList();

	//	request.setAttribute("drugList", drugIdList);
	}

	/**
	 * 统计利润时使用
	 * 
	 * @param assConsumeList
	 * @return
	 */
	public List transformStatic1(List assConsumeList) {

		List lastList = new ArrayList();

		List dataList = this.static3Service.findAll();// 存放Static3对象的集合

		Iterator it = assConsumeList.iterator();
		while (it.hasNext()) {
			AssConsume assConsume = (AssConsume) it.next();

			String d_id = assConsume.getBraId();
			MaterialId mid=new MaterialId();
			mid.setMatId(d_id);

			Material material = this.materialService.findById(mid);

			boolean has = false;

			for (int i = 0; i < dataList.size(); i++) {
				// Statistic sta1=(Statistic)dataList.get(i);
				Static3 st = (Static3) dataList.get(i);

				if (st.getMatId().equals(assConsume.getBraId())) {
//					Integer s1 = assConsume.getId().getDNum();
					Integer s3 = st.getS3Num();
//					st.setDNum(s1 + s3);
					

					BigDecimal mao1 = assConsume.getConAmount();
					BigDecimal mao3 = st.getS3Sumprice();

					st.setS3Sumprice(mao1.add(mao3));

					// 净利润=毛利润-成本
					BigDecimal chengben=material.getMatPreprice().multiply(new BigDecimal(assConsume.getConPoint()));
//					BigDecimal jing1 = mao1- (material.getMatPreprice() * assConsume.getConPoint());
					BigDecimal jing1=mao1.subtract(chengben);
					BigDecimal jing3 = st.getS3Rsumprice();

					st.setS3Rsumprice(jing1 .add(jing3));

					this.static3Service.update(st);
					has = true;
				}
			}

			if (!has) {
				Static3 st3 = new Static3();
				st3.setMatId(assConsume.getAssId());
				st3.setMatName(material.getMatName());
				st3.setMatPreprice(assConsume.getConAmount());// 实际上架的单价
				st3.setS3Num(assConsume.getConPoint());
				st3.setS3Sumprice(assConsume.getConRamount());// 毛利润

				// 净利润=毛利润-成本
				BigDecimal chengben=material.getMatPreprice().multiply(new BigDecimal(assConsume.getConPoint()));
				BigDecimal s = (assConsume.getConAmount()).subtract(chengben);
				st3.setS3Rsumprice(s);// 净利润

				st3.setMatPreprice(material.getMatPreprice());
				this.static3Service.save(st3);
				dataList.add(st3);
			}
		}

		List dataList1 = this.static3Service.findAll();

		Iterator it1 = dataList1.iterator();
		while (it1.hasNext()) {
			Static3 st3 = (Static3) it1.next();
			// data1:药品编号;data2:药品名称;data3:单价;data4:进货价;

			// data5:销量;

			// data6:毛利润;data7:净利润;data7:;data8:;

			String data1 = st3.getMatId();
			String data2 = st3.getMatName();
			String data3 = st3.getMatRealprice().toString();

			String data4 = st3.getMatPreprice().toString();

			String data5 = st3.getS3Num().toString();
			String data6 = st3.getS3Sumprice().toString();
			String data7 = st3.getS3Rsumprice().toString();

			lastList.add(new ViewBean4(data1, data2, data3, data4, data5,
					data6, data7));
		}

		this.static3Service.deleteAll();// 清空数据库
		return lastList;
	}

	/**
	 * 格式化数据
	 * 
	 * @param statisticList
	 * @return
	 */
	public List transfromStatistic(List statisticList) {

		List dataList = new ArrayList();
		Iterator it = statisticList.iterator();
		while (it.hasNext()) {
			AssConsume assConsume = (AssConsume) it.next();
//			StatisticId statisticId = assConsume.getId();
			// data1:交易日期;data2:交易时间;data3:机构编号;data4:职员编号;

			// data5:会员编号;data6:药品编号;data7:药品数量;data8:单价;

			// data9:会员单价;data10:总价;data11:机构名称;data12:职员姓名;

			// data13:会员姓名;data14:药品名称;

			DateFormat dfdate = DateFormat.getDateInstance();
			DateFormat dftime = DateFormat.getTimeInstance();

			String data1 = dfdate.format(assConsume.getConDate());

			String data2 = assConsume.getConTime();

			String data3 = assConsume.getBraId();
			String data4 = assConsume.getStaId();
			String data5 = assConsume.getAssId();
			String data6 = new String("");
			String data7 = new String ("");
			String data8 = new String("");
			String data9 = new String ("");
			String data10 = assConsume.getConAmount().toString();

			Branch branch = this.branchService.findById(data3);

			if (branch == null) {
//				System.out.println("salesAction:branch is null");
				log.error("salesAction:branch is null");
				throw new SystemErrorException("");
			}

			String data11 = branch.getBraName();

			Staffer staffer = this.stafferService.findById(data4);
			if (staffer == null) {
//				System.out.println("salesAction: staffer is null");
				log.error("salesAction: staffer is null");
				throw new SystemErrorException("");
			}

			String data12 = staffer.getStaName();

			Associator associator = this.associatorService.findById(data5);
			String associatorName = "";
			if (!"0000000000".equals(data5)) {
				if (associator == null) {
//					System.out .println("salesAction.settleAccounts: associator is null");
					log.error("salesAction.settleAccounts: associator is null");
					throw new SystemErrorException("");
				}

				associatorName = associator.getAssName();
			} else {
				associatorName = "顾客";
			}
			String data13 = associatorName;

			// 顺便修改药品数据库
			MaterialId materialId = new MaterialId();
			materialId.setMatId(data6);
			materialId.setBraId(data3);
			materialId.setMatStat("1");

			Material material = this.materialService.findById(materialId);
			if (material == null) {
//				System.out.println("salesAction.settleAccounts: drug is null");
				log.error("salesAction.settleAccounts: drug is null");
				throw new SystemErrorException("");
			}

			Integer num1 = material.getMatNum();// 现有多少？
			// System.out.println("settleAccounts:num1:" + num1);
			Integer num2 = material.getMatNum();// 要买多少？
			// System.out.println("settleAccounts:num2:" + num2);

			if (num1.intValue() == num2.intValue()) {
				// 架上药品全部售完，删除此条数据
				// System.out.println("settleAccount:num1==num2");
				this.materialService.delete(material);
				// System.out.println("settleAccount:delete ok");
			} else {
				// 还没有售完，修改数据库
				material.setMatNum(num1 - num2);
				this.materialService.update(material);
				// System.out.println("num1-num2:" + (num1 - num2));
			}

			String data14 = material.getMatName();

			dataList.add(new ViewBean4(data1, data2, data3, data4, data5,
					data6, data7, data8, data9, data10, data11, data12, data13,
					data14));
		}

		return dataList;

	}

	/**
	 * 某机构利润构成 页面初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lrByDrugInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		this.clearForm((ValidatorTimeQueryForm) form);

		this.addToRequestBranchList(request);
		return mapping.findForward("success");
	}

	public void addToRequestBranchList(HttpServletRequest request) {
		DictionaryViews dirViews = new DictionaryViews(this.branchService);

		List branchList = dirViews.getBranchListInUsing();

		request.setAttribute("branchList", branchList);
	}

	/**
	 * 清空ValidatorTimeQueryForm
	 * 
	 * @param vtqf
	 */
	public void clearForm(ValidatorTimeQueryForm vtqf) {
		vtqf.setB_id("");
		vtqf.setD_id("");
		vtqf.setEndDate("");
		vtqf.setFromDate("");
	}

	/**
	 * 某机构利润构成(饼图)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lrByDrug(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ValidatorTimeQueryForm vtqf = (ValidatorTimeQueryForm) form;

		String fromDate = vtqf.getFromDate();
		String endDate = vtqf.getEndDate();
		String b_id = vtqf.getB_id();

		Branch branch = this.branchService.findById(b_id);

		if (fromDate.trim().length() == 8 && endDate.trim().length() == 8) {
			String fromDateStr = fromDate.replace("-", "");
			String endDateStr = endDate.replace("-", "");

			Integer fromDateInt = Integer.parseInt(fromDateStr);
			Integer endDateInt = Integer.parseInt(endDateStr);

			if (fromDateInt.intValue() > endDateInt.intValue()) {
//				System.out.println("fromDate>endDate");
				log.error("fromDate>endDate");
				this.addToRequestBranchList(request);
				throw new DateErrorException("");
			}
		}

		if ("".equals(fromDate.trim())) {
			fromDate = null;
		}
		if ("".equals(endDate.trim())) {
			endDate = null;
		}

		AssConsumeUtil statisticUtil = new AssConsumeUtil(this.assConsumeService);

		List statisticList = statisticUtil.queryAllTimeByBId(fromDate,
				endDate, b_id);// Statistic表中某时间段内所有销售情况,具体到某机构

		List static3List = this.static3Service.findAll();// 统计用表3(Static_3)

		Iterator it = statisticList.iterator();
		while (it.hasNext()) {
			AssConsume st1 = (AssConsume) it.next();
			boolean has = false;

			MaterialId materialId = new MaterialId();
			String d_id = st1.getAssId();

			materialId.setBraId("000000");
			materialId.setMatId("");
			materialId.setMatStat("*");

			Material material = this.materialService.findById(materialId);

			for (int i = 0; i < static3List.size(); i++) {
				Static3 st3 = (Static3) static3List.get(i);

				if (st1.getBraId().trim().equals(st3.getMatId().trim())) {

					// 修改药品数量
					Integer st1Num = st1.getConPoint();
					Integer st3Num = st3.getS3Num();

					st3.setS3Num(st3Num + st1Num);

					// 修改毛利润

					BigDecimal st1Mao = st1.getConRamount();
					BigDecimal st3Mao = st3.getS3Sumprice();

					st3.setS3Sumprice(st1Mao .add(st3Mao));

					// 修改净利润
					// 净利润=毛利润-成本
					BigDecimal chengben=material.getMatRealprice().multiply(new BigDecimal(st1.getConPoint()));
					BigDecimal st1Jing = st1Mao.subtract(chengben);
					BigDecimal st3Jing = st3.getS3Rsumprice();

					st3.setS3Rsumprice(st1Jing .add(st3Jing));
					has = true;
				}
			}

			if (!has) {
				Static3 st3 = new Static3();
				st3.setMatId(material.getId().getMatId());
				st3.setMatName(material.getMatName());
				st3.setMatPreprice(material.getMatRealprice());
				st3.setMatRealprice(material.getMatPreprice());
				st3.setS3Num(st1.getConPoint());
				st3.setS3Sumprice(st1.getConRamount());// 毛利润
				// 净利润=毛利润-成本
				BigDecimal chengben=material.getMatPreprice().multiply(new BigDecimal(st1.getConPoint()));
				st3.setMatPreprice((st1.getConRamount()).subtract(chengben));

				this.static3Service.save(st3);

				static3List.add(st3);

			}
		}

		List static3ListLast = this.static3Service.findAll();// 数据库中所有的数据
		this.static3Service.deleteAll();// 清空数据

		JFreeChart chart;

		DefaultPieDataset dataset = new DefaultPieDataset();

		Iterator it3 = static3ListLast.iterator();
		while (it3.hasNext()) {
			Static3 st3 = (Static3) it3.next();
			dataset.setValue(st3.getMatId() + st3.getMatName(), st3
					.getMatRealprice());

		}

		if (fromDate == null && endDate == null) {
			chart = ChartFactory.createPieChart(branch.getBraName()
					+ "利润构成饼图(全部数据)", dataset, false, true, false);
		} else {
			chart = ChartFactory.createPieChart(branch.getBraName() + "利润构成饼图("
					+ fromDate + "--" + endDate + ")", dataset, false, true,
					false);
		}

		PiePlot piePlot = (PiePlot) chart.getPlot();

		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0} ({2} percent)"));
		response.setContentType("image/png");

		// PrintWriter printWriter = new PrintWriter(response.getWriter());
		String fileName = ServletUtilities.saveChartAsPNG(chart, 900, 500,
				request.getSession());

		String s = request.getServletPath();
		int a = s.length();

		s = s.substring(0, a - 3);
		String urlName = request.getContextPath() + s + "?filename=" + fileName;
		request.setAttribute("chart", urlName);

		return mapping.findForward("success");
	}

	/**
	 * 按机构统计利润 线图初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lrByBranchInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DictionaryViews dirViews = new DictionaryViews(this.branchService);

		List branchList = dirViews.getBranchListInUsing();

		this.clearForm((ValidatorTimeQueryForm) form);

		request.setAttribute("branchList", branchList);

		return mapping.findForward("success");
	}

	/**
	 * 按机构统计利润 线图
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lrByBranch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ValidatorTimeQueryForm vtqf = (ValidatorTimeQueryForm) form;
		JFreeChart chart;

		String fromDate = vtqf.getFromDate();
		String endDate = vtqf.getEndDate();
		String b_id = vtqf.getB_id();
		Branch branch;

		if (fromDate.trim().length() == 8 && endDate.trim().length() == 8) {
			String fromDateStr = fromDate.replace("-", "");
			String endDateStr = endDate.replace("-", "");

			Integer fromDateInt = Integer.parseInt(fromDateStr);
			Integer endDateInt = Integer.parseInt(endDateStr);

			if (fromDateInt > endDateInt) {
//				System.out.println("fromDate>endDate");
				log.error("fromDate>endDate");
				this.addToRequestBranchList(request);
				throw new DateErrorException("");
			}

		}

		if ("".equals(fromDate.trim())) {
			fromDate = null;
		}

		if ("".equals(endDate.trim())) {
			endDate = null;
		}

		if ("".equals(b_id.trim())) {
			b_id = null;
		} else {
			branch = this.branchService.findById(b_id);
		}

		AssConsumeUtil statisticUtil = new AssConsumeUtil(this.assConsumeService);

		List statisticList = statisticUtil.queryAllTimeByBId(fromDate,
				endDate, b_id);

		// 假定已经选择了某一具体机构
		if (b_id != null) {
			branch = this.branchService.findById(b_id);

			Iterator it = statisticList.iterator();
			while (it.hasNext()) {
				AssConsume assConsume = (AssConsume) it.next();

				DateFormat df = DateFormat.getDateInstance();

				String date1 = df.format(assConsume.getConDate());

				List static4List = this.static4Service.findAll();// 存放Static_4对象的集合

				boolean has = false;// 用于判断static4List中是否已经有此条数据

				MaterialId materialId = new MaterialId();
				materialId.setMatId(assConsume.getConId());
				materialId.setBraId("000000");
				materialId.setMatStat("*");

				Material material = this.materialService.findById(materialId);

				for (int i = 0; i < static4List.size(); i++) {
					Static4 st4 = (Static4) static4List.get(i);
					Static4Id st4Id = st4.getId();

					String date4 = df.format(st4Id.getS4Date());

					if (date1.trim().equals(date4.trim())) {
						// 修改毛利润
						BigDecimal st1Mao = assConsume.getConAmount();
						BigDecimal st4Mao = st4.getS4Allprice();

						st4.setS4Realprice(st1Mao.add(st4Mao));

						// 修改净利润
						// 净利润=毛利润-成本
						BigDecimal chengben=material.getMatPreprice().multiply(assConsume.getConAmount());
						BigDecimal st1Jing = st1Mao.subtract(chengben);
						BigDecimal st4Jing = st4.getS4Allprice();

						st4.setS4Allprice(st1Jing .add(st4Jing));

						this.static4Service.update(st4);
						has = true;
					}
				}

				if (!has) {
					Static4Id static4Id = new Static4Id();
					static4Id.setBraId(assConsume.getBraId());
					static4Id.setS4Date(DateUtils.parseDate(assConsume.getConDate(),new String[]{"yyyymmdd"}));

					Static4 static4 = new Static4();
					static4.setId(static4Id);

					static4.setBraName(branch.getBraName());

					// 毛利润
					static4.setS4Allprice(assConsume.getConAmount());

					// 净利润
					// 净利润=毛利润-成本
					BigDecimal chengben=material.getMatPreprice().multiply(assConsume.getConAmount());
					BigDecimal jing = assConsume.getConAmount().subtract(chengben);
					static4.setS4Realprice(jing);

					this.static4Service.save(static4);
					static4List.add(static4);

				}

			}

			List st4List = this.static4Service.findAll();

			

			TimeSeriesCollection dataset = new TimeSeriesCollection();

			TimeSeries t1 = new TimeSeries(branch.getBraName());

			Static4 stEarlist = this.static4Service.findStatic4Earlist();

			Static4 stLast = this.static4Service.findStatic4Last();
			if (stEarlist != null && stLast != null) {
				Date earlistDate = stEarlist.getId().getS4Date();
				Date lastDate = stLast.getId().getS4Date();

				long earlistDateLong = earlistDate.getTime();
				long lastDateLong = lastDate.getTime();

				long perDay = 24 * 60 * 60 * 1000;// 一天的毫秒数
				DateFormat df = DateFormat.getDateInstance();

				for (long i = earlistDateLong; i <= lastDateLong; i += perDay) {

					// java.sql.Date di=new java.sql.Date(i);

					Date di = new Date(i);

					Static4Id test4Id = new Static4Id();
					test4Id.setS4Date(di);
					test4Id.setBraId(branch.getBraId());

					Static4 st4 = this.static4Service.findById(test4Id);

					String diStr = df.format(di);
					String[] ss = diStr.split("-");

					//System.out.println("=====diStr:" + diStr);
					String yearStr = ss[0];
					String monthStr = ss[1];
					String dayStr = ss[2];

				

					Integer yearInt = Integer.parseInt(yearStr);
					Integer monthInt = Integer.parseInt(monthStr);
					Integer dayInt = Integer.parseInt(dayStr);

					t1.add(new TimeSeriesDataItem(new Day(dayInt, monthInt,
							yearInt), st4.getS4Realprice()));
				}

				dataset.addSeries(t1);
			}
				chart = ChartFactory.createTimeSeriesChart("机构利润走向图", "日期", "净利润(单位：元)",
						dataset, true, true, false);

				this.static4Service.deleteAll();// 清空数据库
			
			response.setContentType("image/png");

			String fileName = ServletUtilities.saveChartAsPNG(chart, 900, 500,
					request.getSession());

			String s = request.getServletPath();
			int a = s.length();

			s = s.substring(0, a - 3);
			String urlName = request.getContextPath() + s + "?filename="
					+ fileName;
			request.setAttribute("chart", urlName);
		}

		return mapping.findForward("success");
	}

}
