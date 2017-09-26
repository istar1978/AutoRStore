package com.zhaozhy.autorstore.sysadmin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zhaozhy.autorstore.entity.DepMenu;
import com.zhaozhy.autorstore.entity.Menu;
import com.zhaozhy.autorstore.service.DepMenuService;
import com.zhaozhy.autorstore.service.MenuService;

/**
 * 提供动态菜单生成功能
 * 
 */
public class DynaMenu {

	private DepMenuService depMenuService;
	private MenuService menuService ;

	/* null constructor */
	public DynaMenu() {

	}

	/* full constructor */
	public DynaMenu(DepMenuService depMenuService, MenuService menuService) {
		this.depMenuService = depMenuService;
		this.menuService = menuService;
	}

	/**
	 * 
	 * @param dId
	 *            （当前登陆管理员所属机构的ID）
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String menu(String dId) {
		StringBuffer menuStr = new StringBuffer();

		int countFirst = 0; // 第一层菜单的计数器,因为固化的第一层“登录管理”占用了0，所以从0开始加1
		int countSecond = -1; // 第二层菜单的计数器
		int countThird = -1; // 第三层菜单的计数器

		String s1 = "00"; // 前一菜单项缓存
		String s2 = "";
		String s3 = "";

		String s21 = ""; // 下一个菜单项缓存
		String s22 = "";
		String s23 = "";

		String position = "menuEnd"; // 在同级菜单中的位置标识,对于菜单项前的小连线图片T或者L

		// 产生影响，只有两种情况，"menuEnd"时为L，"normal"为T

		// 默认认为仅有一个菜单项时为"menuEnd"

		boolean arrayEnd = false; // 作为标识数组中最后一个元素的标志
		/**
		 * 按菜单ID顺序,以dep_id等于传入的depId为条件查询Table DEP_MENUt中的交易菜单代码， ( order by
		 * men_id ) 存入menuList
		 */
		List menuList = new ArrayList(); // 用于存储列表数据

		// 从表DEP_MENU中查询出的是DepMenu对象，包含属性：(depId,menId),dmStat
		List list = depMenuService.findByDId(dId);

		// 遍历集合，将list中保存的DepMenu对象中的menId属性取出放到一个集合中
		Iterator it = list.iterator();
		while (it.hasNext()) {
			DepMenu depRight = (DepMenu) it.next();
			menuList.add(depRight.getId().getMenId());
		}

