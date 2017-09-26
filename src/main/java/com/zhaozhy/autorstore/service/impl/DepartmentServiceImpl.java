package com.zhaozhy.autorstore.service.impl;

import java.util.List;

import com.zhaozhy.autorstore.dao.DepartmentDAO;
import com.zhaozhy.autorstore.entity.Department;
import com.zhaozhy.autorstore.service.DepartmentService;
/**
 * 
 * @Title				DepartmentServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午09:36:29
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {

	public List findAllByIdLike(String depId) {
		return this.departmentDAO.findAllByIdLike(depId);
	}

	public List findAllInUsing() {
		return this.departmentDAO.findAllInUsing();
	}

	public List findAllInUsingN() {
		return this.departmentDAO.findAllInUsingN();
	}

	public List findByDepName(Object depName) {
		return this.departmentDAO.findByDepName(depName);
	}

	public List findByDepStat(Object depStat) {
		return this.departmentDAO.findByDepStat(depStat);
	}

	public List findByDepType(Object depType) {
		return this.departmentDAO.findByDepType(depType);
	}

	public Department findById(String id) {
		return this.departmentDAO.findById(id);
	}

	public List queryPerPageByIdLike(String depId, int intPage, int intPageSize) {
		return this.departmentDAO.queryPerPageByIdLike(depId, intPage, intPageSize);
	}

}
