# Generador de codigo para Spring Boot
Esta es una pequeña aplicacion que ayuda a generar las clases mas necesarias para utilizar en un proyecto spring boot.  
Al applicacion utiliza una consola como un CLI para mandar los comandor que necesita. Esta progamado con spring shell.  

## Caracteristicas
Esta pequeña aplicacion genera codigo fuente base para poder trabajar en base a la estructura de los componentes que tiene spring boot.  
Tiene tambien la particularidad de poder generar codigo tambien solo de las clases especificas.  

## Comando CLI
Un resumen completo de los comando seria
```bash
files # Crea todas las carpetas que se usaran para las clases generadas
all Person # Crea todas las clases Persona de dto, filter, mapper, controller, service, implement y repository
remove Person # Elimina todas las clases Persona de dto, filter, mapper, controller, service, implement y repository
dto Person # Crea solo la clase PersonaDto en la carpeta /dtos
filter Person  # Crea solo la clase PersonaFilter en la carpeta /filter
pojo Person  # Crea solo la clase PersonaPojo en la carpeta /pojos
mapper Person  # Crea solo la clase PersonaMapper en la carpeta /mappers
repository Person  # Crea solo la clase PersonaRepository en la carpeta /repository
controller Person  # Crea solo la clase PersonaController en la carpeta /controller
service Person  # Crea solo la clase PersonaService en la carpeta /services
implement Person # Crea solo la clase PersonaServiceImpl en la carpeta /implement
```
## Instalación y Configuración
Necesita solamente tener Java 17 instalado en su maquina.
Si quiere generar una entidad se tiene que crear la Entidad en la carpeta /entities

## Estructura del Código Generado

## Configuración Avanzada

## Integraciones

## Errores Comunes y Soluciones
Si por algun motivo se genero la entidad pero no era la que estaba necesitando se puede usar el siguiente comando  
```bash
remove Person # Elimina todas las clases Persona de dto, filter, mapper, controller, service, implement y repository
```
Este comando eliminara todas las clases crearas apartir de la entidad

## Contribución
¿Quisieras contribuir a proyecto?

## Licencia
MIT License  

Copyright (c) [2025] [HENRY PEREZ GUMIEL]  

Permission is hereby granted, free of charge, to any person obtaining a copy  
of this software and associated documentation files (the "Software"), to deal  
in the Software without restriction, including without limitation the rights  
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell  
copies of the Software, and to permit persons to whom the Software is  
furnished to do so, subject to the following conditions:  

The above copyright notice and this permission notice shall be included in all  
copies or substantial portions of the Software.  

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,  
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE  
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER  
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,  
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  
SOFTWARE.  

## Contactos y soporte
Henry Perez Gumiel - prez.gumiel@gmail.com


carpetas

