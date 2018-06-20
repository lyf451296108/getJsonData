package com.example.demo.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.example.demo.pojo.CallInformation;

public class XmlUtil {

	/**
	 * xml文档Document转对象
	 * 
	 * @param document
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object getObject(Document document, Class<?> clazz) {
		Object obj = null;
		// 获取根节点
		Element root = document.getRootElement();
		try {
			
			List<CallInformation> callInforList = new ArrayList<CallInformation>();
			List<Element> properties = root.elements();
			for (Element pro : properties) {
				// 获取属性名(首字母大写)
				List<Attribute> list = pro.attributes();
				for (Attribute arr : list) {
					obj = clazz.newInstance();// 创建对象
					String propertyname = arr.getName();
					String propertyvalue = arr.getValue();
					Method m = obj.getClass().getMethod("set" + upperCaseFirstLetter(propertyname), String.class);
					m.invoke(obj, propertyvalue);
				}
				
				CallInformation callInfor = (CallInformation) obj;
				callInfor.setCreateTime(new Date());
				callInforList.add(callInfor);
			}

			System.out.println((CallInformation) obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * xml字符串转对象
	 * 
	 * @param xmlString
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static Object getObject(String xmlString, Class<?> clazz) throws Exception {
		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlString);
		} catch (DocumentException e) {
			throw new Exception("获取Document异常" + xmlString);
		} // 获取根节点
		return getObject(document, clazz);
	}

	/**
	 * 对象转xml文件
	 * 
	 * @param b
	 * @return
	 */
	public static Document getDocument(Object b) {
		Document document = DocumentHelper.createDocument();
		try {
			// 创建根节点元素
			Element root = document.addElement(b.getClass().getSimpleName());
			Field[] field = b.getClass().getDeclaredFields(); // 获取实体类b的所有属性，返回Field数组
			for (int j = 0; j < field.length; j++) { // 遍历所有有属性
				String name = field[j].getName(); // 获取属属性的名字
				if (!name.equals("serialVersionUID")) {// 去除串行化序列属性
					name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
					Method m = b.getClass().getMethod("get" + name);
					// System.out.println("属性get方法返回值类型:" + m.getReturnType());
					String propertievalue = (String) m.invoke(b);// 获取属性值
					Element propertie = root.addElement(name);
					propertie.setText(propertievalue);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return document;
	}

	/**
	 * 对象转xml格式的字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String getXmlString(Object b) {
		return getDocument(b).asXML();
	}

	public static String upperCaseFirstLetter(String str) {

		if (str != null && str != "") {
			str = str.substring(0, 1).toUpperCase() + str.substring(1);
		}
		return str;
	}

	public static void main(String[] args) throws Exception {
		// BankData bank=new BankData("1233","2017-06-13
		// 10:24:31","EEEEEE=","000091","0021");//创建一个任意实体对象
		// System.out.println(XmlUtil.getXmlString(bank));//调用方法把对象转xml格式
		// System.out.println(XmlUtil.getObject(XmlUtil.getXmlString(bank),
		// BankData.class));//调用getXmlString（）方法把xml格式重新转换为对象

		String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
				"\r\n" + 
				"<body> \r\n" + 
				"  <record code=\"32edfswfgver\" callType=\"1\" callerNum=\"057110000\" calledNum=\"057110001\" startTime=\"2013-5-16 15:30:20\" timeLength=\"60\" recordURL=\"http://183.129.206.158:8090/service/tempFileDownLoadServlet?str=ToYGjbZTLOys4x0jh0xnvy2vgH4cl9gCTY5n5JFk5/oDkaPwx0dh0x&amp;fileServer=183.129.206.158:8098\" ownerAcc=\"990027\" communicationNO=\"057112536985\" followId=\" bd3d2fd2cdaa43389ade1ece4fd248e6\"/> \r\n" + 
				"</body>\r\n" + 
				"";

		getObject(xmlStr, CallInformation.class);
	}

}
