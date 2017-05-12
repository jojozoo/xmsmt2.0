package net.wit.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_vip_level")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_vip_level_sequence")
public class VipLevel  extends BaseEntity{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**所属企业*/
	private Tenant tenant;

	/**等级名称*/
	private String levelName; 
	/**奖金比例*/
	private Integer bounsLevel;
	/**发券数量*/
	private Integer ticketNum;
	/**邀请人数的条件*/
	private Integer inviteCondition;
	/**备注*/
	private Integer remark;
	/**是否 默认等级*/
	private Boolean isDefault;
	
	
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public Integer getBounsLevel() {
		return bounsLevel;
	}
	public void setBounsLevel(Integer bounsLevel) {
		this.bounsLevel = bounsLevel;
	}
	public Integer getTicketNum() {
		return ticketNum;
	}
	public void setTicketNum(Integer ticketNum) {
		this.ticketNum = ticketNum;
	}
	public Integer getInviteCondition() {
		return inviteCondition;
	}
	public void setInviteCondition(Integer inviteCondition) {
		this.inviteCondition = inviteCondition;
	}
	public Integer getRemark() {
		return remark;
	}
	public void setRemark(Integer remark) {
		this.remark = remark;
	}
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	public Boolean getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
}
