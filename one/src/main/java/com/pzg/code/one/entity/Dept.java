package com.pzg.code.one.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pzg.code.commons.excel.ExportConfig;
import com.pzg.code.commons.utils.CustomDateSerializer;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_dept")
public class Dept implements Serializable {

	private static final long serialVersionUID = -7790334862410409053L;

	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "dept_id")
	@ExportConfig(value = "编号")
	private Long deptId;

	@Column(name = "parent_id")
	private Long parentId;

	@Column(name = "dept_name")
	@ExportConfig(value = "部门名称")
	private String deptName;

	@Column(name = "dept_name_short")
	@ExportConfig(value = "部门名称简称")
	private String deptNameShort;

	@Column(name = "order_num")
	private Long orderNum;

	@Column(name = "create_time")
	@ExportConfig(value = "创建时间", convert = "c:com.pzg.code.commons.excel.TimeConvert")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date createTime;

	@Column(name = "leader")
	@ExportConfig(value = "部门负责人")
	private String leader;

	@Column(name = "property")
	@ExportConfig(value = "属性")
	private String property;

	@Column(name = "remarks")
	@ExportConfig(value = "备注")
	private String remarks;

	/**
	 * @return DEPT_ID
	 */
	public Long getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return PARENT_ID
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return DEPT_NAME
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName == null ? null : deptName.trim();
	}

	/**
	 * @return ORDER_NUM
	 */
	public Long getOrderNum() {
		return orderNum;
	}

	/**
	 * @param orderNum
	 */
	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * @return CREATE_TIME
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDeptNameShort() {
		return deptNameShort;
	}

	public void setDeptNameShort(String deptNameShort) {
		this.deptNameShort = deptNameShort;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}