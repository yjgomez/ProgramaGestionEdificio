import React from 'react'

//http://localhost:8080/api/personas/obtenerporid/DNI 5555
export const login = async(dni) => {
    try {
        const texto="DNI "+dni;
        // Codifica el DNI para que sea seguro en la URL
        const encodedDni = encodeURIComponent(`DNI ${dni}`);
        const url = `http://localhost:8080/api/personas/obtenerporid/${encodedDni}`;
        
        // Enviar la información al backend usando fetch
        const respuesta = await fetch(url, {
            method: 'GET', // El método de la solicitud es POST
            headers: {
              'Content-Type': 'application/json', // Indica que el contenido es JSON
            },
        });
  
        // Manejar la respuesta del servidor
        if (respuesta.ok) {
          const reclamocreaqdo = await respuesta.json();
          return reclamocreaqdo;
          //setMensaje('Unidad creada con éxito.');
        } else {
          return null;
          console.log('Hubo un error en el login.');
        }
      } catch (error) {
        console.error('Error en la solicitud:', error);
        //setMensaje('Error en la conexión con el servidor.');
        return null;
      }
}
