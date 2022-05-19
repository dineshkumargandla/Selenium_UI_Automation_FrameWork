package com.automation.interview.ReadXML;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.automation.interview.ReadXML.CaseObject;
import com.automation.interview.ReadXML.DataBean;
import com.automation.interview.utill.Configaration;

public class ReadXmlData {
	HashMap<String, CaseObject> casesInput = new HashMap<String, CaseObject>();
	private String filename;

	public ReadXmlData() {
	}

	public ReadXmlData(String filename) {
		this.filename = filename;
	}

	public DataBean getDataBean(String testcase, String name) {
		getData();

		CaseObject caseObj = casesInput.get(testcase);
		return caseObj.getDataBeanByName(name);
	}

	public CaseObject getCaseData(String testcase) {
		return getData().get(testcase);
	}

	public HashMap<String, CaseObject> getAllTestData() {
		return getData();
	}

	private HashMap<String, CaseObject> getData() {
		try {
			StringBuilder sb = new StringBuilder(Configaration.getDataDir()).append(File.separatorChar).append(this.filename);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sb.toString()), "UTF-8"));
			InputSource is = new InputSource(br);
			is.setEncoding("UTF-8");

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(is);
			document.getDocumentElement().normalize();
			NodeList caselist = document.getElementsByTagName("testcase");
			CaseObject testcase = null;

			String casename = null;
			for (int i = 0; i < caselist.getLength(); i++) {
				Node casenode = caselist.item(i);
				if (casenode.getNodeType() == Node.ELEMENT_NODE) {
					testcase = new CaseObject();
					casename = ((Element) casenode).getAttribute("casename");
					testcase.setCaseName(casename);
					Node casedata = casenode.getFirstChild();
					DataBean dataBean;
					while (casedata.getNextSibling() != null) {
						casedata = casedata.getNextSibling();
						dataBean = new DataBean();
						if (casedata instanceof Element) {
							StringBuilder value = new StringBuilder(casedata.getTextContent());
							StringBuilder expected = new StringBuilder(value.toString());
							if (casedata.hasAttributes()) {
								if (null != ((Element) casedata).getAttribute("expected"))
									expected = new StringBuilder(((Element) casedata).getAttribute("expected"));
								if (null != ((Element) casedata).getAttribute("isUnique")
										|| ((Element) casedata).getAttribute("isUnique").equalsIgnoreCase("true")) {
									String stamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
									value.append(stamp);
									expected.append(stamp);
								}
							}
							dataBean.setName(casedata.getNodeName());
							dataBean.setValue(value.toString());
							dataBean.setAssertValue(expected.toString());
						}
						if (null != dataBean.getName()) {
							testcase.getBeanMap().put(casedata.getNodeName(), dataBean);
						}
					}

				}
				casesInput.put(casename, testcase);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return casesInput;
	}

	public static void main(String[] args) {
		ReadXmlData rxd = new ReadXmlData("ProcessViews.xml");
		Map<String, CaseObject> testcases = (HashMap<String, CaseObject>) rxd.getAllTestData();
		System.out.println("**** Get suite data ****");
		testcases.entrySet().forEach(tc -> {
			tc.getValue().getBeanMap().entrySet().forEach(entry -> {
				System.out.printf("Key=%s, Name=%s, Value=%s, assertValue=%s \n", entry.getKey(),
						entry.getValue().getName(), entry.getValue().getValue(), entry.getValue().getAssertValue());
			});
		});
		DataBean bean = rxd.getDataBean("NewViewTest", "processViewsName");
		System.out.println("\n*** Get DataBean by case name and element name ***");
		System.out.printf("Key=%s, Value=%s, assertValue=%s \n", bean.getName(), bean.getValue(),
				bean.getAssertValue());

		CaseObject co = rxd.getCaseData("NewViewTest");
		HashMap<String, DataBean> beanMap = co.getBeanMap();
		System.out.println("\n****Get case data by case name ****");
		beanMap.entrySet().forEach(entry -> {
			System.out.printf("Key=%s, Name=%s, Value=%s, assertValue=%s \n", entry.getKey(),
					entry.getValue().getName(), entry.getValue().getValue(), entry.getValue().getAssertValue());
		});
	}

}
