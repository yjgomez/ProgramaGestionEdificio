import React from 'react'

export const crearEdificio = async(edificio) => {
    try {
        // Enviar la información al backend usando fetch
        const respuesta = await fetch('http://localhost:8080/api/edificios/crear', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json', // Tipo de contenido JSON
          },
          body: JSON.stringify(edificio), // Convertir el objeto unidad a JSON
        });
  
        // Manejar la respuesta del servidor
        if (respuesta.ok) {
          const edificioCreada = await respuesta.json();
          console.log('edificio creada con éxito:', edificioCreada);
          return edificioCreada;
          //setMensaje('Unidad creada con éxito.');
        } else {
            console.log('Hubo un error al crear el edificio.');
            return null
         
        }
      } catch (error) {
        console.error('Error en la solicitud:', error);
        return null;
      }
}
