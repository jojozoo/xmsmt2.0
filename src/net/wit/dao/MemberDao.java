/*
 * Copyright 2005-2013 rsico. All rights reserved.
 * Support: http://www.rsico.cn
 * License: http://www.rsico.cn/license
 */
package net.wit.dao;

import java.util.Date;
import java.util.List;

import net.wit.Page;
import net.wit.Pageable;
import net.wit.entity.Member;
import net.wit.entity.Tenant;

/**
 * Dao - 会员
 * @author rsico Team
 * @version 3.0
 */
public interface MemberDao extends BaseDao<Member, Long> {

	/**
	 * 判断用户名是否存在
	 * @param username 用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);

	/**
	 * 判断E-mail是否存在
	 * @param email E-mail(忽略大小写)
	 * @return E-mail是否存在
	 */
	boolean emailExists(String email);

	/**
	 * 判断mobile是否存在
	 * @param mobile mobile(忽略大小写)
	 * @return mobile是否存在
	 */
	boolean mobileExists(String mobile);

	/**
	 * 根据用户名查找会员
	 * @param username 用户名(忽略大小写)
	 * @return 会员，若不存在则返回null
	 */
	Member findByUsername(String username);

	/**
	 * 根据E-mail查找会员
	 * @param email E-mail(忽略大小写)
	 * @return 会员，若不存在则返回null
	 */
	List<Member> findListByEmail(String email);

	/**
	 * 查找会员消费信息
	 * @param beginDate 起始日期
	 * @param endDate 结束日期
	 * @param count 数量
	 * @return 会员消费信息
	 */
	List<Object[]> findPurchaseList(Date beginDate, Date endDate, Integer count);

	/**
	 * 根据代理商查找会员
	 * @param agent
	 * @return 会员，若不存在则返回null
	 */
	Page<Member> findPage(Member member, Pageable pageable);
	
	public Page<Member> findPage(Tenant tenant, Pageable pageable,Boolean isRegister);

	/**
	 * 根据店铺查找员工
	 * @param tenant
	 * @return 会员，若不存在则返回null
	 */
	Page<Member> findPage(Tenant tenant, Pageable pageable);
 	Page<Member> findPage(List<Long> memberID,String mobile, Pageable pageable);

	/**
	 * 根据实名认证查找会员
	 * @param idcard
	 * @return 会员，若不存在则返回null
	 */
	Page<Member> findRealnameMemberPage(Pageable pageable);

	Member findByTel(String mobile);

	Member findByEmail(String email);

	List<Member> findList(Member member);
	
	List<Member> findList(List<Long> id);

	/**
	 * 判断E-mail是否存在
	 * @param email E-mail(忽略大小写)
	 * @return E-mail是否存在
	 */
	boolean emailExistsWithoutUser(String email,Member member);

	/**
	 * 判断mobile是否存在
	 * @param mobile mobile(忽略大小写)
	 * @return mobile是否存在
	 */
	boolean mobileExistsWithoutUser(String mobile,Member member);
	
	/**
	 * 根据手机号查找会员
	 * @param mobile 手机号
	 * @return 会员，若不存在则返回null
	 */
	Member findByBindTel(String mobile);

	/**
	 * 根据邮箱查找会员
	 * @param email 邮箱
	 * @return 会员，若不存在则返回null
	 */
	Member findByBindEmail(String email);

	/**
	 * 查找收藏商家分页
	 * @param member 会员
	 * @param pageable 分页信息
	 * @return 收藏商家分页
	 */
	Page<Member> findFavoritePage(Member member, Pageable pageable);
	
	public Page<Member> findPage(List<Long> id, Pageable pageable,Boolean isRegister);

    public boolean validateMember(String mobile, String comId);

    public int addMembers(List<Object[]> addMembers, String comId);
    /**
     * 查询所有店主-慎用（批处理）
     * @param isShop
     * @return
     */
    public List<Member> findMemberList(boolean isShop);

    public long count(Tenant tenant);
    
    public void addMember(Member member);
}