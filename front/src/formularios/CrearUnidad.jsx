import React, { useState } from 'react'
import {crearUnidad} from "../apis/crearUnidad"
export const CrearUnidad = () => {
  // Estado para manejar los datos de la unidad
  const [piso, setPiso] = useState('');
  const [numero, setNumero] = useState('');
  const [edificioId, setEdificioId] = useState('');
  const [mensaje, setMensaje] = useState('');

  // Función para manejar el envío del formulario
  const manejarEnvio = async (event) => {
    event.preventDefault();

    // Crear un objeto con los datos de la unidad
    const unidad = {
      piso: piso,
      numero: numero,
      edificioId: edificioId,
    };

    const unidadcreada=await crearUnidad(edificioId,unidad)
    if(unidadcreada!=null){
      console.log("unidad CREADa CON EXITO");
      
    }
    // Limpiar los campos del formulario después de enviar
    setPiso('');
    setNumero('');
    setEdificioId('');
  };

  return (
    <div className='bg-danger'>
      <h2>Formulario para Crear Unidad</h2>
      <form onSubmit={manejarEnvio}>
        <div>
          <label htmlFor="piso">Piso:</label>
          <input
          className='form-control'
            type="text"
            id="piso"
            value={piso}
            onChange={(e) => setPiso(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="numero">Número:</label>
          <input
          className='form-control'
            type="text"
            id="numero"
            value={numero}
            onChange={(e) => setNumero(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="edificioId">ID del Edificio:</label>
          <input
          className='form-control'
            type="text"
            id="edificioId"
            placeholder='el edificio debe existir'
            value={edificioId}
            onChange={(e) => setEdificioId(e.target.value)}
            required
          />
        </div>
        <button type="submit"  className='btn btn-primary m-2'>Crear Unidad</button>
      </form>
      {/* Mensaje para mostrar el estado del envío */}
      {mensaje && <p>{mensaje}</p>}
    </div>
  );
}