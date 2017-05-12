/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: TicketSetDaoImpl.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月7日
 */
package net.wit.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.wit.Page;
import net.wit.Pageable;
import net.wit.dao.TicketApplyDao;
import net.wit.dao.TicketDao;
import net.wit.entity.Member;
import net.wit.entity.OwnerIncome;
import net.wit.entity.Tenant;
import net.wit.entity.Ticket;
import net.wit.entity.TicketCache;
import net.wit.entity.Ticket.Status;
import net.wit.entity.TicketApply.ApplyStatus;
import net.wit.entity.TicketApply;

import org.springframework.stereotype.Repository;


/**
 * 
 * @author: yangyang.wu
 * @version Revision: 0.0.1
 * @Date：2015年9月7日
 */
@Repository("ticketApplyDaoImpl")
public class TicketApplyDaoImpl extends BaseDaoImpl<TicketApply, Long> implements TicketApplyDao{

	@Override
	public List<TicketApply> queryTicketApplyByOwner(Member owner ,Boolean applyType){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TicketApply> criteriaQuery = criteriaBuilder.createQuery(TicketApply.class);
		Root<TicketApply> root = criteriaQuery.from(TicketApply.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("owner"), owner));
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("applyType"), applyType));
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("applyStatus"), ApplyStatus.apply));
		criteriaQuery.where(restrictions);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createDate")));
		List<TicketApply> returnlist = super.findList(criteriaQuery);
		return returnlist;
	}
	
	
	@Override
	public List<TicketApply> queryTicketApply(Boolean applyType,ApplyStatus status,Tenant tenant){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TicketApply> criteriaQuery = criteriaBuilder.createQuery(TicketApply.class);
		Root<TicketApply> root = criteriaQuery.from(TicketApply.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("applyType"), applyType));
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("applyStatus"), status));
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("tenant"), tenant));
		criteriaQuery.where(restrictions);
		List<TicketApply> returnlist = super.findList(criteriaQuery);
		return returnlist;
	}
	
	@Override
	public List<TicketApply> queryTicketApplyByOwner(Member owner,Date beginDate, Date endDate){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TicketApply> criteriaQuery = criteriaBuilder.createQuery(TicketApply.class);
		Root<TicketApply> root = criteriaQuery.from(TicketApply.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("owner"), owner));
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("applyType"), false));
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.notEqual(root.get("applyStatus"), ApplyStatus.rejected));
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThanOrEqualTo(root.<Date> get("createDate"), beginDate));
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThan(root.<Date> get("createDate"), endDate));
		criteriaQuery.where(restrictions);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createDate")));
		List<TicketApply> returnlist = super.findList(criteriaQuery);
		return returnlist;
	}
	
	@Override
	public List<TicketApply> queryTicketApplyByMemberOwner(Member owner,Member member){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TicketApply> criteriaQuery = criteriaBuilder.createQuery(TicketApply.class);
		Root<TicketApply> root = criteriaQuery.from(TicketApply.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("owner"), owner));
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("member"), member));
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("applyType"), true));
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("applyStatus"), ApplyStatus.apply));
		criteriaQuery.where(restrictions);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createDate")));
		List<TicketApply> returnlist = super.findList(criteriaQuery);
		return returnlist;
	}

}
