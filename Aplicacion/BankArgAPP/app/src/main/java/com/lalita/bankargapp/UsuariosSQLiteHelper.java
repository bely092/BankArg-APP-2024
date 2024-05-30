package com.lalita.bankargapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lalita.bankargapp.Clases.User;
import com.lalita.bankargapp.Clases.Usuario;
import com.lalita.bankargapp.Clases.Usuarios;

import java.util.ArrayList;
import java.util.List;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BankArgAPP.db";
    private static final int DATABASE_VERSION = 3;

    // Definir la estructura de la tabla "user".
    private static final String CREATE_TABLE_USER = "CREATE TABLE if not exists User (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username TEXT NOT NULL," +
            "password TEXT NOT NULL)";
    public UsuariosSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB_CREATION", "Database created or upgraded");
        // Ejecutar la creación de la tabla "user".
        db.execSQL(CREATE_TABLE_USER);

//        Por alguna razon me da error: table Usuarios not exist
//        db.execSQL("CREATE TABLE if not exists Documentos (id_tipo_doc INTEGER PRIMARY KEY AUTOINCREMENT, tipo_doc TEXT)");
//        db.execSQL("CREATE TABLE if not exists Sexos (id_tipo_sexo INTEGER PRIMARY KEY AUTOINCREMENT, tipo TEXT)");
//        db.execSQL("CREATE TABLE if not exists paises (cod_pais INTEGER PRIMARY KEY AUTOINCREMENT, pais TEXT)");
//        db.execSQL("CREATE TABLE if not exists provincias (cod_provincia INTEGER PRIMARY KEY AUTOINCREMENT, provincia TEXT, cod_pais INTEGER, FOREIGN KEY (cod_pais) REFERENCES paises(cod_pais))");
//        db.execSQL("CREATE TABLE if not exists localidades (cod_localidad INTEGER PRIMARY KEY AUTOINCREMENT, localidad TEXT, cod_provincia INTEGER, FOREIGN KEY (cod_provincia) REFERENCES provincias(cod_provincia))");
//        db.execSQL("CREATE TABLE if not exists Usuarios (id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, username TEXT not null, password TEXT not null, id_tipo_doc INTEGER, nro_doc TEXT, cod_localidad INTEGER, nro_calle INTEGER, calle TEXT, fecha_nac TEXT, id_tipo_sexo INTEGER, FOREIGN KEY (id_tipo_doc) REFERENCES Documentos(id_tipo_doc), FOREIGN KEY (cod_localidad) REFERENCES localidades(cod_localidad), FOREIGN KEY (id_tipo_sexo) REFERENCES Sexos(id_tipo_sexo))");

