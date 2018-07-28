package com.chb.title.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JSONResult implements Serializable {
    private String msg = "success";
    private String flag = "1";
    private Object result;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }



    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }


    public String toJSON() {
        return JSON.toJSONString(this, SerializerFeature.DisableCheckSpecialChar);
    }

    public String toJSON(PropertyFilter pf) {
        return JSON.toJSONString(this,pf, SerializerFeature.DisableCheckSpecialChar);
    }

    private static Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public String toJSONNull() {
        return gson.toJson(this);
    }

    public String toJSONMap() {
        Map<String, Object> result = new HashMap();
        result.put("flag", getFlag());
        result.put("msg", getMsg());
        if (null != this.result) {
            result.put("result", this.result);
        }
        return result.toString();
    }
}
