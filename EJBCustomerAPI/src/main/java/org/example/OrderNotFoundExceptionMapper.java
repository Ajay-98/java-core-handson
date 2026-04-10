package org.example;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class OrderNotFoundExceptionMapper
        implements ExceptionMapper<OrderNotFoundException>
{
    @Override
    public Response toResponse(OrderNotFoundException exception) {

        ErrorDTO error = new ErrorDTO("ORDER_NOT_FOUND", exception.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
}
