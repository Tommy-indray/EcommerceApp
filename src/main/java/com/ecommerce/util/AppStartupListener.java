package com.ecommerce.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppStartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("🚀 Démarrage de l'application EcommerceApp...");
        try {
            DataInitializer.init();
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de l'initialisation des données : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        JPAUtil.close();
        System.out.println("🛑 Application arrêtée.");
    }
}