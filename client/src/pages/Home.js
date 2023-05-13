import React, { useState } from 'react'
import WorkshopSelect from '../components/home-components/workshop-components/WorkshopSelect'
import { PlusOutlined } from '@ant-design/icons'
import { motion } from 'framer-motion'
import WorkshopGenerate from '../components/home-components/workshop-components/WorkshopGenerate';
import { Toaster } from 'react-hot-toast';

function Home() {

  const [selected, setSelected] = useState(true);
  const [selectedVisible, setSelectedVisible] = useState(0);

  const handleClick = (wNum) => {
    setSelected(!selected);
    if (selectedVisible === 0) {
      setSelectedVisible(wNum)
    } else {
      setSelectedVisible(0)
    }
  }

  return (
    <div className='home-container'>
      <Toaster position="middle-right" reverseOrder={false} />
      <div className='workshop-container'>
        {selected &&
          <h1>Válaszd ki a műhelyt!</h1>
        }

        <div className='workshop-selectors'>
          {selectedVisible === 1 || selectedVisible === 0 ?
            <motion.div whileHover={{scale: selected ? 1.05 : 1}} onClick={() => handleClick(1)}>
              <WorkshopSelect num={"1"} />
            </motion.div> :
            <></>
          }

          {selectedVisible === 2 || selectedVisible === 0 ?
            <motion.div whileHover={{scale: selected ? 1.05 : 1}} onClick={() => handleClick(2)}>
              <WorkshopSelect num={"2"} />
            </motion.div> :
            <></>
          }
          {selectedVisible === 3 || selectedVisible === 0 ?
            <motion.div whileHover={{scale: selected ? 1.05 : 1}} onClick={() => handleClick(3)}>
              <WorkshopSelect num={"3"} />
            </motion.div> :
            <></>
          }
        </div>




        <div className='desc-plus-container'>
          {selected &&
            <WorkshopGenerate />
          }
        </div>
      </div>
    </div>
  )
}

export default Home