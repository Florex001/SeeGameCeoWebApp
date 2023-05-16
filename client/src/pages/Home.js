import React, { useState, useEffect } from 'react'
import WorkshopSelect from '../components/home-components/workshop-components/WorkshopSelect'
import { AnimatePresence, motion } from 'framer-motion'
import WorkshopGenerate from '../components/home-components/workshop-components/WorkshopGenerate';
import { Toaster } from 'react-hot-toast';
import axios from 'axios';

function Home() {

  const [myWorkshop, setMyWorkshop] = useState([]);
  const [assistWorkshops, setAssistWorkshops] = useState([]);
  const [createWorkshop, setCreateWorkshop] = useState(0);

  // V3 = 0, LV = 1, SEMMI = 3
  const [selectedLobby, setSelectedLobby] = useState(0);
  const [lobbyShow, setLobbyShow] = useState(3)


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

  const spring = {
    type: "spring",
    stiffness: 700,
    damping: 30
  };

  const [isOn, setIsOn] = useState(false);

  const toggleSwitch = () => {
    setIsOn(!isOn);
    if (selectedLobby === 3) {
      setSelectedLobby(1);
    }
    if (selectedLobby === 1) {
      setSelectedLobby(0);
    }
    if (selectedLobby === 0) {
      setSelectedLobby(1)
    }
    console.log(selectedLobby)
  };


  return (
    <>
      {lobbyShow === 3 &&

        <AnimatePresence>
          <motion.div data-ison={isOn} className='overlay2'></motion.div>
          <motion.div layout exit={{ opacity: [80, 50, 0] }} className='lobby-container'>


            <motion.div className='lobby-message-container' initial={{ opacity: 0 }} animate={{ opacity: 1 }} exit={{ opacity: 0 }} transition={{ duration: 0.5, delay: 0.2 }}>
              <motion.span>Szia, <span style={{ color: '#3A98B9' }}>Xyr Markovic!</span></motion.span>
              <motion.span>Kérlek válaszd ki a <span style={{ color: '#3A98B9' }}>szervert</span>,</motion.span>
              <motion.span>amelyen játszol!</motion.span>
            </motion.div>
            <motion.div layout className='logos-container' initial={{ opacity: 0 }} animate={{ opacity: 1 }} exit={{ opacity: 0 }} transition={{ duration: 0.5, delay: 0.4 }}>

              <motion.div layout className='v3logo-container'>
                {selectedLobby === 0 &&
                  <motion.img src="https://i.postimg.cc/NMmBcfvQ/v4-manager.png"></motion.img>
                }


              </motion.div>

              <div className="switch" data-ison={isOn} onClick={toggleSwitch}>
                <motion.div className="handle" layout transition={spring} />
              </div>

              <motion.div layout className='lvlogo-container'>

                {selectedLobby === 1 &&
                  <>
                    <motion.img src="https://i.postimg.cc/NMmBcfvQ/v4-manager.png"></motion.img>
                    <motion.span>LasVenturas</motion.span>
                  </>
                }

              </motion.div>
            </motion.div>
            <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} exit={{ opacity: 0 }} onClick={() => setLobbyShow(selectedLobby)} whileHover={{ scale: 1.05 }} className='submit-lobby' >
              Kiválasztás
            </motion.div>

          </motion.div>
        </AnimatePresence>
      }

      {lobbyShow < 3 &&
        <AnimatePresence>
          <motion.div layout className='home-container'>
            <motion.div className='home-header' animate={{ opacity: [0, 100], transition: { duration: 0.5, delay: 0 } }}>
              <motion.img src="https://i.postimg.cc/NMmBcfvQ/v4-manager.png"></motion.img>
              <span>SeeGameCeo {selectedLobby === 1 && "LV"}</span>
            </motion.div>
            <div data-ison={isOn} className='overlay3 gradient5'></div>

            <Toaster position="middle-right" reverseOrder={false} />
            <motion.div className='workshop-container' animate={{ opacity: [0, 100], transition: { duration: 0.5, delay: 0.3 } }}>
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
            </motion.div>

          </motion.div>
        </AnimatePresence>
      }
    </>
  )
}

export default Home