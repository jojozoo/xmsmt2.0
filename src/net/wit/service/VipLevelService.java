/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: VersionUpdateService.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月11日
 */
package net.wit.service;

import java.util.Date;
import java.util.List;

import net.wit.Page;
import net.wit.Pageable;
import net.wit.entity.Member;
import net.wit.entity.Tenant;
import net.wit.entity.TenantShopkeeper;
import net.wit.entity.VersionUpdate;
import net.wit.entity.VipLevel;
import net.wit.util.BizException;

/**
 * 
 * @author: yangyang.wu
 * @version Revision: 0.0.1
 * @Date：2015年9月11日
 */
public interface VipLevelService extends BaseService<VipLevel, Long>{
	
	/**获取企业的默认vip等级*/
	public VipLevel getDefaultVipLevel(Tenant tenant) throws BizException;
	
	/**
	 * 根据邀请人数判断是否满足升级条件。
	 * 如果不满足返回当前传入的 currentVipLevel
	 * @param currentVipLevel
	 * @param invitedNum
	 * @return VipLevel
	 */
	public VipLevel checkIsUpdateLevel(VipLevel currentVipLevel, Integer invitedNum);
	/**
	 * 根据member 获取vip等级
	 * @param member
	 * @return
	 */
	public VipLevel getVipLevelByMemeber(Member member);

	/**
	 * 根据企业获取VipLevel
	 * @param tenant
	 * @return
	 */
	public List<VipLevel> getVipLevelByTenant(Tenant tenant);

}
