package net.wit.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.wit.Page;
import net.wit.Pageable;
import net.wit.entity.Admin;
import net.wit.entity.Member;
import net.wit.entity.TenantShopkeeper;
import net.wit.entity.Ticket;
import net.wit.service.AdminService;
import net.wit.service.MemberAttributeService;
import net.wit.service.MemberRankService;
import net.wit.service.MemberService;
import net.wit.service.TenantCategoryService;
import net.wit.service.TenantShopkeeperService;
import net.wit.service.TicketCacheService;
import net.wit.service.TicketService;
import net.wit.service.TicketSetService;
import net.wit.util.DateUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("tickeController")
@RequestMapping({ "/admin/ticket" })
public class TicketController extends BaseController {

	@Resource(name = "tenantCategoryServiceImpl")
	private TenantCategoryService tenantCategoryService;

	@Resource(name = "memberRankServiceImpl")
	private MemberRankService memberRankService;

	@Resource(name = "memberAttributeServiceImpl")
	private MemberAttributeService memberAttributeService;

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;

	@Resource(name = "adminServiceImpl")
	private AdminService adminService;

	@Resource(name = "ticketSetServiceImpl")
	private TicketSetService ticketSetService;
	
	@Resource(name = "ticketServiceImpl")
	private TicketService ticketService;

	@Resource(name = "ticketCacheServiceImpl")
	private TicketCacheService ticketCacheService;
	//
	@Resource(name = "tenantShopkeeperServiceImpl")
	private TenantShopkeeperService tenantShopkeeperService;

	@RequestMapping(value = { "/add" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String add(String searchValue, Pageable pageable, ModelMap model,
			RedirectAttributes redirectAttributes) {
		Admin admin = adminService.getCurrent();
		List<Long> members = new ArrayList<Long>();
		List<TenantShopkeeper> list = tenantShopkeeperService
				.findShopKeeperByTenantId(admin.getTenant().getId());
		for (TenantShopkeeper shop : list) {

			members.add(shop.getMember().getId());

		}
		Page<Member> member = this.memberService.findPage(members, searchValue,
				pageable);
		model.addAttribute("tenantCategoryTree",
				this.tenantCategoryService.findTree());
		model.addAttribute("memberRanks", this.memberRankService.findAll());
		model.addAttribute("memberAttributes",
				this.memberAttributeService.findAll());
		model.addAttribute("page", member);
		if (member.getTotal() == 0) {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
		}
		return "/admin/ticket/add";
	}

	@RequestMapping(value = { "/query" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String query(String searchValue, Pageable pageable, ModelMap model,
			RedirectAttributes redirectAttributes) {
			try {
				searchValue=new String(searchValue.getBytes("ISO-8859-1"),"UTF-8");
			} catch (Exception localException) {
			}
		Admin admin = adminService.getCurrent();
		List<Long> members = new ArrayList<Long>();
		List<TenantShopkeeper> list = tenantShopkeeperService
				.findShopKeeperByTenantId(admin.getTenant().getId());
		for (TenantShopkeeper shop : list) {
			members.add(shop.getMember().getId());
		}
		Page<Member> member = this.memberService.findPage(members, searchValue,
				pageable);
		model.addAttribute("tenantCategoryTree",
				this.tenantCategoryService.findTree());
		model.addAttribute("memberRanks", this.memberRankService.findAll());
		model.addAttribute("memberAttributes",
				this.memberAttributeService.findAll());
		model.addAttribute("page", member);
		return "/admin/ticket/add";
	}
	
	/**分页查询内购券明细
	 * @param shopkeeperMobile
	 * @param ticketStatusParam
	 * @param ticketModifyDate
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String list(String shopkeeperMobile, String ticketStatusParam, String ticketModifyDate, Pageable pageable, ModelMap model) {
		Member member =null;
		if(shopkeeperMobile!=null && !"".equals(shopkeeperMobile)){
			member = memberService.findByTel(shopkeeperMobile);
			model.addAttribute("shopkeeperMobile", shopkeeperMobile);
		}
		Date lastDayOfMonth =null;
		Date firstDayOfMonth =null;
		if(ticketModifyDate!=null && !"".equals(ticketModifyDate)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Date date;
			try {
				date = sdf.parse(ticketModifyDate);
				lastDayOfMonth = DateUtil.getLastDayOfMonth(date);
				firstDayOfMonth = DateUtil.getFirstDayOfMonth(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		Page<Ticket> page = ticketService.findPage(member, ticketStatusParam, firstDayOfMonth,lastDayOfMonth, pageable);
		model.addAttribute("page", page);
		model.addAttribute("ticketStatusParam", ticketStatusParam);
		model.addAttribute("ticketModifyDate", ticketModifyDate);
		return "/admin/ticket_get/list";
	}

	@RequestMapping(value = { "/sendAll" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String sendAll(String num, RedirectAttributes redirectAttributes) {
		Admin admin = adminService.getCurrent();
		Long tenantId = admin.getTenant().getId();
		List<TenantShopkeeper> list = tenantShopkeeperService
				.findShopKeeperByTenantId(admin.getTenant().getId());
		List<Long> memberIdList = new ArrayList<Long>();
		for (TenantShopkeeper e : list) {
			memberIdList.add(e.getMember().getId());
		}
		ticketCacheService.batchSaveTicketCacheByMemberListOnManual(
				memberIdList, tenantId, Integer.parseInt(num));
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:add.jhtml";
	}

	@RequestMapping(value = { "/send" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String send(String num, String day, String sendAll, Long[] ids,
			RedirectAttributes redirectAttributes) {
		if (ids == null) {
			addFlashMessage(redirectAttributes, NOT_SELECTED);
			return "redirect:add.jhtml";
		}
		Admin admin = adminService.getCurrent();
		Long tenantId = admin.getTenant().getId();
		List<Long> memberIdList = new ArrayList<Long>();
		for (long e : ids) {
			memberIdList.add(e);
		}
		ticketCacheService.batchSaveTicketCacheByMemberListOnManual(
				memberIdList, tenantId, Integer.parseInt(num));
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:add.jhtml";
	}
}