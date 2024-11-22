import React, { useState } from 'react'
import { crearEdificio } from '../apis/crearEdificio';

export const CrearEdificio = () => {
  const [nombre, setNombre] = useState('');
  const [direccion, setDireccion] = useState('');
  const [mensaje, setMensaje] = useState('');

  // Función para manejar el envío del formulario
  const manejarEnvio = async (event) => {
    event.preventDefault();

    // Crear un objeto con los datos del edificio
    const edificio = {
      nombre: nombre,
      direccion: direccion,
    };

    const edificiocreado =await crearEdificio(edificio)
    if(edificiocreado!=null){
      console.log("EDIFICIO CREADO CON EXITO");
      
    }
    // Limpiar los campos del formulario después de enviar
    setNombre('');
    setDireccion('');
  };

  return (
    <div>
      <h2>Formulario para Crear Edificio</h2>
      <form onSubmit={manejarEnvio} className='m-2'>
        <div>
          <label htmlFor="nombre" className="form-label">Nombre del Edificio:</label>
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
          <label htmlFor="direccion">Dirección:</label>
          <input
          className='form-control'
            type="text"
            id="direccion"
            value={direccion}
            onChange={(e) => setDireccion(e.target.value)}
            required
          />
        </div>
        <button type="submit" className='btn btn-outline-primary mt-2'>Crear Edificio</button>
      </form>
      {/* Mensaje para mostrar el estado del envío */}
      {mensaje && <p>{mensaje}</p>}
    </div>
  );
}

