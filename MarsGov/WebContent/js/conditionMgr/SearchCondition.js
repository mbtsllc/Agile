/**
 * A javascript object for Condition Grid management.
 * As too much items of the FDA searching paramters which present as
 * a property Grid, the object is response for getting setting data
 * from the target property grid.
 * 
 * JQuery methods are used.
 * 
 * @author Marquis
 * @Date   06-22-2015
 */

function SearchConditionManager(){
	this.TargetObjectId = "" ;
	this.DataSourceFormat = "MM/dd/YYYY" ;
	this.DataTargetFormat = "YYYYMMdd" ;
	this.HashParameters = new Array() ;
	this.SetTargetObjectId=function(objId)
	{
		this.TargetObjectId = objId ;
	}
	/**
	 * Get all items from property grid
	 * Notice:
	 *   JQuery EasyUI should be applied
	 *   
	 * @author Marquis
	 * @Date   06-22-2015
	 */
	this.GetParameters=function()
	{
		var arrRows = $("#"+this.TargetObjectId).datagrid("getRows");
		/** rebuild a new Hash table contains parameters and values **/
		this.HashParameters = new Array() ;
		
		/** navigate all rows and push those in to hash table      **/
		var strData = "" ;
		var iCnt = 0 ;
		for (var i = 0 ;i<arrRows.length ;i++)
		{
			if (arrRows[i].value=="") continue ;
			strData = arrRows[i].value;
			if (arrRows[i].editor == "datebox")
			{
				/** convert date format from SourceFormat to TargetFormat **/
				try{
					var currentDate = new Date(arrRows[i].value) ;
					if (this.DataTargetFormat.toLowerCase()=="YYYYMMdd".toLowerCase())
					{
						strData = currentDate.getFullYear()+StringRight("0"+(currentDate.getMonth()+1),2)+StringRight(("0"+currentDate.getDate()),2) ;
					}
				}catch(e)
				{
					continue ;
				}
			}
			iCnt++ ;
			this.HashParameters[arrRows[i].parameters]=strData ;
		}
		
		return iCnt ;
	}
	
	/** 
	 * Build a URL for Data query
	 * As Data request will be get by http from other grid
	 */
	this.BuildParametersForURL=function()
	{
		var strResult = "" ;
		for(var key in this.HashParameters){
			strResult+=("&" + key+"="+ this.HashParameters[key]) ;
		}
		return strResult ;
	}	
}

var gSearchConditionManager = new SearchConditionManager() ;