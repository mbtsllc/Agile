<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="keywords" content="Marquis Business Tech Solution, jquery, easyUI,FDA">
    <meta name="description" content="easyui help you build your web page easily!">
    <title>Marquis FDA Demo</title>
    <link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="js/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="css/demo.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>    
    <script type="text/javascript" src="js/datagrid-detailview.js"></script>  
    <script type="text/javascript" src="js/common.js"></script>   
    <script type="text/javascript" src="js/conditionMgr/SearchCondition.js"></script>
    <script type="text/javascript" src="js/d3.min.js"></script>
    <script type="text/javascript" src="js/marsD3/treeGrph.js"></script>
</head>
	
<body>
	<div style="margin:0 auto;width:1080px;background:no-repeat;height:64px;vertical-align:text-bottom;font-family:Arial Sans-serif;">
		<span style="text-decoration: underline;color:lightblue"><a href="http://www.mbtsllc.com" style="font-size:24px;" target="_blank"><font style="font-size:36px">M</font>arquis Business and Technical Solutions, LLC</a></span>
		<br/>
		<span style="vertical-align:text-bottom;font-size:16px;margin:3,10,0,0;align:right">Pool Two: Development Prototype</span>
	</div>
	<div style="border-top:2px solid #ddd; width:1180px;margin:0 auto" align="center">		
		<div style="float:left;width:300px;margin:5 5 5 5">
			<div class="demo-info">
	    	<B>Search Condition:</B>
	    	</div>
	    	<div style="margin:0 auto;text-align:top;" >
		    	<table id="pg" class="easyui-propertygrid" style="width:300px;height:485px" data-options="
					url:'js/data/SearchCondition.json',
					method:'get',
					showGroup:true,
					scrollbarSize:0,
					columns:[[
				        {'field':'name','title':'Name','width':'39%'},
				        {'field':'value','title':'Value','width':'50%'},
				        {'field':'parameters','hidden':'true'}]],
				    
				    	onLoadSuccess:function(data){ ConditionLoadOk(data) ;}
				    
					">
				</table>
	    	</div>
	    	<div style="margin:0 0 0 0;text-align:right;border:1px solid #95B8E7;padding:5 5 5 0;">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="DoSearch() ;">Search</a>	
				
			</div>
			    	
	    	
	    </div>
		<div style="width:792px;float:left;background:#fff;margin:5 5 5 5">
			<div class="easyui-tabs" style="width:780px;height:550px">
				<div title="Normal Result" style="padding:10px">
					<div class="demo-info" style="margin-bottom:10px; ">
				        <div class="demo-tip icon-tip">&nbsp;</div>
				        <span><b>Usage:</b><br/>
				        Data mining enables discovering interesting and useful patterns from large volumes of data. Below is an implementation of the process. The concept of "drill down/up" is used and this page is a demo for drilling up based on the data from 
				        <a href="Open.FDA.gov" target="_blank">Open.FDA.gov</a>
<br/>
Click cells with words "click for more", 
and new sub grid will drill down with more details. 
Three levels of drill down information are supported for demo purposes and in theory, unlimited levels of drilling down can be supported. 
				        				    </div>
				    <div style="margin:0 auto">
					    <table id="dg" style="width:950px;height:420px;overflow:auto"				            
					            pagination="true" sortName="itemid" sortOrder="desc"
					            title="FDA Drug Details"
					           
					            singleSelect="true" fitColumns="true"
					            onBeforeLoad="OnBeforeLoadData"
					            onLoadError="onLoadErrorOfResult"
					            data-options="columns:[[
							        {'field':'safetyreportid','title':'safety report id','width':'120px'},
							        {'field':'patient','title':'Patient Info','width':'120px',
							        	styler: function(value,row,index){
											return ChangeTostyleWithExpand(value,row,index);
										}},
							        {'field':'@epoch','title':'@EPOCH','width':'133px'},
							        {'field':'occurcountry','title':'Occur Country','width':'80px'},
							        {'field':'serious','title':'Serious','width':'160px'},
							        {'field':'receivedate','title':'Receive Date','width':'80px'},
							        {'field':'receiver','title':'Receiver','width':'120px',
							        	styler: function(value,row,index){
											return ChangeTostyleWithExpand(value,row,index);
										}},
							        {'field':'companynumb','title':'Company Number','width':'130px'},							        
							        {'field':'currentLevel','hidden':true}]]"
					            >
					           
					    </table>
				    </div>
				    
				</div>
				<div title="IBUPROFEN Report">
					<div class="demo-info" style="margin-bottom:10px; ">
				        <div class="demo-tip icon-tip">&nbsp;</div>
				        <span><b>Usage:</b><br/>
				        This is a demo showing "IBUPROFEN" Report that contains detailed information from "serious" dimension. "Serious" and "Reaction" are reported from FDA APIs. Our demo collected 27 kinds of reaction after which a tree graph using SVG is applied.
