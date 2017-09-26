项目中硬编码：
/autorstore/public/head.jspf
<title>AutoRStore</title>

log

参数说明：
point.rule:定义积分规则，如point.rule=1表示一元一积分，point.rule=10表示10元一积分，取整，不计算小数
point.combo：定义购买套餐时，是否计入会员积分，0计入，1不计入

修改会员积分的地方：
购买套餐时，判断是否修改积分


开发日志：
20170617：抽取service层，添加BaseAction
20170618：将系统中所有Double类型改为BigDecimal

数据库由h2-->mysql时：
1、数据库相关参数修改 application-dao.xml
2、数据备份文件的路径修改 sysinit.properties


