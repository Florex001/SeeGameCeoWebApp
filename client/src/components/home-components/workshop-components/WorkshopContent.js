import React, { useState } from 'react'
import Car from './Car'

function WorkshopContent() {
  const [selected, setSelected] = useState(0); //ide majd a munka idje
  return (
    <div className='workshop-content-container'>
      <div className='workshop-cars-container'>
        <Car id={1} selected={selected} setSelected={setSelected} />
        <Car id={2} selected={selected} setSelected={setSelected} />
      </div>
      <div className='workshop-overview-container'>
        {selected === 0 &&
          <span>Válassz ki egy autót!</span>
        }
        {selected === 1 &&
          <span>Autó 1 tartalom</span>
        }
        {selected === 2 &&
          <span>Autó 2 tartalom</span>
        }
      </div>
    </div>
  )
}

export default WorkshopContent