package com.pzg.code.one.mapper;

import com.pzg.code.commons.utils.MyMapper;
import com.pzg.code.one.entity.Dept;

import java.util.List;

public interface DeptMapper extends MyMapper<Dept> {

    void saveList(List<Dept> list);

}