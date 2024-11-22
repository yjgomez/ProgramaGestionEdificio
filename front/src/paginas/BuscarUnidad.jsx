import React from 'react'
import { DataUnidad } from '../componentes/DataUnidad'
import { AgregarDuenio } from '../formularios/AgregarDuenio'

export const BuscarUnidad = () => {
  //desde aca operamos sobre la unidad
  //agregar dueño
  //alquilar
  //transferir
  //liberar
    return (
    <div className='container border border-info'>
        <h3 className='bg-info'> ---------- OPERAR sobre una Unidad  ---------     </h3>        
        <hr />
        <DataUnidad/>
        

    </div>
  )
}
