import React, { useEffect, useState } from 'react'
import { motion } from 'framer-motion'
import { LaptopOutlined } from '@ant-design/icons'
import WorkshopContent from './WorkshopContent';

function WorkshopSelect({ myWorkshop }) {

  const [isOpen, setIsOpen] = useState(false);

  return (
    <motion.div layout onClick={() => setIsOpen(!isOpen)} className='workshop-select'>
      <div className='title-widgets-container'>
        <motion.h2 layout="position"><i><LaptopOutlined /></i>{myWorkshop && myWorkshop.muhelynev}</motion.h2>
        {isOpen &&
          <div onClick={(e) => e.stopPropagation()} className='widgets-container'>
            <div className='widget second'>
              <span>Tagok: 3db</span>
            </div>
            <div className='widget third'>
              <span>Folyamatban lévő autók: 2db</span>
            </div>
            <div className='widget four'>
              <span>Lejárat: 2nap</span>
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