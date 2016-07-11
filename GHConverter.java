package com.tr.edu.trspace.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import sun.util.logging.resources.logging;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class GHConverter<T> {

	public static List<GHColumn> GHColumnsByOneBean(
			Class<?> classType) {
		List<GHColumn> columns = new ArrayList<GHColumn>();
		Field[] fields = classType.getDeclaredFields();

		for (Field field : fields) {
			Class<?> type = field.getType();
			String str = field.getName();
			GHColumn column = new GHColumn();
			column.setColumnType(type.getSimpleName());
			column.setCoumnName(str);
			columns.add(column);
		}
		return columns;
	}

	public T ConvertGHItem2Bean(GHItem item,
			Class<?> beanType) throws Exception, SecurityException {
		Hashtable<String, Object> fields = item.getFields();
		Object result = beanType.newInstance();
		for (Iterator<String> it = fields.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			Object value = fields.get(key);
			Field field = beanType.getDeclaredField(key);
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			field.set(result, value);
		}
		return (T)result;
	}
	
	public GHItem ConvertBean2GHItem(T bean) throws Exception, SecurityException {
		GHItem result = new GHItem();
		
		Hashtable<String, Object> fieldVals = new Hashtable<String, Object>();
		Class<?> classType = bean.getClass();
		Field[] fields = classType.getDeclaredFields();
		
	    for(Field field : fields){  
	    	if (!field.isAccessible()) {
				field.setAccessible(true);
			}
	        Class<?> type = field.getType();
	        String str = field.getName();
	        Object obj = field.get(bean);
	        fieldVals.put(str, obj);
	    }
	    
	    result.setFields(fieldVals);
		
		return result;
	}
	
	public GHTable ConvertList2GHTable(List<T> list) throws SecurityException, Exception{
		GHTable result = new GHTable();
		Class<?> classType = null;
		if(list != null && list.size() > 0){
			classType = list.get(0).getClass();
		}
		List<GHColumn> columns = GHColumnsByOneBean(classType);
		result.setColumns(columns);
		
		Hashtable<String, GHItem> items = new Hashtable<String, GHItem>();
		int index = 0;
		for(Object obj:list){
			GHItem item = ConvertBean2GHItem((T)obj);
			items.put(String.valueOf(index++) ,item);
		}
		result.setItems(items);
		return result;
	}
	
	public static Object ConvertJsonArray2Bean(String json, Class<?> beanType) throws Exception{
		//
		Object result = beanType.newInstance();
		JSONArray array = JSONArray.fromObject(json);
		for(int i = 0; i<array.size(); ++ i){
			JSONObject obj = array.getJSONObject(i);
			String name = obj.getString("name");
			String value = obj.getString("value");
			Field field = null;
			try{
				field = beanType.getDeclaredField(name);
			}catch(Exception e){
				System.out.print(e.getMessage());
			}
			if(field == null){
				continue;
			}
            if(!field.isAccessible()){
               	field.setAccessible(true);
            }
            Object rValue = null;
            if(field.getType().getSimpleName().toLowerCase().equals("string")){
            	rValue = value;
            }else if(field.getType().getSimpleName().toLowerCase().equals("int") || field.getType().getSimpleName().toLowerCase().equals("integer")){
            	rValue = Integer.parseInt(value);
            }
            field.set(result, rValue);
		}
		
		return result;
	}
}
