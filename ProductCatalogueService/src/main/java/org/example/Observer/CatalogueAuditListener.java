package org.example.Observer;

import org.example.DTO.ProductRegistered;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class CatalogueAuditListener {

    public void onRegister(@Observes ProductRegistered pd)
    {
        System.out.println("[*] "+ " Event is Product Registered" + pd.uuid()+" is the ID");
    }
}
