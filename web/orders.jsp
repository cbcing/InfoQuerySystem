<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 16/11/12
  Time: 下午4:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
    <s:head theme="xhtml"></s:head>
    <sx:head parseContent="true" extraLocales="UTF-8"></sx:head>
    <title>Orders</title>
</head>
<body>
    <s:form method="POST" action="getOrdersByDate">
        <sx:datetimepicker name="startDate" label="Please select startDate" displayFormat="yyyy-MM-dd"
                           type="date" toggleType="wipe" value="today" language="UTF-8" />
        <sx:datetimepicker name="endDate" label="Please select endDate" displayFormat="yyyy-MM-dd"
                           type="date" toggleType="wipe" value="today" language="UTF-8" />
        <s:submit value="提交"></s:submit>
    </s:form>

    <br/><br/>
    <table border="1">
        <tr>
            <td>OrderID</td>
            <td>CustomerID</td>
            <td>OrderDate</td>
        </tr>
        <s:iterator value="#session.listForOrder" id="list" status="st">
            <tr bgcolor=<s:if test="#st.odd">#ADD8E6</s:if>>
                <td><s:property value="#list.orderID"/></td>
                <td><s:property value="#list.customerID"/></td>
                <td><s:property value="#list.orderDate"/></td>
            </tr>
        </s:iterator>
    </table>
    <!--
    <br/><br/>
    <table border="1">
        <s:iterator value="#session.listTwo" id="l" status="st">
            <tr bgcolor=#ADD8E6>
                <td><s:property value="#l.orderID"/></td>
                <td><s:property value="#l.customerID"/></td>
                <td><s:property value="#l.orderDate"/></td>
            </tr>
        </s:iterator>
    </table>
    -->
</body>
</html>
