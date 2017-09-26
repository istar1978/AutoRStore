// attach.js

// 添加附件

var   currFocus;   
var   ExistAttaInfo   =   new   Array();   
var   oldDelAttas   =   new   Array();   
var   attaIdx   =   0;   
var   IsIE;   

function   fInitMSIE(){   
  if(navigator.userAgent.indexOf("MSIE") != -1){   
  	IsIE = true;   
  }else{   
  	IsIE = false;   
  }   
}   

fInitMSIE();   
    
function   fTrim(str){   
  str   =   str.replace(/(^\s*)/,"");   
  str   =   str.replace(/(\s*$)/,"");   
  return   str;   
}   
    
  //本js文件用于compose.htm同时发送多个附件的js函数，支持   IE   和   NNx   系列。   
  //所有的函数都适用于   IE   和   NNx   系列，直接调用就可以   
  //函数里面会根据语句判断浏览器类型，然后依据不同的bowser属性增加元素   
  //主要功能是动态创建   各种表单元素   和   删除表单元素   
  //written   by   xpr   
    
  //         function   list   
  //         -------------------------------------------------------------------------------------------------------------------   
  //         IsIEBrowser()     返回值:   true(IE)   false(NNx)   
  //         exist(fileId)     判断fileId是否已经存在   
  //         addInputFile(spanId,fileId)     在名为spanId   的元素里面增加序号为index的input   file   
  //         addbr(spanId,brId)     在名为spanId   的元素里面增加id为brId的换行符   
  //         adddel(spanId,index)   在名为spanId   的元素里面增加序号为index的"删除"按钮，他可以删除相应的input   file   和   br   
  //         addhidden(spanId,hiddenId,value)     在名为spanId   的元素里面增加id为hiddenId，值为value的input   type=hidden   
  //         -------------------------------------------------------------------------------------------------------------------   
    
var   IsIE;   
var   attaIdx   =   0;   
    
  //search,全局变量   
  //-----------------------------------------------------------------------------------------------------------   