		// 遍历集合，根据表sys_grp_right的gr_mcode从表sys_menu中查询出菜单名称
		for (int i = 0; i < menuList.size(); i++) {
			if (i == (menuList.size() - 1))
				arrayEnd = true;

			String menu1 = menuList.get(i).toString().substring(0, 2);
			String menu2 = menuList.get(i).toString().substring(2, 4);
			String menu3 = menuList.get(i).toString().substring(4, 6);

			if ((!menu1.equals(s1)) && (!menu1.equals("00"))) {
				countFirst = countFirst + 1;
				s1 = menu1; // 相当于buffer缓存
				s2 = ""; // 清空所有下层菜单的缓存
				s3 = "";

				countSecond = -1; // 清空所有下层菜单的计数器
				countThird = -1;

				position = "normal";

				menuStr.append(menuFirst(countFirst, position, menuList.get(i)
						.toString()));
				// menuFirst()方法返回一个String
			}

			if ((!menu2.equals(s2)) && (!menu2.equals("00"))) {
				countSecond = countSecond + 1;
				s2 = menu2;
				s3 = "";

				countThird = -1;

				if (!arrayEnd) // 这时候可以取下一个菜单项作比较，判断是否与后面也相等
				{
					s21 = menuList.get(i + 1).toString().substring(0, 2);

					if (menu1.equals(s21)) // 判断是否都属于第一级菜单的子项
						position = "normal";
					else
						position = "menuEnd";
				} else
					position = "menuEnd";

				/* countFirst =1,countSecond=0 */
				menuStr.append(menuSecond(countFirst, countSecond, position,
						menuList.get(i).toString()));

			}

			if ((!menu3.equals(s3)) && (!menu3.equals("00"))) {
				countThird = countThird + 1;
				s3 = menu3;

				if (!arrayEnd) // 这时候可以取下一个菜单项作比较，判断是否与后面也相等
				{
					s22 = menuList.get(i + 1).toString().substring(2, 4);
					if (menu2.equals(s22))
						position = "normal";
					else
						position = "menuEnd";
				} else
					position = "menuEnd";

				menuStr.append(menuThird(countFirst, countSecond, countThird,
						position, menuList.get(i).toString()));
			}

		}
		return menuStr.toString();

	}

	/**
	 * 第一级菜单(无论组中是否含有路径菜单的权限，路径菜单都要查询并显示)
	 * 
	 * @param countFirst
	 *            (第一层菜单的计数器)
	 * @param position
	 *            (normal or menuEnd)
	 * @param menuId
	 * @return
	 * @throws Exception
	 */
	private String menuFirst(int countFirst, String position, String menuId) {

		StringBuffer menuStr = new StringBuffer();
		String mId = menuId.substring(0, 2);

		mId = mId + "00000000"; // 组装成要显示的菜单的ID
		List menuList = new ArrayList(); // 用于存储列表数据

		String name = "";
		String att = "";// 是否为路径菜单
		String addr = "";
		Menu menu = menuService.findById(mId);

		name = menu.getMenName();
		att = menu.getMenAtt();
		addr = menu.getMenUrl();
		menuStr.append("\n\n      <DIV class=\"tree_cell\" id=\"tree_root/");
		menuStr.append(countFirst);
		menuStr.append(":T\">");

		// id="tree_root/1:T>"

		if (att.equals("0")) // 路径菜单
		{
			menuStr
					.append("<A href=\"JavaScript:tree_switch_folder('tree_root/");
			menuStr.append(countFirst);
			menuStr.append(":T');\">\n");
			menuStr
					.append("        <IMG src=\"./images/tree/conct_T.gif\" border=\"0\" width=\"19\" height=\"16\">\n");
			// 第一层菜单全部为“T”图标
			menuStr
					.append("        <IMG src=\"./images/tree/folder.gif\" border=\"0\" width=\"14\" height=\"14\" ></A>\n ");
			menuStr
					.append("        <A href=\"JavaScript:void(0);\" onClick=\"tree_highlight('tree_root/");
			menuStr.append(countFirst);
			menuStr.append(":T');\">\n");
		} else // 为“1”，动作菜单
		{
			menuStr
					.append("        <IMG src=\"./images/tree/conct_T.gif\" border=\"0\" width=\"19\" height=\"16\">\n ");
			menuStr.append("        <A href=\"");
			menuStr.append(addr);
			menuStr
					.append("\" target=\"frame_right\" onClick=\"tree_highlight('tree_root/");
			menuStr.append(countFirst);
			menuStr.append(":T');\">\n");
			menuStr
					.append("        <IMG src=\"./images/tree/twistcb.gif\" border=\"0\" >\n ");
		}

		menuStr.append("        ");
		menuStr.append(name);
		menuStr.append("</A><BR>\n");
		menuStr.append("      </DIV> \n\n");

		return menuStr.toString();
	}

	/**
	 * 第二级菜单 (同第一级菜单一样，如果第二级菜单是路径菜单，则无论当前登陆的柜员所属的组是否有其权限，都要显示)
	 * 
	 * @param countFirst
	 * @param countSecond
	 * @param position
	 * @param menuId
	 * @return
	 * @throws MenuNotFoundException
	 */
	@SuppressWarnings("unchecked")
	private String menuSecond(int countFirst, int countSecond, String position,
			String menuId) {
		StringBuffer menuStr = new StringBuffer();
		String mId = menuId.substring(0, 4);
		mId = mId + "000000"; // 组装成要显示的菜单的ID
		List menuList = new ArrayList(); // 用于存储列表数据

		String name = "";
		String att = "";
		String addr = "";

		Menu menu = menuService.findById(mId);

		name = menu.getMenName();
		att = menu.getMenAtt();
		addr = menu.getMenUrl();

		menuStr.append("\n\n      <DIV class=\"tree_cell\" id=\"tree_root/");
		menuStr.append(countFirst);
		menuStr.append(":T/");
		menuStr.append(countSecond);

		if (att.equals("0")) // 路径菜单
			menuStr.append(":T\"> ");
		else
			menuStr.append("\"> ");

		menuStr
				.append("<IMG src=\"./images/tree/conct_I.gif\" border=\"0\" width=\"19\" height=\"16\">\n ");

		if (att.equals("0")) // 路径菜单
		{
			menuStr
					.append("<A href=\"JavaScript:tree_switch_folder('tree_root/");
			menuStr.append(countFirst);
			menuStr.append(":T/");
			menuStr.append(countSecond);

			menuStr.append(":T');\">\n");
			if (position.equals("normal")) // 不是同级菜单中的最后一个
				menuStr
						.append("        <IMG src=\"./images/tree/conct_T.gif\" border=\"0\" width=\"19\" height=\"16\">\n");
			else
				menuStr
						.append("        <IMG src=\"./images/tree/conct_L.gif\" border=\"0\" width=\"19\" height=\"16\">\n");
			menuStr
					.append("        <IMG src=\"./images/tree/folder.gif\" border=\"0\" width=\"14\" height=\"14\" ></A>\n ");
			menuStr
					.append("        <A href=\"JavaScript:void(0);\" onClick=\"tree_highlight('tree_root/");
			menuStr.append(countFirst);
			menuStr.append(":T/");
			menuStr.append(countSecond);

			menuStr.append(":T');\">\n");
		} else // 为“1”，动作菜单
		{
			if (position.equals("normal")) // 不是同级菜单中的最后一个
				menuStr
						.append("        <IMG src=\"./images/tree/conct_T.gif\" border=\"0\" width=\"19\" height=\"16\">\n");
			else
				menuStr
						.append("        <IMG src=\"./images/tree/conct_L.gif\" border=\"0\" width=\"19\" height=\"16\">\n");
			menuStr.append("        <A href=\"");
			menuStr.append(addr);
			menuStr
					.append("\" target=\"frame_right\" onClick=\"tree_highlight('tree_root/");
			menuStr.append(countFirst);
			menuStr.append(":T/");
			menuStr.append(countSecond);

			menuStr.append("');\">\n");
			menuStr
					.append("        <IMG src=\"./images/tree/twistcb.gif\" border=\"0\" >\n ");
		}

		menuStr.append("        ");
		menuStr.append(name);
		menuStr.append("</A><BR>\n");
		menuStr.append("      </DIV> \n\n");

		return menuStr.toString();
	}

	/**
	 * 显示第三级菜单
	 * 
	 * @param countFirst
	 * @param countSecond
	 * @param countThird
	 * @param position
	 * @param menuId
	 * @return
	 * @throws MenuNotFoundException
	 */
	@SuppressWarnings("unchecked")
	private String menuThird(int countFirst, int countSecond, int countThird,
			String position, String menuId) {
		StringBuffer menuStr = new StringBuffer();
		String mId = menuId.substring(0, 6);
		
		mId = mId + "0000"; // 组装成要显示的菜单的ID

		List menuList = new ArrayList(); // 用于存储列表数据

		String name = "";
		String att = "";
		String addr = "";

		Menu menu = menuService.findById(mId);

		name = menu.getMenName();
		att = menu.getMenAtt();
		addr = menu.getMenUrl();

		menuStr.append("\n\n      <DIV class=\"tree_cell\" id=\"tree_root/");
		menuStr.append(countFirst);
		menuStr.append(":T/");
		menuStr.append(countSecond);
		menuStr.append(":T/");
		menuStr.append(countThird);

		if (att.equals("0")) // 路径菜单
			menuStr.append(":T\"> ");
		else
			menuStr.append("\"> ");

		menuStr
				.append("<IMG src=\"./images/tree/conct_I.gif\" border=\"0\" width=\"19\" height=\"16\">\n ");
		menuStr
				.append("<IMG src=\"./images/tree/space.gif\" border=\"0\" width=\"19\" height=\"16\">\n ");

		if (att.equals("0")) // 路径菜单
		{
			menuStr
					.append("<A href=\"JavaScript:tree_switch_folder('tree_root/");
			menuStr.append(countFirst);
			menuStr.append(":T/");
			menuStr.append(countSecond);
			menuStr.append(":T/");
			menuStr.append(countSecond);
			menuStr.append(":T');\">\n");
			if (position.equals("normal")) // 不是同级菜单中的最后一个
				menuStr
						.append("        <IMG src=\"./images/tree/conct_T.gif\" border=\"0\" width=\"19\" height=\"16\">\n");
			else
				menuStr
						.append("        <IMG src=\"./images/tree/conct_L.gif\" border=\"0\" width=\"19\" height=\"16\">\n");
			menuStr
					.append("        <IMG src=\"./images/tree/folder.gif\" border=\"0\" width=\"14\" height=\"14\" ></A>\n ");
			menuStr
					.append("        <A href=\"JavaScript:void(0);\" onClick=\"tree_highlight('tree_root/");
			menuStr.append(countFirst);
			menuStr.append(":T/");
			menuStr.append(countSecond);
			menuStr.append(":T/");
			menuStr.append(countThird);
			menuStr.append(":T');\">\n");
		} else // 为“1”，动作菜单
		{
			if (position.equals("normal")) // 不是同级菜单中的最后一个
				menuStr
						.append("        <IMG src=\"./images/tree/conct_T.gif\" border=\"0\" width=\"19\" height=\"16\">\n");
			else
				menuStr
						.append("        <IMG src=\"./images/tree/conct_L.gif\" border=\"0\" width=\"19\" height=\"16\">\n");
			menuStr.append("        <A href=\"");
			menuStr.append(addr);
			menuStr
					.append("\" target=\"frame_right\" onClick=\"tree_highlight('tree_root/");
			menuStr.append(countFirst);
			menuStr.append(":T/");
			menuStr.append(countSecond);
			menuStr.append(":T/");
			menuStr.append(countThird);
			menuStr.append("');\">\n");
			menuStr
					.append("        <IMG src=\"./images/tree/twistcb.gif\" border=\"0\" >\n ");
		}

		menuStr.append("        ");
		menuStr.append(name);
		menuStr.append("</A><BR>\n");
		menuStr.append("      </DIV> \n\n");

		return menuStr.toString();
	}
}