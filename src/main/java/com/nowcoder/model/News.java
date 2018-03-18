package com.nowcoder.model;

import java.util.Date;

public class News {

	private int nId;
	private String title;
	private String link;
	private String image;
	private int commentCount;
	private int uId;
	private Date nDate;
	private int likeCount;
	
	
	
	
	
	public int getnId() {
		return nId;
	}
	public void setnId(int nId) {
		this.nId = nId;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getnDate() {
		return nDate;
	}
	public void setnDate(Date date) {
		this.nDate = date;
	}
	
	
}
