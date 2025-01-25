package com.iposhka.util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class ConnectionManager {
    private static final Configuration cfg;

    static{
        cfg = new Configuration();
        cfg.configure();
    }

    public static SessionFactory getSessionFactory(){
        return cfg.buildSessionFactory();
    }
}