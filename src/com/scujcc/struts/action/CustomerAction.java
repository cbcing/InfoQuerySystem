package com.scujcc.struts.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.scujcc.dao.CustomerDao;

import java.util.List;
import java.util.Map;

/**
 * Created by David on 16/11/12.
 */
public class CustomerAction extends ActionSupport{
    private String country;
    private String city;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List getCountryByQuery(){
        CustomerDao dao = new CustomerDao();
        List list = dao.getCountry();
        return list;
    }

    public Map getCityByQuery(){
        CustomerDao dao = new CustomerDao();
        Map map = dao.getCity();
        return map;
    }

    @Override
    public String execute(){
        return SUCCESS;
    }

    public String getCustomers() throws Exception{
        CustomerDao dao = new CustomerDao();
        List list = dao.getCustomers(country, city);
        ActionContext.getContext().getSession().put("list", list);
        return SUCCESS;
    }
}
