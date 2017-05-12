/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: TenantShopkeeperController.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2015年9月20日
 */
package net.wit.controller.admin;

import java.util.Date;

import javax.annotation.Resource;

import net.wit.Page;
import net.wit.Pageable;
import net.wit.entity.Admin;
import net.wit.entity.TenantShopkeeper;
import net.wit.entity.TenantShopkeeper.IsShopkeeper;
import net.wit.service.AdminService;
import net.wit.service.TenantShopkeeperService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author: yangyang.wu
 * @version Revision: 0.0.1
 * @Date：2015年9月20日
 */
@Controller("tenantShopkeeperController")
@RequestMapping({"/admin/tenant_shopkeeper"})
public class TenantShopkeeperController extends BaseController{
	
	 @Resource(name="tenantShopkeeperServiceImpl")
	 private TenantShopkeeperService tenantShopkeeperService;
	 
	  @Resource(name="adminServiceImpl")
	  private AdminService adminService;

	 @RequestMapping(value={"/list"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  public String list(Pageable pageable, ModelMap model)
	  {
		 String searchValue = null;
			try {
				searchValue=new String(pageable.getSearchValue().getBytes("ISO-8859-1"),"UTF-8");
				pageable.setSearchValue(searchValue);
			} catch (Exception localException) {
			}
		 Admin admin = adminService.getCurrent();
		 Page<TenantShopkeeper> page=this.tenantShopkeeperService.findPage(admin.getTenant(),IsShopkeeper.yes, null , pageable);
		 model.addAttribute("tenant", admin.getTenant());
	     model.addAttribute("page",page);
	    return "/admin/tenant_shopkeeper/list";
	  }
	 //查询
	 @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  public String search(String memberName,Pageable pageable, ModelMap model)
	  {
		 String searchValue = null;
			try {
				memberName=pageable.getSearchValue();
				pageable.setSearchProperty(null);
			} catch (Exception localException) {
			}
		 Admin admin = adminService.getCurrent();
	    model.addAttribute("page", this.tenantShopkeeperService.findPageSearch(memberName, admin.getTenant(),pageable));
	    model.addAttribute("memberName", memberName);
	    return "/admin/tenant_shopkeeper/list";
	  }
}
