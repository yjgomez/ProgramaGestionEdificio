import React from 'react'

export const crearPersona = async(persona={}) => {
 
console.log("creando persona-------------->",JSON.stringify(persona));

  try {
    // Enviar la información al backend usando fetch
    const respuesta = await fetch('http://localhost:8080/api/personas/guardarpersona', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json', // Tipo de contenido JSON
      },
      body: JSON.stringify(persona), // Convertir el objeto persona a JSON
    });

    // Manejar la respuesta del servidor
    if (respuesta.ok) {
        const persona = await respuesta.json(); // ¡Fíjate en la 'j' minúscula!
        console.log('Persona creada con éxito:', persona);
        //setMensaje('Persona creada con éxito.');
        return persona;
      } else {
        return null;
        //setMensaje('Hubo un error al crear la persona.');
      }
  } catch (error) {
    console.error('Error en la solicitud:', error);
    return null;
    //setMensaje('Error en la conexión con el servidor.');
  }
}
