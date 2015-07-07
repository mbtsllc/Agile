/**
 * javascript utils functions
 * 
 * @author Marquis
 * @Date   6/20/2015
 */

/** Hint Information **/
var g_ConditionHint = new Array() ;
g_ConditionHint["Receive Date From"]="Set the first day about reports \"Receieved \" " ;
g_ConditionHint["Receive Date To"]="Set the last day about reports \"Receieved \" " ;
g_ConditionHint["Start Date"]="Set the search field Patient.drug.drugstartdate value" ;
g_ConditionHint["Medicinal Product"]="Set field for patient.drug.medicinalproduct" ;
g_ConditionHint["Product Type"]="Set field for patient.drug.product_type" ;
g_ConditionHint["Indication"]="Set Field for patient.drug.drugindication";
g_ConditionHint["Dosage Text"] = "Set Field for patient.reactionmeddrapt";
g_ConditionHint["Brand Name"] = "Set Field for  patient.openfda.brand_name" ;
g_ConditionHint["Skip Record(s)"]="Set how many records would ignore from FDA APIs" ;
g_ConditionHint["Limit Record(s) "]="Set how many records would return from FDA APIs" ;
g_ConditionHint["Index Name"]="Returned data are Counted by Field Name which is listed from the DropList" ;
g_ConditionHint["Set age"]=	"Set Field for  patient.patientonsetage";
g_ConditionHint["Set age unit"] = "Set Field for  patient.patientonsetageunit";
g_ConditionHint["Reaction"]= "Set Field for  patient.reactionmeddrapt";
g_ConditionHint["End Date"]= "Set Field for patient.drug.drugenddate";

function StringRight(str, n){
	return str.substring(str.length-n);
}

function CreateToolTip(object, indexOfTool){
	object.css("border", "3px solid red") ;
}