package net.wit.controller.admin;

import javax.annotation.Resource;
import net.wit.Message;
import net.wit.Pageable;
import net.wit.service.ProductNotifyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("ProductNotifyntroller")
@RequestMapping({"/admin/product_notify"})
public class ProductNotifyController extends BaseController
{

  @Resource(name="productNotifyServiceImpl")
  private ProductNotifyService productNotifyService;

  @RequestMapping(value={"/send"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public Message send(Long[] ids)
  {
    int count = this.productNotifyService.send(ids);
    return Message.success("admin.productNotify.sentSuccess", new Object[] { Integer.valueOf(count) });
  }

  @RequestMapping(value={"/list"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String list(Boolean isMarketable, Boolean isOutOfStock, Boolean hasSent, Pageable pageable, ModelMap model)
  {
    model.addAttribute("isMarketable", isMarketable);
    model.addAttribute("isOutOfStock", isOutOfStock);
    model.addAttribute("hasSent", hasSent);
    model.addAttribute("page", this.productNotifyService.findPage(null, isMarketable, isOutOfStock, hasSent, pageable));
    return "/admin/product_notify/list";
  }

  @RequestMapping(value={"/delete"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public Message delete(Long[] ids)
  {
    this.productNotifyService.delete(ids);
    return SUCCESS_MESSAGE;
  }
}