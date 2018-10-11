package com.tecnoinfsanjose.transportesronqui.Utilidades;

public class Utilidades {


    //USUARIOS


    public static final String TABLA_USUARIO="usuario";
    public static final String CAMPO_PASSWORD="password";
    public static final String CAMPO_CI="ci";

    public static final String CREAR_TABLA_USUARIO =
            "CREATE TABLE "+TABLA_USUARIO+" ( "+CAMPO_CI+" INTEGER PRIMARY KEY, "+CAMPO_PASSWORD+" TEXT );";




    // VIAJES

    public static final String TABLA_VIAJES = "viajes";
    public static final String VIAJE_ID = "id";
    public static final String VIAJE_ORIGEN = "origen";
    public static final String VIAJE_DESTINO = "destino";
    public static final String VIAJE_CONTACTO = "contacto";
    public static final String VIAJE_TEL = "telefono";
    public static final String VIAJE_CENTRAL = "central";
    public static final String VIAJE_CARGA = "tipo_carga";
    public static final String CREAR_TABLA_VIAJES =
            "CREATE TABLE " + TABLA_VIAJES+ "( "+VIAJE_ID+" INTEGER,"+VIAJE_ORIGEN+" TEXT,"+VIAJE_DESTINO+" TEXT, "+VIAJE_CARGA+" TEXT,"+VIAJE_TEL+" INTEGER,"+VIAJE_CENTRAL+" INTEGER,"+VIAJE_CONTACTO+" TEXT )";

    // DATA_VIAJES

    public static final String TABLA_DATA_VIAJE = "data_viajes";
    public static final String DATA_ID = "id";
    public static final String DATA_FECHA = "fecha";
    public static final String DATA_KM = "km";
    public static final String DATA_TONELADAS = "toneladas";
    public static final String DATA_NRO_REMITO1 = "nro_remito1";
    public static final String DATA_NRO_REMITO2 = "nro_remito2";
    public static final String DATA_OBSERVACIONES = "observaciones";
    public static final String DATA_IMG1 = "imagen1";
    public static final String DATA_IMG2 = "imagen2";
    public static final String DATA_IMG3 = "imagen3";
    public static final String CREAR_TABLA_DATA_VIAJES =
            "CREATE TABLE "+TABLA_DATA_VIAJE+ "( "+DATA_ID+" INTEGER, "+DATA_FECHA+" DATE,"+ DATA_KM+" INTEGER, "+DATA_TONELADAS+" FLOAT,"+DATA_NRO_REMITO1+" INTEGER,"+DATA_NRO_REMITO2+" INTEGER,"
                    +DATA_OBSERVACIONES+" TEXT,"+DATA_IMG1+" TEXT, "+DATA_IMG2+" TEXT, "+DATA_IMG3+" TEXT)";
}
