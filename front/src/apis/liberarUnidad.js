import React from 'react'


//http://localhost:8080/api/unidades/1615/liberarUnidad?dni=DNI 30825333
export const liberarUnidad = async(idunidad,dni) => {
    try {
   
         const encodedDni = encodeURIComponent(`DNI ${dni}`);
        const respuesta = await fetch(`http://localhost:8080/api/unidades/${idunidad}/liberarUnidad?dni=${encodedDni}`, {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json'
            }
           
          });
    
    
      
      if (!respuesta.ok) {
        // Si la respuesta no es correcta (status no 2xx), lanza un error
        console.error('Error al liberar la unidad: ', respuesta.statusText);
        return null;
      }
  
      const data = await respuesta.json();  // Convierte la respuesta a formato JSON
      console.log(' liberado:', data);
      return data;  // Retorna los datos del backend
  
    } catch (error) {
      console.error('Hubo un problema con la solicitud fetch:', error);
      return null;  // Retorna null si hubo un error
    }
}
