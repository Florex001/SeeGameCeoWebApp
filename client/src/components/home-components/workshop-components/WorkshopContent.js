import React, { useState, useEffect } from "react";
import { PlusOutlined, CarOutlined } from "@ant-design/icons";
import Car from "./Car";
import { motion } from "framer-motion";
import { Modal, Input } from "antd";
import axios from "axios";
import { toast } from "react-hot-toast";
import { ChromePicker } from "react-color";

function WorkshopContent({ workshopId, works, selected, setSelected, setAdded, added }) {

  const [modalVisible, setModalVisible] = useState(false);

  const [carName, setCarName] = useState("");
  const [carPrice, setCarPrice] = useState(0);
  const [colorCode, setColorCode] = useState("");

  const handleOpenModal = () => {
    setModalVisible(true);
  };

  const handleCloseModal = () => {
    setModalVisible(false);
  };

  const handleChangeComplete = (color) => {
    setColorCode(color.hex);
  };

  const handleColorChange = (color, evemt) => {
    // color = {
    //   hex: '#333',
    //   rgb: {
    //     r: 51,
    //     g: 51,
    //     b: 51,
    //     a: 1,
    //   },
    //   hsl: {
    //     h: 0,
    //     s: 0,
    //     l: .20,
    //     a: 1,
    //   },
    // }
  };

  const submitWorkAdd = () => {
    console.log(colorCode);

    if (carName === "" || carPrice === 0) {
      toast.error("Tölts ki minden mezőt!");
    } else {
      const data = {
        auto: carName,
        autoar: carPrice,
        autoszine: colorCode,
      };
      axios
        .post(`http://localhost:9000/api/user/v4/createjob/${workshopId}`, data)
        .then((response) => {
          if (response.data.error) {
            toast.error(response.data.error);
          } else {
            toast.success(response.data.message);
            setModalVisible(false);
            setCarName("");
            setCarPrice("");
            setAdded(!added)
          }
        });
    }
  };

  return (
    <div className="workshop-content-container">
      <Modal
        open={modalVisible}
        onCancel={handleCloseModal}
        onOk={handleCloseModal}
        footer={null}
      >
        <div className="plus-work-modal-container">
          <i style={{ color: `${colorCode}` }}>
            <CarOutlined />
          </i>
          <div className="work-add-input">
            <span>Autó neve: </span>
            <Input
              onChange={(e) => setCarName(e.target.value)}
              style={{ width: "200px", height: "25px" }}
            />
          </div>
          <div className="work-add-input ketto">
            <span>Autó ára: </span>
            <div className="placeholder">
              <Input
                onChange={(e) => setCarPrice(e.target.value)}
                style={{ width: "200px", height: "25px" }}
              />
              <span>(csak számok (pl.: 320000))</span>
            </div>
          </div>
          <div className="work-add-input2">
            <span>Autó színe: </span>
            <ChromePicker
              className="zindexup"
              color={colorCode}
              onChange={handleColorChange}
              onChangeComplete={handleChangeComplete}
            />
          </div>
          <div className="work-add-submit" onClick={submitWorkAdd}>
            Létrehozás
          </div>
        </div>
      </Modal>
      <div className="workshop-cars-container">
        {works &&
          works.map((work) => {
            return (
              <Car
                id={work.id}
                selected={selected}
                setSelected={setSelected}
                work={work}
                added={added}
                setAdded={setAdded}
              />
            );
          })}
        {works.length === 0 && (
          <motion.div
            whileHover={{ scale: 1.05 }}
            className="plus-work-container"
            onClick={handleOpenModal}
          >
            <i>
              <PlusOutlined />
            </i>
            <span>Autó hozzáadás</span>
          </motion.div>
        )}
        {works.length === 1 && (
          <motion.div
            whileHover={{ scale: 1.05 }}
            className="plus-work-container"
            onClick={handleOpenModal}
          >
            <i>
              <PlusOutlined />
            </i>
            <span>Autó hozzáadás</span>
          </motion.div>
        )}
      </div>
      <motion.div className="workshop-overview-container">
        {selected === -1 && (
          <span>Adj hozzá egy autót a műveletek elkezdéséhez!</span>
        )}
        {selected === 0 && <span>Válassz ki egy autót!</span>}
        {selected === 1 && <span>Autó 1 tartalom</span>}
        {selected === 2 && <span>Autó 2 tartalom</span>}
      </motion.div>
    </div>
  );
}

export default WorkshopContent;
