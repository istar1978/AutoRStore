function showheader()
{    
document.write("<html:hidden property='rinm1' />");
document.write("<html:hidden property='ricd1' />");
//document.write("dsadadads");
}
function showFooter()
{
    document.write("<br><br><hr width=100% size=1>")
    document.write("<div align=center>Copyright (C)2007 All Rights Reserved　<a href='mailto:beaconpeigen@126.com'>beaconpeigen@126.com</a></div>")    
}
function makePrintForm(a)
{
	//alert("请启用ActiveX控件设置！");
	var aw = window.open('')
	
	aw.document.writeln('<center>')
	aw.document.writeln('<font size=3><b>'+"XXXX交易报告表"+a+'</b></font>')
	aw.document.writeln('</center>')
	todayDate = new Date();
	date  = todayDate.getDate()
	month = todayDate.getMonth()+1
	year  = todayDate.getYear()
	mTime  = todayDate.getTime()
	hour  = todayDate.getHours()
	minute = todayDate.getMinutes()
	second = todayDate.getSeconds()
	
	dateTime = "打印日期：" + year + "年" + month + "月" + date + "日" + hour + "时" + minute + "分" + second + "秒" 
	aw.document.writeln(dateTime)

	var count=0
	var nCol = document.all.RESTBL.rows(0).cells.length
	var nRow = document.all.RESTBL.rows.length
	var Cells = new Array()
    for (i=0; i < nRow; i++) {
        for (j=0; j < nCol; j++) {
			Cells[count] = document.all.RESTBL.rows(i).cells(j).innerText
            count++
        }
    }
   
    aw.document.writeln('<html>')
    aw.document.writeln('<body>')
    aw.document.writeln('<table>') 
    count=1
    j = 1
    for (x in Cells){
    	if (count%nCol==1){
    		j = 1
    		aw.document.writeln('<tr>')
    	}
   		aw.document.writeln("<td>" +  Cells[x] +"</td>")
			j = j+1
    	if (count%nCol==0){
    		aw.document.writeln('</tr>')
    	}
    	count++
    }
    aw.document.writeln('</table>')   
    aw.document.writeln('</body>')   
    aw.document.writeln('</html>')
}

function makePrintFormId(a)
{
	//alert("请启用ActiveX控件设置！");
	var aw = window.open('')
	aw.document.writeln('<center>')
	aw.document.writeln('<font size=3><b>'+"XXXXXXX"+a+'</b></font>')
	aw.document.writeln('</center>')
//	todayDate = new Date();
//	date  = todayDate.getDate()
//	month = todayDate.getMonth()+1
//	year  = todayDate.getYear()
//	mTime  = todayDate.getTime()
//	hour  = todayDate.getHours()
//	minute = todayDate.getMinutes()
//	second = todayDate.getSeconds()
//	dateTime = "打印日期：" + year + "年" + month + "月" + date + "日" + hour + "时" + minute + "分" + second + "秒" 
//	aw.document.writeln(dateTime)

	var count=0
	var nCol = document.all.RESTBL.rows(0).cells.length
	var nRow = document.all.RESTBL.rows.length
	var Cells = new Array()
    for (i=0; i < nRow; i++) {
        for (j=0; j < nCol; j++) {
			Cells[count] = document.all.RESTBL.rows(i).cells(j).innerText
            count++
        }
    }
   
    aw.document.writeln('<html>')
    aw.document.writeln('<body>')
    aw.document.writeln('<form>')
    aw.document.writeln('<table width=600 align=center>')     
    count=1
    j = 1
    var ss="----------------------------------";
    for(;j<3;j++){
    	ss+=ss;
    }
    var dd="|";
    j=1;
    for (x in Cells){
    	if (count%nCol==1){
    		j = 1
    		aw.document.writeln('<tr>')
    	}
    	//document.all.english.value=Cells[1];
    	//alert(document.all.english.value);    	
		aw.document.writeln("<td> <font size='2'>" +  Cells[x] +" </font></td>")
		j = j+1
    	if (count%nCol==0){
    		aw.document.writeln('</tr>') 
    		aw.document.writeln('<tr>') 
    		aw.document.writeln("<td colspan='3'> <font size='1'>"+ss+" </font></td>") 
    		aw.document.writeln('</tr>')
    	}
    	count++
    }
    aw.document.writeln('</table>')
    aw.document.writeln('</form>')
    aw.document.writeln('</body>')   
    aw.document.writeln('</html>')
}


function makeExcel(){
	var count=0
	var nCol = document.all.RESTBL.rows(0).cells.length
	var nRow = document.all.RESTBL.rows.length
	var Cells = new Array()
    for (i=0; i < nRow; i++) {
        for (j=0; j < nCol; j++) {
			Cells[count] = document.all.RESTBL.rows(i).cells(j).innerText
            count++
        }
    }
	// Start Excel and get Application object.

	var oXL;
	try{ 
		oXL = new ActiveXObject("Excel.Application");
	}
	catch(e){
		alert(e);
		return;
	}

	oXL.Visible = true;
	
	// Get a new workbook.
	var oWB = oXL.Workbooks.Add();
	var oSheet = oWB.ActiveSheet;
    for (i=0;i<nRow; ++i){
    	for (j=0; j<nCol; ++j){
			oSheet.Cells(1+i,1+j).Value = Cells[i*nCol+j]
    	}
    } 
}

/*

add by tian 20070820
*/
function setBranchAction(a)
{	with(document.forms(0))
	{
		action.value=a;
		submit();
	}
	return;
}
function insert(){	
	with(document.forms(0)){
		var string='$'+exam.value+':'+term.value+':'+kswz.value+':'+jswz.value+':'+course.value;
		//alert(course.value+"dddddd"+string);
		if(exam.value=="crat")
		{
			string=string+':'+zdz.value;
		}
		else{
			string=string+':!'+zdz.value;
		}
		if(strclose.value=="force")
		{
			strclose.value=string;
		}
		else{
			strclose.value=strclose.value+':'+string;
		}
	}
	return;
}

function coninsert(){	
	with(document.forms(0)){
		var string=exam.value+':'+term.value+':'+course.value+':'+zdz.value;
		if(strcontent.value=="0:1:0:1")
		{
			strcontent.value=string;
		}
		else{
			strcontent.value=strcontent.value+':'+string;
		}
	}
	return;
}