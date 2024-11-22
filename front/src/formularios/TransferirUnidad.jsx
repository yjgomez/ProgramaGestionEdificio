import React, { useState } from 'react'
import { transferirUnidad } from '../apis/transferirUnidad';

export const TransferirUnidad = () => {
    const [dni, setDni] = useState('');
    const [idunidad, setIdunidad] = useState('');
    const [mensaje, setMensaje] = useState('');
    //const [loading, setLoading] = useState(false);
  
    // ID de la unidad (fijo en este caso)
  
    // Función para manejar el cambio en el input del DNI
    const manejarCambio = (e) => {
      setDni(e.target.value);
    };
  
    // Función para manejar el envío del formulario
    const manejarEnvio = async (e) => {
      e.preventDefault(); // Evitar recarga de la página
  
      // Validar si el DNI es válido
      if (!dni || dni.trim().length === 0) {
        setMensaje('El DNI es obligatorio.');
        return;
      }
  
      
      setMensaje('');
      const cuerpo={
        documento:"DNI "+dni
      }
      const resultado=await transferirUnidad(idunidad,cuerpo);
      if(resultado!=null){
        setMensaje('unidad transferida con exito');
    
      }
     
    };
  
    return (
      <div>
        <h1>Formulario para Transferir Unidad</h1>
        <form onSubmit={manejarEnvio}>
          <div>
            <label htmlFor="dni">DNI de la Persona:</label>
            <input
              className='form-control'
              type="text"
              id="dni"
              value={dni}
              onChange={(e)=>setDni(e.target.value)}
              placeholder="DNI del nuevo dueño: ejemplo 8754622"
            />
            <input
              type="text"
              id="idunidad"
              value={idunidad}
              onChange={(e)=>setIdunidad(e.target.value)}
              placeholder="id de la unidad"
            />
          </div>
          <div>
          
            <button className=" btn btn-primary"type="submit" > Transferir Unidad</button>
          </div>
        </form>
        {mensaje && <p>{mensaje}</p>}
      </div>
    );
  };

