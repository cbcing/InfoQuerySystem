<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 16/11/12
  Time: 下午2:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Information Query</title>
</head>
<body>
    <s:form method="POST" action="getCustomers">
        <s:doubleselect name="country" list="countryByQuery" listValue="name" listKey="name"
                        doubleName="city" doubleList="cityByQuery.get(top.id)" doubleListValue="name" doubleListKey="name">
        </s:doubleselect>
        <s:submit value="提交"></s:submit>
    </s:form>

    <br/><br/>
    <table border="1">
        <tr>
            <td width="25%">CustomerID</td>
            <td width="25%">CompanyName</td>
            <td width="25%">ContactName</td>
            <td width="25%">ContactTitle</td>
        </tr>
        <s:iterator value="#session.list" id="l" status="st">
            <tr bgcolor="#ADD8E6">
                <td><a href="order.action?customerID=<s:property value="#l.customerID"/>"><s:property value="#l.customerID"/></a></td>
                <td><s:property value="#l.companyName"/></td>
                <td><s:property value="#l.contactName"/></td>
                <td><s:property value="#l.contactTitle"/></td>
            </tr>
        </s:iterator>
    </table>
</body>
</html>
