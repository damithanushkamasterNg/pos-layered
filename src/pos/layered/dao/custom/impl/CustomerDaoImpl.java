/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.layered.dao.custom.impl;

import java.util.ArrayList;
import pos.layered.dao.CrudUtil;
import pos.layered.dao.custom.CustomerDao;

import java.sql.ResultSet;
import pos.layered.entity.CustomerEntity;

/**
 *
 * @author Damith
 */
public class CustomerDaoImpl implements CustomerDao {

    public boolean add(CustomerEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?,?)",
                t.getId(), t.getTitile(), t.getName(), t.getDob(),
                t.getSalary(), t.getAddress(), t.getCity(), t.getProvince(),
                t.getZip());
    }

    public boolean update(CustomerEntity t) throws Exception {
        return CrudUtil.executeUpdate("UPDATE Customer SET CustTitle =?, CustName=?, DOB=?, salary = ?, CustAddress=?, City=?, Province=?, PostalCode=? WHERE CustID=?",
                t.getTitile(), t.getName(), t.getDob(),
                t.getSalary(), t.getAddress(), t.getCity(), t.getProvince(),
                t.getZip(), t.getId());
    }

    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM Customer WHERE CustID=?",
                id);
    }

    public CustomerEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * FROM Customer WHERE CustID = ?", id);

        while (rst.next()) {
            CustomerEntity customerEntity = new CustomerEntity(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9));

            return customerEntity;
        }
        return null;
    }

    public ArrayList<CustomerEntity> getAll() throws Exception {
        ArrayList<CustomerEntity> customerEntitys = new ArrayList<>();

        ResultSet rst = CrudUtil.executeQuery("Select * FROM Customer");

        while (rst.next()) {
            CustomerEntity customerEntity = new CustomerEntity(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9));

            customerEntitys.add(customerEntity);
        }

        return customerEntitys;
    }
}