<br/>
The column "score" which show numbers from 0 to 1.0 are not from the APIs and are for testing purposes. The higher the number the more serious the reaction.
				        </span>		
						<br/><a href="DrugTreeGrph.html" target="_blank"> Show Report in separate Window </a>
				    </div>
				    <div style="margin:0 auto;float:left">
					    <table id="IBUPROFENREPORT" style="width:400px;height:420px;overflow:auto"				            
					            pagination="false" sortName="itemid" sortOrder="desc"
					            title="FDA Drug Details"
					            singleSelect="true" fitColumns="true"
					            onBeforeLoad="OnBeforeLoadData"
					            onLoadError="onLoadErrorOfResult"
					            data-options="columns:[[
							        {'field':'reaction','title':'Reaction','width':'120px'},
							        {'field':'totalCount','title':'Total','width':'60px'},
							        {'field':'serious1','title':'Serious','width':'80px'},
							        {'field':'serious2','title':'Not serious','width':'80px'},
							        {'field':'score','title':'Score','width':'60px'}]],
							        url:'js/data/IBUPROFEN_ReactionData.json'"
					            >
					           
					    </table>
					    
					</div>
					
				<div id="treeGraph" style="float:left;width:300px;height:410px;overflow:auto;border:1px lightgray solid">
					<iframe src="DrugTreeGrph.html" marginwidth="0" marginheight="0" scrolling="no" style="height: 410px;width:1024px"></iframe>
				</div>
			</div>
			<div title="IBUPROFEN HeatMap">
					<div class="demo-info" style="margin-bottom:10px; ">
				        <div class="demo-tip icon-tip">&nbsp;</div>
				        <span><b>Usage:</b><br/>
				        <br/>Heat maps can help user get useful information from BIG Data. 
				        The demo contains data from 2013/06/01 to 2014/06/30. 
				        Report number for "Serious" = '1' and total 
				        report numbers for each month are obtained from FDA APIs. 
				        The total report count of each month and reports with "Serious=1" are used to create a heat map. 
 
				        </span>		
				    </div>
				    
				<div id="treeGraph" style="float:left;width:790px;height:410px;border:1px lightgray solid">
					<iframe src="heatmapDemo.html" marginwidth="0" marginheight="0" scrolling="no" style="height: 410px;width:790px"></iframe>
				</div>
			</div>
		    
	    </div>
	    
    </div>
    <script>
    	function onLoadErrorOfResult(){
    	}
        /** 
         * Fired by "search" button. 
         * Steps:
         * 	 1, check condition parameters setting
         *   2, create URL
         *   3, call ReloadData and display DataGrid
         */
    	function DoSearch()	{
    		/** 1, check condition parameters  **/
        	gSearchConditionManager.SetTargetObjectId("pg") ;
    		if (gSearchConditionManager.GetParameters()==0){
    			/** 
    			  NOTICE:
    			    properties file should apply here, so that it fits for different language.
    			  But for demo, Just use string here for saving time.
    			**/
    			alert("At least one parameter should set.") ;
    			return ;
    		}
    		/** 2, Create URL **/
    		var strURL = gSearchConditionManager.BuildParametersForURL() ; 
    		/** 3, call ReloadData and display DataGrid  **/
    		ReloadData(strURL) ;
    	}
    	
    	function ChangeTostyleWithExpand(value,row,index){
    		if (value=="Click For More"){
    			value = "More"
    			return "background: url('js/themes/default/images/datagrid_icons2.png') no-repeat -32px center;text-align:right;cursor:pointer" ;
    			//return {class:"datagrid-row-expand",style:"text-align:right"}
    		}
    		//dreturn "" ;
    	}
    
    	/**
    	 * Reload Search result Data, getting from FDA directly
    	 */
    	function ReloadData(strURL){
    		$('#dg').datagrid('loadData', {"total":0,"rows":[]});
    		$("#dg").datagrid({"url":"/drugDemo/search.action?Type=Drug"+strURL}) ;    		
    	}
    	
    	function OnBeforeLoadData()
    	{
    		//alert("ddd") ;
    		return true;
    	}
    	function OnClickMasterCell(index,field,value){
    		
    	}
    	
    	function ConditionLoadOk(data){
    		/** add tool tip **/
    		$("tr[id^='datagrid-row-r1-2']").each(function(){
    			$(this).find("td:eq(0)").each(function(){	
	           		var strHintIndex = $(this)[0].innerText.replace("\n","").replace("\r","") ;
	           		try{
	           			var strHint = g_ConditionHint[strHintIndex] ;
	           			$(this).tooltip({
	           			    position: 'bottom',
	           			    content: '<span style="color:black">'+strHint+'</span>',
	           			    onShow: function(){
	           			        $(this).tooltip('tip').css({
	           			            backgroundColor: 'lightgray',
	           			            borderColor: '#CCCCCC'
	           			           
	           			        });
	           			    }
	           			});
	           		}catch(e){
	           			//alert(e) ;
	           		}
    			})
           	}) ;
    	}
    	
    </script>
    
    <script type="text/javascript">
    	var strCurrentExpandURL="";
    	var strCurrentColumns="" ;
    	var strCurrentGridData ="" ;
    	var currentSubGrid = null ;
        $(function(){
        	$('#IBUPROFENREPORT').datagrid({
        		fitColumns:true,
        		url:'js/data/IBUPROFEN_ReactionData.json'
        	}) ;
            $('#dg').datagrid({
                view: detailview,	
                fitColumns:true,
                onBeforeLoad:function(){
                	return OnBeforeLoadData() ;
                },
                onLoadSuccess:function(data){
                	$("div.tabs-panels div.datagrid-view2 table.datagrid-htable tr.datagrid-header-row td:eq(2) div").css("width","133px") ;
                },
                detailFormatter:function(index,row){
                    return '<div style="padding:2px"><table class="ddv"></table></div>';
                },
                
                onExpandRow: function(index,row){
                    var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
                    var strLevel3Columns, strLevel3Data ;
                    var subGridInfo={
                    		options:{
                                fitColumns:true,
                                columns:strCurrentColumns,
                                data:strCurrentGridData,
                                loadMsg:"Trying to get Data from Server....",
                                onExpandRow: function(xindex,xrow){
                                	//alert(xindex,xrow) ;
                                },
                                onClickCell:function(index,field,value){
                                	/** get data from server side **/
                                	var strCurrentURL =GetDetailDataByCellInfo(index,field, value,$(this)) ;
                                	if (strCurrentURL==null) return ;
                                	$(this).datagrid('collapseRow', index);
                                	$(this).datagrid('loading') ;
                                	$.getJSON(strCurrentURL,function(json){
                            			strCurrentColumns=json.columns;
                            			strCurrentGridData =json.data ;   
                            			$(this).datagrid("subgrid", $(this).subgrid);
                            		  }) ;
                                	$(this).datagrid("expandRow",index) ; 
                                	$(this).datagrid('loaded') ;
                                	
                                }
                            },
                            subgrid:{
                                options:{
                                    fitColumns:true,
                                    foreignField:'companyid',
                                    columns:strCurrentColumns,
                                   	data:strCurrentGridData
                                }
                            }
                    } ;
                    ddv.datagrid("subgrid",subGridInfo) ;
                    
                    currentSubGrid = ddv;
                    $('#dg').datagrid('fixDetailRowHeight',index);
                },
                onClickCell:function(index,field,value){                	
                	/** build URL to get details and expand the sub-table **/
                	var strURL = GetDetailDataByCellInfo(index,field,value,$(this)) ;
                	if (strURL==null) return ;
                	//var strURL = "/drugDemo/searchDetail.action?Type=Drug&currentLevel="+iCurrentLevel+"&field="+field +"&currentRow="+index;
                	$(this).datagrid('collapseRow', index);
                	$(this).datagrid("expandRow",index) ;  
                	$.getJSON(strURL,function(json){
                			strCurrentColumns=json.columns;
                			strCurrentGridData =json.data ;
                			 var strSubGri={options:{ 
                        		fitColumns:true,
                        		foreignField:'testid',
                        		columns:strCurrentColumns,
                        		data:strCurrentGridData
                        		}
                        	};
                			//$(this).datagrid("subgrid",strSubGri) ;
                			currentSubGrid.datagrid({
                		  		columns:strCurrentColumns,
                		  		data:strCurrentGridData
                		  	}) ;                		  	
                		  }) ;
                }            
            });
            
            $("div.tabs-panels div.datagrid-view2 table.datagrid-htable tr.datagrid-header-row td:eq(2) div").css("width","130px") ;
           	
        });
        function GetDetailDataByCellInfo(index,field, value,objGrid){
        	if (value!="Click For More") return ;
        	/** get CurrentLevel **/
        	var iCurrentLevel = -1 ;                	
        	try{
        		iCurrentLevel=objGrid.datagrid("getRows")[index].currentLevel ;                		
        	}catch(e)
        	{
        		alert("Can't get the current Level from Grid.") ;
        		return null;
        	}
        	var strParent=objGrid.datagrid("getRows")[index]._parentFields ;
        	/** build URL to get details and expand the sub-table **/
        	if (typeof(strParent)== 'undefined'){        		
            	return "/drugDemo/searchDetail.action?Type=Drug&currentLevel="+iCurrentLevel+"&field="+field +"&currentRow="+index;
        	}else{
        		if (strParent!=""){
        			return "/drugDemo/searchDetail.action?Type=Drug&currentLevel="+iCurrentLevel+"&field="+field +"&currentRow="+index+"&parentColumns="+strParent;
        		}else{
        			return "/drugDemo/searchDetail.action?Type=Drug&currentLevel="+iCurrentLevel+"&field="+field +"&currentRow="+index;	
        		}
        	}
        	
        	
        }
    </script>
    
</body>
</html>