import React from 'react'


//http://localhost:8080/api/reclamos/generareclamojson

export const crearReclamo = async(dni
    ,idedificio
    ,descripcion
    ,idunidad
    ,tiporeclamo) => {

   

    // Construye la URL correctamente codificada
   
    //console.log("la url para el reclamo es esta esta --->",`http://localhost:8080/api/reclamos/generareclamo/DNI ${dni}/${idunidad}/${idedificio}?descripcion=${descripcion}&tiporeclamo=${tiporeclamo}`);
    //let estedni="DNI "+dni
    //let texto = String(descripcion)
    try {

        const url = `http://localhost:8080/api/reclamos/generareclamojson`;
        const cuerpo={
          "persona":"DNI "+dni,//debe existir
          "unidad":idunidad,//debe existir
          "edificio":idedificio, //debe existir
          "descripcion":descripcion,
          "estado":"nuevo",  
          "tiporeclamo":tiporeclamo //creo que lsta al pedo en las tablas
        }
        console.log("el cuerpo enviado es--->",cuerpo);
        
        // Enviar la información al backend usando fetch
        const respuesta = await fetch(url, {
            method: 'POST', // El método de la solicitud es POST
            headers: {
              'Content-Type': 'application/json', // Indica que el contenido es JSON
            },
          body: JSON.stringify(cuerpo), // Convertir el objeto unidad a JSON
        });
  
        // Manejar la respuesta del servidor
        if (respuesta.ok) {
          const reclamocreaqdo = await respuesta.json();
          console.log('reclamocreaqdo con éxito:', reclamocreaqdo);
          return reclamocreaqdo;
          //setMensaje('Unidad creada con éxito.');
        } else {
          return null;
          console.log('Hubo un error al crear el reclamo.');
        }
      } catch (error) {
        console.error('Error en la solicitud:', error);
        //setMensaje('Error en la conexión con el servidor.');
        return null;
      }
}
/*
{
    "persona":"DNI 2222",//debe existir
	"unidad":1,//debe existir
	"edificio":1, //debe existir
	"descripcion":"mancha de salitre dos",
	"estado":"nuevo",  
	"tiporeclamo":4 //creo que lsta al pedo en las tablas
}
*/ 