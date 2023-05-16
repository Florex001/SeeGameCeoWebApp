import React, { useState, useEffect } from 'react'
import { motion } from 'framer-motion'
import { LaptopOutlined } from '@ant-design/icons'
import WorkshopContent from './WorkshopContent';

function WorkshopSelect({ myWorkshop }) {

  const [workerCount, setWorkerCount] = useState(0);
  const [lejarat, setLejarat] = useState(0);

  useEffect(() => {

    const today = new Date();
    const expire = new Date(myWorkshop.lejarat);

    const diff = Math.abs(expire.getTime() - today.getTime());

    setLejarat(Math.ceil(diff / (1000 * 60 * 60 * 24)));

    let counter = 0;
    if (myWorkshop.dolgozo1 !== null) {
      counter++
    }
    if (myWorkshop.dolgozo2 !== null) {
      counter++
    }
    if (myWorkshop.dolgozo3 !== null) {
      counter++
    }
    if (myWorkshop.dolgozo4 !== null) {
      counter++
    }
    if (myWorkshop.dolgozo5 !== null) {
      counter++
    }
    if (myWorkshop.dolgozo6 !== null) {
      counter++
    }
    if (myWorkshop.dolgozo7 !== null) {
      counter++
    }
    if (myWorkshop.dolgozo8 !== null) {
      counter++
    }
    if (myWorkshop.dolgozo9 !== null) {
      counter++
    }
    if (myWorkshop.dolgozo10 !== null) {
      counter++
    }
    if (myWorkshop.dolgozo11 !== null) {
      counter++
    }
    if (myWorkshop.dolgozo12 !== null) {
      counter++
    }
    setWorkerCount(counter);
  }, [])

  const [isOpen, setIsOpen] = useState(false);

  return (
    <motion.div layout onClick={() => setIsOpen(!isOpen)} className='workshop-select'>
      <div className='title-widgets-container'>
        <motion.h2 layout="position"><i><LaptopOutlined /></i>{myWorkshop && myWorkshop.muhelynev}</motion.h2>
        {isOpen &&
          <div onClick={(e) => e.stopPropagation()} className='widgets-container'>
            <div className='widget second'>
              <span>Tagok: {workerCount}db</span>
            </div>
            <div className='widget third'>
              <span>Folyamatban lévő autók: 2db</span>
            </div>
            <div className='widget four'>
              <span>Lejárat: {lejarat}nap</span>
            </div>
          </div>
        }
        {isOpen &&
          <div className='exit-button'>
            <span>-</span>
          </div>
        }
      </div>
      {isOpen &&
        <hr className='hr-style'></hr>
      }



      <div onClick={(e) => e.stopPropagation()} className='container'>
        {isOpen &&
          <motion.div>
            <WorkshopContent />
          </motion.div>
        }
      </div>
    </motion.div>
  )
}

export default WorkshopSelect