function   IsIEBrowser()   {   
  if (navigator.userAgent.indexOf("MSIE")   !=   -1)   {   
  	return true;   
  } else {   
 	 return false;   
  }   
}   
    
  //   增加附件函数   ()，增加到   idfilespan,基数为   attaIdx   。   
    
  function   add()   {   
    
  addfile("idfilespan",attaIdx);   
  attaIdx++;   
  return   false;   
  }   
    
  //----------------------------------------fileexist()----------------------------------------------------------   
  //edited   by   alun   
  function   exist(fileId)   {   
              var   file   =   document.getElementById(fileId);   
      var   result   =   false;   
      if   (   file   !=null   )   {   
  if   (   file.value   !=   null   &&   file.value   !=   "")   {   
  result   =   existFile(   file);   
    
  }//if   
      }   
      if(   result)   
      {   
  alert("这个附件您已经添加过了,新添加的附件将已被删除");   
  //document.sendmail."+fileId+".value=""";   
  var   attach_num   =   fileId.substring(10,12);   
    
  delfile("idfilespan",attach_num);   
      
  //--attaIdx;   
  //document.getElementById(   "idAttachOper"+   attaIdx).click();   
      }   
  }//end   of   function   
  //added   by   alun   
  function   getfilename(   attaName   )   {   
  var   s   =   attaName.lastIndexOf(   '\\'   );   
  return   attaName.substr(s+1,   attaName.length   -   s   -1);   
  }   
  //added   by   alun   
  function   existFile(   file)   
  {   
  var   form   =   document.sendmail;   
  for   (   var   i=   0   ;   i   <   form.elements.length   ;   i   ++   )   {           
  if   (   form.elements[i].type   ==   "file"   &&   form.elements[i].name   !=   file.name   )   {   
  if   (   file.value   ==   form.elements[i].value   )   {   
  //alert(form.elements[i].value=null);   
  return   true;   
  }   
  }   
  }//for   
  for   (var   i=0;   i<ExistAttaInfo.length;   i++)   {   
  var   theName   =   ExistAttaInfo[i];   
  if   (   theName   !=   null   &&   theName   !=   ""   &&   theName   ==   getfilename(file.name)   )   {   
  return   true;   
  }   
  }   
  return   false;   
  }   
  //----------------------------------------addfile(spanId,index)----------------------------------------------   
  function   addfile(spanId,index)   
  {   
                var   strIndex   =   ""   +   index;   
          
        var   fileId   =   "attachfile"+   strIndex;   
        var   brId   =   "idAttachBr"   +   strIndex;   
        addInputFile(spanId,fileId);   
    
        adddel(spanId,index);   
    
        addbr(spanId,brId);   
        //document.getElementById(   "attachfile"+   strIndex).click();   
        return;   
  }   
  //-------------------------------------------sub   fuction   addInputFile-----------------------------------------   
  function   addInputFile(spanId,fileId)   
  {   
      var   span   =   document.getElementById(spanId);   
      if   (   span   !=null   )   {   
                                  if   (   !IsIE   )   {   
  var   fileObj   =   document.createElement("input");   
  if   (   fileObj   !=   null   )   {   
  fileObj.type="file";   
  fileObj.name   =   fileId;   
  fileObj.id   =   fileId;   
  fileObj.size="50";   
  var   clickEvent   =   "exist('"   +   fileId   +   "')";   
  fileObj.setAttribute("onclick",clickEvent,0);       
  span.appendChild(fileObj);   
  }//if   fileObj   
  }//   !IsIE   
    
  if   (   IsIE   )   {   
  var   fileTag   =   "<input   type='file'     id   ='"   +   fileId   +   "'   style='BORDER-RIGHT:   #000000   1px   solid;   background-color:   #FFFFFF;   BORDER-TOP:   #000000   1px   solid;   OVERFLOW:   no;   vertical-align:   top;     BORDER-LEFT:   #000000   1px   solid;   WIDTH:   90%;   BORDER-BOTTOM:   #000000   1px   solid;   HEIGHT:   20'   size=50   name='"   +   fileId   +   "'   size=50   onchange=exist('"   +   fileId   +   "')>";   
  var   fileObj   =   document.createElement(fileTag);     
  span.appendChild(fileObj);   
  }//IsIE   if   
    
      }//if   span   
  }   
    
  function   addbr(spanId,brId)   
  {   
      var   span   =   document.getElementById(spanId);   
      if   (   span   !=null   )   {   
  var   brObj   =   document.createElement("br");   
  if   (   brObj   !=null   )   {   
  brObj.name   =   brId;   
  brObj.id   =   brId;   
  span.appendChild(brObj);   
                          }//i2f   
            }//if   
    return;   
  }   
    
  function   adddel(spanId,index)   
  {   
              var   strIndex   =   ""   +   index;   
      var   delId   =   "idAttachOper"   +   strIndex;   
      var   span   =   document.getElementById(spanId);   
      if   (   span   !=   null   )   {   
  var   oTextNode   =   document.createElement("SPAN");   
  oTextNode.style.width   =   "5px";   
  span.appendChild(oTextNode);   
          if   (   IsIE   )   {   
                  var   tag   =   "<input   type='submit'   style='BORDER-RIGHT:   #000000   1px   solid;   BORDER-TOP:   #000000   1px   solid;   OVERFLOW:   no;   BORDER-LEFT:   #000000   1px   solid;     BORDER-BOTTOM:   #000000   1px   solid;   HEIGHT:   20'   size=70   value='$subject'     id='"   +   delId   +   "'   onclick=delfile('"   +   spanId   +   "',"+strIndex+")></input>";   
  var   delObj   =   document.createElement(tag);   
  if   (   delObj   !=   null   )   {   
  span.appendChild(delObj);   
  }//if   
    
  }//   Is   IE   
    
                  if   (   !IsIE   )   {   
  var   delObj   =   document.createElement("input");   
  if   (   delObj   !=   null   )   {   
  delObj.name   =   delId;   
  delObj.id   =   delId;   
  delObj.type   =   "button";   
  var   clickEvent   =   "return   delfile('"   +   spanId   +   "',"+strIndex+");";   
  delObj.setAttribute("onclick",clickEvent);       
  span.appendChild(delObj);   
  }//if   
  }//   !IsIE   if   
  if(   delObj   !=   null)   delObj.value   =   "删 除";   
  }//main   if   
  return;   
  }   
    
    
  //-------------------------------------------------------------------------------------------------------------   
    
    
  //---------------------------------------------delete   input   file-----------------------------------------------   
  function   delfile(spanId,index)   
  {   
        var   strIndex   =   ""   +   index;   
        var   fileId   =   "attachfile"+   strIndex;   
        var   brId   =   "idAttachBr"   +   strIndex;   
        var   delId   =   "idAttachOper"   +   strIndex;   
        //first,get   the   element   
                var   span   =   document.getElementById(spanId);   
        //alert(     "del   span:   "   +   span     );   
        if   (   span   ==   null   )   return   false;   
    
        var   fileObj   =   document.getElementById(fileId);   
        if   (   fileObj   ==   null   )   return   false;   
    
        var   brObj   =   document.getElementById(brId);   
        if   (   brObj   ==null   )   return   false;   
    
        var   delObj   =   document.getElementById(delId   );   
        //alert(     "del   delId:   "   +   delObj     );   
        if   (   delObj   ==   null   )   return   false;   
    
                //second,create   the   replace   element   
        var   temp=   document.createElement("SPAN");   
        //third,replace   it   
          span.replaceChild(temp,fileObj);   
  span.replaceChild(temp,brObj);   
  //   Added   by   Harry,   Repair   Remove   attached   bug   2005/04/04   
  span.removeChild(delObj.previousSibling);   
  var   attach   =   document.getElementById("attach");   
  if(span.getElementsByTagName("INPUT").length   ==   1)   attach.childNodes[0].nodeValue='添加附件';   
  //   End   
  span.replaceChild(temp,delObj);   
    
  return   false;   
  }   
