import React, {useState} from 'react'
import { CarOutlined, PlusOutlined } from '@ant-design/icons'
import {motion} from "framer-motion"

function Car({selected, setSelected, id}) {
  return (
    <div onClick={() => setSelected(id)} className={selected === id ? 'car-container car-selected' : 'car-container'}>

        <div className='car-header'>
        
        <i><CarOutlined /></i>
        <span className='car-name'>Audi S1<span className='car-price'>320.000$</span></span>
        <div className='color-box-container'>
            <span>Szín:</span>
        <div className='color-box'></div>
        </div>
        </div>
        <div className='car-content'>
        <span className='material-price'>Anyagköltség: 32.000$</span>
        <hr className='hr-style'></hr>
        <span style={{fontSize: "1.1rem"}}>Folyamatok</span>
        <div className='process-container'>
          <div className='process-name' id="gradient">
          <span>Csiszolás</span>
          </div>
          <div className='process-worker'>
          <span>Ermanno Gutierrezzz</span>
          </div>
        </div>
        <div className='process-container'>
          <div className='process-name' id="gradient">
          <span>Csiszolás</span>
          </div>
          <div className='process-worker'>
          <span>Ermanno Gutierrezzz</span>
          </div>
        </div>
        <div className='process-container'>
          <div className='process-name' id="gradient2">
          <span>Alapozás</span>
          </div>
          <div className='process-worker'>
          <span>Ermanno Gutierrezzz</span>
          </div>
        </div>
        <div className='process-container'>
          <div className='process-name' id="gradient3">
          <span>Fényezés</span>
          </div>
          <div className='process-worker'>
          <span>Ermanno Gutierrezzz</span>
          </div>
        </div>
        <motion.div whileHover={{scale: 1.1}} className='plus-process'>
          <i><PlusOutlined /></i>
          <span>folyamat hozzáadás.</span>
        </motion.div>
        <hr className='hr-style'></hr>
        <motion.div whileHover={{scale: 1.1}} className='submit-processes'>
          Véglegesítés
        </motion.div>
        </div>
    </div>
  )
}

export default Car