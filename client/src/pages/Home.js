import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import WorkshopSelect from "../components/home-components/workshop-components/WorkshopSelect";
import { AnimatePresence, motion } from "framer-motion";
import WorkshopGenerate from "../components/home-components/workshop-components/WorkshopGenerate";
import { Toaster, toast } from "react-hot-toast";
import axios from "axios";

function Home() {
  axios.defaults.withCredentials = true;

  const navigate = useNavigate();

  const [myWorkshop, setMyWorkshop] = useState([]);
  const [assistWorkshops, setAssistWorkshops] = useState([]);
  const [createWorkshop, setCreateWorkshop] = useState(0);

  // V3 = 0, LV = 1, LOBBY = 3, KARAKTERKÉSZÍTŐ/VÁLASZTÓ = 4
  const [selectedLobby, setSelectedLobby] = useState(0);
  const [lobbyShow, setLobbyShow] = useState(3);

  const [isHaveCharacter, setIsHaveCharacter] = useState();
  const [character, setCharacter] = useState([]);
  const [characterName, setCharacterName] = useState("");

  // az adatok aszinkron betöltése
  function loadMyWorkshop() {
    return axios
      .get("http://localhost:9000/api/user/v4/getmyworkshop")
      .then((response) => response.data);
  }

  function loadMyAssistWorkshops() {
    return axios
      .get("http://localhost:9000/api/user/v4/workshopiworkin")
      .then((response) => response.data);
  }

  useEffect(() => {
    axios.get("http://localhost:9000/api/user/auth").then((response) => {
      if (!response.data.loggedin) {
        navigate("/");
      }
    });

    let selectedServer = JSON.parse(localStorage.getItem("selectedServer"));
    const isHaveCharacterReq = JSON.parse(
      localStorage.getItem("isHaveCharacter")
    );


    if (isHaveCharacterReq === "false") {
      setLobbyShow(4);
    } else if (selectedServer === 0 || selectedServer === 1) {
      setLobbyShow(selectedServer);
    } else {
      setLobbyShow(3);
    }

    if (isHaveCharacterReq === "true") {
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
          console.log(error);
          //hiba kezelése
        });

      loadMyAssistWorkshops()
        .then((data) => {
          setAssistWorkshops(data.message);
        })
        .catch((error) => {
          console.error(error);
          // hiba kezelése
        });
    }
  }, [createWorkshop, isHaveCharacter]);
  //useEffect vége

  const [selected, setSelected] = useState(true);
  const [selectedVisible, setSelectedVisible] = useState(0);

  const handleClick = (wNum) => {
    setSelected(!selected);
    if (selectedVisible === 0) {
      setSelectedVisible(wNum);
    } else {
      setSelectedVisible(0);
    }
  };

  const spring = {
    type: "spring",
    stiffness: 700,
    damping: 30,
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
      setSelectedLobby(1);
    }
    console.log(selectedLobby);
  };

  const handleLobbySubmit = () => {
    setLobbyShow(selectedLobby);
    setCreateWorkshop(createWorkshop + 1);
    axios
      .post("http://localhost:9000/api/user/v4/chooseserver")
      .then((response) => {
        if (response.data.error === "Nincs karaktered.") {
          localStorage.setItem("isHaveCharacter", JSON.stringify("false"));
          toast.error("Nincs karaktered, hozz létre egyet.");
          setIsHaveCharacter(false);
          setLobbyShow(4);
        } else if (response.data.server === "v4") {
          localStorage.setItem("isHaveCharacter", JSON.stringify("true"));
          setLobbyShow(0);
          toast.success("V4 adatok betöltve.");
          setCreateWorkshop(createWorkshop + 1);
          setIsHaveCharacter(true);
        }
        localStorage.setItem("selectedServer", JSON.stringify(selectedLobby));
      })
      .catch(function (error) {
        if (error.response) {
          toast.error(error.response);
        }
      });
  };

  const submitCharacter = () => {
    const data = {
      icnev: characterName,
    };
    axios
      .post("http://localhost:9000/api/user/v4/createcharacter", data)
      .then((response) => {
        if (response.data.error === "Már létezik egy ilyen karakter.") {
          toast.error("Már létezik egy ilyen nevű karakter.");
        } else if (response.data.error === "Már van egy karaktered.") {
          toast.error("Szép próbálkozás..");
        } else {
          toast.success("Sikeres karakter létrehozás!");
          setIsHaveCharacter(true);
          setLobbyShow(0);
          localStorage.setItem("isHaveCharacter", JSON.stringify("true"));
        }
      });
  };

  const logout = () => { 
    axios.post("http://localhost:9000/api/user/logout").then((response) => {
      localStorage.clear();
      navigate("/");
      //<span onClick={logout}>logout</span>
    });
  };

  return (
    <>
      {lobbyShow === 3 && (
        <AnimatePresence>
          <span onClick={logout}>logout</span>
          <motion.div data-ison={isOn} className="overlay2"></motion.div>
          <motion.div
            layout
            exit={{ opacity: [80, 50, 0] }}
            className="lobby-container"
          >
            <motion.div
              className="lobby-message-container"
              initial={{ opacity: 0 }}
              animate={{ opacity: 1 }}
              exit={{ opacity: 0 }}
              transition={{ duration: 0.5, delay: 0.2 }}
            >
              <motion.span>
                Szia, <span style={{ color: "#3A98B9" }}>Xyr Markovic!</span>
              </motion.span>
              <motion.span>
                Kérlek válaszd ki a{" "}
                <span style={{ color: "#3A98B9" }}>szervert</span>,
              </motion.span>
              <motion.span>amelyen játszol!</motion.span>
            </motion.div>
            <motion.div
              layout
              className="logos-container"
              initial={{ opacity: 0 }}
              animate={{ opacity: 1 }}
              exit={{ opacity: 0 }}
              transition={{ duration: 0.5, delay: 0.4 }}
            >
              <motion.div layout className="v3logo-container">
                {selectedLobby === 0 && (
                  <motion.img src="https://i.postimg.cc/NMmBcfvQ/v4-manager.png"></motion.img>
                )}
              </motion.div>

              <div className="switch" data-ison={isOn} onClick={toggleSwitch}>
                <motion.div className="handle" layout transition={spring} />
              </div>

              <motion.div layout className="lvlogo-container">
                {selectedLobby === 1 && (
                  <>
                    <motion.img src="https://i.postimg.cc/NMmBcfvQ/v4-manager.png"></motion.img>
                    <motion.span>LasVenturas</motion.span>
                  </>
                )}
              </motion.div>
            </motion.div>
            <motion.div
              onClick={handleLobbySubmit}
              initial={{ opacity: 0 }}
              animate={{ opacity: 1 }}
              exit={{ opacity: 0 }}
              whileHover={{ scale: 1.05 }}
              className="submit-lobby"
            >
              Kiválasztás
            </motion.div>
          </motion.div>
        </AnimatePresence>
      )}

      {lobbyShow === 4 && (
        <motion.div layout className="home-container">
          <motion.div
            className="home-header"
            animate={{
              opacity: [0, 100],
              transition: { duration: 0.5, delay: 0 },
            }}
          >
            <motion.img src="https://i.postimg.cc/NMmBcfvQ/v4-manager.png"></motion.img>
            <span>
              SeeGameCeo - Karakter készítő {selectedLobby === 1 && "LV"}
            </span>
          </motion.div>

          <motion.div
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ opacity: 0 }}
            transition={{ duration: 0.5, delay: 0.4 }}
            className="character-container"
          >
            <motion.span>Úgy látom még nincs karaktered,</motion.span>
            <motion.span>kérlek add meg az IC neved.</motion.span>
            <input
              type="text"
              onChange={(e) => setCharacterName(e.target.value)}
              placeholder="IC név..."
            ></input>
            <motion.div
              onClick={submitCharacter}
              whileHover={{ scale: 1.1 }}
              className="submit-character"
            >
              Folytatás
            </motion.div>
          </motion.div>
          <div data-ison={isOn} className="overlay3 gradient5"></div>
        </motion.div>
      )}

      {lobbyShow < 3 && (
        <AnimatePresence>
          <span onClick={logout}>logout</span>
          <motion.div layout className="home-container">
            <motion.div
              className="home-header"
              animate={{
                opacity: [0, 100],
                transition: { duration: 0.5, delay: 0 },
              }}
            >
              <motion.img src="https://i.postimg.cc/NMmBcfvQ/v4-manager.png"></motion.img>
              <span>SeeGameCeo {selectedLobby === 1 && "LV"}</span>
            </motion.div>
            <div data-ison={isOn} className="overlay3 gradient5"></div>

            <Toaster position="middle-right" reverseOrder={false} />
            <motion.div
              className="workshop-container"
              animate={{
                opacity: [0, 100],
                transition: { duration: 0.5, delay: 0.3 },
              }}
            >
              {selected && <h1>Válaszd ki a műhelyt!</h1>}

              <div className="workshop-selectors">
                {myWorkshop &&
                  myWorkshop.map((item, key) => {
                    return (
                      <div key={key}>
                        {selectedVisible === item.id ||
                        selectedVisible === 0 ? (
                          <motion.div
                            whileHover={{ scale: selected ? 1.05 : 1 }}
                            onClick={() => handleClick(item.id)}
                          >
                            <WorkshopSelect myWorkshop={item} />
                          </motion.div>
                        ) : (
                          <></>
                        )}
                      </div>
                    );
                  })}
                {assistWorkshops &&
                  assistWorkshops.map((item, key) => {
                    return (
                      <div key={key}>
                        {selectedVisible === item.id ||
                        selectedVisible === 0 ? (
                          <motion.div
                            whileHover={{ scale: selected ? 1.05 : 1 }}
                            onClick={() => handleClick(item.id)}
                          >
                            <WorkshopSelect myWorkshop={item} />
                          </motion.div>
                        ) : (
                          <></>
                        )}
                      </div>
                    );
                  })}
              </div>
              <div className="desc-plus-container">
                {myWorkshop.length === 0 && (
                  <>
                    {selected && (
                      <WorkshopGenerate
                        isHaveCharacter={isHaveCharacter}
                        setCreateWorkshop={setCreateWorkshop}
                      />
                    )}
                  </>
                )}
              </div>
            </motion.div>
          </motion.div>
        </AnimatePresence>
      )}
    </>
  );
}

export default Home;
