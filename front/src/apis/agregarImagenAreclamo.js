import React from 'react'

export const agregarImagenAreclamo = async(idreclamo,body) => {
    try {
        // Realizar la petición POST
        const response = await fetch(`http://localhost:8080/api/reclamos/agregarimagen/${idreclamo}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(body),
        });
  
        if (response.ok) {
          // Maneja la respuesta exitosa
          const data = await response.json();
          console.log('Imagen agregada correctamente:', data);
          return data;
        } else {
          // Maneja errores en la respuesta
          console.error('Error al agregar la imagen');
          return null;
        }
      } catch (error) {
        // Maneja errores de la petición
        console.error('Error en la petición:', error);
        return null;
      }
    };

