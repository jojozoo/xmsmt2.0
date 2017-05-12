/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: VersionUpdateServiceImpl.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月11日
 */
package net.wit.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.wit.Page;
import net.wit.Pageable;
import net.wit.dao.VersionUpdateDao;
import net.wit.dao.VipLevelDao;
import net.wit.entity.Member;
import net.wit.entity.Tenant;
import net.wit.entity.TenantShopkeeper;
import net.wit.entity.VersionUpdate;
import net.wit.entity.VipLevel;
import net.wit.service.TenantShopkeeperService;
import net.wit.service.VersionUpdateService;
import net.wit.service.VipLevelService;
import net.wit.util.BizException;

/**
 * 
 * @author: yangyang.wu
 * @version Revision: 0.0.1
 * @Date：2015年9月11日
 */
@Service("vipLevelServiceImpl")
public class VipLevelServiceImpl extends BaseServiceImpl<VipLevel, Long> implements VipLevelService{
	
	@Resource(name = "vipLevelDaoImpl")
	private VipLevelDao vipLevelDao;
	
	@Resource(name = "vipLevelDaoImpl")
	public void setBaseDao(VipLevelDao vipLevelDao) {
		super.setBaseDao(vipLevelDao);;
	}
	
	@Autowired
	private TenantShopkeeperService tenantShopKeeperService;

	@Override
	public VipLevel getDefaultVipLevel(Tenant tenant) throws BizException {
		List<VipLevel> list = vipLevelDao.queryLevel(tenant, null, true);
		if(list.size()==0) throw new BizException("未设置默认vip等级！");
		else return list.get(0);
	}

	@Override
	public VipLevel checkIsUpdateLevel(VipLevel currentVipLevel, Integer invitedNum) {
		Tenant tenant = currentVipLevel.getTenant();
		List<VipLevel> list = vipLevelDao.queryLevel(tenant, null, null);
		for (VipLevel vipLevel : list) {
			if(invitedNum==vipLevel.getInviteCondition())return vipLevel;
		}
		return currentVipLevel;
	}
	
	@Override
	public VipLevel getVipLevelByMemeber(Member member){
		TenantShopkeeper ts = tenantShopKeeperService.findShopKeeperByMemberId(member.getId());
		if(ts==null) return null;
		else return ts.getVipLevel();
	}
	
	@Override
	public List<VipLevel> getVipLevelByTenant(Tenant tenant){
		List<VipLevel> list = vipLevelDao.queryLevel(tenant, null, null);
		return list;
	}

}