//        db.execSQL("CREATE TABLE if not exists Clientes (id_cliente INTEGER PRIMARY KEY, id_usuario INTEGER, nro_afiliado INTEGER, FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario))");
//        db.execSQL("CREATE TABLE if not exists Tipos_transferencias (id_tipo_transferencia INTEGER PRIMARY KEY, tipo_transferencia TEXT)");
//        db.execSQL("CREATE TABLE if not exists Transferencias (id_transferencia INTEGER PRIMARY KEY, id_tipo_transferencia INTEGER, id_cliente INTEGER, fecha TEXT, monto INTEGER, cuenta_envio TEXT, cuenta_recibo TEXT, FOREIGN KEY (id_tipo_transferencia) REFERENCES Tipos_transferencias(id_tipo_transferencia), FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente))");
//        db.execSQL("CREATE TABLE if not exists Tipo_Transaccion (id_tipo_transaccion INTEGER PRIMARY KEY, tipo_transaccion TEXT)");
//        db.execSQL("CREATE TABLE if not exists Transacciones (id_transaccion INTEGER PRIMARY KEY, id_cliente INTEGER, fecha_hora TEXT, id_tipo_transaccion INTEGER, cuenta_origen TEXT, cuenta_destino TEXT, monto INTEGER, descripcion TEXT, FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente), FOREIGN KEY (id_tipo_transaccion) REFERENCES Tipo_Transaccion(id_tipo_transaccion))");
//        db.execSQL("CREATE TABLE if not exists Tipos_cuentas (id_tipo_cuenta INTEGER PRIMARY KEY, tipo_cuenta TEXT)");
//        db.execSQL("CREATE TABLE if not exists Tipo_moneda (id_tipo_moneda INTEGER PRIMARY KEY, tipo_moneda TEXT, codigo_moneda TEXT)");
//        db.execSQL("CREATE TABLE if not exists Tasas_de_Cambio (id_tasa_cambio INTEGER PRIMARY KEY, id_tipo_moneda_origen INTEGER, id_tipo_moneda_destino INTEGER, fecha TEXT, tasa REAL, FOREIGN KEY (id_tipo_moneda_origen) REFERENCES Tipo_moneda(id_tipo_moneda), FOREIGN KEY (id_tipo_moneda_destino) REFERENCES Tipo_moneda(id_tipo_moneda))");
//        db.execSQL("CREATE TABLE if not exists Tipo_estado_cuenta (id_tipo_estado_cuenta INTEGER PRIMARY KEY, tipo_estado_cuenta TEXT)");
//        db.execSQL("CREATE TABLE if not exists Cuenta (id_cuenta INTEGER PRIMARY KEY, id_tipo_estado_cuenta INTEGER, Monto INTEGER, fecha_creacion TEXT, CBU TEXT, Alias TEXT, password TEXT, Credito INTEGER, Debito INTEGER, FOREIGN KEY (id_tipo_estado_cuenta) REFERENCES Tipo_estado_cuenta(id_tipo_estado_cuenta))");
//        db.execSQL("CREATE TABLE if not exists Cliente_Cuenta (cod_cc INTEGER PRIMARY KEY, id_cuenta INTEGER, id_cliente INTEGER, FOREIGN KEY (id_cuenta) REFERENCES Cuenta(id_cuenta), FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente))");
//        db.execSQL("CREATE TABLE if not exists Tipo_empleado (id_tipo_empleado INTEGER PRIMARY KEY, tipo_empleado TEXT)");
//        db.execSQL("CREATE TABLE if not exists Tipo_estado_empleado (id_tipo_estado_empleado INTEGER PRIMARY KEY, tipo_estado_empleado TEXT)");
//        db.execSQL("CREATE TABLE if not exists Tipos_contactos (id_tipo_contacto INTEGER PRIMARY KEY, tipo_contacto TEXT)");
//        db.execSQL("CREATE TABLE if not exists Empleados (id_empleado INTEGER PRIMARY KEY, id_usuario INTEGER, id_tipo_empleado INTEGER, id_tipo_estado_empleado INTEGER, fecha_ingreso TEXT, sueldo REAL, FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario), FOREIGN KEY (id_tipo_empleado) REFERENCES Tipo_empleado(id_tipo_empleado), FOREIGN KEY (id_tipo_estado_empleado) REFERENCES Tipo_estado_empleado(id_tipo_estado_empleado))");
//        db.execSQL("CREATE TABLE if not exists Historial_Acceso_Seguridad (id_registro_acc_seguridad INTEGER PRIMARY KEY, id_usuario INTEGER, fecha_hora TEXT, tipo_evento TEXT, dispositivo TEXT, direccion_ip TEXT, FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario))");
//        db.execSQL("CREATE TABLE if not exists Contactos (id_contactos INTEGER PRIMARY KEY, id_tipo_contacto INTEGER, id_cliente INTEGER, id_empleado INTEGER, contactos TEXT, FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente), FOREIGN KEY (id_empleado) REFERENCES Empleados(id_empleado), FOREIGN KEY (id_tipo_contacto) REFERENCES Tipos_contactos(id_tipo_contacto))");
//        db.execSQL("CREATE TABLE if not exists Tipo_prestamo (id_tipo_prestamo INTEGER PRIMARY KEY, tipo_prestamo TEXT)");
//        db.execSQL("CREATE TABLE if not exists Tipo_estado_prestamo (id_tipo_estado_prestamo INTEGER PRIMARY KEY, tipo_estado_prestamo TEXT)");
//        db.execSQL("CREATE TABLE if not exists Tipo_cuota (id_tipo_cuota INTEGER PRIMARY KEY, tipo_cuota TEXT)");
//        db.execSQL("CREATE TABLE if not exists Tipo_estado_cuota (id_tipo_estado_cuota INTEGER PRIMARY KEY, tipo_estado_cuota TEXT)");
//        db.execSQL("CREATE TABLE if not exists Tipo_interes (id_tipo_interes INTEGER PRIMARY KEY, tipo_interes TEXT)");
//        db.execSQL("CREATE TABLE if not exists Cantidad_cuotas (id_cantidad_cuotas INTEGER PRIMARY KEY, cantidad_cuotas INTEGER)");
//        db.execSQL("CREATE TABLE if not exists Prestamos (id_prestamo INTEGER PRIMARY KEY, id_cliente INTEGER, id_tipo_moneda INTEGER, id_tipo_prestamo INTEGER, id_tipo_estado_prestamo INTEGER, id_tipo_cuota INTEGER, id_tipo_interes INTEGER, id_cantidad_cuotas INTEGER, monto INTEGER, fecha_creacion TEXT, fecha_vencimiento TEXT, fecha_pago TEXT, fecha_cancelacion TEXT, fecha_ultimo_pago TEXT, monto_cuota INTEGER, monto_interes INTEGER, monto_total INTEGER, monto_ultimo_pago INTEGER, monto_cancelado INTEGER, monto_pendiente INTEGER, FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente), FOREIGN KEY (id_tipo_moneda) REFERENCES Tipo_moneda(id_tipo_moneda), FOREIGN KEY (id_tipo_prestamo) REFERENCES Tipo_prestamo(id_tipo_prestamo), FOREIGN KEY (id_tipo_estado_prestamo) REFERENCES Tipo_estado_prestamo(id_tipo_estado_prestamo), FOREIGN KEY (id_tipo_cuota) REFERENCES Tipo_cuota(id_tipo_cuota), FOREIGN KEY (id_tipo_interes) REFERENCES Tipo_interes(id_tipo_interes), FOREIGN KEY (id_cantidad_cuotas) REFERENCES Cantidad_cuotas(id_cantidad_cuotas))");
//        db.execSQL("CREATE TABLE if not exists Cuotas (id_cuota INTEGER PRIMARY KEY, nro_cuota INTEGER, id_prestamo INTEGER, id_tipo_cuota INTEGER, id_tipo_estado_cuota INTEGER, fecha_vencimiento TEXT, fecha_pago TEXT, monto INTEGER, FOREIGN KEY (id_prestamo) REFERENCES Prestamos(id_prestamo), FOREIGN KEY (id_tipo_cuota) REFERENCES Tipo_cuota(id_tipo_cuota), FOREIGN KEY (id_tipo_estado_cuota) REFERENCES Tipo_estado_cuota(id_tipo_estado_cuota))");
//        db.execSQL("CREATE TABLE if not exists Historial_Prestamo (id_historial_prestamo INTEGER PRIMARY KEY, id_prestamo INTEGER, fecha_hora TEXT, descripcion TEXT, FOREIGN KEY (id_prestamo) REFERENCES Prestamos(id_prestamo))");
//        db.execSQL("CREATE TABLE if not exists Tipo_tarjeta (id_tipo_tarjeta INTEGER PRIMARY KEY, tipo_tarjeta TEXT)");
//        db.execSQL("CREATE TABLE if not exists Tipo_estado_tarjeta (id_tipo_estado_tarjeta INTEGER PRIMARY KEY, tipo_estado_tarjeta TEXT)");
//        db.execSQL("CREATE TABLE if not exists Tarjeta (id_tarjeta INTEGER PRIMARY KEY, id_cuenta INTEGER NOT NULL, id_tipo_tarjeta INTEGER NOT NULL, id_tipo_estado_tarjeta INTEGER NOT NULL, fecha_vencimiento DATETIME, numero_tarjeta TEXT, codigo_seguridad TEXT, FOREIGN KEY (id_cuenta) REFERENCES Cuenta(id_cuenta), FOREIGN KEY (id_tipo_tarjeta) REFERENCES Tipo_tarjeta(id_tipo_tarjeta), FOREIGN KEY (id_tipo_estado_tarjeta) REFERENCES Tipo_estado_tarjeta(id_tipo_estado_tarjeta))");
//        db.execSQL("CREATE TABLE if not exists Cuenta_TipoCuenta (cod_ctc INTEGER PRIMARY KEY, id_cuenta INTEGER NOT NULL, id_tipo_cuenta INTEGER NOT NULL, FOREIGN KEY (id_cuenta) REFERENCES Cuenta(id_cuenta), FOREIGN KEY (id_tipo_cuenta) REFERENCES Tipos_cuentas(id_tipo_cuenta))");
//        db.execSQL("CREATE TABLE if not exists Cuenta_TipoMoneda (cod_ctm INTEGER PRIMARY KEY, id_cuenta INTEGER NOT NULL, id_tipo_moneda INTEGER NOT NULL, FOREIGN KEY (id_cuenta) REFERENCES Cuenta(id_cuenta), FOREIGN KEY (id_tipo_moneda) REFERENCES Tipo_moneda(id_tipo_moneda))");
//        db.execSQL("CREATE TABLE if not exists Plazo_fijo (id_plazo_fijo INTEGER PRIMARY KEY, id_cuenta INTEGER NOT NULL, id_tipo_moneda INTEGER NOT NULL, monto INTEGER, interes FLOAT, fecha_creacion DATETIME, fecha_vencimiento DATETIME, FOREIGN KEY (id_cuenta) REFERENCES Cuenta(id_cuenta), FOREIGN KEY (id_tipo_moneda) REFERENCES Tipo_moneda(id_tipo_moneda))");
//        db.execSQL("CREATE TABLE if not exists Historial_Sesiones (id_sesion INTEGER PRIMARY KEY, id_cliente INTEGER NOT NULL, fecha_inicio DATETIME, fecha_fin DATETIME, duracion_sesion INTEGER, acciones_realizadas TEXT, FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente))");
//
//        db.execSQL("CREATE TABLE if not exists Usuarios_admin (cod_usuario INTEGER PRIMARY KEY, id_usuario INTEGER, id_tipo_empleado INTEGER, id_tipo_estado_empleado INTEGER, fecha_ingreso TEXT, sueldo REAL, FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario), FOREIGN KEY (id_tipo_empleado) REFERENCES Tipo_empleado(id_tipo_empleado), FOREIGN KEY (id_tipo_estado_empleado) REFERENCES Tipo_estado_empleado(id_tipo_estado_empleado))");



        db.execSQL("INSERT INTO User (username, password) VALUES ('admin', '1234')");
        db.execSQL("INSERT INTO User (username, password) VALUES ('eze', '1234')");
        db.execSQL("INSERT INTO User (username, password) VALUES ('joaco', '1234')");
        db.execSQL("INSERT INTO User (username, password) VALUES ('valen', '1234')");
        db.execSQL("INSERT INTO User (username, password) VALUES ('user', '1234')");

        db.execSQL("CREATE TABLE if not exists Documentos (id_tipo_doc INTEGER PRIMARY KEY AUTOINCREMENT, tipo_doc TEXT);");
        db.execSQL("CREATE TABLE if not exists Sexos (id_tipo_sexo INTEGER PRIMARY KEY AUTOINCREMENT, tipo TEXT);");
        db.execSQL("CREATE TABLE if not exists paises (cod_pais INTEGER PRIMARY KEY AUTOINCREMENT, pais TEXT);");
        db.execSQL("CREATE TABLE if not exists provincias (cod_provincia INTEGER PRIMARY KEY AUTOINCREMENT, provincia TEXT, cod_pais INTEGER, FOREIGN KEY (cod_pais) REFERENCES paises (cod_pais));");
        db.execSQL("CREATE TABLE if not exists localidades (cod_localidad INTEGER PRIMARY KEY AUTOINCREMENT, localidad TEXT, cod_provincia INTEGER, FOREIGN KEY (cod_provincia) REFERENCES provincias (cod_provincia));");
        db.execSQL("CREATE TABLE if not exists Usuarios (id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, password TEXT, id_tipo_doc INTEGER, nro_doc TEXT, cod_localidad INTEGER, nro_calle INTEGER, calle TEXT, fecha_nac TEXT, id_tipo_sexo INTEGER, FOREIGN KEY (id_tipo_doc) REFERENCES Documentos (id_tipo_doc), FOREIGN KEY (cod_localidad) REFERENCES localidades (cod_localidad), FOREIGN KEY (id_tipo_sexo) REFERENCES Sexos (id_tipo_sexo));");

        insertarPais(db, "Argentina");
        insertarPais(db, "Brasil");
        insertarPais(db, "Chile");
        insertarPais(db, "Uruguay");
        insertarPais(db, "Paraguay");
        insertarPais(db, "Bolivia");
        insertarPais(db, "Peru");
        insertarPais(db, "Ecuador");
        insertarPais(db, "Colombia");
        insertarPais(db, "Venezuela");
        insertarPais(db, "Panama");
        insertarPais(db, "Costa Rica");
        insertarPais(db, "El Salvador");
        insertarPais(db, "Guatemala");
        insertarPais(db, "Honduras");
        insertarPais(db, "Nicaragua");
        insertarPais(db, "Mexico");
        insertarPais(db, "Estados Unidos");
        insertarPais(db, "Canada");
        insertarPais(db, "España");
        insertarPais(db, "Francia");
        insertarPais(db, "Italia");
        insertarPais(db, "Alemania");
        insertarPais(db, "Inglaterra");
        insertarPais(db, "Portugal");
        insertarPais(db, "Rusia");
        insertarPais(db, "China");
        insertarPais(db, "Japon");
        insertarPais(db, "Corea del Sur");
        insertarPais(db, "Australia");
        insertarPais(db, "Nueva Zelanda");
        insertarPais(db, "Sudafrica");
        insertarPais(db, "Otro");

        // Insertar Provincias
        // Arg
        insertarProvincia(db, "Buenos Aires", 1);
        insertarProvincia(db, "Catamarca", 1);
        insertarProvincia(db, "Chaco", 1);
        insertarProvincia(db, "Chubut", 1);
        insertarProvincia(db, "Cordoba", 1);
        insertarProvincia(db, "Corrientes", 1);
        insertarProvincia(db, "Entre Rios", 1);
        insertarProvincia(db, "Formosa", 1);
        insertarProvincia(db, "Jujuy", 1);
        insertarProvincia(db, "La Pampa", 1);
        insertarProvincia(db, "La Rioja", 1);
        insertarProvincia(db, "Mendoza", 1);
        insertarProvincia(db, "Misiones", 1);
        insertarProvincia(db, "Neuquen", 1);
        insertarProvincia(db, "Rio Negro", 1);
        insertarProvincia(db, "Salta", 1);
        insertarProvincia(db, "San Juan", 1);
        insertarProvincia(db, "San Luis", 1);
        insertarProvincia(db, "Santa Cruz", 1);
        insertarProvincia(db, "Santa Fe", 1);
        insertarProvincia(db, "Santiago del Estero", 1);
        insertarProvincia(db, "Tierra del Fuego", 1);
        insertarProvincia(db, "Tucuman", 1);

        // Brasil
        insertarProvincia(db, "Acre", 2);
        insertarProvincia(db, "Alagoas", 2);
        insertarProvincia(db, "Amapa", 2);
        insertarProvincia(db, "Amazonas", 2);
        insertarProvincia(db, "Bahia", 2);
        insertarProvincia(db, "Ceara", 2);
        insertarProvincia(db, "Distrito Federal", 2);
        insertarProvincia(db, "Espirito Santo", 2);
        insertarProvincia(db, "Goias", 2);
        insertarProvincia(db, "Maranhao", 2);
        insertarProvincia(db, "Mato Grosso", 2);
        insertarProvincia(db, "Mato Grosso do Sul", 2);
        insertarProvincia(db, "Minas Gerais", 2);
        insertarProvincia(db, "Para", 2);
        insertarProvincia(db, "Paraiba", 2);
        insertarProvincia(db, "Parana", 2);
        insertarProvincia(db, "Pernambuco", 2);
        insertarProvincia(db, "Piaui", 2);
        insertarProvincia(db, "Rio de Janeiro", 2);
        insertarProvincia(db, "Rio Grande do Norte", 2);
        insertarProvincia(db, "Rio Grande do Sul", 2);
        insertarProvincia(db, "Rondonia", 2);
        insertarProvincia(db, "Roraima", 2);
        insertarProvincia(db, "Santa Catarina", 2);
        insertarProvincia(db, "Sao Paulo", 2);
        insertarProvincia(db, "Sergipe", 2);
        insertarProvincia(db, "Tocantins", 2);

