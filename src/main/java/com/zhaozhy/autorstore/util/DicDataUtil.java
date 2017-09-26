package com.zhaozhy.autorstore.util;

import java.util.ArrayList;
import java.util.List;

import com.zhaozhy.autorstore.entity.DicDataId;
import com.zhaozhy.autorstore.service.DicDataService;

/**
 * 
 * 创建者：zhaozhy
 * 创建时间：2017-5-23   下午06:50:19
 * 说明：系统常用变量在此类中定义
 * 
 * 修改者：
 * 修改时间：
 * 修改说明：
 * E-mail : zhongyong@qq.com
 */
public class DicDataUtil {
	private String footer;
	private DicDataService dicDataService;
	
	
	public DicDataUtil() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public DicDataUtil(DicDataService dicDataService) {
		super();
		this.dicDataService = dicDataService;
	}
	public String getFooter() {
		return footer;
	}
	
	/**
	 * 
	 * 创建时间：2017-6-2  下午01:59:34
	 * 	创建者：zhaozhy
	 *	方法说明：
	 * @param did
	 * @param pageState
	 * @return
	 */
	public List queryPerPageById(DicDataId did,PageState pageState){
		List data = new ArrayList();

		String nowPage = pageState.getPages();
		String nowPageSize = pageState.getPageSize();

		int intPage = 0;
		int intPageSize = 0;

		if (nowPage == null) {
			intPage = 1;
		} else {
			intPage = Integer.parseInt(nowPage);
			if (intPage < 0)
				intPage = 1;
		}
		if (nowPageSize == null) {
			intPageSize = 20;
		} else {
			intPageSize = Integer.parseInt(nowPageSize);
			if (intPageSize < 0)
				intPageSize = 20;
		}

//		data = this.dicDataDAO.findByDicDataLargeLike(dicLarge);
		
		data=this.dicDataService.findByIdProperty(did);
		
		int intCount = data.size();// 数据库中的总记录数

		List list = this.dicDataService
				.findPageByIdProperty(did, intPage, intPageSize);

		int intPageCount = ((intCount + intPageSize) - 1) / intPageSize;// 总页数

		FooterUtil footerUtil = new FooterUtil();
		footer = footerUtil.getPageFooter(intPage, intPageCount, intPageSize,
				intCount);

		// System.out.println("list.size:"+list.size());
		return list;
	}
	
	
	public List queryPerPage(String dicLarge, PageState pageState) {
		List data = new ArrayList();

		String nowPage = pageState.getPages();
		String nowPageSize = pageState.getPageSize();

		int intPage = 0;
		int intPageSize = 0;

		if (nowPage == null) {
			intPage = 1;
		} else {
			intPage = Integer.parseInt(nowPage);
			if (intPage < 0)
				intPage = 1;
		}
		if (nowPageSize == null) {
			intPageSize = 20;
		} else {
			intPageSize = Integer.parseInt(nowPageSize);
			if (intPageSize < 0)
				intPageSize = 20;
		}

//		data = this.dicDataDAO.findByDicDataLargeLike(dicLarge);
		DicDataId did=new DicDataId();
		did.setDicLarge(dicLarge);
		data=this.dicDataService.findByIdProperty(did);
		
		int intCount = data.size();// 数据库中的总记录数

		List list = this.dicDataService
				.findPageByIdProperty(did, intPage, intPageSize);

		int intPageCount = ((intCount + intPageSize) - 1) / intPageSize;// 总页数

		FooterUtil footerUtil = new FooterUtil();
		footer = footerUtil.getPageFooter(intPage, intPageCount, intPageSize,
				intCount);

		// System.out.println("list.size:"+list.size());
		return list;
	}
	
	/** boolean取值 */
	public static String BOOLEAN_TRUE="true";
	public static String BOOLEAN_FALSE="false";
	
	/** split分组使用的标记 */
	public static String GLOBAL_STRING_SPLIT="global.string.split";

	/** 默认会员卡号，如销售页面没有输入会员卡号时，创建 */
	public static String ASSOCIATOR_DEFAULT="0000000000";
	
	/** 系统用户 默认密码 */
	public static String STAFFER_DEFAULT_PASSWORD="123";
	
	/** 会员 默认密码 */
	public static String ASSOCIATOR_DEFAULT_PASSWORD="666666";
	
	/** 维修项目 默认rapId 用于销售页面输入金额使用 */
	public static String REPAIR_ITEM_DEFAULT="0000000001";
	
	/** 系统设置 购买套餐是否计入积分 */
	public static String POINT_COMBO="point.combo";
	/** 积分规则，如 1：一元一积分；10：十元一积分 */
	public static String POINT_RULE="point.rule";
	
	/** 会员升级所需要积分设置 */
	public static String ASSOCIATOR_LEVEL_00="associator.level.00";
	public static String ASSOCIATOR_LEVEL_01="associator.level.01";
	public static String ASSOCIATOR_LEVEL_02="associator.level.02";
	
	/** 会员折扣数据 */
	public static String ASSOCIATOR_DISCOUNT_00="associator.discount.00";
	public static String ASSOCIATOR_DISCOUNT_01="associator.discount.01";
	public static String ASSOCIATOR_DISCOUNT_02="associator.discount.02";
	
