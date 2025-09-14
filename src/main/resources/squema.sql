CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE usuario (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    rol VARCHAR(20) CHECK (rol IN ('ALUMNO', 'PROPIETARIO', 'ADMIN')) NOT NULL
);

CREATE TABLE habitacion (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    titulo VARCHAR(100) NOT NULL,
    imagen VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    descripcion TEXT,
    ciudad VARCHAR(100) NOT NULL,
    precio_mensual DECIMAL(10, 2) NOT NULL,
    disponible_desde DATE NOT NULL,
    propietario_id UUID REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE reserva (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    habitacion_id UUID REFERENCES habitacion(id) ON DELETE CASCADE,
    alumno_id UUID REFERENCES usuario(id) ON DELETE CASCADE,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    estado VARCHAR(20) CHECK (estado IN ('PENDIENTE', 'CONFIRMADA', 'CANCELADA')) NOT NULL
);
