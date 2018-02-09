package com.lequ.login.bootstrap.dao.userinfo;



import java.util.List;
import java.util.Map;

import com.lequ.login.bootstrap.dao.BaseDao;
import com.lequ.login.bootstrap.model.UserInfoEntity;


public interface UserInfoDao extends BaseDao<UserInfoEntity> {


    /**
     * @param params
     * @return
     */
    List<UserInfoEntity> queryUserInfoList(Map<String,Object> params);
	
}
