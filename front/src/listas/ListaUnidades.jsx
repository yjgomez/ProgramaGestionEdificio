import React, { useEffect, useState } from 'react'
import { fetchUnidades } from '../apis/fetchUnidades';

export const ListaUnidades = () => {

  const [unidades, setUnidades] = useState([])

  const doFetch=async()=>{
    const buscado=await fetchUnidades();
    console.log("buscado",buscado);
    
    if(buscado){
      setUnidades(buscado)
    }
  }

  useEffect(() => {
    doFetch();

  }, [])
  
  return (
    <div className='border border-dark'>
      <h3> ListaUnidades: </h3>
      <hr />
      {unidades.map((elem)=>{
        const duenio = elem.duenios && elem.duenios.length > 0 ? elem.duenios[0] : "Sin dueño";
        const inquilino = elem.inquilinos && elem.inquilinos.length > 0 ? elem.inquilinos[0] : "Sin inquilino";

      return(
        <div key={elem.id} className='p-2 border border-primary mb-2'>
             <h3 className='bg-primary'>id unidad: {elem.id}</h3>
            <h3 className='bg-primary'>piso: {elem.piso}</h3>
            <h3 className='bg-primary'>numero: {elem.numero}</h3>
            <h3 className='bg-primary'>duenios: {JSON.stringify(duenio)}</h3>
            <h3 className='bg-primary'>inquilinos: {JSON.stringify(inquilino)}</h3>
        </div>
      )
    })}
    </div>
  )
}
/*
"id": 1,
        "piso": "43",
        "numero": "5",
        "duenios": [],
        "inquilinos": []
*/ 

/*
 <h3 className='bg-primary'>duenios: {elem.duenios[0]}</h3>
            <h3 className='bg-primary'>inquilinos: {elem.inquilinos[0]}</h3>
*/ 