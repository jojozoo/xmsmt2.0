package net.wit.dao;

import java.util.List;

import net.wit.entity.Tenant;
import net.wit.entity.VipLevel;

public interface VipLevelDao  extends BaseDao<VipLevel, Long>{

	public List<VipLevel> queryLevel(Tenant tenant,String levelName,Boolean isDefault);
}
