package com.lequ.mybatis.common;

/**
 * DB上下文环境.
 * 
 * @author liuyangming
 */
public enum DBContext {

	KeFu /* 连接Kefu从库，读权限 */
	, MasterKeFu /* 连接CatB主库，读权限 */
	, WritableKeFu/* 连接CatB主库，读/写权限 */
    ;
}