// Chile
        insertarProvincia(db, "Arica y Parinacota", 3);
        insertarProvincia(db, "Tarapaca", 3);
        insertarProvincia(db, "Antofagasta", 3);
        insertarProvincia(db, "Atacama", 3);
        insertarProvincia(db, "Coquimbo", 3);
        insertarProvincia(db, "Valparaiso", 3);
        insertarProvincia(db, "Metropolitana de Santiago", 3);
        insertarProvincia(db, "Libertador General Bernardo O'Higgins", 3);
        insertarProvincia(db, "Maule", 3);
        insertarProvincia(db, "Biobio", 3);
        insertarProvincia(db, "La Araucania", 3);
        insertarProvincia(db, "Los Rios", 3);
        insertarProvincia(db, "Los Lagos", 3);
        insertarProvincia(db, "Aysen del General Carlos Ibanez del Campo", 3);
        insertarProvincia(db, "Magallanes y de la Antartica Chilena", 3);

// Uruguay
        insertarProvincia(db, "Artigas", 4);
        insertarProvincia(db, "Canelones", 4);
        insertarProvincia(db, "Cerro Largo", 4);
        insertarProvincia(db, "Colonia", 4);
        insertarProvincia(db, "Durazno", 4);
        insertarProvincia(db, "Flores", 4);
        insertarProvincia(db, "Florida", 4);
        insertarProvincia(db, "Lavalleja", 4);
        insertarProvincia(db, "Maldonado", 4);
        insertarProvincia(db, "Montevideo", 4);
        insertarProvincia(db, "Paysandu", 4);

// Paraguay
        insertarProvincia(db, "Alto Parana", 5);
        insertarProvincia(db, "Alto Paraguay", 5);
        insertarProvincia(db, "Amambay", 5);
        insertarProvincia(db, "Asuncion", 5);
        insertarProvincia(db, "Boqueron", 5);
        insertarProvincia(db, "Caaguazu", 5);
        insertarProvincia(db, "Caazapa", 5);
        insertarProvincia(db, "Canindeyu", 5);
        insertarProvincia(db, "Central", 5);
        insertarProvincia(db, "Concepcion", 5);
        insertarProvincia(db, "Cordillera", 5);
        insertarProvincia(db, "Guaira", 5);
        insertarProvincia(db, "Itapua", 5);
        insertarProvincia(db, "Misiones", 5);
        insertarProvincia(db, "Neembucu", 5);
        insertarProvincia(db, "Paraguari", 5);
        insertarProvincia(db, "Presidente Hayes", 5);
        insertarProvincia(db, "San Pedro", 5);

// Bolivia
        insertarProvincia(db, "Chuquisaca", 6);
        insertarProvincia(db, "Cochabamba", 6);
        insertarProvincia(db, "El Beni", 6);
        insertarProvincia(db, "La Paz", 6);
        insertarProvincia(db, "Oruro", 6);
        insertarProvincia(db, "Pando", 6);
        insertarProvincia(db, "Potosi", 6);
        insertarProvincia(db, "Santa Cruz", 6);
        insertarProvincia(db, "Tarija", 6);

        // Peru
        insertarProvincia(db, "Amazonas", 7);
        insertarProvincia(db, "Ancash", 7);
        insertarProvincia(db, "Apurimac", 7);
        insertarProvincia(db, "Arequipa", 7);
        insertarProvincia(db, "Ayacucho", 7);
        insertarProvincia(db, "Cajamarca", 7);
        insertarProvincia(db, "Callao", 7);
        insertarProvincia(db, "Cusco", 7);
        insertarProvincia(db, "Huancavelica", 7);
        insertarProvincia(db, "Huanuco", 7);
        insertarProvincia(db, "Ica", 7);
        insertarProvincia(db, "Junin", 7);
        insertarProvincia(db, "La Libertad", 7);
        insertarProvincia(db, "Lambayeque", 7);
        insertarProvincia(db, "Lima", 7);
        insertarProvincia(db, "Loreto", 7);
        insertarProvincia(db, "Madre de Dios", 7);
        insertarProvincia(db, "Moquegua", 7);
        insertarProvincia(db, "Pasco", 7);
        insertarProvincia(db, "Piura", 7);
        insertarProvincia(db, "Puno", 7);
        insertarProvincia(db, "San Martin", 7);
        insertarProvincia(db, "Tacna", 7);
        insertarProvincia(db, "Tumbes", 7);
        insertarProvincia(db, "Ucayali", 7);

// Ecuador
        insertarProvincia(db, "Azuay", 8);
        insertarProvincia(db, "Bolivar", 8);
        insertarProvincia(db, "Quito", 8);
        insertarProvincia(db, "Carchi", 8);

// Colombia
        insertarProvincia(db, "Amazonas", 9);
        insertarProvincia(db, "Antioquia", 9);
        insertarProvincia(db, "Arauca", 9);
        insertarProvincia(db, "Atlantico", 9);
        insertarProvincia(db, "Bogota", 9);
        insertarProvincia(db, "Bolivar", 9);

// Venezuela
        insertarProvincia(db, "Caracas", 10);
        insertarProvincia(db, "Miranda", 10);
        insertarProvincia(db, "Distrito Capital", 10);

