package com.lequ.server.bootstrap.dao.userinfo;



import java.util.List;
import java.util.Map;

import com.lequ.server.bootstrap.dao.BaseDao;
import com.lequ.server.bootstrap.model.UserInfoEntity;



public interface UserInfoDao extends BaseDao<UserInfoEntity> {


    /**
     * @param params
     * @return
     */
    List<UserInfoEntity> queryUserInfoList(Map<String,Object> params);
	
}
