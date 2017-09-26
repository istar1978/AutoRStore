package com.zhaozhy.autorstore.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.zhaozhy.autorstore.entity.ConsumeCart;
import com.zhaozhy.autorstore.entity.Material;
import com.zhaozhy.autorstore.entity.MaterialId;
import com.zhaozhy.autorstore.service.ConsumeCartService;
import com.zhaozhy.autorstore.sysadmin.UserContext;
import com.zhaozhy.autorstore.util.DicDataUtil;
/**
 * 
 * @Title				ConsumeCartServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-19   上午10:13:30
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class ConsumeCartServiceImpl extends BaseServiceImpl<ConsumeCart> implements ConsumeCartService {

	public List findByAssId(Object assId) {
		return this.consumeCartDAO.findByAssId(assId);
	}

	public List findByCartMoney(Object cartMoney) {
		return this.consumeCartDAO.findByCartMoney(cartMoney);
	}

	public List findByCartNum(Object cartNum) {
		return this.consumeCartDAO.findByCartNum(cartNum);
	}

	public List findByCartPerprice(Object cartPerprice) {
		return this.consumeCartDAO.findByCartPerprice(cartPerprice);
	}

	public ConsumeCart findById(Integer id) {
		return this.consumeCartDAO.findById(id);
	}

	public List findByMatId(Object matId) {
		return this.consumeCartDAO.findByMatId(matId);
	}

	public List findByRepId(Object repId) {
		return this.consumeCartDAO.findByRepId(repId);
	}

	public ConsumeCart ifHasSameItem(ConsumeCart cart) {
		return this.consumeCartDAO.ifHasSameItem(cart);
	}

	public int[] deleteCartItems(List keyList) {
		return this.consumeCartDAO.deleteCartItems(keyList);
	}

	public BigDecimal getSumPriceByAssId(String assId) {
		List<ConsumeCart> cartList=this.consumeCartDAO.findByAssId(assId);
		BigDecimal sumPrice=new BigDecimal(0);
		for(ConsumeCart cart:cartList){
			sumPrice=sumPrice.add(cart.getCartMoney());
		}
		return sumPrice;
	}

	public String validateCartNum(String assId, UserContext uc) {
		StringBuffer rtnStr=new StringBuffer("");
		List<ConsumeCart> ccList=this.consumeCartDAO.findByAssIdWithMatIdNotNull(assId);
		MaterialId mId=new MaterialId();
		mId.setBraId(uc.getStafferBranchId());
		mId.setMatStat(DicDataUtil.DICDATA_000000);
		
		if(ccList.size()>0){
			for(ConsumeCart cart:ccList){
				mId.setMatId(cart.getMatId());
				Material material=this.materialDAO.findById(mId);
				if(cart.getCartNum()>material.getMatNum()){
					rtnStr.append(material.getMatName()+" 库存量["+material.getMatNum()+"]不足，请确认；\n");
				}
			}
		}
		return rtnStr.toString();
	}

}
