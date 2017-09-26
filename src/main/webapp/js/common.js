
lastobj=null;//全程变量，记录上一个选中的记录
ten=false;
var tmpcolor="";
/**功能：高亮显示选中的记录
*作者：
*/
function setBgcolor(obj)
{	
	if(lastobj!==null){
		lastobj.bgColor=tmpcolor;
	}
	tmpcolor=obj.bgColor;
	obj.bgColor="#6495ED";
	lastobj=obj;
}