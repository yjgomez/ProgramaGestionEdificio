import React from 'react'

export const fetchEdificios = async () => {
  const url = `http://localhost:8080/api/edificios`;

  try {
    const response = await fetch(url);  // Espera la respuesta de la solicitud fetch
    if (!response.ok) {
      // Si la respuesta no es correcta (status no 2xx), lanza un error
      console.error('Error al obtener los edificios: ', response.statusText);
      return null;
    }

    const data = await response.json();  // Convierte la respuesta a formato JSON
    console.log('Edificios encontrados:', data);
    return data;  // Retorna los datos del backend

  } catch (error) {
    console.error('Hubo un problema con la solicitud fetch:', error);
    return null;  // Retorna null si hubo un error
  }
}

