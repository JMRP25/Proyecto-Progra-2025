-- Crear base de datos y usarla
IF DB_ID('BANK') IS NULL
CREATE DATABASE prograll24Grupo7A;

    CREATE DATABASE BANK;
GO
USE BANK;
GO

-- Habilitar y configurar login 'sa'
ALTER LOGIN sa ENABLE;
GO
ALTER LOGIN sa WITH PASSWORD = 'Admin2024';
GO

-- Eliminar tablas si existen (para evitar errores)
IF OBJECT_ID('Cliente_Prestamo', 'U') IS NOT NULL DROP TABLE Cliente_Prestamo;
IF OBJECT_ID('Cliente_Cuenta', 'U') IS NOT NULL DROP TABLE Cliente_Cuenta;
IF OBJECT_ID('Prestamo', 'U') IS NOT NULL DROP TABLE Prestamo;
IF OBJECT_ID('Cuenta_Corriente', 'U') IS NOT NULL DROP TABLE Cuenta_Corriente;
IF OBJECT_ID('Cuenta_Ahorro', 'U') IS NOT NULL DROP TABLE Cuenta_Ahorro;
IF OBJECT_ID('Cuenta', 'U') IS NOT NULL DROP TABLE Cuenta;
IF OBJECT_ID('Cliente', 'U') IS NOT NULL DROP TABLE Cliente;
IF OBJECT_ID('Sucursal', 'U') IS NOT NULL DROP TABLE Sucursal;
IF OBJECT_ID('Banco', 'U') IS NOT NULL DROP TABLE Banco;
IF OBJECT_ID('tblusuario', 'U') IS NOT NULL DROP TABLE tblusuario;
IF OBJECT_ID('Transferencia', 'U') IS NOT NULL DROP TABLE Transferencia;
IF OBJECT_ID('Retiro', 'U') IS NOT NULL DROP TABLE Retiro;
GO

-- Tabla de usuarios
CREATE TABLE tblusuario (
    id INT PRIMARY KEY IDENTITY(1,1),
    usuario VARCHAR(50) NOT NULL,
    Clave VARCHAR(100) NOT NULL
);
INSERT INTO tblusuario (usuario, Clave) VALUES ('admin', '1234');

-- Tabla Banco
CREATE TABLE Banco (
    Codigo INT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL
);

-- Tabla Sucursal
CREATE TABLE Sucursal (
    Codigo INT PRIMARY KEY,
    Ciudad VARCHAR(100) NOT NULL,
    Banco_Codigo INT NOT NULL,
    FOREIGN KEY (Banco_Codigo) REFERENCES Banco(Codigo)
);

-- Tabla Cliente
CREATE TABLE Cliente (
    Id_Cliente INT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Direccion VARCHAR(200),
    Telefono VARCHAR(15)
);

-- Tabla Cuenta (base para herencia)
CREATE TABLE Cuenta (
    No_Cuenta INT PRIMARY KEY,
    Saldo NUMERIC(15, 2) NOT NULL DEFAULT 0,
    Sucursal_Codigo INT NOT NULL,
    FOREIGN KEY (Sucursal_Codigo) REFERENCES Sucursal(Codigo)
);

-- Tabla Cuenta de Ahorro
CREATE TABLE Cuenta_Ahorro (
    No_Cuenta INT PRIMARY KEY,
    Saldo_Minimo NUMERIC(15, 2) NOT NULL,
    Fecha_Apertura DATE NOT NULL,
    FOREIGN KEY (No_Cuenta) REFERENCES Cuenta(No_Cuenta)
);

-- Tabla Cuenta Corriente
CREATE TABLE Cuenta_Corriente (
    No_Cuenta INT PRIMARY KEY,
    Tasa_Interes INT NOT NULL,
    Fecha_Apertura DATE NOT NULL,
    FOREIGN KEY (No_Cuenta) REFERENCES Cuenta(No_Cuenta)
);

-- Tabla Prestamo
CREATE TABLE Prestamo (
    No_Prestamo INT PRIMARY KEY,
    Monto NUMERIC(15, 2) NOT NULL,
    Tipo VARCHAR(50) NOT NULL,
    Sucursal_Codigo INT NOT NULL,
    FOREIGN KEY (Sucursal_Codigo) REFERENCES Sucursal(Codigo)
);

-- Tabla unión Cliente-Cuenta (many-to-many)
CREATE TABLE Cliente_Cuenta (
    Cliente_Id INT,
    Cuenta_No INT,
    PRIMARY KEY (Cliente_Id, Cuenta_No),
    FOREIGN KEY (Cliente_Id) REFERENCES Cliente(Id_Cliente),
    FOREIGN KEY (Cuenta_No) REFERENCES Cuenta(No_Cuenta)
);

-- Tabla unión Cliente-Prestamo (many-to-many)
CREATE TABLE Cliente_Prestamo (
    Cliente_Id INT,
    Prestamo_No INT,
    PRIMARY KEY (Cliente_Id, Prestamo_No),
    FOREIGN KEY (Cliente_Id) REFERENCES Cliente(Id_Cliente),
    FOREIGN KEY (Prestamo_No) REFERENCES Prestamo(No_Prestamo)
);

-- Tabla Transferencia
CREATE TABLE Transferencia (
    Id_Transferencia INT PRIMARY KEY,
    Monto NUMERIC(15, 2) NOT NULL,
    Fecha DATE NOT NULL,
    Empresa_Origen VARCHAR(100),
    Empresa_Destino VARCHAR(100),
    Cuenta_Origen INT NOT NULL,
    FOREIGN KEY (Cuenta_Origen) REFERENCES Cuenta(No_Cuenta)
);

-- Tabla Retiro
CREATE TABLE Retiro (
    Id_Retiro INT PRIMARY KEY,
    Fecha DATE NOT NULL,
    Monto NUMERIC(15, 2) NOT NULL,
    Tipo_Cajero VARCHAR(50) NOT NULL,
    Cuenta_No INT NOT NULL,
    FOREIGN KEY (Cuenta_No) REFERENCES Cuenta(No_Cuenta)
);

-- Índices para mejorar rendimiento
CREATE INDEX idx_cuenta_sucursal ON Cuenta(Sucursal_Codigo);
CREATE INDEX idx_prestamo_sucursal ON Prestamo(Sucursal_Codigo);
CREATE INDEX idx_transferencia_cuenta ON Transferencia(Cuenta_Origen);
CREATE INDEX idx_retiro_cuenta ON Retiro(Cuenta_No);
GO

