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
				        {'field':'name','title':'name','width':'39%'},
				        {'field':'value','title':'value','width':'50%'},
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
				        Data mining is used widely. The basic tool of it is "drill down/up". The page is a demo for drilling up based on the data from Open.FDA.gov. 
				        <br/>Click cells with words "click for more", and new sub grid will drill down with more details. For demo,<b> only <font color="red">three levels</font> is supported</b>. In theory, unlimited drilling down can support. 
				         
				        </span>		
						
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
				<div title="IBUPROFEN Report Alyst">
					<div class="demo-info" style="margin-bottom:10px; ">
				        <div class="demo-tip icon-tip">&nbsp;</div>
				        <span><b>Usage:</b><br/>
				        This is a demo showing <b>"IBUPROFEN"</b> Report detail information from "serious" dimension. "Serious" and "Reaction" are reported from FDA APIs. Our demo collected 27 kinds of reaction. And a tree graph using <b>SVG</b> is applied.
				        <br/> The column "score" is not from APIs <font color="red">JUST FOR TEST</font>, which shows a "serious" number from 0~1.0. The higher number means more serious.
				        <br/> <b><font color="blue">Click below to GET FULL VISION </font></b>
				        </span>		
						<br/><a href="DrugTreeGrph.html" target="_blank"> show in separate Window </a>
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
			<div title="IBUPROFEN HeatMap Alyst">
					<div class="demo-info" style="margin-bottom:10px; ">
				        <div class="demo-tip icon-tip">&nbsp;</div>
				        <span><b>Usage:</b><br/>
				        <br/> With widely using of Heatmap in researching field, Heatmap can help users get useful information from BIG Data. The demo contains data from 20130601 to 20140630. 
				        No data after 20140630 about IBUPROFEN report. Report number for "serious='1'" and total report number are getten from FDA APIs for each month.
				        <br/> To Create the heatmap, total report count of each month and reports with "Serious=1", means very serious based on FDA web site, in the same month are applied. 
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