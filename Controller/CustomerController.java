/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Customer;
import Model.CustomerDao;
import Model.Facilities;
import Model.Service;
import View.CustomerView;
import View.ServiceItem;
import View.TableModelCustomer;
import java.util.Date;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author quoct
 */
public class CustomerController {

    private CustomerView customerView;
    private CustomerDao dataCustomer;
    private TableModelCustomer model;

    public CustomerController(CustomerView customerView) {
        this.customerView = customerView;
        dataCustomer = new CustomerDao();
        model = new TableModelCustomer();
        setTable();
        searchTextChange();
    }

    public void setTable() {
        List<Customer> list = dataCustomer.getAllCustomersWithUniquePhoneNumbers();
        model.setData(list);
        customerView.setTable(model);
    }
     public void setTableBookingInHotel() {
        List<Customer> list = dataCustomer.getAllCustomerBookingInRealTime();
        model.setData(list);
        customerView.setTable(model);
    }

    public void searchTextChange() {
        customerView.getClickInTextFieldChange(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleTextChange();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                handleTextChange();
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
    }

    private void handleTextChange() {
        String newText = customerView.getTextSearch();
        List<Customer> list = dataCustomer.getInformationCustomerByName(newText);
        model.setData(list);
        customerView.setTable(model);
    }
      public Customer setTableItemSelected() {
        int rowSelected = customerView.getRowSelected();
        int id = (int) model.getValueAt(rowSelected, 0);
        String name = (String) model.getValueAt(rowSelected, 1);
        int age = (int) model.getValueAt(rowSelected, 2);
        String cccd = (String) model.getValueAt(rowSelected, 3);
        String gender = (String) model.getValueAt(rowSelected, 4);
        String sdt = (String) model.getValueAt(rowSelected, 5);
        String address = (String) model.getValueAt(rowSelected, 6);
        Customer customer = new Customer(id, name, cccd, name, address, sdt, age);
        return customer;
    }
    
}
