package com.example.demo.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.CallInformationMapper;
import com.example.demo.mapper.LogdataMapper;
import com.example.demo.model.AcceptData;
import com.example.demo.model.LogBean;
import com.example.demo.pojo.CallInformation;
import com.example.demo.pojo.Logdata;
import com.example.demo.utils.FwCodeUtils;

@Controller
public class ParsientsDataController {

	@Autowired
	private LogdataMapper mapper;

	@Autowired
	private CallInformationMapper cMapper;
	
	@ResponseBody
	@RequestMapping(value = "/executeT", method = RequestMethod.POST)
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String datakey = "!&-join+";
		String jsonData = "";
		byte[] data;
		try {
			data = getData(request.getInputStream());

			jsonData = new String(FwCodeUtils.decrypt(datakey.getBytes("UTF-8"), FwCodeUtils.base64Decode(data)),
					"UTF-8");
			AcceptData dataOne = JSONObject.parseObject(jsonData, AcceptData.class);

			Map<String, Object> custIdContextMap = dataOne.getCustIdContextMap();
			LogBean logBean = dataOne.getLogBean();

			List<Logdata> dataList = new ArrayList<Logdata>();

			Logdata logData = null;

			if (custIdContextMap.size() > 0) {
				for (Map.Entry<String, Object> entry : custIdContextMap.entrySet()) {
					logData = new Logdata();

					logData.setResouceId(entry.getKey());
					if (StringUtils.isEmpty(entry.getValue().toString())) {
						logData.setOperateDes(logBean.getOperateName() + ":" + logBean.getResOperateName());
					} else {
						logData.setOperateDes(entry.getValue().toString());
					}

					logData = converLogBeanToLogData(logData, logBean);

					dataList.add(logData);
				}
			} else {
				logData = new Logdata();

				logData = converLogBeanToLogData(logData, logBean);

				dataList.add(logData);
			}
			mapper.insertList(dataList);

			System.out.println(dataOne.toString());
			System.out.println(dataOne.getLogBean().toString());
			return FwCodeUtils.base64Encode(FwCodeUtils.encrypt("!&-join+","1"));
		} catch (Exception e) {
			System.out.println(e);
			return FwCodeUtils.base64Encode(FwCodeUtils.encrypt("!&-join+","0"));
		}

	}

	public static byte[] getData(InputStream is) throws IOException {
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = null;

		try {
			bis = new BufferedInputStream(is, 1024);
			baos = new ByteArrayOutputStream(1024);

			byte[] b = new byte[1024];
			int len;
			while ((len = bis.read(b)) != -1) {
				baos.write(b, 0, len);
			}

			return baos.toByteArray();
		} finally {
			bis.close();
			baos.close();
		}
	}

	private static Logdata converLogBeanToLogData(Logdata logData, LogBean logBean) {

		logData.setBatchId(logBean.getBatchId());
		logData.setContent(logBean.getContent());
		logData.setData(logBean.getData());
		logData.setModuleId(logBean.getModuleId());
		logData.setModuleName(logBean.getModuleName());
		logData.setOperateId(logBean.getOperateId());
		logData.setOperateName(logBean.getOperateName());
		logData.setOperateNum(logBean.getOperateNum());
		logData.setOrgId(logBean.getOrgId());
		logData.setOwnerAcc(logBean.getOwnerAcc());
		logData.setResOperateId(logBean.getResOperateId());
		logData.setResOperateName(logBean.getResOperateName());
		logData.setUserAcc(logBean.getUserAcc());
		logData.setUserName(logBean.getUserName());

		return logData;
	}

	
	@ResponseBody
	@RequestMapping(value = "/executeX", method = RequestMethod.POST)
	public String executeXML(HttpServletRequest request,HttpServletResponse response) throws Exception{		
		String datakey = "!&-join+";
		String jsonData = "";
		byte[] data;
		try {
			data = getData(request.getInputStream());
			
			jsonData = new String(FwCodeUtils.decrypt(datakey.getBytes("UTF-8"),FwCodeUtils.base64Decode(data)), "UTF-8");
			getObject(jsonData,CallInformation.class).toString();
			
			return FwCodeUtils.base64Encode(FwCodeUtils.encrypt("!&-join+","<xml><result>1</result><body></body></xml>"));
		} catch (Exception e) {
			return FwCodeUtils.base64Encode(FwCodeUtils.encrypt("!&-join+","<xml><result>0</result><body></body></xml>"));
		}
	}
	
	
	/**
	 * xml文档Document转对象
	 * 
	 * @param document
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object getObject(Document document, Class<?> clazz) {
		Object obj = null;
		// 获取根节点
		Element root = document.getRootElement();
		try {
			Element body = root.element("body");
			List<CallInformation> callInforList = new ArrayList<CallInformation>();
			List<Element> properties = body.elements();

			for (Element pro : properties) {
				obj = clazz.newInstance();// 创建对象
				// 获取属性名(首字母大写)
				List<Attribute> list = pro.attributes();
				for (Attribute arr : list) {
					String propertyname = arr.getName();
					String propertyvalue = arr.getValue();
					Method m = obj.getClass().getMethod("set" + upperCaseFirstLetter(propertyname), String.class);
					m.invoke(obj, propertyvalue);
				}

				CallInformation callInfor = (CallInformation) obj;
				callInfor.setCreateTime(new Date());
				callInforList.add(callInfor);
			}

			cMapper.insertList(callInforList);

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
	public Object getObject(String xmlString, Class<?> clazz) throws Exception {
		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlString);
		} catch (DocumentException e) {
			throw new Exception("获取Document异常" + xmlString);
		} // 获取根节点
		return getObject(document, clazz);
	}

	public static String upperCaseFirstLetter(String str) {

		if (str != null && str != "") {
			str = str.substring(0, 1).toUpperCase() + str.substring(1);
		}
		return str;
	}
}
