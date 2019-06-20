package com.pzg.code.commons.utils;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @ClassName :  MyMapper
 * @Author : PZG
 * @Date : 2019-05-06   16:57
 * @Description :
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
