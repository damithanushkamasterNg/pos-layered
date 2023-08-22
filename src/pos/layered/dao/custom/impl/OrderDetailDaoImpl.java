/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.layered.dao.custom.impl;

import java.util.ArrayList;
import pos.layered.dao.CrudUtil;
import pos.layered.dao.custom.OrderDetailDao;
import pos.layered.entity.OrderDetailEntity;

/**
 *
 * @author Damith
 */
public class OrderDetailDaoImpl implements OrderDetailDao {

    public boolean add(OrderDetailEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO orderdetail VALUES(?,?,?,?)", t.getOrderId(), t.getItemId(), t.getQty(), t.getDiscount());
    }

    public boolean update(OrderDetailEntity t) throws Exception {
        return false;
    }

    public boolean delete(Object id) throws Exception {
        return false;
    }

    public OrderDetailEntity get(Object id) throws Exception {
        return null;
    }

    public ArrayList<OrderDetailEntity> getAll() throws Exception {
        return null;
    }
}
