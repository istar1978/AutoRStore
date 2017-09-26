package com.zhaozhy.autorstore.service.impl;

import java.util.List;

import com.zhaozhy.autorstore.entity.Branch;
import com.zhaozhy.autorstore.service.BranchService;
/**
 * 
 * @Title				BranchServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午08:40:14
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class BranchServiceImpl extends BaseServiceImpl<Branch> implements BranchService {


	public List findAllByIdLike(String b_id) {
		return this.branchDAO.findAllByIdLike(b_id);
	}

	public List findAllInUsing() {
		return this.branchDAO.findAllInUsing();
	}

	public List findAllOthers(String branchId) {
		return this.branchDAO.findAllOthers(branchId);
	}

	public List findByBraLevel(Object braLevel) {
		return this.branchDAO.findByBraLevel(braLevel);
	}

	public List findByBraName(Object braName) {
		return this.branchDAO.findByBraName(braName);
	}

	public List findByBraStat(Object braStat) {
		return this.branchDAO.findByBraStat(braStat);
	}

	public List findByBraUpid(Object braUpid) {
		return this.branchDAO.findByBraUpid(braUpid);
	}

	public Branch findById(String id) {
		return this.branchDAO.findById(id);
	}

	public List queryPageByIdLike(String b_id, int intPage, int intPageSize) {
		return this.branchDAO.queryPageByIdLike(b_id, intPage, intPageSize);
	}

}
