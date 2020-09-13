package com.framework.samples.orderapp.mapper;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/9/8 17:51
 * @since JDK 1.8
 */
import com.framework.samples.orderapp.entity.OrderEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderMapper {

    @Insert(value = "INSERT INTO `order_info` VALUES (#{id}, #{name}, #{orderMoney},#{orderId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int addOrder(OrderEntity orderEntity);

    @Select("SELECT id as id ,name as name , order_money as orderMoney,orderId as orderId from order_info where orderId=#{orderId};")
    public OrderEntity findOrderId(@Param("orderId") String orderId);

}