	/** 组织下拉列表框""选项显示内容 */
	public static String SELECT_NULLVALUE="------请选择------";
	
	/**  ECSIDE使用的 recordKey */
	public static String RECORDKEY="recordKey";
	
	/** 发送邮件相关参数  */
	public static String MAIL_SMTP_HOST="mail.smtp.host";
	public static String MAIL_SMTP_AUTH="mail.smtp.auth";
	public static String MAIL_SENDER_USERNAME="mail.sender.username";
	public static String MAIL_SENDER_PASSWORD="mail.sender.password";
	public static String MAIL_SESSION_DEBUG="mail.session.debug";
	public static String DBTYPE="dbtype";
//	public static String MAIL_FILEPATH="mail.filepath";
	public static String MAIL_FILEPATH="mail.filepath";
	public static String MAIL_RECEIVER_USERNAMES="mail.receiver.usernames";

	/** 大类一：生成表主键 */
	public static String DICDATA_10="10";
	/** 小类：充值流水号 */
	public static String DICDATA_1000="00";
	/** 小类：优惠套餐编号 */
	public static String DICDATA_1001="01";
	/** 小类：消费表流水号 */
	public static String DICDATA_1002="02";
	/** 消费清单表CONSUME_LIST 主键 lis_id */
	public static String DICDATA_1003="03";
	/** 维修材料表MATERIAL编号mat_id */
	public static String DICDATA_1004="04";
	/** 维修项目表REPAIR_ITEM 主键 编号rep_id */
	public static String DICDATA_1005="05";
	
	/** 状态 */
	public static String DICDATA_0000="0000";
	/**状态：有效*/
	public static String DICDATA_000000="00";
	/** 状态：无效*/
	public static String DICDATA_000001="01";
	
	/** 部门类型 */
	public static String DICDATA_0001="0001";
	/** 部门类型：系统管理员 */
	public static String DICDATA_000100="00";
	/** 部门类型：销售类 */
	public static String DICDATA_000101="01";
	/** 部门类型：支持部门 */
	public static String DICDATA_000102="02";
	
	/** 是否 */
	public static String DICDATA_0002="0002";
	/** 是否：是*/
	public static String DICDATA_00020="0";
	/** 是否：否*/
	public static String DICDATA_00021="1";
	
	/** 员工岗位 */
	public static String DICDATA_0003="0003";
	/** 员工岗位：管理员 */
	public static String DICDATA_000300="00";
	/** 员工岗位：销售 */
	public static String DICDATA_000301="01";
	/** 员工岗位：主任 */
	public static String DICDATA_000302="02";
	
	/** 员工级别 */
	public static String DICDATA_0004="0004";
	/** 员工级别：管理员 */
	public static String DICDATA_000400="00";
	/** 员工级别：一级 */
	public static String DICDATA_000401="01";
	/** 员工级别：二级 */
	public static String DICDATA_000402="02";
	
	/** 机构级别 */
	public static String DICDATA_0005="0005";
	/** 机构级别：总店 */
	public static String DICDATA_000500="00";
	/** 机构级别：一级分支机构 */
	public static String DICDATA_000501="01";
	/** 机构级别：二级分支机构 */
	public static String DICDATA_000502="02";
	
	/** 会员级别 */
	public static String DICDATA_0006="0006";
	/** 会员级别：普通会员 */
	public static String DICDATA_000600="00";
	/** 会员级别：一级会员 */
	public static String DICDATA_000601="01";
	/** 会员级别：二级会员 */
	public static String DICDATA_000602="02";
	
	/** 性别 */
	public static String DICDATA_0007="0007";
	/** 性别：男 */
	public static String DICDATA_000700="00";
	/** 性别：女 */
	public static String DICDATA_000701="01";
	
	/** 维修材料(项目、套餐)分类 */
	public static String DICDATA_0008="0008";
	/** 维修材料(项目、套餐)分类：保养类 */
	public static String DICDATA_000800="00";
	/** 维修材料(项目、套餐)分类：维修类 */
	public static String DICDATA_000801="01";
	
	/** 付款方式 */
	public static String PAYMENTMODE_0009="0009";
	/** 付款方式：现金 */
	public static String PAYMENTMODE_000901="01";
	/** 付款方式：套餐 */
	public static String PAYMENTMODE_000902="02";
	/** 付款方式：银行卡 */
	public static String PAYMENTMODE_000903="03";
	/** 付款方式：支付宝 */
	public static String PAYMENTMODE_000904="04";
	/** 付款方式：微信支付 */
	public static String PAYMENTMODE_000905="05";
	/** 付款方式：组合方式 */
	public static String PAYMENTMODE_000906="06";
	/** 付款方式：会员卡余额 */
	public static String PAYMENTMODE_000907="07";
	/** 付款方式：其他  */
	public static String PAYMENTMODE_000999="99";
	
	/** 消费标志 **/
	public static String CONSUMEMARK_0010="0010";
	/** 消费标志:普通类型(存在消费清单) **/
	public static String CONSUMEMARK_001001="01";
	/** 消费标志:使用套餐 **/
	public static String CONSUMEMARK_001002="02";
	/** 消费标志:手工录入消费金额 **/
	public static String CONSUMEMARK_001003="03";

}
