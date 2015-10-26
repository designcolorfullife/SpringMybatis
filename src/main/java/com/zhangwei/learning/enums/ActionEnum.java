package com.zhangwei.learning.enums;

import org.springframework.util.StringUtils;

public enum ActionEnum {
	DOWNLOAD("DOWNLOAD"),;
    private String code;

    private  ActionEnum(String download) {
        this.code = download;
    }

    public ActionEnum getActionEnumByCode(String code){
        if(code==null){
            return null;
        }
        for(ActionEnum a : ActionEnum.values()){
            if(a.getCode().equals(code))
                return a;
        }
        return null;
    }

    public String getCode() {
        return code;
    }
}
