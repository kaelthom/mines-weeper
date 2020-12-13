// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConnexionFactory.java

package dto;

// Referenced classes of package connexions:
//            ConnexionProperties

public class DeminorGamePropertiesFactory {

    private static DeminorGamePropertiesFactory gamePropertiesFactory = null;
    private static DeminorGameProperties gameProperties = null;

    private DeminorGamePropertiesFactory() {
    }

    public static DeminorGameProperties getDeminorGamePropertiesInstance() {
        gamePropertiesFactory = gamePropertiesFactory == null ? new DeminorGamePropertiesFactory() : gamePropertiesFactory;
        return gameProperties == null ? initDeminorGameProperties() : gameProperties;
    }

    private static DeminorGameProperties initDeminorGameProperties() {
        gameProperties = new DeminorGameProperties();
        return gameProperties;
    }

    public static void updateDeminorGameProperties(DeminorGameProperties gameProperties) {
        gameProperties.calculateGamePropertiesFromLevel();
        DeminorGamePropertiesFactory.gameProperties = gameProperties;
    }

}
