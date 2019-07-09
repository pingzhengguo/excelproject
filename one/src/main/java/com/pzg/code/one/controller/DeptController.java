package com.pzg.code.one.controller;

import com.pzg.code.commons.excel.ExcelToData;
import com.pzg.code.commons.excel.FileUtil;
import com.pzg.code.commons.utils.ResultInfo;
import com.pzg.code.one.entity.Dept;
import com.pzg.code.one.mapper.DeptMapper;
import com.pzg.code.one.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName :  DeptController
 * @Author : PZG
 * @Date : 2019-06-20   16:17
 * @Description :
 */
@Api(tags = {"DeptController"})
@RestController
@RequestMapping("/deptController")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Autowired
    private DeptMapper deptMapper;

    @ApiOperation(value = "excel2Data", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/excel2Data", method = RequestMethod.GET)
    @ResponseBody
    public Object excel2Data() {
        try {
            Dept dept = new Dept();
            List<Dept> objects = ExcelToData.excel2Data(dept);
            deptMapper.saveList(objects);
            return null;
        } catch (Exception e) {
            return ResultInfo.failure("导入Excel失败，请联系网站管理员！");
        }
    }


    @RequestMapping(value = "dept/excel", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo deptExcel() {
        try {
            List<Dept> list = deptService.getAll();
            return FileUtil.createExcelByPOIKit("部门表", list, Dept.class);
        } catch (Exception e) {
            return ResultInfo.failure("导出Excel失败，请联系网站管理员！");
        }
    }


    @RequestMapping(value = "dept/csv", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo deptCsv() {
        try {
            List<Dept> list = deptService.getAll();
            return FileUtil.createCsv("部门表", list, Dept.class);
        } catch (Exception e) {
            return ResultInfo.failure("导出Csv失败，请联系网站管理员！");
        }
    }
}
