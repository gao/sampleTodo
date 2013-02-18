package com.example.sampletodo.util;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.britesnow.snow.util.JsonUtil;
import com.example.sampletodo.dao.IDao.SortOrder;

/**
 * Very simple wrapper around JSON Sort / Page / Search options 
 * to be reused across a couple of WebHandlers
 * @author rbanikaz
 *
 */

public class JSONOptions {
	
	// default values
	private int pageIndex = 0;
	private int pageSize = 50;
	private Map match = null;
	private String keyword = null;
	private String orderBy = null;
	private SortOrder orderType = SortOrder.ASC;
	
	public JSONOptions(String jsonOpts) {
        if(StringUtils.isNotEmpty(jsonOpts)) {
	        JSONObject opts = (JSONObject) JsonUtil.toMapAndList(jsonOpts);
	        
	        Object matchMap = opts.opt("match");
	        
	        pageIndex  = getInt(opts.opt("pageIndex"), 0);
	        pageSize   = getInt(opts.opt("pageSize"), 50);
	        
	        match      = matchMap != null && matchMap instanceof Map ? (Map)matchMap : null;
	        keyword    = opts.optString("keyword");
	        
	        orderBy    = opts.optString("orderBy");
	        try { 
	        	orderType  = SortOrder.valueOf(opts.optString("orderType").toUpperCase()); }
	     	catch(Exception e) {
	     		/* do nothing, orderType is wrong, take default val */
	     	}
        }
	}
	

	/*  Page Options */
	public int getPageIndex() {
		return this.pageIndex;
	}
	public int getPageSize() {
		return this.pageSize;
	}
	
	
	/* Search Options */
	public Map getMatchMap() {
		return this.match;
	}
	public String getKeyword() {
		return this.keyword;
	}

	
	/* Sort Options */
	public String getOrderBy() {
		return this.orderBy;
	}
	public SortOrder getOrderType() {
		return this.orderType;
	}
	
	
	/*helper functions*/
	private int getInt(Object obj, int defaultVal) {
        if(obj == null) {
            return defaultVal;
        }
        
        if(obj instanceof String) {
            return getInt(obj.toString(), defaultVal);
        } else if(obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        
        return defaultVal;
    } 
	
	private  int getInt(String str, int defaultVal) {
        try {
            return Integer.parseInt(str);
        } catch(Exception e) {
            return defaultVal;
        }
    }

}
