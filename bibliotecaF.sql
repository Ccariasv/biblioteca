-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         PostgreSQL 16.4, compiled by Visual C++ build 1940, 64-bit
-- SO del servidor:              
-- HeidiSQL Versión:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Volcando estructura para tabla public.autor
CREATE TABLE IF NOT EXISTS "autor" (
	"id" SERIAL NOT NULL,
	"nombres" VARCHAR(100) NULL DEFAULT NULL,
	"apellidos" VARCHAR(100) NULL DEFAULT NULL,
	PRIMARY KEY ("id")
);

-- Volcando datos para la tabla public.autor: 2 rows
/*!40000 ALTER TABLE "autor" DISABLE KEYS */;
REPLACE INTO "autor" ("id", "nombres", "apellidos") VALUES
	(1, 'Miguel Ángel', 'Asturias Rosales'),
	(2, 'Rafael', 'Arévalo Martínez');
/*!40000 ALTER TABLE "autor" ENABLE KEYS */;

-- Volcando estructura para tabla public.clientes
CREATE TABLE IF NOT EXISTS "clientes" (
	"id" SERIAL NOT NULL,
	"dpi" VARCHAR(20) NULL DEFAULT NULL,
	"nombres" VARCHAR(255) NOT NULL,
	"apellidos" VARCHAR(255) NOT NULL,
	"genero_persona_id" BIGINT NULL DEFAULT NULL,
	"fecha_nacimiento" DATE NULL DEFAULT NULL,
	"idioma_id" BIGINT NULL DEFAULT NULL,
	"grupo_etnico_id" BIGINT NOT NULL,
	"nivel_escolar_id" BIGINT NULL DEFAULT NULL,
	"telefono" VARCHAR(20) NULL DEFAULT NULL,
	"email" VARCHAR(255) NULL DEFAULT NULL,
	"municipio_id" BIGINT NOT NULL,
	"estado_cl_id" BIGINT NOT NULL,
	UNIQUE "clientes_dpi_key" ("dpi"),
	PRIMARY KEY ("id"),
	CONSTRAINT "clientes_estado_cl_id_fkey" FOREIGN KEY ("estado_cl_id") REFERENCES "estado_cl" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "clientes_genero_persona_id_fkey" FOREIGN KEY ("genero_persona_id") REFERENCES "genero_persona" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "clientes_grupo_etnico_id_fkey" FOREIGN KEY ("grupo_etnico_id") REFERENCES "grupo_etnico" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "clientes_idioma_id_fkey" FOREIGN KEY ("idioma_id") REFERENCES "idioma" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "clientes_municipio_id_fkey" FOREIGN KEY ("municipio_id") REFERENCES "municipios" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "clientes_nivel_escolar_id_fkey" FOREIGN KEY ("nivel_escolar_id") REFERENCES "nivel_escolar" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Volcando datos para la tabla public.clientes: 4 rows
/*!40000 ALTER TABLE "clientes" DISABLE KEYS */;
REPLACE INTO "clientes" ("id", "dpi", "nombres", "apellidos", "genero_persona_id", "fecha_nacimiento", "idioma_id", "grupo_etnico_id", "nivel_escolar_id", "telefono", "email", "municipio_id", "estado_cl_id") VALUES
	(5, '4853102931891', 'Roberto Jose', 'Madrid Díaz', 1, '1999-11-20', 1, 1, 1, '29834018', 'rjose@gmail.com', 1, 1),
	(3, '2010672021901', 'Carlos Enrique', 'Carias Vidal', 1, '1995-11-22', 1, 1, 4, '49007938', 'ccariasv@miumg.edu.gt', 1, 1),
	(4, '1922453051981', 'Mario Anibal', 'Mendez Robles', 1, '1990-10-15', 1, 1, 1, '17281723', 'manibal@gmail.com', 1, 1),
	(1, '1233545944894', 'Eduardo Raul', 'Cordon Sosa', 1, '2021-09-15', 1, 1, 1, '78976543', 'ecordon@gmail.com', 1, 1);
/*!40000 ALTER TABLE "clientes" ENABLE KEYS */;

-- Volcando estructura para tabla public.departamento
CREATE TABLE IF NOT EXISTS "departamento" (
	"id" SERIAL NOT NULL,
	"nombre" VARCHAR(100) NOT NULL,
	PRIMARY KEY ("id")
);

-- Volcando datos para la tabla public.departamento: 1 rows
/*!40000 ALTER TABLE "departamento" DISABLE KEYS */;
REPLACE INTO "departamento" ("id", "nombre") VALUES
	(1, 'Guatemala');
/*!40000 ALTER TABLE "departamento" ENABLE KEYS */;

-- Volcando estructura para tabla public.detalle_prestamo
CREATE TABLE IF NOT EXISTS "detalle_prestamo" (
	"id" SERIAL NOT NULL,
	"prestamo_id" BIGINT NOT NULL,
	"descripcion" TEXT NULL DEFAULT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "detalle_prestamo_prestamo_id_fkey" FOREIGN KEY ("prestamo_id") REFERENCES "prestamos" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Volcando datos para la tabla public.detalle_prestamo: 1 rows
/*!40000 ALTER TABLE "detalle_prestamo" DISABLE KEYS */;
REPLACE INTO "detalle_prestamo" ("id", "prestamo_id", "descripcion") VALUES
	(1, 1, 'presatmo del señor presidente');
/*!40000 ALTER TABLE "detalle_prestamo" ENABLE KEYS */;

-- Volcando estructura para tabla public.editorial
CREATE TABLE IF NOT EXISTS "editorial" (
	"id" SERIAL NOT NULL,
	"nombre" VARCHAR(100) NULL DEFAULT NULL,
	PRIMARY KEY ("id")
);

-- Volcando datos para la tabla public.editorial: 1 rows
/*!40000 ALTER TABLE "editorial" DISABLE KEYS */;
REPLACE INTO "editorial" ("id", "nombre") VALUES
	(1, 'Costa-Amic');
/*!40000 ALTER TABLE "editorial" ENABLE KEYS */;

-- Volcando estructura para tabla public.estado_cl
CREATE TABLE IF NOT EXISTS "estado_cl" (
	"id" SERIAL NOT NULL,
	"estado" VARCHAR(45) NOT NULL,
	PRIMARY KEY ("id")
);

-- Volcando datos para la tabla public.estado_cl: 3 rows
/*!40000 ALTER TABLE "estado_cl" DISABLE KEYS */;
REPLACE INTO "estado_cl" ("id", "estado") VALUES
	(1, 'Activo'),
	(2, 'Desactivado'),
	(3, 'Penalizado');
/*!40000 ALTER TABLE "estado_cl" ENABLE KEYS */;

-- Volcando estructura para tabla public.estado_lb
CREATE TABLE IF NOT EXISTS "estado_lb" (
	"id" SERIAL NOT NULL,
	"estado" TEXT NOT NULL,
	PRIMARY KEY ("id")
);

-- Volcando datos para la tabla public.estado_lb: 1 rows
/*!40000 ALTER TABLE "estado_lb" DISABLE KEYS */;
REPLACE INTO "estado_lb" ("id", "estado") VALUES
	(1, 'Disponible');
/*!40000 ALTER TABLE "estado_lb" ENABLE KEYS */;

-- Volcando estructura para tabla public.genero_literario
CREATE TABLE IF NOT EXISTS "genero_literario" (
	"id" SERIAL NOT NULL,
	"nombre" VARCHAR(100) NOT NULL,
	PRIMARY KEY ("id")
);

-- Volcando datos para la tabla public.genero_literario: 1 rows
/*!40000 ALTER TABLE "genero_literario" DISABLE KEYS */;
REPLACE INTO "genero_literario" ("id", "nombre") VALUES
	(1, 'narrativo');
/*!40000 ALTER TABLE "genero_literario" ENABLE KEYS */;

-- Volcando estructura para tabla public.genero_persona
CREATE TABLE IF NOT EXISTS "genero_persona" (
	"id" SERIAL NOT NULL,
	"nombre" VARCHAR(50) NOT NULL,
	PRIMARY KEY ("id")
);

-- Volcando datos para la tabla public.genero_persona: 2 rows
/*!40000 ALTER TABLE "genero_persona" DISABLE KEYS */;
REPLACE INTO "genero_persona" ("id", "nombre") VALUES
	(1, 'Hombre'),
	(2, 'Mujer');
/*!40000 ALTER TABLE "genero_persona" ENABLE KEYS */;

-- Volcando estructura para tabla public.grupo_etnico
CREATE TABLE IF NOT EXISTS "grupo_etnico" (
	"id" SERIAL NOT NULL,
	"nombre" VARCHAR(100) NOT NULL,
	PRIMARY KEY ("id")
);

-- Volcando datos para la tabla public.grupo_etnico: 2 rows
/*!40000 ALTER TABLE "grupo_etnico" DISABLE KEYS */;
REPLACE INTO "grupo_etnico" ("id", "nombre") VALUES
	(1, 'Ladino'),
	(2, 'Maya');
/*!40000 ALTER TABLE "grupo_etnico" ENABLE KEYS */;

-- Volcando estructura para tabla public.idioma
CREATE TABLE IF NOT EXISTS "idioma" (
	"id" SERIAL NOT NULL,
	"nombre" VARCHAR(100) NOT NULL,
	PRIMARY KEY ("id")
);

-- Volcando datos para la tabla public.idioma: 2 rows
/*!40000 ALTER TABLE "idioma" DISABLE KEYS */;
REPLACE INTO "idioma" ("id", "nombre") VALUES
	(1, 'Español'),
	(2, 'Ingles');
/*!40000 ALTER TABLE "idioma" ENABLE KEYS */;

-- Volcando estructura para tabla public.libros
CREATE TABLE IF NOT EXISTS "libros" (
	"id" SERIAL NOT NULL,
	"titulo" VARCHAR(255) NOT NULL,
	"autor_id" BIGINT NULL DEFAULT NULL,
	"editorial_id" BIGINT NULL DEFAULT NULL,
	"fecha_publicacion" DATE NULL DEFAULT NULL,
	"isbn" VARCHAR(13) NULL DEFAULT NULL,
	"genero_literario_id" BIGINT NULL DEFAULT NULL,
	"descripcion" TEXT NULL DEFAULT NULL,
	"numero_paginas" INTEGER NULL DEFAULT NULL,
	"idioma_id" BIGINT NULL DEFAULT NULL,
	"numero_edicion" INTEGER NULL DEFAULT NULL,
	"estado_lb_id" BIGINT NOT NULL,
	"portada_id" BIGINT NULL DEFAULT NULL,
	UNIQUE "libros_isbn_key" ("isbn"),
	PRIMARY KEY ("id"),
	CONSTRAINT "libros_autor_id_fkey" FOREIGN KEY ("autor_id") REFERENCES "autor" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "libros_editorial_id_fkey" FOREIGN KEY ("editorial_id") REFERENCES "editorial" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "libros_estado_lb_id_fkey" FOREIGN KEY ("estado_lb_id") REFERENCES "estado_lb" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "libros_genero_literario_id_fkey" FOREIGN KEY ("genero_literario_id") REFERENCES "genero_literario" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "libros_idioma_id_fkey" FOREIGN KEY ("idioma_id") REFERENCES "idioma" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "libros_portada_id_fkey" FOREIGN KEY ("portada_id") REFERENCES "portada" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Volcando datos para la tabla public.libros: 1 rows
/*!40000 ALTER TABLE "libros" DISABLE KEYS */;
REPLACE INTO "libros" ("id", "titulo", "autor_id", "editorial_id", "fecha_publicacion", "isbn", "genero_literario_id", "descripcion", "numero_paginas", "idioma_id", "numero_edicion", "estado_lb_id", "portada_id") VALUES
	(1, 'El señor Presidente', 1, 1, '1946-10-18', '8437615178', 1, 'Libro de el señor presidente', 432, 1, 1, 1, 1);
/*!40000 ALTER TABLE "libros" ENABLE KEYS */;

-- Volcando estructura para tabla public.municipios
CREATE TABLE IF NOT EXISTS "municipios" (
	"id" SERIAL NOT NULL,
	"departamento_id" BIGINT NOT NULL,
	"nombre" VARCHAR(100) NOT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "municipios_departamento_id_fkey" FOREIGN KEY ("departamento_id") REFERENCES "departamento" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Volcando datos para la tabla public.municipios: 4 rows
/*!40000 ALTER TABLE "municipios" DISABLE KEYS */;
REPLACE INTO "municipios" ("id", "departamento_id", "nombre") VALUES
	(1, 1, 'Guatemala'),
	(2, 1, 'Villa Nueva'),
	(3, 1, 'Mixco'),
	(4, 1, 'Santa Catarina Pinula');
/*!40000 ALTER TABLE "municipios" ENABLE KEYS */;

-- Volcando estructura para tabla public.nivel_escolar
CREATE TABLE IF NOT EXISTS "nivel_escolar" (
	"id" SERIAL NOT NULL,
	"nombre" VARCHAR(100) NOT NULL,
	PRIMARY KEY ("id")
);

-- Volcando datos para la tabla public.nivel_escolar: 4 rows
/*!40000 ALTER TABLE "nivel_escolar" DISABLE KEYS */;
REPLACE INTO "nivel_escolar" ("id", "nombre") VALUES
	(1, 'Primaria'),
	(3, 'Diversificado'),
	(2, 'Básico '),
	(4, 'Universitario');
/*!40000 ALTER TABLE "nivel_escolar" ENABLE KEYS */;

-- Volcando estructura para tabla public.penalizacion
CREATE TABLE IF NOT EXISTS "penalizacion" (
	"id" SERIAL NOT NULL,
	"cliente_id" BIGINT NOT NULL,
	"prestamos_id" BIGINT NOT NULL,
	"tiempo_penalizacion" NUMERIC(8,2) NOT NULL,
	"fecha_penalizacion" BIGINT NOT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "penalizacion_cliente_id_fkey" FOREIGN KEY ("cliente_id") REFERENCES "clientes" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "penalizacion_prestamos_id_fkey" FOREIGN KEY ("prestamos_id") REFERENCES "prestamos" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Volcando datos para la tabla public.penalizacion: 1 rows
/*!40000 ALTER TABLE "penalizacion" DISABLE KEYS */;
REPLACE INTO "penalizacion" ("id", "cliente_id", "prestamos_id", "tiempo_penalizacion", "fecha_penalizacion") VALUES
	(2, 4, 1, 20.00, 39);
/*!40000 ALTER TABLE "penalizacion" ENABLE KEYS */;

-- Volcando estructura para tabla public.portada
CREATE TABLE IF NOT EXISTS "portada" (
	"id" SERIAL NOT NULL,
	"url" VARCHAR(255) NOT NULL,
	PRIMARY KEY ("id")
);

-- Volcando datos para la tabla public.portada: 1 rows
/*!40000 ALTER TABLE "portada" DISABLE KEYS */;
REPLACE INTO "portada" ("id", "url") VALUES
	(1, 'https://localhost/señorPresdinte');
/*!40000 ALTER TABLE "portada" ENABLE KEYS */;

-- Volcando estructura para tabla public.prestamos
CREATE TABLE IF NOT EXISTS "prestamos" (
	"id" SERIAL NOT NULL,
	"libro_id" BIGINT NOT NULL,
	"cliente_id" BIGINT NOT NULL,
	"fecha_prestamo" DATE NOT NULL,
	"fecha_limite_devolucion" DATE NOT NULL,
	"fecha_devolucion" DATE NULL DEFAULT NULL,
	"observaciones" TEXT NULL DEFAULT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "prestamos_cliente_id_fkey" FOREIGN KEY ("cliente_id") REFERENCES "clientes" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "prestamos_libro_id_fkey" FOREIGN KEY ("libro_id") REFERENCES "libros" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Volcando datos para la tabla public.prestamos: 1 rows
/*!40000 ALTER TABLE "prestamos" DISABLE KEYS */;
REPLACE INTO "prestamos" ("id", "libro_id", "cliente_id", "fecha_prestamo", "fecha_limite_devolucion", "fecha_devolucion", "observaciones") VALUES
	(1, 1, 1, '2024-10-18', '2024-10-29', '2024-10-20', NULL);
/*!40000 ALTER TABLE "prestamos" ENABLE KEYS */;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
