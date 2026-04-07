package org.example.Records;

import java.util.Objects;
import java.util.Random;
import java.util.RandomAccess;
import java.util.UUID;
import java.util.random.RandomGenerator;

public record ProductId(String uuid) {

    public ProductId
    {
        Objects.requireNonNull(uuid, "uuid must not be null");
    }

    public ProductId generate(){
        return new ProductId(UUID.randomUUID().toString());
    }
}
