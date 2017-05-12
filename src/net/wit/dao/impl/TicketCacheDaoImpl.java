/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: TicketSetDaoImpl.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月7日
 */
package net.wit.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import net.wit.Page;
import net.wit.Pageable;
import net.wit.dao.TicketCacheDao;
import net.wit.dao.TicketSetDao;
import net.wit.entity.Member;
import net.wit.entity.Order;
import net.wit.entity.OrderItem;
import net.wit.entity.OrderSettlement;
import net.wit.entity.Tenant;
import net.wit.entity.TenantCategory;
import net.wit.entity.TicketCache;
import net.wit.entity.TicketSet;
import net.wit.entity.Order.OrderStatus;
import net.wit.entity.Order.PaymentStatus;
import net.wit.entity.Order.ShippingStatus;
import net.wit.util.TicketUtil;

/**
 * 
 * @author: yangyang.wu
 * @version Revision: 0.0.1
 * @Date：2015年9月7日
 */
@Repository("ticketCacheDaoImpl")
public class TicketCacheDaoImpl extends BaseDaoImpl<TicketCache, Long> implements TicketCacheDao{

	public List<TicketCache> getTicketCacheByTenantId(Long memberId,String receiveStatus){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TicketCache> criteriaQuery = criteriaBuilder.createQuery(TicketCache.class);
		Root<TicketCache> root = criteriaQuery.from(TicketCache.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("memberId"), memberId));
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("receiveStatus"), receiveStatus));
		criteriaQuery.where(restrictions);
		List<TicketCache> list = super.findList(criteriaQuery);
		return list;
	}
	
	public Long getshopkeeperNoUseCount(Long memberId,String receiveStatus){
		Long count = null;
		String jpql = "select sum(tc.num) from TicketCache tc where tc.memberId =:memberId and tc.receiveStatus = :receiveStatus";
		try {
			count = entityManager.createQuery(jpql,Long.class)
					.setFlushMode(FlushModeType.COMMIT).setParameter("memberId", memberId)
					.setParameter("receiveStatus",receiveStatus).getSingleResult();
		} catch (NoResultException e) {
			
		}
		if(count==null) count = new Long(0);
		return count;
	}
	@Override
	public boolean updateTicketCache(Long memberId){
		if(memberId==null) return false;
		try{
			String jpql = "update  TicketCache set  tc.receiveStatus =:receiveStatus  where tc.memberId =:memberId and tc.receiveStatus = :receiveStatus1";
			entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("memberId",memberId).setParameter("receiveStatus", TicketCache.TICKETCACHE_RECEIVEDSTATUS).
			setParameter("receiveStatus1", TicketCache.TICKETCACHE_NORECEIVESTATUS).executeUpdate();
			return true;
		}catch(Exception e){
			return false;
		}

	}
	@Override
	public List<TicketCache> getTicketCacheByMember(Long memberId){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TicketCache> criteriaQuery = criteriaBuilder.createQuery(TicketCache.class);
		Root<TicketCache> root = criteriaQuery.from(TicketCache.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("memberId"), memberId));
		criteriaQuery.where(restrictions);
		List<TicketCache> list = super.findList(criteriaQuery);
		return list;	
	}
	
	
	@Override
	public Page<TicketCache> findTickeCacheByPage(Long tenantId,Date startDate,Date endDate,String sendModel,String name,Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TicketCache> criteriaQuery = criteriaBuilder.createQuery(TicketCache.class);
		Root<TicketCache> root = criteriaQuery.from(TicketCache.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (tenantId != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.<Long>get("tenantId"), tenantId));
		}
		if(startDate!=null)
		{
			restrictions=criteriaBuilder.and(restrictions,criteriaBuilder.greaterThanOrEqualTo(root.<Date> get("createDate"), startDate));
		}
		if(endDate!=null)
		{
			restrictions=criteriaBuilder.and(restrictions,criteriaBuilder.lessThanOrEqualTo(root.<Date> get("createDate"), endDate));
		}
		if(StringUtils.isNotBlank(sendModel)){
			restrictions=criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.<String> get("sendModel"), sendModel));
		}
		if (StringUtils.isNotBlank(name)) {
			Subquery<Member> namebquery = criteriaQuery.subquery(Member.class);
			Root<Member> nameRoot = namebquery.from(Member.class);
			namebquery.select(nameRoot);
			namebquery.where(criteriaBuilder.like(nameRoot.<String> get("name"), name + "%"));
			restrictions = criteriaBuilder.and(criteriaBuilder.in(namebquery), restrictions);
		}
		criteriaQuery.where(restrictions);
		return super.findPage(criteriaQuery, pageable);
	}
	
	
	
}
