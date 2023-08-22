/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.layered.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import pos.layered.dao.DaoFactory;
import pos.layered.dao.custom.ItemDao;
import pos.layered.dao.custom.OrderDao;
import pos.layered.dao.custom.OrderDetailDao;
import pos.layered.dto.OrderDetailDto;
import pos.layered.dto.OrderDto;
import pos.layered.entity.ItemEntity;
import pos.layered.entity.OrderDetailEntity;
import pos.layered.entity.OrderEntity;
import pos.layered.service.custom.OrderService;
import java.sql.Connection;
import pos.layered.db.DBConnection;

/**
 *
 * @author Damith
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = (OrderDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.ORDER);
    private OrderDetailDao orderDetailDao = (OrderDetailDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.ORDER_DETAIL);
    private ItemDao itemDao = (ItemDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.ITEM);


    public String placeOrder(OrderDto dto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (orderDao.add(new OrderEntity(dto.getOrderId(), sdf.format(new Date()), dto.getCustomerid()))) {
                boolean isOrderSaved = true;
                for (OrderDetailDto orderDetailDto : dto.getOrderDetailDtos()) {
                    if (!orderDetailDao.add(new OrderDetailEntity(dto.getOrderId(),
                            orderDetailDto.getItemId(),
                            orderDetailDto.getQty(),
                            orderDetailDto.getDiscount()))) {
                        isOrderSaved = false;
                    }
                }

                if (isOrderSaved) {
                    
                    boolean isItemUpdated = true;
                    
                    for (OrderDetailDto orderDetailDto : dto.getOrderDetailDtos()) {
                        ItemEntity entity = itemDao.get(orderDetailDto.getItemId());
                        entity.setQoh(entity.getQoh() - orderDetailDto.getQty());
                        if(!itemDao.update(entity)){
                            isItemUpdated = false;
                        }
                    } 
                    
                    if(isItemUpdated){
                        connection.commit();
                        return "Success";
                    } else{
                        connection.rollback();
                        return "Item Update Error";
                    }

                } else {
                    connection.rollback();
                    return "Order Detail Save Error";
                }

            } else {
                connection.rollback();
                return "Order Save Error";
            }

        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

}

