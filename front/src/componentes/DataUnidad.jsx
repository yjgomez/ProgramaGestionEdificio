import React, { useState } from 'react'
import { getUnidadById } from '../apis/getUnidadById';
import { AgregarDuenio } from '../formularios/AgregarDuenio';
import { AgregarInquilino } from '../formularios/AgregarInquilino';
import { LiberarUnidad } from '../formularios/LiberarUnidad';
import { TransferirUnidad } from '../formularios/TransferirUnidad';


export const DataUnidad = () => {
  const [unidad, setUnidad] = useState(null)
  const [idUnidad, setIdUnidad] = useState('');
  //const [refresh, setRefresh] = useState(false);


  const manejarPeticionGet = async (event) => {
    event.preventDefault();
    const unidadbuscada=await getUnidadById(idUnidad);
    if(unidadbuscada!=null){
      setUnidad(unidadbuscada);
    }
  };
  
  //const duenio = unidad!=null && unidad.duenios.length > 0 ? unidad.duenios[unidad.duenios.length-1] : "Sin dueño";
  //const inquilino = unidad!=null && unidad.inquilinos.length > 0 ? unidad.inquilinos[unidad.inquilinos.length-1] : "Sin inquilino";
  const obtenerDuenios=()=>{
    if(unidad!=null && unidad.duenios.length > 0) {
      let dueños=""
      return unidad.duenios.map((elem)=>dueños+" "+elem.documento+" ,nombre: "+elem.nombre+" ;") 
    }
    return "SIN DUEÑOS"
  }

  const obtenerInquilinos=()=>{
    if(unidad!=null && unidad.inquilinos.length > 0) {
      let inquilinos=""
      return unidad.inquilinos.map((elem)=>inquilinos+" "+elem.documento+" ,nombre: "+elem.nombre+" ;") 
    }
    return "SIN INQUILINOS"
  }
  return (
    <div className='bg-light'>
      <h3>  Datos de la unidad:  </h3>
      <hr />
      <form onSubmit={manejarPeticionGet}>
        <input
            type="number"
            min={1}
            id="idUnidad"
            className="form-control"
            value={idUnidad}
            onChange={(e) => setIdUnidad(e.target.value)}
            placeholder="Ingrese el ID de la unidad"
            required
          />
        <button type="submit" className="btn btn-outline-primary">
          Obtener Unidad
        </button>

      </form>
      {unidad!=null? 
      
      <div className='containter  p-2 m-2'>
        <h2 className=' bg-primary text-dark p-2'>DATOS DE LA UNIDAD:</h2>
          <h3>id unidad:{unidad.id}</h3>
          <h3>piso unidad:{unidad.piso}</h3>
          <h3>numero unidad:{unidad.numero}</h3>
          <h3>duenios unidad:{obtenerDuenios()}</h3>
          <h3>inquilinos unidad:{obtenerInquilinos()}</h3>
      </div>
      :
      null
      }
        <hr />
        <AgregarDuenio/>
        <hr />
        <AgregarInquilino/> 
        <hr />
       

        <TransferirUnidad/>
        <hr />

        <LiberarUnidad/>
    </div>
  )
}
/*
"id": 1,
    "piso": "43",
    "numero": "5",
    "duenios": [
        {
            "documento": "DNI 89231201",
            "nombre": "JUANA, HERNANDEZ"
        }
    ],
    "inquilinos": []
*/ 