import { useState } from "react";
import { fetchEdificios } from "./apis/fetchEdificios";
import { fetchEdificioPorId } from "./apis/fetchEdificioPorId";
import { fetchAllReclamos } from "./apis/fetchAllReclamos";
import './App.css'; 

export const App=()=> {
  
  const [edificios, setEdificios] = useState([])
  const [reclamos, setReclamos] = useState([])
  //
  const doFetcheEdificios=async()=>{
    const buscado=await fetchEdificios(2);
    console.log("buscado",buscado);
    
    if(buscado){
      setEdificios(buscado)
    }
  }
  const doFetchReclamos=async()=>{
    const buscado=await fetchAllReclamos();
    console.log("buscado",buscado);
    
    if(buscado){
      setReclamos(buscado)
    }
  }
  return (
    <div className="App">
    
      <h3>SOY ADMIN</h3>
      <hr/>
      <button 
        onClick={ doFetcheEdificios}
        type="button">fetch all edificios
      </button>
      <h3>cantidad: {edificios.lenght}</h3>
      {edificios.map((elem)=>{
        return(
          <div key={elem.codigo}>
              <h3>nombre:{elem.nombre}</h3>
              <h3>direccion:{elem.direccion}</h3>
          </div>
        )
      })}
      <hr/>
      <button 
        onClick={ doFetchReclamos}
        type="button">fetch all RECLAMOS
      </button>
      <h3>cantidad: {reclamos.lenght}</h3>
      {reclamos.map((elem)=>{
        return(
          <div key={elem.codigo}>
              <h3>nombre:{elem.nombre}</h3>
              <h3>direccion:{elem.direccion}</h3>
          </div>
        )
      })}
    </div>
  );
}

//{JSON.stringify(lista[0])}