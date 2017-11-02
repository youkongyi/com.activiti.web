<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/js/commons.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假管理</title>
</head>
<body>
 	<form action="leaveBillAction_save" method="POST">
 		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="6%" height="19" valign="bottom"><div align="center"><img src="${pageContext.request.contextPath }/images/tb.gif" width="14" height="14" /></div></td>
		                <td width="94%" valign="bottom">
		                	<span class="STYLE1">
		                			新增/修改请假申请
		                	</span>
		                	</td>
		              </tr>
		            </table></td>
		            <td><div align="right"><span class="STYLE1">
		              </span></div></td>
		          </tr>
		        </table></td>
		      </tr>
		    </table></td>
		  </tr>
		  <tr>
		    <td>
			    <c:choose>
				   <c:when test="${empty leaveBill}">
					   <div align="left" class="STYLE21">
					 		请假天数:<input type="text" name="days" style="width: 200px;"/><br/>
					 		请假原因:<textarea type="text" name="content" style="width:360;height:60;overflow-x:visible;overflow-y:visible;"></textarea><br/>
					 		备&emsp;&emsp;注:<textarea type="text" name="remark" style="width:360;height:60;overflow-x:visible;overflow-y:visible;"></textarea><br/>
					 		<input type="submit" value="提交" class="button_ok"/>
					   </div>
				   </c:when>
				   <c:otherwise>
					   <div align="left" class="STYLE21">
						    <input type="hidden" name="id" value="${leaveBill.id }"/>
						    <input type="hidden" name="userId" value="${employee.id }"/>
					 		请假天数:<input type="text" name="days" value="${leaveBill.days }" style="width: 200px;"/><br/>
					 		请假原因:<textarea type="text" name="content" style="width:360;height:60;overflow-x:visible;overflow-y:visible;">${leaveBill.content }</textarea><br/>
					 		备&emsp;&emsp;注:<textarea type="text" name="remark" style="width:360;height:60;overflow-x:visible;overflow-y:visible;">${leaveBill.remark }</textarea><br/>
					 		<input type="submit" value="提交" class="button_ok" style="height: 20px;"/>
					   </div>
				   </c:otherwise>
				</c:choose>
		    </td>
		  </tr>
	</table>
	 	
	</form>
</body>
</html>