-- Script convertido para PostgreSQL

-- -----------------------------------------------------
-- Tables Creation
-- -----------------------------------------------------

-- Table public.autor
CREATE TABLE IF NOT EXISTS public.autor (
  id SERIAL PRIMARY KEY,
  nombres VARCHAR(100) NULL,
  apellidos VARCHAR(100) NULL
);

-- Table public.editorial
CREATE TABLE IF NOT EXISTS public.editorial (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(100) NULL
);

-- Table public.estado_lb
CREATE TABLE IF NOT EXISTS public.estado_lb (
  id SERIAL PRIMARY KEY,
  estado TEXT NOT NULL
);

-- Table public.departamento
CREATE TABLE IF NOT EXISTS public.departamento (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL
);

-- Table public.municipios
CREATE TABLE IF NOT EXISTS public.municipios (
  id SERIAL PRIMARY KEY,
  departamento_id INT NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  FOREIGN KEY (departamento_id) REFERENCES public.departamento (id)
);

-- Table public.genero_literario
CREATE TABLE IF NOT EXISTS public.genero_literario (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL
);

-- Table public.Idioma
CREATE TABLE IF NOT EXISTS public.Idioma (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL
);

-- Table public.portada
CREATE TABLE IF NOT EXISTS public.portada (
  id SERIAL PRIMARY KEY,
  url VARCHAR(255) NOT NULL
);

-- Table public.libros
CREATE TABLE IF NOT EXISTS public.libros (
  id SERIAL PRIMARY KEY,
  titulo VARCHAR(255) NOT NULL,
  autor_id INT NULL,
  editorial_id INT NULL,
  fecha_publicacion DATE NULL,
  isbn VARCHAR(13) UNIQUE,
  genero_literario_id INT NULL,
  descripcion TEXT NULL,
  numero_paginas INT NULL,
  idioma_id INT NULL,
  numero_edicion INT NULL,
  estado_lb_id INT NOT NULL,
  portada_id INT NULL,
  FOREIGN KEY (editorial_id) REFERENCES public.editorial (id),
  FOREIGN KEY (autor_id) REFERENCES public.autor (id),
  FOREIGN KEY (genero_literario_id) REFERENCES public.genero_literario (id),
  FOREIGN KEY (estado_lb_id) REFERENCES public.estado_lb (id),
  FOREIGN KEY (idioma_id) REFERENCES public.Idioma (id),
  FOREIGN KEY (portada_id) REFERENCES public.portada (id)
);

-- Table public.genero_persona
CREATE TABLE IF NOT EXISTS public.genero_persona (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL
);

-- Table public.grupo_etnico
CREATE TABLE IF NOT EXISTS public.grupo_etnico (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL
);

-- Table public.nivel_escolar
CREATE TABLE IF NOT EXISTS public.nivel_escolar (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL
);

-- Table public.estado_cl
CREATE TABLE IF NOT EXISTS public.estado_cl (
  id SERIAL PRIMARY KEY,
  estado VARCHAR(45) NOT NULL
);

-- Table public.clientes
CREATE TABLE IF NOT EXISTS public.clientes (
  id SERIAL PRIMARY KEY,
  dpi VARCHAR(20) UNIQUE,
  nombres VARCHAR(255) NOT NULL,
  apellidos VARCHAR(255) NOT NULL,
  genero_persona_id INT NULL,
  fecha_nacimiento DATE NULL,
  idioma_id INT NULL,
  grupo_etnico_id INT NOT NULL,
  nivel_escolar_id INT NULL,
  telefono VARCHAR(20) NULL,
  email VARCHAR(255) NULL,
  municipio_id INT NOT NULL,
  estado_cl_id INT NOT NULL,
  FOREIGN KEY (idioma_id) REFERENCES public.Idioma (id),
  FOREIGN KEY (genero_persona_id) REFERENCES public.genero_persona (id),
  FOREIGN KEY (municipio_id) REFERENCES public.municipios (id),
  FOREIGN KEY (grupo_etnico_id) REFERENCES public.grupo_etnico (id),
  FOREIGN KEY (nivel_escolar_id) REFERENCES public.nivel_escolar (id),
  FOREIGN KEY (estado_cl_id) REFERENCES public.estado_cl (id)
);

-- Table public.prestamos
CREATE TABLE IF NOT EXISTS public.prestamos (
  id SERIAL PRIMARY KEY,
  libro_id INT NOT NULL,
  cliente_id INT NOT NULL,
  fecha_prestamo DATE NOT NULL,
  fecha_limite_devolucion DATE NOT NULL,
  fecha_devolucion DATE NULL,
  observaciones TEXT NULL,
  FOREIGN KEY (cliente_id) REFERENCES public.clientes (id),
  FOREIGN KEY (libro_id) REFERENCES public.libros (id)
);

-- Table public.detalle_prestamo
CREATE TABLE IF NOT EXISTS public.detalle_prestamo (
  id SERIAL PRIMARY KEY,
  prestamo_id INT NOT NULL,
  descripcion TEXT NULL,
  FOREIGN KEY (prestamo_id) REFERENCES public.prestamos (id)
);

-- Table public.penalizacion
CREATE TABLE IF NOT EXISTS public.penalizacion (
  id SERIAL PRIMARY KEY,
  cliente_id INT NOT NULL,
  prestamos_id INT NOT NULL,
  tiempo_penalizacion DECIMAL(8,2) NOT NULL,
  fecha_penalizacion BIGINT NOT NULL,
  FOREIGN KEY (cliente_id) REFERENCES public.clientes (id),
  FOREIGN KEY (prestamos_id) REFERENCES public.prestamos (id)
);
