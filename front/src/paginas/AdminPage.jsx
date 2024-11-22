import React, { useState } from 'react'
import {crearPersona} from "../apis/crearPersona"
import { CrearPersona } from '../formularios/CrearPersona'
import { CrearEdificio } from '../formularios/CrearEdificio'
import { CrearUnidad } from '../formularios/CrearUnidad'
import { CrearReclamo } from '../formularios/CrearReclamo'
import { ListaReclamos } from '../listas/ListaReclamos'
import { otroLogin } from '../apis/otroLogin'
import { BuscarUnidad } from './BuscarUnidad'


export const AdminPage = ({AdminLogueado ,setAdminLogueado}) => {

  //hacer login como admin

  const [dni, setDni] = useState("")
  const [nombre, setNombre] = useState("")
  const  [logueado, setLogueado] = useState(false)

  const manejarlogin=async()=>{

    const respuesta=await otroLogin(dni,nombre);
    if(respuesta==true){
      console.log("logueado con exito");
      setAdminLogueado(true)
      //setNombre(respuesta.nombre)
    }
  }
   return(
    AdminLogueado?
    <div className='container'>
         <h3>----------AdminPage----------</h3>
         <hr />
        <CrearPersona/>
        <hr />
        <CrearEdificio/>
        <hr />
        <CrearUnidad />
        <hr />
        <ListaReclamos/>
        <hr />
        <BuscarUnidad/>
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
