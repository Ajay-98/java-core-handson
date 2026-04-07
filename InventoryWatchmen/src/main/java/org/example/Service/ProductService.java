package org.example.Service;

import org.example.DTO.ProductRegistered;
import org.example.Entity.Product;
import org.example.Repository.ProductRepository;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

@Stateless
public class ProductService {

    @Inject
    private ProductRepository productRepository;
    @Inject
    private Event<ProductRegistered> productRegisteredEvent;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void registerProduct(String name, String category, BigDecimal price) throws ExecutionException, InterruptedException
    {

       productRegisteredEvent.fire(new ProductRegistered(productRepository.SaveProduct(new Product(name, category, price, true))));

    }

    public void getProductByCategory(String category) throws ExecutionException, InterruptedException
    {
        productRepository.findActiveByCategory(category)
                        .forEach(System.out::println);
    }


}
