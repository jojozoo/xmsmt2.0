package net.wit.util;





public class TicketUtil {
	
	/**
	 * 未收取状态
	 */
	public static final String TICKETCACHE_NORECEIVESTATUS = "0";
	/**
	 * 已经领取
	 */
	public static final String TICKETCACHE_RECEIVEDSTATUS = "1";
	/**
	 * 已失效
	 */
	public static final String TICKETCACHE_EXPIREDSTATUS = "2";
	/**
	 * 系统发放
	 */
	public  static final String TICKETCACHE_SYSTEM_MODEL = "0";
	/**
	 * 新店主发放
	 */
	public static final String TICKETCACHE_NEW_SHOPKEEPER_MODEL = "1";
	/**
	 * 申请发放
	 */
	public static final String TICKETCACHE_APPLY_MODEL="2";
	/**
	 * 定向发放
	 */
	public static final String TICKETCACHE_MENUAL_MODEL = "3";
	
	/**
	 * 每月定额发放模式
	 */
	public static final String TICKETSET_REGULAR_TICKETSET_TYPE = "0";
	/**
	 * 新店主发放模式
	 */
	public static final String TICKETSET_NEWSHOPKEEPER_TYPE = "1";
	/**
	 * 内购券店主领取还没有被买家领取
	 */
	public static final String  TICKET_SHOPKEERP_NOSHARED="0";
	/**
	 * 店主分享买家已经领取
	 */
	public static final String  TICKET_BUYER_RECEIVED="1";
	/**
	 * 买家领取经使用
	 */
	public static final String TICKET_BUYER_USED="2";
	/**
	 * 内购券已经失效
	 */
	public static final String TICKET_EXPIRED = "3";
}
