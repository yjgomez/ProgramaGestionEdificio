import React from 'react'

export const agregarDuenio = async(idunidad,documento) => {
    console.log("AGREGANDO DUENIO");
    console.log("http://localhost:8080/api/unidades/${idunidad}/agregarduenio");
    console.log({documento});
    
    
    try {
        // Realizar la solicitud PUT
        const respuesta = await fetch(`http://localhost:8080/api/unidades/${idunidad}/agregarduenio`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ documento:"DNI "+documento }) // Enviar el documento en el cuerpo de la solicitud
        });
  
        if (respuesta.ok) {
          console.log('Dueño agregado con éxito.');
          return "dueño agregado"
        } else {
            console.log('Error al agregar el dueño.');
            return null;
        }
      } catch (error) {
        console.error('Error en la solicitud:', error);
        return null;
        //setMensaje('Error en la conexión con el servidor.');
      }
    };
  

