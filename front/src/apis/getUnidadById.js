import React from 'react'

//http://localhost:8080/api/unidades/1
export const getUnidadById = async(idunidad) => {
    const url = `http://localhost:8080/api/unidades/${idunidad}`;
    try {
      const response = await fetch(url);  // Espera la respuesta de la solicitud fetch
      if (!response.ok) {
        // Si la respuesta no es correcta (status no 2xx), lanza un error
        console.error('Error al obtener la unidad ', response.statusText);
        return null;
      }
  
      const data = await response.json();  // Convierte la respuesta a formato JSON
      console.log('unidad encontrada:', data);
      return data;  // Retorna los datos del backend
  
    } catch (error) {
      console.error('Hubo un problema con la solicitud fetch:', error);
      return null;  // Retorna null si hubo un error
    }
  }
