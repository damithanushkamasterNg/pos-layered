/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.layered.controller;

import pos.layered.dto.OrderDto;
import pos.layered.service.ServiceFactory;
import pos.layered.service.custom.OrderService;

/**
 *
 * @author Damith
 */
public class OrderController {

    private OrderService orderService = (OrderService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ORDER);

    public String placeOrder(OrderDto orderDto) throws Exception {
        return orderService.placeOrder(orderDto);
    }
}
