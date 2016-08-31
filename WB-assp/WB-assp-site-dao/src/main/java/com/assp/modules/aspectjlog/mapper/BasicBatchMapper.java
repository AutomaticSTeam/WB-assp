package com.assp.modules.aspectjlog.mapper;

/**
 * 扩展批量插入接口
 * 
 * 使用方法:
 * 1、在自己的Mapper中继承该接口
 * 2、实体类中,目前主键必须唯一并且自增长,而且主键对应的实体类成员变量名称必须为id
 *   如数据库表中的主键名不为id,那么则需要使用@Column(name="xxx")进行转换
 */

import java.util.List;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

import com.github.abel533.provider.SpecialProvider;

public interface BasicBatchMapper<T> {
	/**
	 * 批量插入
	 * @param recordList
	 * @return
	 */
	@Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = SpecialProvider.class, method = "dynamicSQL")
	int insertList(List<T> recordList);
	
}
