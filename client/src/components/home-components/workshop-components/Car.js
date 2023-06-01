import React, { useEffect, useState } from "react";
import { CarOutlined, PlusOutlined, CloseOutlined } from "@ant-design/icons";
import { motion } from "framer-motion";
import axios from "axios";
import { Button, Modal } from "antd";
import toast from "react-hot-toast";
import Select from "react-select";

function Car({ selected, setSelected, id, work, added, setAdded }) {

  axios.defaults.withCredentials = true;
  
  const [processes, setProcesses] = useState([]);
  const [icNames, setIcNames] = useState([]);
  const [selectedIcNames, setSelectedIcNames] = useState([]);

  const [selectedProcess, setSelectedProcess] = useState(0);

  const [isModalOpen, setIsModalOpen] = useState(false);
  const showModal = () => {
    setIsModalOpen(true);
  };
  const handleOk = () => {
    setIsModalOpen(false);
  };
  const handleCancel = () => {
    setIsModalOpen(false);
  };

  const [inputValue, setInputValue] = useState("");

  function handleChange(selectedIcNames) {
    setSelectedIcNames(selectedIcNames);
  }

  function handleInputChange(inputValue) {
    setInputValue(inputValue);
  }

  const customStyles = {
    control: (provided, state) => ({
      ...provided,
      borderColor: state.isFocused ? "#93BFCF" : provided.borderColor,
      "&:hover": {
        borderColor: state.isFocused ? "#93BFCF" : provided.borderColor,
      },
    }),
    multiValue: (provided, state) => ({
      ...provided,
      backgroundColor: "#93BFCF",
      borderRadius: "12px",
      color: "black",
      boxShadow: "rgba(99, 99, 99, 0.2) 0px 2px 8px 0px",
    }),
    multiValueLabel: (provided, state) => ({
      ...provided,
      color: "black",
    }),
    multiValueRemove: (provided, state) => ({
      ...provided,
      color: "black",
      ":hover": {
        color: "#E06469",
      },
    }),
  };

  useEffect(() => {
    axios
      .get(`http://localhost:9000/api/user/v4/getprocess/${work.id}`)
      .then((response) => {
        if (response.data.message) {
          const rendezett = [...response.data.message].sort(
            (a, b) => a.folyamat - b.folyamat
          );
          setProcesses(rendezett);
        }
      });
  }, [selectedIcNames, added]);

  useEffect(() => {
    axios
        .get(`http://localhost:9000/api/user/v4/getworkerbyworkshop/${work.muhelyId}`)
        .then((response) => {
          setIcNames(response.data.message)
        });
  })

  const handleClick = (num) => {
    if(selectedProcess === 0){
      setSelectedProcess(num);
    }else {
      toast.error("Előbb töröld a választott folyamatot!")
    }
    
  }

  const submitAddProcess = () => {
    console.log(selectedIcNames)
    const data = {
      folyamat: selectedProcess,
      icnev: selectedIcNames.value,
    }
    axios.post(`http://localhost:9000/api/user/v4/createprocess/${work.id}`, data).then((response) => {
      if(response.data.message){
        toast.success("Sikeresen hozzáadtad a folyamatot.")
        setSelectedIcNames([]);
      }
    })
  };

  const handleDone = () => {
    axios.post(`http://localhost:9000/api/user/v4/pay/${work.id}`).then((response) => {
      if(response.data.message) {
        console.log(response.data.message);
        setAdded(!added)
      }else {
        console.log(response.data.error);
      }
    })
  }

  const handleProcessDelete = (processId) => {
    axios.delete(`http://localhost:9000/api/user/v4/deleteprocess/${processId}`).then((response) => {
      if(response.data.message) {
        toast.success(response.data.message);
        setSelectedIcNames()
      } else {
        toast.error(response.data.error)
      }
    })
  }

  return (
    <div

      className={
        selected === id ? "car-container car-selected" : "car-container"
      }
    >
      <Modal open={isModalOpen} footer={null} onCancel={handleCancel}>
        <motion.div layout className="add-process-container">
        <motion.div>
          <span className="selectspan">Válaszd ki a hozzáadni kívánt folyamatot!</span>
          {selectedProcess === 0 && (
            <motion.div layout className="processes-container">
              <motion.div
                onClick={() => handleClick(1)}
                className="process-process"
                id="gradient1"
              >
                Csiszolás
              </motion.div>
              <motion.div
                onClick={() => handleClick(2)}
                className="process-process"
                id="gradient2"
              >
                Alapozás
              </motion.div>
              <motion.div
                onClick={() => handleClick(3)}
                className="process-process"
                id="gradient3"
              >
                Fényezés
              </motion.div>
            </motion.div>
          )}
          {selectedProcess === 1 && (
            <motion.div className="processes-container">
              <motion.div
                className="placeholder2"
              >
              </motion.div>
              <motion.div
                onClick={() => handleClick(2)}
                className="process-process"
                id="gradient2"
              >
                Alapozás
              </motion.div>
              <motion.div
                onClick={() => handleClick(3)}
                className="process-process"
                id="gradient3"
              >
                Fényezés
              </motion.div>
            </motion.div>
          )}

          {selectedProcess === 2 && (
            <motion.div className="processes-container">
              <motion.div
                onClick={() => handleClick(1)}
                className="process-process"
                id="gradient1"
              >
                Csiszolás
              </motion.div>
              <motion.div
                className="placeholder2"
              ></motion.div>
              <motion.div
                onClick={() => handleClick(3)}
                className="process-process"
                id="gradient3"
              >
                Fényezés
              </motion.div>
            </motion.div>
          )}

          {selectedProcess === 3 && (
            <motion.div className="processes-container">
              <motion.div
                onClick={() => handleClick(1)}
                className="process-process"
                id="gradient1"
              >
                Csiszolás
              </motion.div>
              <motion.div
                onClick={() => handleClick(2)}
                className="process-process"
                id="gradient2"
              >
                Alapozás
              </motion.div>
              <motion.div
                className="placeholder2"
              ></motion.div>
            </motion.div>
          )}

          <motion.div className="selected-process-container">
            <span>
              Választott folyamat:
            </span>
            <motion.div layout className="processes-container">
              {selectedProcess === 1 && (
                <motion.div onClick={() => setSelectedProcess(0)} className="process-process" id="gradient1">
                  Csiszolás
                </motion.div>
              )}
              {selectedProcess === 2 && (
                <motion.div onClick={() => setSelectedProcess(0)} className="process-process" id="gradient2">
                  Alapozás
                </motion.div>
              )}
              {selectedProcess === 3 && (
                <motion.div onClick={() => setSelectedProcess(0)} className="process-process" id="gradient3">
                  Fényezés
                </motion.div>
              )}
            </motion.div>
            
          </motion.div>
          <div className="process-add-name-container">
              <span>Folyamatot végző neve: </span>
              <div className="process-create-input">
              <Select
              options={icNames}
              noOptionsMessage={() => "Nincs több lehetőség."}
              value={selectedIcNames}
              onChange={handleChange}
              placeholder="Add meg a dolgozók nevét!"
              onInputChange={handleInputChange}
              inputValue={inputValue}
              menuIsOpen={inputValue.length > 1}
              styles={customStyles}
            />
            </div>
          </div>
          <div onClick={submitAddProcess} className="submit-process-add">
            Hozzáadás
          </div>
        </motion.div>
        </motion.div>
      </Modal>
      <div className="car-header">
        <i style={{ color: `${work.autoszine}` }}>
          <CarOutlined />
        </i>
        <span className="car-name">
          {work.auto}
          <span className="car-price">{work.autoar}$</span>
        </span>
        <div className="color-box-container">
          <span>Szín:</span>
          <div
            className="color-box"
            style={{ backgroundColor: `${work.autoszine}` }}
          ></div>
        </div>
      </div>
      <div className="car-content">
        <span className="material-price">
          Anyagköltség: {work.anyagkoltseg}$
        </span>
        <hr className="hr-style"></hr>
        <span style={{ fontSize: "1.1rem" }}>Folyamatok</span>

        {processes.map((item) => {
          return (
            <div className="process-container">
              <div className="delete-process-icon" onClick={() => handleProcessDelete(item.id)}>
              <CloseOutlined />
              </div>
              <div className="process-name" id={`gradient${item.folyamat}`}>
                {item.folyamat === 1 && <span>Csiszolás</span>}
                {item.folyamat === 2 && <span>Alapozás</span>}
                {item.folyamat === 3 && <span>Fényezés</span>}
              </div>
              <div className="process-worker">
                <span>{item.icnev}</span>
              </div>
            </div>
          );
        })}

        <motion.div
          onClick={showModal}
          whileHover={{ scale: 1.1 }}
          className="plus-process"
        >
          <i>
            <PlusOutlined />
          </i>
          <span>folyamat hozzáadás.</span>
        </motion.div>
        <hr className="hr-style"></hr>
        <motion.div whileHover={{ scale: 1.1 }} onClick={handleDone} className="submit-processes">
          Véglegesítés
        </motion.div>
      </div>
    </div>
  );
}

export default Car;
