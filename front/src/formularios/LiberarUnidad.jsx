import React, { useState } from 'react'
import { liberarUnidad } from '../apis/liberarUnidad';

export const LiberarUnidad = () => {
    const [dni, setDni] = useState('');
    const [idunidad, setIdunidad] = useState('');
    const [mensaje, setMensaje] = useState('');
    //const [loading, setLoading] = useState(false);
    
    // Función para manejar el cambio del input
   
  
    // Función para manejar el envío del formulario
    const manejarEnvio = async (e) => {
      e.preventDefault(); // Evitar recarga de la página
  
      // Validar si el DNI es válido
      if (!dni || dni.trim().length === 0) {
        setMensaje('El DNI es obligatorio.');
        return;
      }
      setMensaje('');
      const resultado=await liberarUnidad(idunidad,dni);
      if(resultado!=null){
        setMensaje('unidad liberada');
      }
      setIdunidad("")
      setDni("")
      
    };
  
    return (
      <div>
        <h1>Formulario para liberar unidad</h1>
        <form onSubmit={manejarEnvio}>
          <div>
            <label htmlFor="dni">DNI:</label>
            <input
              type="text"
              id="dni"
              value={dni}
              onChange={ (e)=>setDni(e.target.value)}
              placeholder="Ingresa el DNI del dueño"
            />
            <input
              type="text"
              id="idunidad"
              value={idunidad}
              onChange={ (e)=>setIdunidad(e.target.value)}
              placeholder="Ingresa el id de la unidad"
            />
          </div>
          <div>
            <button type="submit" className='btn btn-danger'>'Liberar Unidad'
            </button>
          </div>
        </form>
        {mensaje && <p>{mensaje}</p>}
      </div>
    );
  };

