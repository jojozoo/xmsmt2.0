package net.wit.dao;

import java.util.Date;
import java.util.List;

import net.wit.entity.Member;
import net.wit.entity.Tenant;
import net.wit.entity.TicketApply;
import net.wit.entity.TicketApply.ApplyStatus;

public interface TicketApplyDao extends BaseDao<TicketApply, Long>{

	/**
	 * 查询店长在申请状态 内购券申请记录
	 * @param owner
	 * @return
	 */
	public List<TicketApply> queryTicketApplyByOwner(Member owner,Boolean applyType);

	/**
	 * 查询会员与店长是否有
	 * @param owner
	 * @param member
	 * @return
	 */
	public List<TicketApply> queryTicketApplyByMemberOwner(Member owner, Member member);

	/**
	 * 查询店长时间范围内向企业申请券的记录,不包含被拒绝的申请；
	 * @param owner
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<TicketApply> queryTicketApplyByOwner(Member owner, Date beginDate,
			Date endDate);

	public List<TicketApply> queryTicketApply(Boolean applyType, ApplyStatus status,Tenant tenant);

}
