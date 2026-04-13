
CREATE TABLE personas (
    dir_tel VarCHAR(15) PRIMARY KEY,
    dir_tipo_tel VARCHAR(50),
    dir_nombre VARCHAR(255) NOT NULL,
    dir_direccion INT NOT NULL,
    dir_cuid_id INT,
    FOREIGN KEY (dir_cuid_id) REFERENCES ciudades(cuid_id)
);