import React from 'react'

//http://localhost:8080/api/unidades/1615/transferir

/*
{
    "documento":"DNI30825333",
    "nombre":"MEDINA, CRISTIAN"
}
*/ 
export const transferirUnidad = async(idunidad,cuerpo) => {
    try {
        const respuesta = await fetch(`http://localhost:8080/api/unidades/${idunidad}/transferir`, {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(cuerpo)
          });
    
    
      
      if (!respuesta.ok) {
        // Si la respuesta no es correcta (status no 2xx), lanza un error
        console.error('Error al transferir la unidad: ', respuesta.statusText);
        return null;
      }
  
      const data = await respuesta.json();  // Convierte la respuesta a formato JSON
      console.log(' transferido:', data);
      return data;  // Retorna los datos del backend
  
    } catch (error) {
      console.error('Hubo un problema con la solicitud fetch:', error);
      return null;  // Retorna null si hubo un error
    }
}
