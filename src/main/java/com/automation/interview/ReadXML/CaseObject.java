
package com.automation.interview.ReadXML;

import java.util.HashMap;

import com.automation.interview.utill.Log;

public class CaseObject {

	private String caseName = "";
	private HashMap<String, DataBean> beanMap = new HashMap<String, DataBean>();

	/*
	 * Get the name of a test case
	 */
	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public void addBeanToMap(DataBean dataBean) {
		beanMap.put(dataBean.getName(), dataBean);
	}

	/*
	 * Get data of an element in the format of DataBean for a given element name
	 */
	public DataBean getDataBeanByName(String name) {
		if (null != beanMap.get(name)) {
			return beanMap.get(name);

		} else {
			Log.error("CaseObject::" + name + " is not existed");
			return null;
		}
	}

	/*
	 * Retrieve all elements DataBean of a test case
	 * @return a HashMap of DataBean. The key is the name of element and value
	 * is the matching DataBean.
	 */
	public HashMap<String, DataBean> getBeanMap() {
		return beanMap;
	}

	public void setBeanMap(HashMap<String, DataBean> beanMap) {
		this.beanMap = beanMap;
	}
}