// Estados Unidos
        insertarProvincia(db, "Alabama", 18);
        insertarProvincia(db, "Alaska", 18);
        insertarProvincia(db, "Arizona", 18);
        insertarProvincia(db, "Arkansas", 18);
        insertarProvincia(db, "California", 18);
        insertarProvincia(db, "Colorado", 18);
        insertarProvincia(db, "Connecticut", 18);
        insertarProvincia(db, "Delaware", 18);
        insertarProvincia(db, "Florida", 18);
        insertarProvincia(db, "Georgia", 18);
        insertarProvincia(db, "Hawaii", 18);
        insertarProvincia(db, "Idaho", 18);
        insertarProvincia(db, "Illinois", 18);
        insertarProvincia(db, "Indiana", 18);
        insertarProvincia(db, "Iowa", 18);
        insertarProvincia(db, "Kansas", 18);
        insertarProvincia(db, "Kentucky", 18);
        insertarProvincia(db, "Louisiana", 18);
        insertarProvincia(db, "Maine", 18);
        insertarProvincia(db, "Maryland", 18);
        insertarProvincia(db, "Massachusetts", 18);
        insertarProvincia(db, "Michigan", 18);
        insertarProvincia(db, "Minnesota", 18);
        insertarProvincia(db, "Mississippi", 18);
        insertarProvincia(db, "Missouri", 18);
        insertarProvincia(db, "Montana", 18);
        insertarProvincia(db, "Nebraska", 18);
        insertarProvincia(db, "Nevada", 18);
        insertarProvincia(db, "New Hampshire", 18);
        insertarProvincia(db, "New Jersey", 18);
        insertarProvincia(db, "New Mexico", 18);
        insertarProvincia(db, "New York", 18);
        insertarProvincia(db, "North Carolina", 18);
        insertarProvincia(db, "North Dakota", 18);
        insertarProvincia(db, "Ohio", 18);
        insertarProvincia(db, "Oklahoma", 18);
        insertarProvincia(db, "Oregon", 18);
        insertarProvincia(db, "Pennsylvania", 18);
        insertarProvincia(db, "Rhode Island", 18);
        insertarProvincia(db, "South Carolina", 18);
        insertarProvincia(db, "South Dakota", 18);
        insertarProvincia(db, "Tennessee", 18);
        insertarProvincia(db, "Texas", 18);
        insertarProvincia(db, "Utah", 18);
        insertarProvincia(db, "Vermont", 18);
        insertarProvincia(db, "Virginia", 18);
        insertarProvincia(db, "Washington", 18);
        insertarProvincia(db, "West Virginia", 18);
        insertarProvincia(db, "Wisconsin", 18);
        insertarProvincia(db, "Wyoming", 18);

        // Canadá
        insertarProvincia(db, "Alberta", 19);
        insertarProvincia(db, "British Columbia", 19);
        insertarProvincia(db, "Newfoundland", 19);
        insertarProvincia(db, "New Brunswick", 19);
        insertarProvincia(db, "Nova Scotia", 19);
        insertarProvincia(db, "Ontario", 19);
        insertarProvincia(db, "Prince Edward Island", 19);
        insertarProvincia(db, "Quebec", 19);
        insertarProvincia(db, "Saskatchewan", 19);

// México
        insertarProvincia(db, "Aguascalientes", 20);
        insertarProvincia(db, "Baja California", 20);
        insertarProvincia(db, "Baja California Sur", 20);
        insertarProvincia(db, "Campeche", 20);
        insertarProvincia(db, "Chiapas", 20);
        insertarProvincia(db, "Chihuahua", 20);
        insertarProvincia(db, "Coahuila", 20);
        insertarProvincia(db, "Colima", 20);
        insertarProvincia(db, "Distrito Federal", 20);
        insertarProvincia(db, "Durango", 20);
        insertarProvincia(db, "Guanajuato", 20);
        insertarProvincia(db, "Guerrero", 20);
        insertarProvincia(db, "Hidalgo", 20);
        insertarProvincia(db, "Jalisco", 20);
        insertarProvincia(db, "Mexico", 20);
        insertarProvincia(db, "Michoacan", 20);
        insertarProvincia(db, "Morelos", 20);
        insertarProvincia(db, "Nayarit", 20);
        insertarProvincia(db, "Nuevo Leon", 20);
        insertarProvincia(db, "Oaxaca", 20);
        insertarProvincia(db, "Puebla", 20);
        insertarProvincia(db, "Queretaro", 20);
        insertarProvincia(db, "Quintana Roo", 20);
        insertarProvincia(db, "San Luis Potosi", 20);
        insertarProvincia(db, "Sinaloa", 20);
        insertarProvincia(db, "Sonora", 20);
        insertarProvincia(db, "Tabasco", 20);
        insertarProvincia(db, "Tamaulipas", 20);
        insertarProvincia(db, "Tlaxcala", 20);
        insertarProvincia(db, "Veracruz", 20);
        insertarProvincia(db, "Yucatan", 20);
        insertarProvincia(db, "Zacatecas", 20);

// Otros
        insertarProvincia(db, "Otro", 21);
        insertarProvincia(db, "Otro", 22);
        insertarProvincia(db, "Otro", 23);
        insertarProvincia(db, "Otro", 24);
        insertarProvincia(db, "Otro", 25);
        insertarProvincia(db, "Otro", 26);
        insertarProvincia(db, "Otro", 27);
        insertarProvincia(db, "Otro", 28);
        insertarProvincia(db, "Otro", 29);
        insertarProvincia(db, "Otro", 30);
        insertarProvincia(db, "Otro", 31);
        insertarProvincia(db, "Otro", 32);

        // Argentina
        insertarLocalidad(db, "Buenos Aires", 1);
        insertarLocalidad(db, "La Plata", 1);
        insertarLocalidad(db, "Mar del Plata", 1);
        insertarLocalidad(db, "Quilmes", 1);
        insertarLocalidad(db, "Lomas de Zamora", 1); // ID: 5

        insertarLocalidad(db, "San Fernando del Valle de Catamarca", 2); // Catamarca
        insertarLocalidad(db, "Resistencia", 3); // Chaco
        insertarLocalidad(db, "Rawson", 4); // Chubut

        insertarLocalidad(db, "Córdoba", 5);
        insertarLocalidad(db, "Río Cuarto", 5);
        insertarLocalidad(db, "Villa María", 5);
        insertarLocalidad(db, "Villa Carlos Paz", 5);
        insertarLocalidad(db, "Río Tercero", 5); // ID: 13

        insertarLocalidad(db, "Corrientes", 6);
        insertarLocalidad(db, "Paraná", 7); // Entre Ríos
        insertarLocalidad(db, "Formosa", 8);
        insertarLocalidad(db, "San Salvador de Jujuy", 9); // Jujuy
        insertarLocalidad(db, "Santa Rosa", 10); // La Pampa
        insertarLocalidad(db, "La Rioja", 11);
        insertarLocalidad(db, "Mendoza", 12);
        insertarLocalidad(db, "Godoy Cruz", 12);
        insertarLocalidad(db, "Guaymallén", 12);
        insertarLocalidad(db, "Luján de Cuyo", 12);
        insertarLocalidad(db, "San Rafael", 12); // ID: 24
        insertarLocalidad(db, "Posadas", 13); // Misiones
        insertarLocalidad(db, "Neuquén", 14); // Neuquén
        insertarLocalidad(db, "Viedma", 15); // Rio Negro
        insertarLocalidad(db, "Salta", 16);
        insertarLocalidad(db, "San Juan", 17);
        insertarLocalidad(db, "San Luis", 18);
        insertarLocalidad(db, "Río Gallegos", 19); // Santa Cruz
        insertarLocalidad(db, "Santa Fe", 20);
        insertarLocalidad(db, "Rosario", 20);
        insertarLocalidad(db, "Venado Tuerto", 20);
        insertarLocalidad(db, "San Lorenzo", 20);
        insertarLocalidad(db, "San Cristóbal", 20); // ID: 36
        insertarLocalidad(db, "Santiago del Estero", 21);
        insertarLocalidad(db, "Ushuaia", 22); // Tierra del Fuego
        insertarLocalidad(db, "San Miguel de Tucumán", 23); // Tucumán

