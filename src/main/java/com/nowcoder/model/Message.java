package com.nowcoder.model;

import java.util.Date;

public class Message {
		private int mId;
	    private int fromId;
	    private int toId;
	    private String mContent;
	    private Date mDate;
	    private int hasRead;
	    private String conversationId;//表示会话
		public int getmId() {
			return mId;
		}
		public void setmId(int mId) {
			this.mId = mId;
		}
		public int getFromId() {
			return fromId;
		}
		public void setFromId(int fromId) {
			this.fromId = fromId;
		}
		public int getToId() {
			return toId;
		}
		public void setToId(int toId) {
			this.toId = toId;
		}
		public String getmContent() {
			return mContent;
		}
		public void setmContent(String mContent) {
			this.mContent = mContent;
		}
		public Date getmDate() {
			return mDate;
		}
		public void setmDate(Date mDate) {
			this.mDate = mDate;
		}
		public int getHasRead() {
			return hasRead;
		}
		public void setHasRead(int hasRead) {
			this.hasRead = hasRead;
		}
		public String getConversationId() {
			return conversationId;
		}
		public void setConversationId(String conversationId) {
			this.conversationId = conversationId;
		}
}
