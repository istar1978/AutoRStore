//下面是检查需要的JavaScript函数
var ten=false;//是否采用10位日期检测
function checkdec(fieldName, dec,positive) {
//参数dec为0，代表整数，其他数字代表允许的小数个数。
//参数positive XXX以次表示负数，0，正数，1表示允许，0表示不允许
  decallowed = dec; 
  pp=positive;
//清除空格
  fieldValue = fieldName.value; 
  declength = 0;
  if (fieldValue == "" ){
    alert("输入数字不能为空");
    fieldName.select();
    fieldName.focus(); 
    fieldName.value = "0";
    return false;
  } else if (isNaN(fieldValue)){
    alert("输入字符中含有非法数字键.");
    fieldName.select();
    fieldName.focus();
    return false;
  } else if((pp=="011" || pp=="001" || pp=="010") && parseFloat(fieldValue)<0){
    alert("输入项不能为负值");
    fieldName.select();
    fieldName.focus();
    return false;
 }  else if( decallowed == 0 ) {
    if (fieldValue.indexOf('.') != -1)   {
      alert ("不允许有小数,请重新输入数字.");
      fieldName.select();
      fieldName.focus();
      return false;
    }
  } else {    
    if (fieldValue.indexOf('.') == -1)  {
      declength = 0;
    }  else {
      dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);
      declength = dectext.length;
    }
    if (declength > decallowed) {
      alert ("小数位数最多为"+decallowed+"位,请重新输入数字.");
      fieldName.select();
      fieldName.focus();
      return false;
    }
  }
  fieldValue = fieldValue*1;
  fieldName.value = fieldValue;
  return fieldValue;
}


function checkdec2(varthis) {
  
  if(event.keyCode==46 && varthis.value.indexOf('.')==-1)
      event.returnValue = true;
  else  {
    if (event.keyCode >= 48 && event.keyCode <= 57 )   {
          event.returnValue = true;
       }  else
          event.returnValue = false;
  }
}
/**
*控制只能输入整形数据.
*/
function onlyint() {
//不是数字而且不等于'='
  if ((event.keyCode < 48 || event.keyCode > 57)&&event.keyCode!=45) 
  event.returnValue = false;
}

function onlydec() {
  kc = event.keyCode;
  if (kc < 46 || kc > 57 || kc == 47) 
  event.returnValue = false;
}

function trim( a) {
while(a.indexOf(" ")>-1)
 a=a.replace(" ", "");
 return a;
}
function checkdtinput() {
  if ((event.keyCode >= 48 && event.keyCode <= 57 ) || event.keyCode==45) 
      event.returnValue = true;
  else
      event.returnValue = false;
}


function checknum() {
	if (event.keyCode < 48 || event.keyCode > 57) {
		//window.status = '请在此处输入数字！';
		event.returnValue = false;
	}
}

/****************************************
	以千为单位分割数字串
	
	chnumber0 用于输入时实时分割 Example: onkeyup="chnumber0(this);" onblur="this.value=chnumber(this.value)"
	chnumber1 用于分割数字后赋于用于显示的变量 Example: var1=chnumber1(var2);
	unchnumber 用于去掉数字中的分割字符 Example: var1=unchnumber(var2);

*****************************************/
function chnumber0(varthis){
	var a,renum='';
	var j=0;
	var a1='',a2='',a3='';
	if(event.keyCode>=37&&event.keyCode<=40) return;

	a=unchnumber(varthis.value);
	j=a.indexOf('-');
	a1=a.substr(0,++j);
	a=a.substr(j);
	j=a.indexOf('.');
	if(j<0) j=a.length;
	a2=a.substr(0,j);
	a3=a.substr(j);
	j=0;
	for( i=a2.length;i>3;i=i-3){       
		renum=","+a2.substr(i-3,3)+renum;
		j++;
	}
	renum=a1+a2.substr(0,a2.length-j*3)+renum+a3;
	varthis.value=renum;
}

function chnumber1(input_je){
	var a,renum='';
	var j=0;
	var a1='',a2='',a3='';
	a= unchnumber(input_je);
	j=a.indexOf('-');
	a1=a.substr(0,++j);
	a=a.substr(j);
	j=a.indexOf('.');
	if(j<0) j=a.length;
	a2=a.substr(0,j);
	a3=a.substr(j);
	j=0;
	for( i=a2.length;i>3;i=i-3){       
		renum=","+a2.substr(i-3,3)+renum;
		j++;
	}
	renum=a1+a2.substr(0,a2.length-j*3)+renum+a3;
	return renum;
}

function unchnumber(input_je){
	var	a='',a1='';
	var	j=0;
	a=input_je;
	while((j=a.indexOf(','))>=0){
		a1 += a.substr(0,j);
		a=a.substr(++j);
	}
	a =a1+a;
	return a;
}

function chnumber(input_je){
	var a,renum='';
	var j=0;
	var a1='',a2='',a3='';
	a=input_je;
	j=a.indexOf('-');
	a1=a.substr(0,++j);
	a=a.substr(j);
	j=a.indexOf('.');
	if(j<0) j=a.length;
	a2=a.substr(0,j);
	a3=a.substr(j);
	j=0;
	for( i=a2.length;i>3;i=i-3){       
		renum=","+a2.substr(i-3,3)+renum;
		j++;
	}
	renum=a1+a2.substr(0,a2.length-j*3)+renum+a3;
	return renum;
}

