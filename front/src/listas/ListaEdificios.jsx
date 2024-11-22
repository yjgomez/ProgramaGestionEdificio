import React, { useEffect, useState } from 'react'
import { fetchEdificios } from '../apis/fetchEdificios'

export const ListaEdificios = () => {

    const [edificios, setEdificios] = useState([])
    const [reclamos, setReclamos] = useState([])
    //
    const doFetcheEdificios=async()=>{
      const buscado=await fetchEdificios();
      console.log("buscado",buscado);
      
      if(buscado){
        setEdificios(buscado)
      }
    }
    useEffect(() => {
        doFetcheEdificios();
    }, [])
    
    
  return (
    <div className='border border-dark'>
        <h3>ListaEdificios: </h3>
        <hr />
        {edificios.map((elem)=>{
        return(
          <div key={elem.codigo} className='border-primary mb-2 texto'>
              <h3 className='bg-primary'>nombre:{elem.nombre}</h3>
              <h3 className='bg-primary'>codigo:{elem.codigo}</h3>
              <h3>direccion:{elem.direccion}</h3>
             
          </div>
        )
      })}

    </div>
  )
}