// Brasil
        insertarLocalidad(db, "Rio Branco", 24); // Acre
        insertarLocalidad(db, "Maceió", 25); // Alagoas
        insertarLocalidad(db, "Macapá", 26); // Amapá
        insertarLocalidad(db, "Amazonas", 27); // Amazonas
        insertarLocalidad(db, "Salvador", 28); // Bahia
        insertarLocalidad(db, "Feira de Santana", 28);
        insertarLocalidad(db, "Vitória da Conquista", 28);
        insertarLocalidad(db, "Camaçari", 28);
        insertarLocalidad(db, "Ilhéus", 28); // ID: 48
        insertarLocalidad(db, "Fortaleza", 29); // Ceará
        insertarLocalidad(db, "Caucaia", 29);
        insertarLocalidad(db, "Sobral", 29);
        insertarLocalidad(db, "Juazeiro do Norte", 29);
        insertarLocalidad(db, "Maracanaú", 29); // ID: 53
        insertarLocalidad(db, "Brasilia", 30); // Distrito Federal
        insertarLocalidad(db, "Vitória", 31); // Espírito Santo
        insertarLocalidad(db, "Goiânia", 32); // Goiás
        insertarLocalidad(db, "Aparecida de Goiânia", 32);
        insertarLocalidad(db, "Anápolis", 32);
        insertarLocalidad(db, "Rio Verde", 32);
        insertarLocalidad(db, "Catalão", 32); // ID: 60
        insertarLocalidad(db, "São Luís", 33); // Maranhão
        insertarLocalidad(db, "Cuiabá", 34); // Mato Grosso
        insertarLocalidad(db, "Várzea Grande", 34);
        insertarLocalidad(db, "Rondonópolis", 34);
        insertarLocalidad(db, "Cáceres", 34); // ID: 65
        insertarLocalidad(db, "Campo Grande", 35); // Mato Grosso do Sul
        insertarLocalidad(db, "Dourados", 35);
        insertarLocalidad(db, "Três Lagoas", 35); // ID: 68
        insertarLocalidad(db, "Belo Horizonte", 36); // Minas Gerais
        insertarLocalidad(db, "Uberlândia", 36);
        insertarLocalidad(db, "Contagem", 36);
        insertarLocalidad(db, "Juiz de Fora", 36);
        insertarLocalidad(db, "Betim", 36); // ID: 73
        insertarLocalidad(db, "Belém", 37); // Pará
        insertarLocalidad(db, "Santarém", 37);
        insertarLocalidad(db, "Ananindeua", 37);
        insertarLocalidad(db, "Marabá", 37);
        insertarLocalidad(db, "Castanhal", 37); // ID: 78
        insertarLocalidad(db, "João Pessoa", 38); // Paraíba
        insertarLocalidad(db, "Campina Grande", 38); // ID: 80
        insertarLocalidad(db, "Curitiba", 39); // Paraná
        insertarLocalidad(db, "Londrina", 39);
        insertarLocalidad(db, "Maringá", 39);
        insertarLocalidad(db, "Ponta Grossa", 39); // ID: 84
        insertarLocalidad(db, "Recife", 40); // Pernambuco
        insertarLocalidad(db, "Jaboatão dos Guararapes", 40);
        insertarLocalidad(db, "Caruaru", 40);
        insertarLocalidad(db, "Olinda", 40);
        insertarLocalidad(db, "Paulista", 40); // ID: 89
        insertarLocalidad(db, "Teresina", 41); // Piauí
        insertarLocalidad(db, "Parnaíba", 41); // ID: 91
        insertarLocalidad(db, "Rio de Janeiro", 42); // Rio de Janeiro
        insertarLocalidad(db, "São Gonçalo", 42);
        insertarLocalidad(db, "Duque de Caxias", 42);
        insertarLocalidad(db, "Nova Iguaçu", 42);
        insertarLocalidad(db, "Niterói", 42); // ID: 97
        insertarLocalidad(db, "Natal", 43); // Rio Grande do Norte
        insertarLocalidad(db, "Mossoró", 43); // ID: 99
        insertarLocalidad(db, "Porto Alegre", 44); // Rio Grande do Sul
        insertarLocalidad(db, "Caxias do Sul", 44);
        insertarLocalidad(db, "Pelotas", 44);
        insertarLocalidad(db, "Canoas", 44);
        insertarLocalidad(db, "Santa Maria", 44); // ID: 104
        insertarLocalidad(db, "Porto Velho", 45); // Rondônia
        insertarLocalidad(db, "Boa Vista", 46); // Roraima
        insertarLocalidad(db, "Florianópolis", 47); // Santa Catarina
        insertarLocalidad(db, "Joinville", 47);
        insertarLocalidad(db, "Blumenau", 47);
        insertarLocalidad(db, "São José", 47);
        insertarLocalidad(db, "Criciúma", 47); // ID: 110
        insertarLocalidad(db, "São Paulo", 48); // São Paulo
        insertarLocalidad(db, "Guarulhos", 48);
        insertarLocalidad(db, "Campinas", 48);
        insertarLocalidad(db, "São Bernardo do Campo", 48);
        insertarLocalidad(db, "Santo André", 48); // ID: 115
        insertarLocalidad(db, "Aracaju", 49); // Sergipe
        insertarLocalidad(db, "Palmas", 50); // Tocantins

