package com.nowcoder.model;

import java.util.Date;

public class Comment {
	private int cId;
    private int uId;
    //将评论扩展为评论中心
    private int entityId;   //n_id
    private int entityType; //news
    private String cContent;
    private Date cDate;
    private int status;
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	public int getEntityType() {
		return entityType;
	}
	public void setEntityType(int entityType) {
		this.entityType = entityType;
	}
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public Date getcDate() {
		return cDate;
	}
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
