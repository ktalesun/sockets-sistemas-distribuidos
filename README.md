# Sistema de Consulta Telefónica - Sockets Java

Aplicación cliente-servidor basada en sockets Java que permite consultar información de personas (nombre, dirección y ciudad) a través de su número de teléfono desde una base de datos MySQL.

## Requisitos

- Java 17+
- Maven 3.6+
- Docker y Docker Compose
- MySQL 8.0 (via Docker)

## Estructura del Proyecto

```
sockets/
├── docker-compose.yaml       # Configuración de MySQL y phpMyAdmin
├── README.md                 # Este archivo
├── cuidades.sql              # Script para crear tabla ciudades
├── personas.sql              # Script para crear tabla personas
├── populate.sql              # Script para llenar datos de prueba
└── sockets/
    ├── pom.xml              # Dependencias Maven
    └── src/main/java/com/sockets/
        ├── Server.java      # Servidor socket que consulta BD
        └── Cliente.java     # Cliente socket interactivo
```

## Instalación y Configuración

### 1. Levantar la Base de Datos

```bash
docker-compose up -d
```

Esto iniciará:
- **MySQL**: puerto 3306 (usuario: `root`, contraseña: `root123456`)
- **phpMyAdmin**: http://localhost:8080

### 2. Crear la Base de Datos

En phpMyAdmin o por línea de comandos:

```bash
mysql -h localhost -u root -p root123456 < cuidades.sql
mysql -h localhost -u root -p root123456 < personas.sql
mysql -h localhost -u root -p root123456 < populate.sql
```

O ejecuta los scripts desde phpMyAdmin.

### 3. Compilar el Proyecto

```bash
cd sockets
mvn clean install
```

## Uso

### Terminal 1: Ejecutar el Servidor

```bash
cd sockets
mvn exec:java@server
```

O desde VS Code: ejecuta `Server.java` (Run → Server)

El servidor escucha en `localhost:9999` y espera conexiones de clientes.

### Terminal 2: Ejecutar el Cliente

```bash
cd sockets
mvn exec:java@client
```

O desde VS Code: ejecuta `Cliente.java` (Run → Cliente)

El cliente solicitará un número de teléfono y mostrará los datos de la persona asociada en la BD.

## Ejemplo de Ejecución

```
Servidor esperando conexiones...
Cliente conectado
```

En el cliente:
```
Ingrese el número de teléfono: 1234567890
Respuesta del servidor: Nombre: Juan Pérez, Dirección: Calle 5 #123, Ciudad: Bogotá
```

## Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación
- **Sockets**: Comunicación cliente-servidor
- **MySQL 8.0**: Base de datos
- **Maven**: Gestor de dependencias y construcción
- **Docker**: Contenedores para BD
- **phpMyAdmin**: Interfaz web para administrar MySQL

## Dependencias Maven

- `mysql-connector-j` (9.0.0): Driver JDBC para MySQL

## Notas

- La conexión a la BD usa credenciales por defecto en `Server.java`, edita según sea necesario
- Los scripts SQL deben ejecutarse en orden: `cuidades.sql` → `personas.sql` → `populate.sql`
- Para detener los contenedores: `docker-compose down`

