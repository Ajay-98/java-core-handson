package org.example.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.Entity.Product;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class ProductRepository {

    @PersistenceContext
    private EntityManager em;

    public String SaveProduct(Product p) throws ExecutionException, InterruptedException
    {
        em.persist(p);
        return p.getId();
    }

    public List<Product> findActiveByCategory(String category) {
       return em.createNamedQuery("Product.findActiveByCategory", Product.class).
               setParameter("ctgry", category).
               getResultList();
    }

}
