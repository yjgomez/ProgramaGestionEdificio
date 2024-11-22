import React, { useEffect, useState } from 'react'
import { fetchAllReclamos } from '../apis/fetchAllReclamos'
import {cambiarEstadoReclamo} from "../apis/cambiarEstadoReclamo"
export const ListaReclamos = () => {

    const [reclamos, setReclamos] = useState([])
    const [refresh, setRefresh] = useState(false)

    const doFetch=async()=>{
        const reclamosbuscados =await fetchAllReclamos()
        if(reclamosbuscados!=null){
            console.log("encontre reclamos!!!!");
            
            setReclamos(reclamosbuscados)
        }
    }
    useEffect(() => {
      
        doFetch();
    }, [refresh])

    const onCambiarEstado=async(numero, nuevoestado)=>{
        const esperar =await cambiarEstadoReclamo(numero,nuevoestado)
        //refresh
        setRefresh(!refresh)
    }
    
  return (
    <div className='border border-dark'>
        <h3>Lista de Reclamos:</h3>
        <hr />
        {reclamos.map((elem)=>{
            console.log("descripcion",elem.descripcion);
            
            return(
                <div key={elem.numero} className='p-2'>
                        <h3 >descripcion: {elem.descripcion}</h3>
                        <h3 className='bg-primary'>numero :{elem.numero}</h3>
                        <h3>estado :{elem.estado}</h3>
                        <h3>ubicacion :{elem.ubicacion}</h3>
                        <div className='d-flex  justify-content-around mb-2'>
                            <button
                                onClick={()=>onCambiarEstado(elem.numero,"enProceso")} 
                                className='btn btn-info'>procesar reclamo</button>
                            <button  
                                onClick={()=>onCambiarEstado(elem.numero,"desestimado")} 
                                className='btn btn-primary'>desestimar reclamo</button>
                            <button 
                                onClick={()=>onCambiarEstado(elem.numero,"terminado")} 
                                className='btn btn-danger'>terminar reclamo</button>
                            <button 
                                onClick={()=>onCambiarEstado(elem.numero,"anulado")} 
                                className='btn btn-danger'>anular reclamo</button>
                        </div>
                     
                </div>
            )
        })}
    </div>
  )
}

/*
[identificador]
      ,[idreclamo]
      ,[idtiporeclamo]
      ,[descripcion]
      ,[documento]
      ,[estado]
      ,[ubicacion]
*/ 