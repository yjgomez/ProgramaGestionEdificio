import React from 'react'

export const agregarInquilino = async(idunidad,documento) => {
  //http://localhost:8080/api/unidades/${1}/alquilar
  try {
    // Realizar la solicitud PUT
    const respuesta = await fetch(`http://localhost:8080/api/unidades/${idunidad}/alquilar`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ documento:"DNI "+documento }) // Enviar el documento en el cuerpo de la solicitud
    });

    if (respuesta.ok) {
      console.log('Inquilino agregado con éxito.');
      return "dueño agregado"
    } else {
        console.log('Error al agregar el Inquilino.');
        return null;
    }
  } catch (error) {
    console.error('Error en la solicitud:', error);
    return null;
    //setMensaje('Error en la conexión con el servidor.');
  }
}
