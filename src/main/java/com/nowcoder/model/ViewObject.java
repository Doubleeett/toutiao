package com.nowcoder.model;

import java.util.HashMap;
import java.util.Map;

//方便在velocity展示数据
//是一个专门给视图展示的对象
public class ViewObject {

	private Map<String, Object> objs=new HashMap<String,Object>();
	public void set(String key,Object value) {

		objs.put(key, value);
	}
	
	public Object get(String key) {
		return objs.get(key);
	}

}
