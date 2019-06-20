package com.pzg.code.one.service.impl;

import com.pzg.code.one.entity.Dept;
import com.pzg.code.one.mapper.DeptMapper;
import com.pzg.code.one.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName :  DeptServiceImpl
 * @Author : PZG
 * @Date : 2019-06-20   16:15
 * @Description :
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> getAll() {
        return deptMapper.selectAll();
    }
}
