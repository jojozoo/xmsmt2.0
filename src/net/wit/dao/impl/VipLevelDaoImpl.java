/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: VersionUpdateDaoImpl.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月11日
 */
package net.wit.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import net.wit.Page;
import net.wit.Pageable;
import net.wit.dao.VersionUpdateDao;
import net.wit.dao.VipLevelDao;
import net.wit.entity.Tenant;
import net.wit.entity.TenantCategory;
import net.wit.entity.Ticket;
import net.wit.entity.TicketCache;
import net.wit.entity.VersionUpdate;
import net.wit.entity.VipLevel;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author: yangyang.wu
 * @version Revision: 0.0.1
 * @Date：2015年9月11日
 */
@Repository("vipLevelDaoImpl")
public class VipLevelDaoImpl extends BaseDaoImpl<VipLevel, Long> implements VipLevelDao{

	@Override
	public List<VipLevel> queryLevel(Tenant tenant, String levelName,
			Boolean isDefault) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<VipLevel> criteriaQuery = criteriaBuilder.createQuery(VipLevel.class);
		Root<VipLevel> root = criteriaQuery.from(VipLevel.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if(tenant!=null){
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("tenant"), tenant));
		}
		if(levelName!=null){
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("levelName"), levelName));
		}
		if(isDefault!=null){
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isDefault"), isDefault));
		}
		criteriaQuery.where(restrictions);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("inviteCondition")));
		List<VipLevel> list = super.findList(criteriaQuery);
		return list;
	}
	
	

}
