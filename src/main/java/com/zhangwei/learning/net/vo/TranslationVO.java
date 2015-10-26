package com.zhangwei.learning.net.vo;

import java.io.Serializable;

import com.zhangwei.learning.enums.ActionEnum;

/**
 * 
 * @author Administrator
 * 
 */
public class TranslationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2097716960982167392L;

	public static final String SPLITFIX = "EOP";

	private ActionEnum actionEnum;
	private Object data;

	public ActionEnum getActionEnum() {
		return actionEnum;
	}

	public void setActionEnum(ActionEnum actionEnum) {
		this.actionEnum = actionEnum;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "TranslationVO [actionEnum=" + actionEnum + ", data=" + data
				+ "]";
	}

}