// Estados Unidos
        insertarLocalidad(db, "Birmingham", 51); // Alabama
        insertarLocalidad(db, "Montgomery", 51);
        insertarLocalidad(db, "Mobile", 51);
        insertarLocalidad(db, "Huntsville", 51);
        insertarLocalidad(db, "Tuscaloosa", 51); // ID: 120
        insertarLocalidad(db, "Anchorage", 52); // Alaska
        insertarLocalidad(db, "Fairbanks", 52);
        insertarLocalidad(db, "Juneau", 52);
        insertarLocalidad(db, "Sitka", 52);
        insertarLocalidad(db, "Ketchikan", 52); // ID: 125
        insertarLocalidad(db, "Phoenix", 53); // Arizona
        insertarLocalidad(db, "Tucson", 53);
        insertarLocalidad(db, "Mesa", 53);
        insertarLocalidad(db, "Chandler", 53);
        insertarLocalidad(db, "Scottsdale", 53); // ID: 130
        insertarLocalidad(db, "Little Rock", 54); // Arkansas
        insertarLocalidad(db, "Fort Smith", 54);
        insertarLocalidad(db, "Fayetteville", 54);
        insertarLocalidad(db, "Springdale", 54);
        insertarLocalidad(db, "Jonesboro", 54); // ID: 135
        insertarLocalidad(db, "Los Angeles", 55); // California
        insertarLocalidad(db, "San Diego", 55);
        insertarLocalidad(db, "San Jose", 55);
        insertarLocalidad(db, "San Francisco", 55);
        insertarLocalidad(db, "Fresno", 55); // ID: 140
        insertarLocalidad(db, "Denver", 56); // Colorado
        insertarLocalidad(db, "Colorado Springs", 56);
        insertarLocalidad(db, "Aurora", 56);
        insertarLocalidad(db, "Fort Collins", 56);
        insertarLocalidad(db, "Lakewood", 56); // ID: 145
        insertarLocalidad(db, "Bridgeport", 57); // Connecticut
        insertarLocalidad(db, "New Haven", 57);
        insertarLocalidad(db, "Hartford", 57);
        insertarLocalidad(db, "Stamford", 57);
        insertarLocalidad(db, "Waterbury", 57); // ID: 150
        insertarLocalidad(db, "Wilmington", 58); // Delaware
        insertarLocalidad(db, "Dover", 58);
        insertarLocalidad(db, "Newark", 58);
        insertarLocalidad(db, "Middletown", 58);
        insertarLocalidad(db, "Smyrna", 58); // ID: 155
        insertarLocalidad(db, "Jacksonville", 59); // Florida
        insertarLocalidad(db, "Miami", 59);
        insertarLocalidad(db, "Tampa", 59);
        insertarLocalidad(db, "Orlando", 59);
        insertarLocalidad(db, "St. Petersburg", 59); // ID: 160
        insertarLocalidad(db, "Atlanta", 60); // Georgia
        insertarLocalidad(db, "Augusta", 60);
        insertarLocalidad(db, "Columbus", 60);
        insertarLocalidad(db, "Savannah", 60);
        insertarLocalidad(db, "Athens", 60); // ID: 165
        insertarLocalidad(db, "Honolulu", 61); // Hawaii
        insertarLocalidad(db, "Pearl City", 61);
        insertarLocalidad(db, "Hilo", 61);
        insertarLocalidad(db, "Kailua", 61);
        insertarLocalidad(db, "Waipahu", 61); // ID: 170
        insertarLocalidad(db, "Boise", 62); // Idaho
        insertarLocalidad(db, "Nampa", 62);
        insertarLocalidad(db, "Meridian", 62);
        insertarLocalidad(db, "Idaho Falls", 62);
        insertarLocalidad(db, "Pocatello", 62); // ID: 175
        insertarLocalidad(db, "Chicago", 63); // Illinois
        insertarLocalidad(db, "Aurora", 63);
        insertarLocalidad(db, "Rockford", 63);
        insertarLocalidad(db, "Joliet", 63);
        insertarLocalidad(db, "Naperville", 63); // ID: 180
        insertarLocalidad(db, "Indianapolis", 64); // Indiana
        insertarLocalidad(db, "Fort Wayne", 64);
        insertarLocalidad(db, "Evansville", 64);
        insertarLocalidad(db, "South Bend", 64);
        insertarLocalidad(db, "Carmel", 64); // ID: 185
        insertarLocalidad(db, "Des Moines", 65); // Iowa
        insertarLocalidad(db, "Cedar Rapids", 65);
        insertarLocalidad(db, "Davenport", 65);
        insertarLocalidad(db, "Sioux City", 65);
        insertarLocalidad(db, "Iowa City", 65); // ID: 190
        insertarLocalidad(db, "Wichita", 66); // Kansas
        insertarLocalidad(db, "Overland Park", 66);
        insertarLocalidad(db, "Kansas City", 66);
        insertarLocalidad(db, "Topeka", 66);
        insertarLocalidad(db, "Olathe", 66); // ID: 195
        insertarLocalidad(db, "Louisville", 67); // Kentucky
        insertarLocalidad(db, "Lexington", 67);
        insertarLocalidad(db, "Bowling Green", 67);
        insertarLocalidad(db, "Owensboro", 67);
        insertarLocalidad(db, "Covington", 67); // ID: 200
        insertarLocalidad(db, "New Orleans", 68); // Luisiana
        insertarLocalidad(db, "Baton Rouge", 68);
        insertarLocalidad(db, "Shreveport", 68);
        insertarLocalidad(db, "Lafayette", 68);
        insertarLocalidad(db, "Lake Charles", 68); // ID: 205
        insertarLocalidad(db, "Portland", 69); // Maine
        insertarLocalidad(db, "Lewiston", 69);
        insertarLocalidad(db, "Bangor", 69);
        insertarLocalidad(db, "South Portland", 69);
        insertarLocalidad(db, "Auburn", 69); // ID: 210
        insertarLocalidad(db, "Baltimore", 70); // Maryland
        insertarLocalidad(db, "Columbia", 70);
        insertarLocalidad(db, "Germantown", 70);
        insertarLocalidad(db, "Silver Spring", 70);
        insertarLocalidad(db, "Waldorf", 70); // ID: 215
        insertarLocalidad(db, "Boston", 71); // Massachusetts
        insertarLocalidad(db, "Worcester", 71);
        insertarLocalidad(db, "Springfield", 71);
        insertarLocalidad(db, "Lowell", 71);
        insertarLocalidad(db, "Cambridge", 71); // ID: 220
        insertarLocalidad(db, "Detroit", 72); // Michigan
        insertarLocalidad(db, "Grand Rapids", 72);
        insertarLocalidad(db, "Warren", 72);
        insertarLocalidad(db, "Sterling Heights", 72);
        insertarLocalidad(db, "Lansing", 72); // ID: 225
        insertarLocalidad(db, "Minneapolis", 73); // Minnesota
        insertarLocalidad(db, "Saint Paul", 73);
        insertarLocalidad(db, "Rochester", 73);
        insertarLocalidad(db, "Bloomington", 73);
        insertarLocalidad(db, "Duluth", 73); // ID: 230
        insertarLocalidad(db, "Jackson", 74); // Mississippi
        insertarLocalidad(db, "Gulfport", 74);
        insertarLocalidad(db, "Southaven", 74);
        insertarLocalidad(db, "Hattiesburg", 74);
        insertarLocalidad(db, "Biloxi", 74); // ID: 235
        insertarLocalidad(db, "Kansas City", 75); // Misuri
        insertarLocalidad(db, "Saint Louis", 75);
        insertarLocalidad(db, "Springfield", 75);
        insertarLocalidad(db, "Independence", 75);
        insertarLocalidad(db, "Columbia", 75); // ID: 240
        insertarLocalidad(db, "Billings", 76); // Montana
        insertarLocalidad(db, "Missoula", 76);
        insertarLocalidad(db, "Great Falls", 76);
        insertarLocalidad(db, "Bozeman", 76);
        insertarLocalidad(db, "Butte", 76); // ID: 245
        insertarLocalidad(db, "Omaha", 77); // Nebraska
        insertarLocalidad(db, "Lincoln", 77);
        insertarLocalidad(db, "Bellevue", 77);
        insertarLocalidad(db, "Grand Island", 77);
        insertarLocalidad(db, "Kearney", 77); // ID: 250
        insertarLocalidad(db, "Las Vegas", 78); // Nevada
        insertarLocalidad(db, "Henderson", 78);
        insertarLocalidad(db, "Reno", 78);
        insertarLocalidad(db, "North Las Vegas", 78);
        insertarLocalidad(db, "Sparks", 78); // ID: 255
        insertarLocalidad(db, "Manchester", 79); // New Hampshire
        insertarLocalidad(db, "Nashua", 79);
        insertarLocalidad(db, "Concord", 79);
        insertarLocalidad(db, "Derry", 79);
        insertarLocalidad(db, "Rochester", 79); // ID: 260
        insertarLocalidad(db, "Newark", 80); // New Jersey
        insertarLocalidad(db, "Jersey City", 80);
        insertarLocalidad(db, "Paterson", 80);
        insertarLocalidad(db, "Elizabeth", 80);
        insertarLocalidad(db, "Edison", 80); // ID: 265
        insertarLocalidad(db, "Albuquerque", 81); // New Mexico
        insertarLocalidad(db, "Las Cruces", 81);
        insertarLocalidad(db, "Rio Rancho", 81);
        insertarLocalidad(db, "Santa Fe", 81);
        insertarLocalidad(db, "Roswell", 81); // ID: 270
        insertarLocalidad(db, "New York", 82); // New York
        insertarLocalidad(db, "Buffalo", 82);
        insertarLocalidad(db, "Rochester", 82);
        insertarLocalidad(db, "Yonkers", 82);
        insertarLocalidad(db, "Syracuse", 82); // ID: 275
        insertarLocalidad(db, "Charlotte", 83); // North Carolina
        insertarLocalidad(db, "Raleigh", 83);
        insertarLocalidad(db, "Greensboro", 83);
        insertarLocalidad(db, "Durham", 83);
        insertarLocalidad(db, "Winston-Salem", 83); // ID: 280
        insertarLocalidad(db, "Fargo", 84); // North Dakota
        insertarLocalidad(db, "Bismarck", 84);
        insertarLocalidad(db, "Grand Forks", 84);
        insertarLocalidad(db, "Minot", 84);
        insertarLocalidad(db, "West Fargo", 84); // ID: 285
        insertarLocalidad(db, "Columbus", 85); // Ohio
        insertarLocalidad(db, "Cleveland", 85);
        insertarLocalidad(db, "Cincinnati", 85);
        insertarLocalidad(db, "Toledo", 85);
        insertarLocalidad(db, "Akron", 85); // ID: 290
        insertarLocalidad(db, "Oklahoma City", 86); // Oklahoma
        insertarLocalidad(db, "Tulsa", 86);
        insertarLocalidad(db, "Norman", 86);
        insertarLocalidad(db, "Broken Arrow", 86);
        insertarLocalidad(db, "Lawton", 86); // ID: 295
        insertarLocalidad(db, "Portland", 87); // Oregon
        insertarLocalidad(db, "Salem", 87);
        insertarLocalidad(db, "Eugene", 87);
        insertarLocalidad(db, "Gresham", 87);
        insertarLocalidad(db, "Hillsboro", 87); // ID: 300
        insertarLocalidad(db, "Philadelphia", 88); // Pennsylvania
        insertarLocalidad(db, "Pittsburgh", 88);
        insertarLocalidad(db, "Allentown", 88);
        insertarLocalidad(db, "Erie", 88);
        insertarLocalidad(db, "Reading", 88); // ID: 305
        insertarLocalidad(db, "Providence", 89); // Rhode Island
        insertarLocalidad(db, "Warwick", 89);
        insertarLocalidad(db, "Cranston", 89);
        insertarLocalidad(db, "Pawtucket", 89);
        insertarLocalidad(db, "East Providence", 89); // ID: 310
        insertarLocalidad(db, "Columbia", 90); // South Carolina
        insertarLocalidad(db, "Charleston", 90);
        insertarLocalidad(db, "North Charleston", 90);
        insertarLocalidad(db, "Mount Pleasant", 90);
        insertarLocalidad(db, "Rock Hill", 90); // ID: 315
        insertarLocalidad(db, "Sioux Falls", 91); // South Dakota
        insertarLocalidad(db, "Rapid City", 91);
        insertarLocalidad(db, "Aberdeen", 91);
        insertarLocalidad(db, "Brookings", 91);
        insertarLocalidad(db, "Watertown", 91); // ID: 320
        insertarLocalidad(db, "Nashville", 92); // Tennessee
        insertarLocalidad(db, "Memphis", 92);
        insertarLocalidad(db, "Knoxville", 92);
        insertarLocalidad(db, "Chattanooga", 92);
        insertarLocalidad(db, "Clarksville", 92); // ID: 325
        insertarLocalidad(db, "Houston", 93); // Texas
        insertarLocalidad(db, "San Antonio", 93);
        insertarLocalidad(db, "Dallas", 93);
        insertarLocalidad(db, "Austin", 93);
        insertarLocalidad(db, "Fort Worth", 93); // ID: 330
        insertarLocalidad(db, "Salt Lake City", 94); // Utah
        insertarLocalidad(db, "West Valley City", 94);
        insertarLocalidad(db, "Provo", 94);
        insertarLocalidad(db, "West Jordan", 94);
        insertarLocalidad(db, "Orem", 94); // ID: 335
        insertarLocalidad(db, "Burlington", 95); // Vermont
        insertarLocalidad(db, "Essex", 95);
        insertarLocalidad(db, "South Burlington", 95);
        insertarLocalidad(db, "Colchester", 95);
        insertarLocalidad(db, "Rutland", 95); // ID: 340
        insertarLocalidad(db, "Virginia Beach", 96); // Virginia
        insertarLocalidad(db, "Norfolk", 96);
        insertarLocalidad(db, "Chesapeake", 96);
        insertarLocalidad(db, "Richmond", 96);
        insertarLocalidad(db, "Newport News", 96); // ID: 345
        insertarLocalidad(db, "Seattle", 97); // Washington
        insertarLocalidad(db, "Spokane", 97);
        insertarLocalidad(db, "Tacoma", 97);
        insertarLocalidad(db, "Vancouver", 97);
        insertarLocalidad(db, "Bellevue", 97); // ID: 350
        insertarLocalidad(db, "Charleston", 98); // West Virginia
        insertarLocalidad(db, "Huntington", 98);
        insertarLocalidad(db, "Parkersburg", 98);
        insertarLocalidad(db, "Morgantown", 98);
        insertarLocalidad(db, "Wheeling", 98); // ID: 355
        insertarLocalidad(db, "Milwaukee", 99); // Wisconsin
        insertarLocalidad(db, "Madison", 99);
        insertarLocalidad(db, "Green Bay", 99);
        insertarLocalidad(db, "Kenosha", 99);
        insertarLocalidad(db, "Racine", 99); // ID: 360
        insertarLocalidad(db, "Cheyenne", 100); // Wyoming
        insertarLocalidad(db, "Casper", 100);
        insertarLocalidad(db, "Laramie", 100);
        insertarLocalidad(db, "Gillette", 100);
        insertarLocalidad(db, "Rock Springs", 100); // ID: 365

        insertarDocumento(db, "Dni");
        insertarDocumento(db, "Pasaporte");
        insertarDocumento(db, "Obra social");
        insertarDocumento(db, "Licencia de conducir");

        insertarSexo(db, "Hombre");
        insertarSexo(db, "Mujer");
        insertarSexo(db, "Otro");

        insertarUsuario(db, "Ana", "López", "12345", 1, "12345678", 1, 101, "Calle Principal", "1990-04-15", 2);
        insertarUsuario(db, "Juan", "Martínez", "12345", 2, "87654321", 2, 202, "Avenida Secundaria", "1985-07-20", 1);
        insertarUsuario(db, "María", "González", "12345", 1, "98765432", 3, 303, "Calle del Centro", "1992-02-10", 2);
        insertarUsuario(db, "Carlos", "Rodríguez", "12345", 2, "56789012", 1, 404, "Calle Residencial", "1998-05-05", 1);
        insertarUsuario(db, "Luisa", "Sánchez", "12345", 1, "34567890", 2, 505, "Avenida Principal", "1987-11-30", 2);
        insertarUsuario(db, "Javier", "Pérez", "12345", 2, "23456789", 3, 606, "Calle del Parque", "1994-08-25", 1);
        insertarUsuario(db, "Sofía", "Fernández", "12345", 1, "43210987", 1, 707, "Avenida Central", "1991-01-18", 2);
        insertarUsuario(db, "Diego", "Ramírez", "12345", 2, "54321098", 2, 808, "Calle Comercial", "1996-03-02", 1);
        insertarUsuario(db, "Lucía", "Torres", "12345", 1, "67890123", 3, 909, "Avenida Residencial", "1988-06-10", 2);
        insertarUsuario(db, "Mateo", "Luna", "12345", 2, "45678901", 1, 1010, "Calle de la Plaza", "1993-12-08", 1);


