package com.scujcc.struts.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.scujcc.dao.OrderDao;

import java.util.List;

/**
 * Created by David on 16/11/12.
 */
public class OrderAction extends ActionSupport{
    private String customerID;
    private String startDate;
    private String endDate;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String execute() throws Exception{
        OrderDao dao = new OrderDao();
        List list = dao.getOrdersByCustomerID(customerID);
        ActionContext.getContext().getSession().put("listForOrder", list);
        ActionContext.getContext().getSession().put("listForReady", list);
        return SUCCESS;
    }

    public String getOrdersByDate(){
        OrderDao dao = new OrderDao();
        List listForParameter = (List) ActionContext.getContext().getSession().get("listForReady");
        List listToSession = dao.getOrdersByDate(startDate, endDate, listForParameter);
        ActionContext.getContext().getSession().put("listForOrder", listToSession);
        return SUCCESS;
    }
}
