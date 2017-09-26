package com.zhaozhy.autorstore.service.impl;

import java.util.List;

import com.zhaozhy.autorstore.entity.Staffer;
import com.zhaozhy.autorstore.service.StafferService;
/**
 * 
 * @Title				StafferServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   下午01:37:30
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class StafferServiceImpl extends BaseServiceImpl<Staffer> implements StafferService {


	public List findAllByIdLike(String id) {
		return this.stafferDAO.findAllByIdLike(id);
	}

	public List findByBraId(Object braId) {
		return this.stafferDAO.findByBraId(braId);
	}

	public List findByDepId(Object depId) {
		return this.stafferDAO.findByDepId(depId);
	}

	public Staffer findById(String id) {
		return this.stafferDAO.findById(id);
	}

	public List findByStaLevel(Object staLevel) {
		return this.stafferDAO.findByStaLevel(staLevel);
	}

	public List findByStaName(Object staName) {
		return this.stafferDAO.findByStaName(staName);
	}

	public List findByStaPosition(Object staPosition) {
		return this.stafferDAO.findByStaPosition(staPosition);
	}

	public List findByStaPwd(Object staPwd) {
		return this.stafferDAO.findByStaPwd(staPwd);
	}

	public List findByStaStat(Object staStat) {
		return this.stafferDAO.findByStaStat(staStat);
	}

	public List queryPageByIdLike(String id, int intPage, int intPageSize) {
		return this.stafferDAO.queryPageByIdLike(id, intPage, intPageSize);
	}

}
