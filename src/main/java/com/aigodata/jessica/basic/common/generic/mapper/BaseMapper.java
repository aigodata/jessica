package com.aigodata.jessica.basic.common.generic.mapper;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertUseGeneratedKeysMapper;

public interface BaseMapper<T> extends Mapper<T>, InsertUseGeneratedKeysMapper<T>, InsertListMapper<T> {

}
