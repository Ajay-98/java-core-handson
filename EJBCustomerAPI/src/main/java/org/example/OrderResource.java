package org.example;

import javax.ws.rs.Path;

@Path("")
@RequestScoped
public class OrderResource {

    @Inject
    OrderRepo or;

    @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderResponse> getOrders() {

        List<OrderResponse> orders = or.getall();
        return  orders;
    }

    @GET
    @Path("orders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("id") Long id) {
        OrderResponse response = or.getid(id);
        return  Response.status(Response.Status.OK).entity(response).build();
    }


}
