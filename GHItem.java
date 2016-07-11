package com.tr.edu.trspace.common;

import java.util.Hashtable;

public class GHItem {

	private Hashtable<String, Object> fields = null;

	public Hashtable<String, Object> getFields() {
		return fields;
	}

	public void setFields(Hashtable<String, Object> fields) {
		this.fields = fields;
	}

	public Object getProperty(String propertyName) {
		try {
			return this.fields.get(propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
