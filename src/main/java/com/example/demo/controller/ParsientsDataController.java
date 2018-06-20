package com.example.demo.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.LogdataMapper;
import com.example.demo.model.AcceptData;
import com.example.demo.model.LogBean;
import com.example.demo.pojo.Logdata;
import com.example.demo.utils.FwCodeUtils;

@Controller
public class ParsientsDataController {
	
	@Autowired
	private LogdataMapper mapper;
	
	@ResponseBody
	@RequestMapping(value = "/executeT", method = RequestMethod.POST)
	public String execute(HttpServletRequest request,HttpServletResponse response){		
		String datakey = "!&-join+";
		String jsonData = "";
		byte[] data;
		try {
			data = getData(request.getInputStream());
			
			jsonData = new String(FwCodeUtils.decrypt(datakey.getBytes("UTF-8"),FwCodeUtils.base64Decode(data)), "UTF-8");
			AcceptData dataOne = JSONObject.parseObject(jsonData, AcceptData.class);
			
			Map<String, Object> custIdContextMap = dataOne.getCustIdContextMap();
			LogBean logBean = dataOne.getLogBean();
			
			List<Logdata> dataList = new ArrayList<Logdata>();
			
			Logdata logData = null;
			
			if(custIdContextMap.size() > 0) {
				for (Map.Entry<String, Object> entry : custIdContextMap.entrySet()) {
				      logData = new Logdata();
				      
				      logData.setResouceId(entry.getKey());
				      if(StringUtils.isEmpty(entry.getValue().toString())) {
				    	  logData.setOperateDes(logBean.getOperateName() + ":" + logBean.getResOperateName());
				      }else {
				    	  logData.setOperateDes(entry.getValue().toString());
				      }
				      
				      logData = converLogBeanToLogData(logData, logBean);
				      
				      dataList.add(logData);
				    }
			}else{
				logData = new Logdata();
				
				logData = converLogBeanToLogData(logData, logBean);
				
				dataList.add(logData);
			}
			mapper.insertList(dataList);
			
			System.out.println(dataOne.toString());
			System.out.println(dataOne.getLogBean().toString());
			return jsonData;
		} catch (Exception e) {
			return null;
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
}
