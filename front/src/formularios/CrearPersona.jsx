import React, { useState } from 'react'
import { crearPersona } from '../apis/crearPersona';

export const CrearPersona = () => {
    const [nombre, setNombre] = useState('');
  const [documento, setDocumento] = useState('');

  // Función que maneja el envío del formulario
  const manejarEnvio = async(event) => {
    event.preventDefault();
    // Aquí puedes manejar los datos del formulario como desees
    console.log('Nombre:', nombre);
    console.log('documento:', documento);
    const dnienviable="DNI "
    const persona={
        documento:dnienviable+documento,
        nombre: nombre
    }
    const personacreada= await crearPersona(persona)
    if(personacreada!=null){
        console.log("persona creada con exito");
        
    }
    // Limpiar los campos del formulario después de enviar
    setNombre('');
    setDocumento('');
  };


  return (
    <div className='bg-success'>
       
        <form className="form-label" onSubmit={manejarEnvio}>
            <h4>Formulario para  CREAR PERSONA</h4>
            <div>
            <label htmlFor="nombre">Nombre:</label>
            <input
            className='form-control'
                type="text"
                id="nombre"
                value={nombre}
                onChange={(e) => setNombre(e.target.value)}
                required
            />
            </div>
            <div>
            <label htmlFor="dni">DNI:</label>
            <input
            className='form-control'
                type="text"
                id="documento"
                value={documento}
                onChange={(e) => setDocumento(e.target.value)}
                required
            />
            </div>
            <button type="submit"  className='btn btn-primary m-2'>Crear Persona</button>
      </form>
    </div>
    )
}
