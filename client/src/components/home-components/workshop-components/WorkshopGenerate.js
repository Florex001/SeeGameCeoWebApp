import React, { useEffect, useState } from 'react'
import { motion } from 'framer-motion';
import { Button, Modal, Input, DatePicker } from 'antd';
import locale from 'antd/es/date-picker/locale/hu_HU';
import axios from "axios";
import { toast } from 'react-hot-toast';
import Select from 'react-select'
import { RawPurePanel } from 'antd/es/popover/PurePanel';

function WorkshopGenerate({ setCreateWorkshop }) {

    const [icNames, setIcNames] = useState([]);
    const [selectedIcNames, setSelectedIcNames] = useState([]);
    const [inputValue, setInputValue] = useState('');

    function handleChange(selectedIcNames) {
        setSelectedIcNames(selectedIcNames);
    }

    function handleInputChange(inputValue) {
        setInputValue(inputValue);
    }

    useEffect(() => {
        axios.get("http://localhost:9000/getallicname").then((response) => {
            console.log(response.data)
            setIcNames(response.data)
        })
    }, [])

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

    const [workshopName, setWorkshopName] = useState("");
    const [workshopId, setWorkshopId] = useState(0);

    const [expireDate, setExpireDate] = useState(new Date());

    const submitGenerate = () => {
        if (workshopName === "" || workshopId === "") {
            toast.error("Hiányzik a műhely neve/id-ja!")
        } else {
            const data = {
                muhelyid: workshopId,
                muhelynev: workshopName,
                dolgozo1: selectedIcNames[0] && selectedIcNames[0].value,
                dolgozo2: selectedIcNames[1] && selectedIcNames[1].value,
                dolgozo3: selectedIcNames[2] && selectedIcNames[2].value,
                dolgozo4: selectedIcNames[3] && selectedIcNames[3].value,
                dolgozo5: selectedIcNames[4] && selectedIcNames[4].value,
                dolgozo6: selectedIcNames[5] && selectedIcNames[5].value,
                dolgozo7: selectedIcNames[6] && selectedIcNames[6].value,
                dolgozo8: selectedIcNames[7] && selectedIcNames[7].value,
                dolgozo9: selectedIcNames[8] && selectedIcNames[8].value,
                dolgozo10: selectedIcNames[9] && selectedIcNames[9].value,
                dolgozo11: selectedIcNames[10] && selectedIcNames[10].value,
                dolgozo12: selectedIcNames[11] && selectedIcNames[11].value,
                lejarat: expireDate,
            }
            axios.post("http://localhost:9000/createworkshop", data).then((response) => {
                if (response.data.error) {
                    toast.error(response.data.message);
                } else {
                    if (response.data.message === "Már van egy műhely a tulajdonodban.") {
                        toast.error(response.data.message);
                    } else {
                        toast.success(response.data.message);
                        setCreateWorkshop(2);
                    }
                }
            })
        }
    }


    return (

        <>
            <motion.h2 onClick={showModal} whileHover={{ scale: 1.1 }}>...vagy adj hozzá újat.</motion.h2>
            <Modal title="Műhely létrehozás" footer={null} open={isModalOpen} onOk={handleOk} onCancel={handleCancel}>
                <div className='workshop-create-container'>
                    <div className='workshop-create-input'>
                        <Input onChange={(e) => setWorkshopName(e.target.value)} placeholder='Műhely neve...' style={{
                            width: 200,
                            height: 30,
                        }} />
                        <p>(szerveren lévő műhelyed neve)</p>
                    </div>
                    <div className='workshop-create-input'>
                        <Input onChange={(e) => setWorkshopId(e.target.value)} placeholder='Műhely ID-ja' style={{
                            width: 70,
                            height: 30,
                        }} />
                        <p>(szerveren lévő műhelyed ID-ja)</p>
                    </div>
                    <Select
                        options={icNames}
                        noOptionsMessage={() => "Nincs több lehetőség."}
                        isMulti value={selectedIcNames}
                        onChange={handleChange}
                        placeholder="Add meg a dolgozók nevét!"
                        onInputChange={handleInputChange}
                        inputValue={inputValue}
                        menuIsOpen={inputValue.length > 1}
                    />


                    <div className='workshop-create-input'>
                        <DatePicker locale={locale} style={{ marginTop: "0.5rem", marginRight: "0.5rem" }} size="medium" placeholder="Dátum kiválasztása" />
                        <p>(lejárat dátuma)</p>
                    </div>
                    <div className='workshop-create-input'>
                        <Button onClick={submitGenerate} type="primary" style={{ marginTop: '0.3rem' }}>Létrehozás</Button>

                    </div>
                </div>
            </Modal>
        </>
    )
}

export default WorkshopGenerate