////        Paises
//        db.execSQL("INSERT INTO paises (pais) VALUES ('Argentina')");
//        db.execSQL("INSERT INTO paises (pais) VALUES ('Brasil')");
//        db.execSQL("INSERT INTO paises (pais) VALUES ('Chile')");
//        db.execSQL("INSERT INTO paises (pais) VALUES ('Uruguay')");
//
////        Provincias
//        db.execSQL("INSERT INTO provincias (provincia, cod_pais) VALUES ('Buenos Aires', 1)");
//        db.execSQL("INSERT INTO provincias (provincia, cod_pais) VALUES ('Catamarca', 1)");
//        db.execSQL("INSERT INTO provincias (provincia, cod_pais) VALUES ('Chaco', 1)");
//        db.execSQL("INSERT INTO provincias (provincia, cod_pais) VALUES ('Chubut', 1)");
//        db.execSQL("INSERT INTO provincias (provincia, cod_pais) VALUES ('Cordoba', 1)");
//
//        db.execSQL("INSERT INTO provincias (provincia, cod_pais) VALUES ('Some Province in Brazil', 2)");
//        db.execSQL("INSERT INTO provincias (provincia, cod_pais) VALUES ('Some Province in Chile', 3)");
//        db.execSQL("INSERT INTO provincias (provincia, cod_pais) VALUES ('Some Province in Uruguay', 4)");
//
////        Localidades
//        db.execSQL("INSERT INTO localidades (localidad, cod_provincia) VALUES ('Buenos Aires', 1)");
//        db.execSQL("INSERT INTO localidades (localidad, cod_provincia) VALUES ('La Plata', 1)");
//        db.execSQL("INSERT INTO localidades (localidad, cod_provincia) VALUES ('Mar del Plata', 1)");
//
//        db.execSQL("INSERT INTO localidades (localidad, cod_provincia) VALUES ('City in Catamarca', (SELECT cod_provincia FROM provincias WHERE provincia = 'Catamarca'))");
//        db.execSQL("INSERT INTO localidades (localidad, cod_provincia) VALUES ('City in Chaco', (SELECT cod_provincia FROM provincias WHERE provincia = 'Chaco'))");
//        db.execSQL("INSERT INTO localidades (localidad, cod_provincia) VALUES ('City in Chubut', (SELECT cod_provincia FROM provincias WHERE provincia = 'Chubut'))");
//        db.execSQL("INSERT INTO localidades (localidad, cod_provincia) VALUES ('City in Cordoba', (SELECT cod_provincia FROM provincias WHERE provincia = 'Cordoba'))");
//        db.execSQL("INSERT INTO localidades (localidad, cod_provincia) VALUES ('Some City in Brazil', (SELECT cod_provincia FROM provincias WHERE provincia = 'Some Province in Brazil'))");
//        db.execSQL("INSERT INTO localidades (localidad, cod_provincia) VALUES ('Some City in Chile', (SELECT cod_provincia FROM provincias WHERE provincia = 'Some Province in Chile'))");
//        db.execSQL("INSERT INTO localidades (localidad, cod_provincia) VALUES ('Some City in Uruguay', (SELECT cod_provincia FROM provincias WHERE provincia = 'Some Province in Uruguay'))");
//
////        Documentos
//        db.execSQL("INSERT INTO Documentos (tipo_doc) VALUES ('Dni')");
//        db.execSQL("INSERT INTO Documentos (tipo_doc) VALUES ('Pasaporte')");
//        db.execSQL("INSERT INTO Documentos (tipo_doc) VALUES ('Obra social')");
//        db.execSQL("INSERT INTO Documentos (tipo_doc) VALUES ('Licencia de conducir')");
//
////        Sexos
//        db.execSQL("INSERT INTO Sexos (tipo) VALUES ('Hombre')");
//        db.execSQL("INSERT INTO Sexos (tipo) VALUES ('Mujer')");
//        db.execSQL("INSERT INTO Sexos (tipo) VALUES ('Otro')");
//
////        Usuarios
//        db.execSQL("INSERT INTO Usuarios (nombre, apellido, username, password, id_tipo_doc, nro_doc, cod_localidad, nro_calle, calle, fecha_nac, id_tipo_sexo) " +
//                "VALUES ('Ana', 'López', 'analopez', '12345', 1, '12345678', 1, 101, 'Calle Principal', '1990-04-15', 2)");
//        String insertMultipleUsersQuery = "INSERT INTO Usuarios (nombre, apellido, username, password, id_tipo_doc, nro_doc, cod_localidad, nro_calle, calle, fecha_nac, id_tipo_sexo) " +
//                "VALUES " +
//                "('Juan', 'Martínez', 'juanmartinez', '12345', 2, '87654321', 2, 202, 'Avenida Secundaria', '1985-07-20', 1), " +
//                "('María', 'González', 'mariagonzalez', '12345', 1, '98765432', 3, 303, 'Calle del Centro', '1992-02-10', 2), " +
//                "('Carlos', 'Rodríguez', 'carlosrodriguez', '12345', 2, '56789012', 1, 404, 'Calle Residencial', '1998-05-05', 1), " +
//                "('Luisa', 'Sánchez', 'luisasanchez', '12345', 1, '34567890', 2, 505, 'Avenida Principal', '1987-11-30', 2), " +
//                "('Javier', 'Pérez', 'javierperez', '12345', 2, '23456789', 3, 606, 'Calle del Parque', '1994-08-25', 1), " +
//                "('Sofía', 'Fernández', 'sofiafernandez', '12345', 1, '43210987', 1, 707, 'Avenida Central', '1991-01-18', 2), " +
//                "('Diego', 'Ramírez', 'diegoramirez', '12345', 2, '54321098', 2, 808, 'Calle Comercial', '1996-03-02', 1), " +
//                "('Lucía', 'Torres', 'luciatorres', '12345', 1, '67890123', 3, 909, 'Avenida Residencial', '1988-06-10', 2), " +
//                "('Mateo', 'Luna', 'mateoluna', '12345', 2, '45678901', 1, 1010, 'Calle de la Plaza', '1993-12-08', 1)";
//        db.execSQL(insertMultipleUsersQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // En caso de actualizaciones futuras de la base de datos, puedes realizar cambios en las tablas aquí.

    }

    private void insertarPais(SQLiteDatabase db, String pais) {
        ContentValues values = new ContentValues();
        values.put("pais", pais);
        db.insert("paises", null, values);
    }

    private void insertarProvincia(SQLiteDatabase db, String provincia, int codPais) {
        ContentValues values = new ContentValues();
        values.put("provincia", provincia);
        values.put("cod_pais", codPais);
        db.insert("provincias", null, values);
    }

    private void insertarLocalidad(SQLiteDatabase db, String localidad, int codProvincia) {
        ContentValues values = new ContentValues();
        values.put("localidad", localidad);
        values.put("cod_provincia", codProvincia);
        db.insert("localidades", null, values);
    }

    private void insertarDocumento(SQLiteDatabase db, String tipoDocumento) {
        ContentValues values = new ContentValues();
        values.put("tipo_doc", tipoDocumento);
        db.insert("Documentos", null, values);
    }

    private void insertarSexo(SQLiteDatabase db, String tipoSexo) {
        ContentValues values = new ContentValues();
        values.put("tipo", tipoSexo);
        db.insert("Sexos", null, values);
    }

    private void insertarUsuario(SQLiteDatabase db, String nombre, String apellido, String password, int idTipoDoc, String nroDoc, int codLocalidad, int nroCalle, String calle, String fechaNac, int idTipoSexo) {
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellido", apellido);
        values.put("password", password);
        values.put("id_tipo_doc", idTipoDoc);
        values.put("nro_doc", nroDoc);
        values.put("cod_localidad", codLocalidad);
        values.put("nro_calle", nroCalle);
        values.put("calle", calle);
        values.put("fecha_nac", fechaNac);
        values.put("id_tipo_sexo", idTipoSexo);
        db.insert("Usuarios", null, values);
    }

    public void insertarUsuario(String nombre, String apellido, String password, int tipoDoc, String nroDoc, int localidad, int nroCalle, String calle, String fechaNac, int tipoSexo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellido", apellido);
        values.put("password", password);
        values.put("id_tipo_doc", tipoDoc);
        values.put("nro_doc", nroDoc);
        values.put("cod_localidad", localidad);
        values.put("nro_calle", nroCalle);
        values.put("calle", calle);
        values.put("fecha_nac", fechaNac);
        values.put("id_tipo_sexo", tipoSexo);

        db.insert("Usuarios", null, values);
        db.close();
    }

    public void actualizarUsuario(int id, String nombre, String apellido, String password, int tipoDoc, String nroDoc, int localidad, int nroCalle, String calle, String fechaNac, int tipoSexo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellido", apellido);
        values.put("password", password);
        values.put("id_tipo_doc", tipoDoc);
        values.put("nro_doc", nroDoc);
        values.put("cod_localidad", localidad);
        values.put("nro_calle", nroCalle);
        values.put("calle", calle);
        values.put("fecha_nac", fechaNac);
        values.put("id_tipo_sexo", tipoSexo);

        db.update("Usuarios", values, "id_usuario" + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public int buscarUsuarioId(String nombre, String apellido, String nroDoc) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("Usuarios", new String[]{"id_usuario"},
                "nombre" + "=? AND " + "nro_doc" + "=?",
                new String[]{nombre, nroDoc}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int userId = cursor.getInt(cursor.getColumnIndex("id_usuario"));
            cursor.close();
            return userId;
        }

        return -1; // Return -1 if user is not found
    }

    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT u.*, d.tipo_doc AS DocNombre, l.localidad AS LocalidadNombre, s.tipo AS SexoNombre " +
                "FROM Usuarios u " +
                "JOIN Documentos d ON u.id_tipo_doc = d.id_tipo_doc " +
                "JOIN Localidades l ON u.cod_localidad = l.cod_localidad " +
                "JOIN Sexos s ON u.id_tipo_sexo = s.id_tipo_sexo";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Usuario usuario = new Usuario(
                        cursor.getInt(cursor.getColumnIndex("id_usuario")),
                        cursor.getString(cursor.getColumnIndex("nombre")),
                        cursor.getString(cursor.getColumnIndex("apellido")),
                        cursor.getString(cursor.getColumnIndex("password")),
                        cursor.getString(cursor.getColumnIndex("DocNombre")),
                        cursor.getString(cursor.getColumnIndex("nro_doc")),
                        cursor.getString(cursor.getColumnIndex("LocalidadNombre")),
                        cursor.getInt(cursor.getColumnIndex("nro_calle")),
                        cursor.getString(cursor.getColumnIndex("calle")),
                        cursor.getString(cursor.getColumnIndex("fecha_nac")),
                        cursor.getString(cursor.getColumnIndex("SexoNombre"))
                );
                usuarios.add(usuario);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return usuarios;
    }

    public List<String> getAllLabels(String columnName, String tableName) {
        List<String> labels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + columnName + " FROM " + tableName + " ORDER BY " + columnName, null);

        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return labels;
    }

    public List<Integer> getAllIds(String COLUMN_ID, String tableName) {
        List<Integer> ids = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_ID + " FROM " + tableName, null);

        if (cursor.moveToFirst()) {
            do {
                ids.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return ids;
    }

    public Usuarios findUserByUsernameAndPassword(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Usuarios usuario = null;

        String[] columns = {
                "id_usuario",
                "nombre",
                "apellido",
                "username",
                "password",
                "id_tipo_doc",
                "nro_doc",
                "cod_localidad",
                "nro_calle",
                "calle",
                "fecha_nac",
                "id_tipo_sexo"
        };

        String selection = "SELECT username, password FROM Usuarios WHERE username='"+
        username+"' and password='"+password+"'";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.rawQuery(selection, null);

        if (cursor != null && cursor.moveToFirst()) {
            usuario = new Usuarios(
                    cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getInt(5),
                    cursor.getString(6), cursor.getInt(7), cursor.getInt(8),
                    cursor.getString(9), cursor.getString(10), cursor.getInt(11)
            );
            cursor.close();
        }

        return usuario;
    }

    public Boolean insertData(String nombre, String apellido, String username, String password, int idTipoDoc, String nroDoc, int codLocalidad, int nroCalle, String calle, String fechaNac, int idTipoSexo) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("nombre", nombre);
        contentValues.put("apellido", apellido);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("id_tipo_doc", idTipoDoc);
        contentValues.put("nro_doc", nroDoc);
        contentValues.put("cod_localidad", codLocalidad);
        contentValues.put("nro_calle", nroCalle);
        contentValues.put("calle", calle);
        contentValues.put("fecha_nac", fechaNac);
        contentValues.put("id_tipo_sexo", idTipoSexo);

        long result = MyDatabase.insert("Usuarios", null, contentValues);

        if (result == -1) {
            return false; // Insertion failed
        } else {
            return true; // Insertion successful
        }
    }



}