import React from 'react'

export const crearUnidad = async(idedificio,unidad) => {
    try {
        // Enviar la información al backend usando fetch
        const respuesta = await fetch(`http://localhost:8080/api/unidades/crear/${idedificio}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(unidad), // Convertir el objeto unidad a JSON
        });
  
        // Manejar la respuesta del servidor
        if (respuesta.ok) {
          const unidadCreada = await respuesta.json();
          console.log('Unidad creada con éxito:', unidadCreada);
          return unidadCreada;
          //setMensaje('Unidad creada con éxito.');
        } else {
          return null;
            //setMensaje('Hubo un error al crear la unidad.');
        }
      } catch (error) {
        console.error('Error en la solicitud:', error);
        //setMensaje('Error en la conexión con el servidor.');
        return null;
      }
}
