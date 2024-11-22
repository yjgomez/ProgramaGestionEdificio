import React, { useState } from 'react'
import { CrearReclamo } from '../formularios/CrearReclamo'
import {login} from "../apis/login"
import { AgregarImagenAReclamo } from '../formularios/AgregarImagenAReclamo'
import { otroLogin } from '../apis/otroLogin'

export const UserPage = ({UserLogueado ,setUserLogueado}) => {
  //fetch mis reclamos
  //fetch mis unidades
  //fetch
  const [dni, setDni] = useState("")
  const [nombre, setNombre] = useState("")
  const  [logueado, setLogueado] = useState(false)

  const manejarlogin=async()=>{

    const respuesta=await otroLogin(dni,nombre);
    if(respuesta==true){
      console.log("logueado con exito");
      setUserLogueado(true)
      //setNombre(respuesta.nombre)
    }
  }
  return (
    UserLogueado?
    
    <div className='container'>
      
      <h3>----------UserPage ---------</h3>
      <hr />
      
      
      <CrearReclamo/>
      <AgregarImagenAReclamo/>
      
    </div>:
    <>
      <h3>NO estas logueado</h3>
      <hr />
      <button
        className='btn btn-primary'
        onClick={ ()=>manejarlogin()}
      >LOG IN</button>
      <input 
        type="text" 
        placeholder='ingrese su numero de dni'
        value={dni}
        onChange={(event)=>setDni(event.target.value)}
        />
        <input 
        type="text" 
        placeholder='contraseña'
        value={nombre}
        onChange={(event)=>setNombre(event.target.value)}
        
        />
        <hr />
    </>
 
  )
}
/*
<h3> faltaria hacer estos endpoints:</h3>
      <h3>--traer todas las unidades de un usuario dado su dni</h3>
      <hr />
      <h3>--traer todos los reclamos de un usuario dado su dni</h3>
      <hr />
      <h3>--agregar una imagen a un reclamo existeten usando id reclamo</h3>
      <hr />
*/ 