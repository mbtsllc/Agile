/**
 * 
 */
package com.mars.web.gov.demo.drug;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mars.util.Logger;
import com.mars.util.LoggerFactory;
import com.mars.web.gov.action.HttpReader;
import com.mars.web.gov.action.MarsActionBase;
import com.mars.web.gov.properties.DrugSearchPropertiesBean;
import com.mars.web.gov.properties.SearchResultConfigurationInfo;
import com.mars.web.gov.search.GovSearchConstants;
import com.opensymphony.xwork2.ActionContext;

/**
 * Standard URL:
 * 	Type=Drug&currentLevel="+iCurrentLevel+"&field="+field +"&currentRow="+index
 * @author tiger
 * @version Demo
 * @date Jun 23, 2015
 */
public class SearchAction extends MarsActionBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3544086585701158614L;
	private static Logger logger = LoggerFactory.getLogger(SearchAction.class.getName()) ;
	
	private String currentLevel=null ;
	private String field =null;
	private int currentRow =-1;
	private String parentColumns="" ;
	
	public String execute() throws Exception {
		return "";
	}

	/**
	 * Get Drug Data from FDA Server.
	 * Steps:
	 * 	 1, get the full URL format from property file
	 *   2, call Http client get data from FDA server
	 *   3, Build objects
	 *   4, Save to Session
	 * @param strURL contains information from Search without key
	 * @return true or false If returned data form FDA contains Error then return false
	 */
	@Override
	public boolean GetDataFromFDAServer(String strURL){
		/** 1, get the full URL format from property file **/
		/** strURL is the full path which generated by parent method**/
		
		/** 2, call Http client get data from FDA server  **/
		HttpReader objReader = new HttpReader() ;
		String strDataFromFDA = null ;  
		try {
			strDataFromFDA = objReader.getURLInfoByHttp(strURL);
		} catch (Exception e) {
			logger.logError("GetDataFromFDAServer", -1, String.format("Exception get when Call getURLInfoByHttp. \r\n\tURL [%s], \r\n\tException:[%s]", strURL,e.getMessage()),e);
			e.printStackTrace();
			return false ;
		}
		
		/** 3, Build objects **/
		JSONObject objJson = JSONObject.fromObject(strDataFromFDA) ;
		Object objJsonTmpJava = JSONObject.toBean(objJson) ;
		
		System.out.println(objJson.keySet());
		Object objResult = objJson.get("results") ;
		
		Hashtable<Integer, Object> objResultHash = new Hashtable<Integer, Object>() ;
		HashMap<String, Object> objTopResult = null ;
		if (objResult instanceof JSONArray){
			JSONArray arrObj = (JSONArray)objResult;
			int [] ai = JSONArray.getDimensions(arrObj) ;
			/** 4, Save to Sessio **/
			GetCurrentSession().setAttribute(GovSearchConstants.CNST_SEARCHRESULT_SESSION_ID, arrObj);
		}else{
			/** perhaps ,there are error here **/
		}
		logger.logInfo("GetDataFromFDAServer",1,String.format("Get Json object:[%s]", objJsonTmpJava)) ;
		return true; 
	}
	
	/**
	 * Build client side acceptable Json information, based on different level. 
	 * Assume, that all rows are right
	 * Steps:
	 *   1, Get session stored data
	 *   2, Get row Ids,column ids stack (stored in request)
	 *   3, Get Current Column Index (stored in request)
	 *   4, Get Session Row Data
	 *   5, Get special 
	 */
	@Override
	protected void BuildClientTableInfo() {
		// TODO Auto-generated method stub
		/** 1, Get session stored data **/
		Object objResult = GetCurrentSession().getAttribute(GovSearchConstants.CNST_SEARCHRESULT_SESSION_ID ) ;
		if (!(objResult instanceof JSONArray)) {
			/** Build Error information **/
			return ;
		}
		
		/** 2, Get row Ids,column ids stack (stored in request) **/
		JSONArray arrObj = (JSONArray)objResult ;
		HashMap<String, SearchResultConfigurationInfo> objNodeInfo = DrugSearchPropertiesBean.getInstance().getConfigurationInfoByLevel(2) ;
		ArrayList<SearchResultConfigurationInfo> lstConfigObj = GetDataNodeConfigurationInfo(objNodeInfo) ;
		
		JSONArray objRowRowToClient = new JSONArray();
		for (int iRow =0;iRow<arrObj.size();iRow++){
			JSONObject objJsnRow = (JSONObject)arrObj.get(iRow) ;
			String strCurrentValue="" ;
			JSONObject oneJS = new JSONObject() ;
			for (int i=0;i<lstConfigObj.size();i++){
				
				String strKey = lstConfigObj.get(i).getLastPartOfKey();
				Object objOneValue = objJsnRow.get(strKey);
				boolean isSampleData = lstConfigObj.get(i).isSampleData() ;
				if (isSampleData){
					if (objOneValue instanceof Double){
						strCurrentValue = BigDecimal.valueOf((Double)objOneValue).toPlainString();
					}else  
						strCurrentValue = objOneValue==null?"":objOneValue.toString() ;
				}
				else{  
					isSampleData = lstConfigObj.get(i).isMarsObject() ;
					if (isSampleData){
						strCurrentValue = lstConfigObj.get(i).ConvertToCaptionDataByValue(objOneValue.toString()) ;
					}else
						strCurrentValue = "Click For More";
				}
				oneJS.put(strKey, strCurrentValue) ;
			}
			oneJS.put("currentLevel", 2) ;
			objRowRowToClient.add(oneJS) ;
		}
		
		//logger.logInfo("BuildClientTableInfo", 0, String.format("Get JSON DATA:%s", objRowRowToClient.toString())) ;
		JSONObject objGridResult = new JSONObject() ;
		objGridResult.put("total",arrObj.size() ) ;
		objGridResult.put("rows", objRowRowToClient) ;
		
		/**Write to response **/
		WriteJSONData2Reponse(objGridResult) ;		
	}
	
	private boolean WriteJSONData2Reponse(JSONObject objData){
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse objresp =(HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
		objresp.setContentType("application/json");
		try {
			objresp.getWriter().write(objData.toString());
			objresp.flushBuffer();
			return true ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false ;
		}	
	}
	
	private boolean WriteJSONData2Reponse(JSONArray objData){
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse objresp =(HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
		objresp.setContentType("application/json");
		try {
			objresp.getWriter().write(objData.toString());
			objresp.flushBuffer();
			return true ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false ;
		}	
	} 
	
	/**
	 * Typical URL:
	 * 	Type=Drug&currentLevel="+iCurrentLevel+"&field="+field +"&currentRow="+index
	 * Steps:
	 *   1, get parameters
	 *   2, get data from session
	 *   3, assembly json data to client
	 * @return
	 */
	public String DoSearchDetail(){
		/**1, get parameters **/
		/** installed by strtus 2 **/
		
		/**2, get data from session **/
		if ((this.currentLevel.compareToIgnoreCase("2")==0)||(this.currentLevel.compareToIgnoreCase("3")==0)){
			int iCurrentLevel=2 ;
			try{
				iCurrentLevel=Integer.parseInt(this.currentLevel) ;
			}catch(Exception e){
				logger.logError("DoSearchDetail", -1, String.format("Can't get right current level [%s] as a integer", this.currentLevel),e);
				return null ;
			}
			Object objResult = GetCurrentSession().getAttribute(GovSearchConstants.CNST_SEARCHRESULT_SESSION_ID ) ;
			if (!(objResult instanceof JSONArray)) {
				logger.logError("DoSearchDetail", -1, "object is not an array in Session");
				return null;
			}  
			String strNewParentColumns=parentColumns ;
			if (strNewParentColumns ==""){
				strNewParentColumns = this.field+":"+this.currentRow ;
			}else{
				strNewParentColumns += (";"+this.field+":"+this.currentRow );
			}
			String[] arrPrntInfo = strNewParentColumns.split(";") ;
			JSONArray arrCurrentData = (JSONArray)objResult ;
			JSONObject objCurrentRowData = null ;
			Object objLoop = objResult;
			//Object objResult = null ;
			if ((arrPrntInfo!=null)) {
				if (arrPrntInfo.length!=(iCurrentLevel-1)){
					logger.logError("DoSearchDetail", -1,String.format("ParentInfo from client side is wrong. [%s]", this.parentColumns));
					return null ;
				}
				/** get the parent object info **/
				for (int i=0 ;i<arrPrntInfo.length;i++){
					String[] arrPrntInfoItem = arrPrntInfo[i].split(":") ;
					if (arrPrntInfoItem.length!=2) {
						logger.logError("DoSearchDetail", -1, String.format("ParaInfo from client side is wrong, Format should be [Field]:[RowNumber], [%s]", arrPrntInfo[i]));
						return null ;
					}
					int iRowId = Integer.parseInt(arrPrntInfoItem[1]) ;
					
					if (objLoop instanceof JSONArray){
						try{
							arrCurrentData = (JSONArray)objLoop ;
							if (arrCurrentData.size()<=iRowId){
								logger.logError("DoSearchDetail", -1, String.format("Parent Infor from client side is with wrong data, Row:[%d], Data size:[%d], Data infor:[%s]", iRowId,arrCurrentData.size(), arrPrntInfo[i]));
								return null ;
							}	
							objCurrentRowData = (JSONObject)arrCurrentData.get(iRowId);
							if (!objCurrentRowData.containsKey(arrPrntInfoItem[0])){
								logger.logError("DoSearchDetail", -1, String.format("No such key [%s] found, Rownumber:[%d] Data info:[%s]", arrPrntInfoItem[0],iRowId,arrPrntInfo[i]));
								return null ;
							}
							objLoop = objCurrentRowData.get(arrPrntInfoItem[0]) ;
						}catch(Exception e){
							e.printStackTrace(); 
							return null ;
						}
					}else{
						objLoop = ((JSONObject)objLoop).get(arrPrntInfoItem[0]) ;
					}
					if (i==arrPrntInfo.length-1){
						objResult = objLoop ;
					}
				}
			}
			/** Write data to response **/
			if (objResult==null) return null ;
			
			//return null ;
//			JSONArray arrData = (JSONArray)objResult ;
//			if (this.currentRow>=arrData.size()) {
//				logger.logError("DoSearchDetail", -1, "No such row id in Session");
//				return null ;
//			}
//			JSONObject objJsnRow =(JSONObject)arrData.get(this.currentRow);
//			/** check wether the field exits **/
//			if (!objJsnRow.containsKey(this.field)){
//				logger.logError("DoSearchDetail", -1, String.format("No such field [%s] exists", this.field));
//				return null;
//			}
			  
			/** Claim the result array object **/
			JSONArray objArrResult = new JSONArray() ; 
			
			/** 3, assembly json data to client **/
			HashMap<String, SearchResultConfigurationInfo> objNodeInfo = DrugSearchPropertiesBean.getInstance().getConfigurationInfoByLevel(iCurrentLevel+1,this.field) ;
			ArrayList<SearchResultConfigurationInfo> lstConfigObj = GetDataNodeConfigurationInfo(objNodeInfo) ;
			/** get columns Info **/
			JSONArray objColumns = GenerateColumnInfoBasedLevel(lstConfigObj) ;
			/** create tmp parent column to columns **/
			JSONObject objParentColumns = CreateParentColumns() ;
			if (objParentColumns!=null) objColumns.add(objParentColumns) ;
			
//			Object tmpTargetField = objJsnRow.get(this.field) ;
			Object tmpTargetField =objResult ;
			if (!(tmpTargetField instanceof JSONArray)){
				JSONObject objCurrentJS = GenenerateClientJsonData((JSONObject)tmpTargetField, lstConfigObj) ;
				/** add level information **/
				objCurrentJS.accumulate("currentLevel", iCurrentLevel+1);
				/** add Parent columns info **/
				objCurrentJS.accumulate("_parentFields", String.format("%s%s", this.parentColumns==null?"":(this.parentColumns==""?"":(this.parentColumns+";")),this.field+":"+this.currentRow)) ;
				
				/** put it into array **/
				JSONArray objTmpColumnsArr = new JSONArray() ;
				objTmpColumnsArr.add(objColumns);
				objArrResult.add((new JSONObject()).accumulate("columns", objTmpColumnsArr)) ;
				JSONObject objTarget=new JSONObject() ;
				objTarget.accumulate("columns", objTmpColumnsArr) ;
				
				JSONArray objRow = new JSONArray() ;
				objRow.add(objCurrentJS) ;
				
				objTarget.accumulate("data", objRow) ;
				
				/** write data to Response **/
				//WriteJSONData2Reponse(objArrResult) ;
				WriteJSONData2Reponse(objTarget) ;
			}else{
				/** Generate row information **/
				JSONArray arrTarget = (JSONArray)tmpTargetField ;
				/** result object for response object writing **/
				JSONObject objTarget=new JSONObject() ;
				/** put it into array **/
				JSONArray objTmpColumnsArr = new JSONArray() ;
				objTmpColumnsArr.add(objColumns);
				objTarget.accumulate("columns", objTmpColumnsArr) ;
				
				/** put it into array **/
				JSONArray objTmpRowsArr = new JSONArray() ;
				for (int i=0 ;i<arrTarget.size();i++){
					JSONObject objCurrentJS = GenenerateClientJsonData((JSONObject)arrTarget.get(i), lstConfigObj) ;
					/** add level information **/
					objCurrentJS.accumulate("currentLevel", iCurrentLevel+1);
					/** add Parent columns info **/
					objCurrentJS.accumulate("_parentFields", String.format("%s%s", this.parentColumns==null?"":(this.parentColumns==""?"":(this.parentColumns+";")),this.field+":"+this.currentRow)) ;
					objTmpRowsArr.add(objCurrentJS) ;
				}
				objTarget.accumulate("data", objTmpRowsArr) ;
				logger.logInfo("DoSearchDetail", 0, objTarget.toString());
				WriteJSONData2Reponse(objTarget) ;
			}
			
		}
		return null ;
	}
	
	private JSONObject CreateParentColumns() {
		JSONObject objTmp = new JSONObject() ;
		objTmp.accumulate("field", "_parentFields");
		objTmp.accumulate("hidden", true) ;
		return objTmp ;
	}

	/**
	 * Generate JSON Row information for Column information
	 * @param lstConfigObj
	 * @return
	 */
	public JSONArray GenerateColumnInfoBasedLevel(
			ArrayList<SearchResultConfigurationInfo> lstConfigObj) {
		logger.logMethodBegin("GenerateColumnInfoBasedLevel");
		JSONArray objColumns = new JSONArray() ;		
		for (SearchResultConfigurationInfo objSearchInfo:lstConfigObj){
			JSONObject objOneColumn = new JSONObject() ;
			/** demo string field:'code',title:'Code',width:100 **/
			String strField=objSearchInfo.getFieldNameForColumn() ;
			String strTitle=objSearchInfo.getTitleForColumn() ; 
			String strWidth=objSearchInfo.getColumnWidth() ;
			/** strField and strTitle are required **/
			if (strField==null||strTitle==null) continue ;
			objOneColumn.accumulate("field", strField) ;
			objOneColumn.accumulate("title", strTitle) ;
			if (strWidth!=null)
				objOneColumn.accumulate("width", strWidth) ;
			objColumns.add(objOneColumn) ;
		}
		return objColumns;
	}

	private JSONObject GenenerateClientJsonData(JSONObject objJson,
			ArrayList<SearchResultConfigurationInfo> lstConfigObj) {
		JSONObject objResult = new JSONObject() ;
		String strCurrentValue ;
		for (SearchResultConfigurationInfo objConfig : lstConfigObj){
			String strKey = objConfig.getLastPartOfKey();
			Object objOneValue = objJson.get(strKey);
			if (objOneValue==null) continue ;
			boolean isSampleData = objConfig.isSampleData() ;
			if (isSampleData){ 
				if (objOneValue instanceof Double){
					strCurrentValue = BigDecimal.valueOf((Double)objOneValue).toPlainString();
				}else
					strCurrentValue = objOneValue==null?"":objOneValue.toString() ;
			}
			else{
				isSampleData = objConfig.isMarsObject() ;
				if (isSampleData){
					strCurrentValue = objConfig.ConvertToCaptionDataByValue(objOneValue.toString()) ;
				}else
					strCurrentValue = "Click For More";
			}
			objResult.put(strKey, strCurrentValue) ;
		}
		return objResult ;
	}

	private ArrayList<SearchResultConfigurationInfo> GetDataNodeConfigurationInfo(HashMap<String, SearchResultConfigurationInfo> objNodeInfo){
		ArrayList<SearchResultConfigurationInfo> lstConfigObj=new ArrayList<SearchResultConfigurationInfo>() ;
		for (String key:objNodeInfo.keySet()){
			SearchResultConfigurationInfo objSearchInfo = objNodeInfo.getOrDefault(key, null) ;
			if (objSearchInfo==null) continue ;
			if (!objSearchInfo.isDataNode()) continue ;
			
			lstConfigObj.add(objSearchInfo) ;
		}
		return lstConfigObj ;
	}
	

	
	
	public String getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(String currentLevel) {
		this.currentLevel = currentLevel;
	}

	public String getField() {
		return field;
	}

	public String getParentColumns() {
		return parentColumns;
	}

	public void setParentColumns(String parentColumns) {
		this.parentColumns = parentColumns;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	
}
