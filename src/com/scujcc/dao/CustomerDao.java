package com.scujcc.dao;

import com.scujcc.domain.CountryAndCity;
import com.scujcc.domain.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by David on 16/11/12.
 */
public class CustomerDao {
    public List getCountry(){
        List<CountryAndCity> list = new ArrayList<CountryAndCity>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int id = 101;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Northwind" ,"root", "cbc903205927");
                java.lang.String sql = "select distinct Country from Customers";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()){
                    java.lang.String country = rs.getString("Country");
                    CountryAndCity countryAndCity = new CountryAndCity();
                    if (country == null)
                        continue;
                    countryAndCity.setId(id);
                    countryAndCity.setName(country);
                    list.add(countryAndCity);
                    id++;
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

    public Map getCity(){
        Map<Integer, List<CountryAndCity>> map = new HashMap<Integer, List<CountryAndCity>>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Northwind", "root", "cbc903205927");
                for (int i = 0; i < this.getCountry().size(); i++){
                    int num = 1;
                    List<CountryAndCity> list = new ArrayList<CountryAndCity>();
                    List<CountryAndCity> listhelp = (List<CountryAndCity>) this.getCountry();
                    java.lang.String sql = "select DISTINCT City from Customers where Country = \'" + listhelp.get(i).getName() + "\'";
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();
                    while (rs.next()){
                        java.lang.String city = rs.getString("city");
                        if (city == null)
                            continue;
                        CountryAndCity countryAndCity = new CountryAndCity();
                        countryAndCity.setId(listhelp.get(i).getId() * 100 + num);
                        countryAndCity.setName(city);
                        list.add(countryAndCity);
                        num++;
                    }
                    map.put(listhelp.get(i).getId(), list);
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return map;
    }

    public List getCustomers(String country, String city){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Customer> list = new ArrayList<Customer>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Northwind", "root", "cbc903205927");
                //select * from Customers where City = 'city' and Country = 'country'
                java.lang.String sql = "select * from Customers where City = \'" + city + "\'" + " and Country = \'" + country + "\'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()){
                    Customer customer = new Customer();
                    customer.setCustomerID(rs.getString("CustomerID"));
                    customer.setCompanyName(rs.getString("CompanyName"));
                    customer.setContactTitle(rs.getString("ContactName"));
                    customer.setContactName(rs.getString("ContactTitle"));
                    list.add(customer);
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
}
