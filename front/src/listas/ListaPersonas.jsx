import React, { useEffect, useState } from 'react'
import {fetchPersonas} from "../apis/fetchPersonas"

export const ListaPersonas = () => {

  const [personas, setPersonas] = useState([])
  //
  const doFetch=async()=>{
    const buscado=await fetchPersonas();
    console.log("buscado",buscado);
    
    if(buscado){
      setPersonas(buscado)
    }
  }
  useEffect(() => {
    doFetch();

  }, [])
return (
  <div className='border border-dark'>
      <h3>LISTA PERSONAS: </h3>
      <hr />
      {personas.map((elem)=>{
        console.log("elem--->",elem);
        
      return(
        <div key={elem.codigo} className='p-2 border-primary mb-2'>
            <h3 className='bg-primary'>nombre: {elem.nombre}</h3>
            <h3>documento :{elem.documento}</h3>
        </div>
      )
    })}

  </div>
)
}

