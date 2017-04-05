package com.scujcc.dao;

import com.scujcc.domain.Order;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by David on 16/11/12.
 */
public class OrderDao {
    public List getOrdersByCustomerID(String customerID){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List list = new ArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Northwind", "root", "cbc903205927");
                String sql = "select * from Orders where CustomerID = \'" + customerID + "\'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()){
                    Order order = new Order();
                    order.setOrderID(rs.getString("OrderID"));
                    order.setCustomerID(customerID);
                    String orderDate = rs.getString("OrderDate").split(" ")[0];
                    order.setOrderDate(orderDate);
                    list.add(order);
                }

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List getOrdersByDate(String startDate, String endDate, List<Order> listOfParamerter){
        String startDateHere = startDate.substring(0,10);
        String endDateHere = endDate.substring(0,10);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List list = new ArrayList();

        try {
            Date currentStartDate = simpleDateFormat.parse(startDateHere);
            Date currentEndDate = simpleDateFormat.parse(endDateHere);

            for (int i = 0; i < listOfParamerter.size(); i++){
                Date date = simpleDateFormat.parse(listOfParamerter.get(i).getOrderDate());
                if (date.before(currentEndDate) && date.after(currentStartDate)) {
                    list.add(listOfParamerter.get(i));
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return list;
    }
}