function chnumber2(inputfield,outfield){
	var a,renum='';
	var j=0;
	var a1='',a2='',a3='';
	a=inputfield.value;
	j=a.indexOf('-');
	a1=a.substr(0,++j);
	a=a.substr(j);
	j=a.indexOf('.');
	if(j<0) j=a.length;
	a2=a.substr(0,j);
	a3=a.substr(j);
	j=0;
	for( i=a2.length;i>3;i=i-3){       
		renum=","+a2.substr(i-3,3)+renum;
		j++;
	}
	renum=a1+a2.substr(0,a2.length-j*3)+renum+a3;
	outfield.value=renum;
}

/*********************************************************
	检查手机号码输入是否是数字,是否是11位的数字,不
	可以输入字母或其他字符.function chmobile()
***********************************************************/
function chmobile(fieldName, dec)  {
//参数dec为0，代表整数，其他数字代表允许的数字的个数。
  decallowed = dec; 
//清除空格
  fieldValue = fieldName.value; 
  declength = 0;
  if (isNaN(fieldValue))  {
    alert("输入字符中含有非法数字键.");
    fieldName.select();
    fieldName.focus();
    return false;
  }  else if( decallowed != 0 ) {
    if (fieldValue.length != 11)   {
      alert ("手机号码应该为11位数字.");
      fieldName.select();
      fieldName.focus();
      return false;
    }
  }
   fieldValue = fieldValue*1;
  fieldName.value = fieldValue;
  return fieldValue;
}
/**********************************************
	检查邮政编码是否是6位数字
**********************************************/
function chpostal(fieldName, dec)  {
//参数dec为0，代表整数，其他数字代表允许的数字的个数。
  decallowed = dec; 
//清除空格
  fieldValue = fieldName.value; 
  declength = 0;
  if (isNaN(fieldValue))  {
    alert("输入字符中含有非法数字键.");
    fieldName.select();
    fieldName.focus();
    return false;
  } else if( decallowed != 0 )  {
    if (fieldValue.length != 6)   {
      alert ("邮政编码应该为6位数字.");
      fieldName.select();
      fieldName.focus();
      return false;
    }
  }
   fieldValue = fieldValue*1;
  fieldName.value = fieldValue;
  return fieldValue;
}
/************************************
	判断E_mail地址中是否存在@
**************************************/

function tmail(fieldName){
   fieldValue = fieldName.value; 	     
   if(fieldValue.indexOf('@') == -1)     {
     	 alert("输入的E_mail地址不正确!");
    	 fieldName.select();
    	 fieldName.focus();
   	 	 return false;      
      }
  fieldName.value = fieldValue;
  return fieldValue;
}

function checkAmt(varthis,len){
	var i,j;
	var val=varthis.value; 
	j=val.indexOf('.');
	if(j>0) {
		var len1=val.substring(j+1,val.length).length;
		if(len1>2) {
			alert("小数位数太长！【最大允许两位小数】");
			varthis.select();
			varthis.focus();
			return false;
		}
	}
	if(j>0)
		tmpval=val.substring(0,j);
	else
		tmpval=val;
	if(tmpval.length<1)
		return false;

	if(tmpval.length<4) {
		if(isNaN(tmpval))
			return false;
		else
			return true;		
	}	
	j=tmpval.indexOf(',');
	if(j<1)
		return false;
		
	tmpval2=tmpval.substring(0,j);
	if(isNaN(tmpval2)||tmpval2.length>3)
		return false;		
	
	tmpval=tmpval.substring(j+1,tmpval.length);
 
	while(tmpval.length>0) {
		if(tmpval.indexOf(',')<0) {
			if(!isNaN(tmpval)&&tmpval.length==3)
				return true;
			else
				return false;
		}
		if(isNaN(tmpval.substring(0,3)))
			return false;
		else
			tmpval=tmpval.substring(4,tmpval.length);
	} 	
	return false;
}

function checkMoney(varthis){

	if(!checkAmt(varthis,2))
		return false;
	if(!checkdec(varthis,2))
		return false;

	var a,renum='';
	var j=0;
	var a1='',a2='',a3='';
	if(event.keyCode>=37&&event.keyCode<=40) return false;

	a=unchnumber(varthis.value);
	j=a.indexOf('-');
	a1=a.substr(0,++j);
	a=a.substr(j);
	j=a.indexOf('.');
	if(j<0) j=a.length;
	a2=a.substr(0,j);
	a3=a.substr(j);
	j=0;
	for( i=a2.length;i>3;i=i-3){       
		renum=","+a2.substr(i-3,3)+renum;
		j++;
	}
	renum=a1+a2.substr(0,a2.length-j*3)+renum+a3;
	varthis.value=renum;
	return true;
}

function validMoney(a) {
		var c='',b=a.value;
		var d=a;
		var i=0,j=b.indexOf(',');
		if (j>0){
			c=c+b.substr(i,j-i);
			i=j+1;
			j=b.indexOf(',',i);
			while(j>0){
				c=c+b.substr(i,j-i);
				i=j+1;
				j=b.indexOf(',',i);
			}
			c=c+b.substr(i,b.length-1);
		}else{
			c=b;
		}
		if(isNaN(c)){
			alert("输入金额中含有非法字符");
			a.select();
    		a.focus();
			return false;
		}	
		if(parseInt(a.value)<=0) {
			alert("金额必须大于0");
			a.select();
    		a.focus();
			return false;
		}
		d.value=c;
		if(!checkMoney(d)) return false;
		if(a.value.indexOf('.')<0)
			a.value=a.value+".00";	
		return true;
	}
//判断是否没有输入数据
function validateIfBlank(varthis,msg){
	if(varthis.value==""){
		alert(msg);
		varthis.focus();
		return false;
	}
	return true;
}