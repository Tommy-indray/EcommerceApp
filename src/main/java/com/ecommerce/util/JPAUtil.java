package com.ecommerce.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory("ecommercePU");

    public static EntityManagerFactory getEntityManagerFactory() {
        return EMF;
    }

    public static void close() {
        if (EMF != null && EMF.isOpen()) EMF.close();
    }
}