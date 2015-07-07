package com.mars.web.gov.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.mars.util.CommonConstants;
import com.mars.util.Logger;
import com.mars.util.LoggerFactory;
import com.mars.web.RequestStatus;
import com.mars.web.gov.properties.DrugSearchPropertiesBean;
import com.mars.web.gov.search.inter.ParametersInterface;
import com.mars.web.gov.search.paramters.ParamtersFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Web action base class providing parameters setting methods from JSP
 * 
 * @author tiger
 * @version Demo
 * @date Jun 22, 2015
 */
public abstract class MarsActionBase extends ActionSupport implements
		RequestAware, SessionAware, ApplicationAware {
	private static Logger logger = LoggerFactory.getLogger(MarsActionBase.class
			.getName());
	private static final long serialVersionUID = -887511002992731611L;

	private Map request;
	private Map session;
	private Map application;

	protected String Type = "";

	protected ParametersInterface ParametersDeal = null;
	protected RequestStatus CurrentRequestStatus = new RequestStatus() ;

	public String getType() { 
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	/**
	 * 
	 */
	protected void LoadParameters() {

	}
	

	/**
	 * Same as Excute method. 
	 * Steps:
	 *   1, Load parameters information
	 *   2, check parameters 
	 *   3, return Error Json to front when error
	 *   4, do restful from inner service
	 *   5, return Json result (error or normal)
	 * @return
	 */
	public String DoBusiness() {
		/** 1, Load parameters information **/
		LoadParameterInformation();

		if (ParametersDeal==null)
		{
			GenerateErrorJson(CommonConstants.ERROR_TYPE_PARAOBJECT_ISNULL) ;
			return "" ;
		}
		/** 2, check parameters             **/
		/** let it be 					    **/
		
		/** 4, do restful from inner service**/
		String strURLSearch = ParametersDeal.BuildSearchURL() ;
		 
		/** 5, get data from FDA Sever      **/
		System.out.println(strURLSearch);
		String strURLFull = GetFDAFullDrugURL(strURLSearch) ;
		boolean isDataReady=GetDataFromFDAServer(strURLFull) ;
		if (!isDataReady) {
			/** build Error data **/
		}else{
			/** build **/
			BuildClientTableInfo() ;
		}
		return null;
	}
	
	/**
	 * Build JSON String and write back to client side
	 * should be override by its children
	 */
	protected void BuildClientTableInfo() {
		// TODO Auto-generated method stub
		
	}

	protected String GetFDAFullDrugURL(String strURL) {
		/**
		 * get data from restful server
		 */
		String strURLPrefix = getURLPrefixFromRESTfulServer() ;
		//String strURLPrefix = DrugSearchPropertiesBean.getInstance().GetFDASearchPattern().trim() ;
		if (strURLPrefix==null){
			logger.logError("GetDataFromFDAServer", -1, "GetFDASearchPattern return null,i.e. No FDA Search information is configured or no Properties file.");
			return null ;
		}		 
		String strFullURL = String.format("%s&%s", strURLPrefix, strURL) ;
		
		logger.logInfo("GetFDAFullDrugURL", 1, String.format("Get Full URL :[%s]", strFullURL)) ;
		return strFullURL;
	}
	
	
	
	private String getURLPrefixFromRESTfulServer() {
		HttpReader objReader = new HttpReader() ;
		try {
			logger.logInfo("getURLPrefixFromRESTfulServer", 0, "Begin to use restful get Data");
			return objReader.getURLInfoByHttp("http://localhost:8080/FDARESTful/rest/urlsvc").trim() ;
		} catch (Exception e) {
			logger.logInfo("getURLPrefixFromRESTfulServer", -1, String.format("Can't get Data From Server side, with exception:[%s]", e.getMessage()),e);
			return DrugSearchPropertiesBean.getInstance().GetFDASearchPattern().trim() ;
		}
	}

	protected HttpSession GetCurrentSession(){
		return ((HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST)).getSession(true) ;
	}

	/** 
	 * Get data from Special Server. 
	 * This method should be override by its children 
	 * @param strURLSearch
	 * @return
	 */
	protected boolean GetDataFromFDAServer(String strURLSearch) {
		// TODO Auto-generated method stub
		return true;
	}
	


	protected void LoadParameterInformation() {
		ParametersDeal = ParamtersFactory.GetParametersInstanceByType(Type);
		
		if (ParametersDeal==null) return ;
		ParametersDeal.InitParameterInfo();
		
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);		
		ParametersDeal.ParaseRequest(request) ;		
	}

	protected void GenerateErrorJson(CommonConstants errType) {
		// TODO Auto-generated method stub
		CurrentRequestStatus.setCurrentStatus(CommonConstants.WEB_ERROR.getValue());
		CurrentRequestStatus.setInformationToDisplay(errType.getValue());		
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;	
		request.get("struts.actionMapping") ;
		System.out.println( request.keySet()) ;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	@Override
	public void setApplication(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.application = arg0;
	}
}
