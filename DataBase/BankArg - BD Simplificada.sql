CREATE DATABASE BankArgAndroidDemo;

USE BankArgAndroidDemo;


-- Tabla de Usuarios

CREATE TABLE Usuarios (

  id_usuario BIGINT PRIMARY KEY AUTO_INCREMENT,

  nombre VARCHAR(255),

  apellido VARCHAR(255),

  email VARCHAR(255) UNIQUE,

  password VARCHAR(255),

  creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  actualizado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

);


-- Tabla de Tipos de Cuenta

CREATE TABLE Tipos_Cuenta (

  id_tipo_cuenta BIGINT PRIMARY KEY AUTO_INCREMENT,

  tipo_cuenta VARCHAR(255)

);


-- Tabla de Cuentas

CREATE TABLE Cuentas (

  id_cuenta BIGINT PRIMARY KEY AUTO_INCREMENT,

  id_usuario BIGINT NOT NULL,

  id_tipo_cuenta BIGINT NOT NULL,

  saldo DECIMAL(10, 2) DEFAULT 0.00,

  creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  actualizado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),

  FOREIGN KEY (id_tipo_cuenta) REFERENCES Tipos_Cuenta(id_tipo_cuenta)

);


-- Tabla de Tipos de Transacción

CREATE TABLE Tipos_Transaccion (

  id_tipo_transaccion BIGINT PRIMARY KEY AUTO_INCREMENT,

  tipo_transaccion VARCHAR(255)

);


-- Tabla de Transacciones

CREATE TABLE Transacciones (

  id_transaccion BIGINT PRIMARY KEY AUTO_INCREMENT,

  id_cuenta BIGINT NOT NULL,

  id_tipo_transaccion BIGINT NOT NULL,

  monto DECIMAL(10, 2),

  fecha_transaccion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  descripcion TEXT,

  creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  actualizado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  FOREIGN KEY (id_cuenta) REFERENCES Cuentas(id_cuenta),

  FOREIGN KEY (id_tipo_transaccion) REFERENCES Tipos_Transaccion(id_tipo_transaccion)

);


-- Tabla de Tipos de Contacto

CREATE TABLE Tipos_Contacto (

  id_tipo_contacto BIGINT PRIMARY KEY AUTO_INCREMENT,

  tipo_contacto VARCHAR(255)

);


-- Tabla de Contactos

CREATE TABLE Contactos (

  id_contacto BIGINT PRIMARY KEY AUTO_INCREMENT,

  id_tipo_contacto BIGINT NOT NULL,

  id_usuario BIGINT NOT NULL,

  contacto VARCHAR(255),

  creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  actualizado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  FOREIGN KEY (id_tipo_contacto) REFERENCES Tipos_Contacto(id_tipo_contacto),

  FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)

);