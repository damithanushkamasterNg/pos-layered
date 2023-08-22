/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pos.layered.service.custom;

import pos.layered.dto.OrderDto;
import pos.layered.service.SuperService;

/**
 *
 * @author Damith
 */
public interface OrderService extends SuperService{
    
    String placeOrder(OrderDto dto) throws Exception;
    
}
