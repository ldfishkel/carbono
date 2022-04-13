# Entidades

* Teniendo en cuenta lo que se menciono en clase sobre la capa de authentication, no vamos a modelar las clases Usuario y Roles 

### Organizacion
- razonSocial
- tipo
- ubicacion : Ubicacion
- clasificacion
- sectores

### Sector
- nombre
- miembros

### Miembro
- nombre
- apellido
- tipoDeDocumento
- numeroDeDumento
- trayectos

### Trayecto
- tramos
- puntoInicial : Ubicacion
- puntoFinal : Ubicacion

### Tramo
- medioDeTransporte
- puntoA : Ubicacion
- puntoB : Ubicacion

### Ubicacion (attrs a revisar)
- lat : Double
- lon : Double
- direccion : String 

### MedioDeTransporte (clase Abstracta)
- tipo

### MedioNoMotorizado
- tipo

### VehiculoParticular
- tipo
- tipoCombustible

### ServicioContratado
- tipo

### TransportePublico
- tipo : TipoDeTransportePublico
- nombre : String
- estaciones : Ubicacion[]


Tablas

Organizacion
- id

Sector
- id
- organizacion_id

Sector_Miembro
- sector_id
- miembro_id

Miembro
- id

SELECT * from Organizacion as o
INNER JOIN Sector as s ON o.id = s.organizacion_id
INNER JOIN Sector_Miembro as sm ON s.id = sm.sector_id
where sm.miembro_id = 5;  