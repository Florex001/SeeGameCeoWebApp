import React, { useState, useEffect } from 'react'
import WorkshopSelect from '../components/home-components/workshop-components/WorkshopSelect'
import { PlusOutlined } from '@ant-design/icons'
import { motion } from 'framer-motion'
import WorkshopGenerate from '../components/home-components/workshop-components/WorkshopGenerate';
import { Toaster } from 'react-hot-toast';
import axios from 'axios';

function Home() {

  const [myWorkshop, setMyWorkshop] = useState([]);
  const [assistWorkshops, setAssistWorkshops] = useState([]);
  const [createWorkshop, setCreateWorkshop] = useState(0)


  // az adatok aszinkron betöltése
  function loadMyWorkshop() {
    return axios.get('http://localhost:9000/getmyworkshop')
      .then(response => response.data);
  }

  function loadMyAssistWorkshops() {
    return axios.get('http://localhost:9000/workshopiworkin')
      .then(response => response.data);
  }

  useEffect(() => {
    loadMyWorkshop()
      .then((data) => {
        if (data.error) {
          setMyWorkshop([]);
        } else {
          const muhely = [];
          muhely.push(data.message[0]);
          setMyWorkshop(muhely);
        }



      })
      .catch((error) => {
        console.error(error);
        // hiba kezelése
      });

    loadMyAssistWorkshops()
      .then((data) => {
        const muhelyw = [];
        setAssistWorkshops(data.message);
      })
      .catch((error) => {
        console.error(error);
        // hiba kezelése
      });

  }, [createWorkshop])


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
          {myWorkshop && myWorkshop.map((item, key) => {
            return (
              <div key={key}>
                {selectedVisible === item.id || selectedVisible === 0 ?
                  <motion.div whileHover={{ scale: selected ? 1.05 : 1 }} onClick={() => handleClick(item.id)}>
                    <WorkshopSelect myWorkshop={item} />
                  </motion.div> :
                  <></>
                }
              </div>)
          })
          }

          {assistWorkshops && assistWorkshops.map((item, key) => {
            return (
              <div key={key}>
                {selectedVisible === item.id || selectedVisible === 0 ?
                  <motion.div whileHover={{ scale: selected ? 1.05 : 1 }} onClick={() => handleClick(item.id)}>
                    <WorkshopSelect myWorkshop={item} />
                  </motion.div> :
                  <></>
                }
              </div>)
          })
          }

        </div>




        <div className='desc-plus-container'>
          {myWorkshop.length === 0 &&
            <>
              {selected &&
                <WorkshopGenerate setCreateWorkshop={setCreateWorkshop} />
              }
            </>
          }

        </div>
      </div>
    </div>
  )
}

export default Home