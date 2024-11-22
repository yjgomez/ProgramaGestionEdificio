import React from 'react'


//http://localhost:8080/api/personas/login/DNI 89231201/jorge
export const otroLogin = async(dni,nombre) => {
    try {
        
        // Codifica el DNI para que sea seguro en la URL
        const encodedDni = encodeURIComponent(`DNI ${dni}`);
        const url = `http://localhost:8080/api/personas/login/${encodedDni}/${nombre}`;
        console.log("la url es"+url);
        
        // Enviar la información al backend usando fetch
        const respuesta = await fetch(url, {
            method: 'GET', // El método de la solicitud es POST
            headers: {
              'Content-Type': 'application/json', // Indica que el contenido es JSON
            },
        });
  
        // Manejar la respuesta del servidor
        if (respuesta.ok) {
          const bandera = await respuesta.json();
          console.log("el login fue"+bandera)
          return bandera; //true o false
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
