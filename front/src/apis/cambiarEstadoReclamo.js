import React from 'react'

//http://localhost:8080/api/reclamos/cambiarestado/${idreclamo}?estado=${estado}`
//http://localhost:8080/api/reclamos/cambiarestado/5?estado=abierto
export const cambiarEstadoReclamo = async(idreclamo,estado) => {
    try {
        const respuesta = await fetch(`http://localhost:8080/api/reclamos/cambiarestado/${idreclamo}?estado=${estado}`, {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json'
            }
          });
    
    
      
      if (!respuesta.ok) {
        // Si la respuesta no es correcta (status no 2xx), lanza un error
        console.error('Error al cambiar el estado del reclamo: ', respuesta.statusText);
        return null;
      }
  
      const data = await respuesta.json();  // Convierte la respuesta a formato JSON
      console.log('estado cambiado:', data);
      return data;  // Retorna los datos del backend
  
    } catch (error) {
      console.error('Hubo un problema con la solicitud fetch:', error);
      return null;  // Retorna null si hubo un error
    }
  }
  
