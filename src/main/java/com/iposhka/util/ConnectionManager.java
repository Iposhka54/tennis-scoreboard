package com.iposhka.util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class ConnectionManager {
    private static final Configuration cfg;

    static{
        cfg = new Configuration();
        cfg.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        cfg.configure();
    }

    public static SessionFactory getSessionFactory(){
        return cfg.buildSessionFactory();
    }
}