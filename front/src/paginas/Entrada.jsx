import React, { useState } from 'react'
import { Route, Routes } from 'react-router-dom'
import { AdminPage } from './AdminPage'
import { UserPage } from './UserPage'
import { HomePage } from './HomePage'
import { Navbar } from '../componentes/Navbar'
import { BuscarUnidad } from './BuscarUnidad'

export const Entrada = () => {
  const [AdminLogueado, setAdminLogueado] = useState(false)
  const [UserLogueado, setUserLogueado] = useState(false)
  return (
    <>
    <Navbar/>
        <Routes>
            <Route path="/" element={<HomePage/>}/>
            <Route path="/user" element={<UserPage UserLogueado={UserLogueado} setUserLogueado={setUserLogueado}/>}/>
            <Route path="/buscarunidad" element={<BuscarUnidad/>}/>
            <Route path="/admin" element={<AdminPage AdminLogueado={AdminLogueado} setAdminLogueado={setAdminLogueado}/>}/> {/* 👈 Renders at /app/ */}
        </Routes>
    </>
  )
}
