import React from 'react'
import { ListaEdificios } from '../listas/ListaEdificios'
import { ListaPersonas } from '../listas/ListaPersonas'
import { ListaUnidades } from '../listas/ListaUnidades'

export const HomePage = () => {
  return (
    <div className='container'>
      <h3 >-------HomePage----------</h3>
      <hr />
      <ListaEdificios/>
      <hr />
      <ListaPersonas/>
      <hr />
      <ListaUnidades/>
    </div>

  )
}
