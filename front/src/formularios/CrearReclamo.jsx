import React, { useState } from 'react'
import { crearReclamo } from '../apis/crearReclamo';
export const CrearReclamo = () => {

  //@PostMapping("generareclamo/{persona}/{unidad}/{edificio}")

  const [dni, setDni] = useState('');
  const [idedificio, setIdEdificio] = useState('');
  const [descripcion, setDescripcion] = useState('');
  const [idunidad, setIdUnidad] = useState('');
  const [tiporeclamo, setTipoReclamo] = useState('');
  const [mensaje, setMensaje] = useState('');

  // Función para manejar el envío del formulario
  const manejarEnvio = async (event) => {
    event.preventDefault();

    // Crear el objeto de reclamo con los datos del formulario


    const creado=await crearReclamo(
      dni
    ,idedificio
    ,descripcion
    ,idunidad
    ,tiporeclamo
    )

      if (creado!=null) {
        setMensaje('Reclamo creado con éxito.');
        console.log('Reclamo creado:', creado);
      } else {
        console.log("Hubo un error al crear el reclamo.");
        setMensaje('Hubo un error al crear el reclamo.');
      }


    // Limpiar el formulario después de enviar
    setDni('');
    setIdEdificio('');
    setDescripcion('');
    setIdUnidad('');
    setTipoReclamo('');
  };

  return (
    <div className='container bg-success'>
      <h3> CrearReclamo</h3>
      <hr />  
      <form onSubmit={manejarEnvio}>
        <div className="mb-3">
          <label htmlFor="dni" className="form-label">
            DNI:
          </label>
          <input
            type="text"
            id="dni"
            className="form-control"
            placeholder='ejemplo: 22456789'
            value={dni}
            onChange={(e) => setDni(e.target.value)}
            required
          />
        </div>

        <div className="mb-3">
          <label htmlFor="idedificio" className="form-label">
            ID del Edificio:
          </label>
          <input
            type="text"
            id="idedificio"
            className="form-control"
            value={idedificio}
            onChange={(e) => setIdEdificio(e.target.value)}
            required
          />
        </div>

        <div className="mb-3">
          <label htmlFor="descripcion" className="form-label">
            Descripción del Reclamo:
          </label>
          <textarea
            id="descripcion"
            className="form-control"
            value={descripcion}
            onChange={(e) => setDescripcion(e.target.value)}
            required
          />
        </div>

        <div className="mb-3">
          <label htmlFor="idunidad" className="form-label">
            ID de la Unidad:
          </label>
          <input
            type="text"
            id="idunidad"
            className="form-control"
            value={idunidad}
            onChange={(e) => setIdUnidad(e.target.value)}
            required
          />
        </div>

        <div className="mb-3">
          <label htmlFor="tiporeclamo" className="form-label">
            Tipo de Reclamo:
          </label>
          <select
            id="tiporeclamo"
            className="form-select"
            value={tiporeclamo}
            onChange={(e) => setTipoReclamo(e.target.value)}
            required
          >
            <option value="">Seleccionar tipo de reclamo</option>
            <option value="0">0</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
          </select>
        </div>

        <button type="submit" className="btn btn-primary mb-2">
          Crear Reclamo
        </button>
      </form>

    </div>
  )
}
