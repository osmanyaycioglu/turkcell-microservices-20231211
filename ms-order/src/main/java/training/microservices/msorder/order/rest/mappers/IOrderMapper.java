package training.microservices.msorder.order.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import training.microservices.msorder.order.rest.models.OrderDto;
import training.microservices.msorder.order.services.models.Order;

@Mapper
public interface IOrderMapper {

    IOrderMapper INSTANCE = Mappers.getMapper(IOrderMapper.class);

    Order toOrder(OrderDto order);

    OrderDto toOrder(Order order);

}
