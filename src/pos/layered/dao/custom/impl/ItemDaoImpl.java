/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.layered.dao.custom.impl;

import java.util.ArrayList;
import pos.layered.dao.CrudUtil;
import java.sql.ResultSet;
import pos.layered.dao.custom.ItemDao;
import pos.layered.entity.ItemEntity;

/**
 *
 * @author Damith
 */
public class ItemDaoImpl implements ItemDao{

    public boolean add(ItemEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Item VALUES(?,?,?,?,?)",
                t.getId(),
                t.getDescription(),
                t.getPackSize(),
                t.getUnitPrice(),
                t.getQoh());
    }

    public boolean update(ItemEntity t) throws Exception {
        return CrudUtil.executeUpdate("UPDATE Item SET  Description=?, PackSize=?, UnitPrice=?, QtyOnHand=? WHERE ItemCode = ?",
                t.getDescription(),
                t.getPackSize(),
                t.getUnitPrice(),
                t.getQoh(),
                t.getId());
    }


    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM item WHERE ItemCode = ?", id);
    }


    public ItemEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM item WHERE ItemCode = ?", id);
        while (rst.next()) {
            return new ItemEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5));
        }
        return null;
    }

    public ArrayList<ItemEntity> getAll() throws Exception {
        ArrayList<ItemEntity> itemEntitys = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM item");
        while (rst.next()) {
            ItemEntity entity = new ItemEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5));
            itemEntitys.add(entity);
        }
        return itemEntitys;
    }

}
