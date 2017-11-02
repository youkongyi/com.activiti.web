package com.activiti.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 请假单
 */
@Table(name="A_LEAVEBILL")
public class LeaveBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select act_evt_log_seq.nextval from dual")
	private String id;//主键ID
	private String days;// 请假天数
	private String content;// 请假内容
	@Column(name="LEAVEDATE")
	@Temporal (TemporalType.TIMESTAMP)
	private Date leaveDate = new Date();// 请假时间
    private String remark;// 备注
	private String state="0";// 请假单状态 0初始录入,1.开始审批,2为审批完成
	private String userId;// 请假人
	
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDays() {
        return days;
    }
    public void setDays(String days) {
        this.days = days;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Date getLeaveDate() {
        return leaveDate;
    }
    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }
}
