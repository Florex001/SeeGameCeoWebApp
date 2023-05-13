import React, { useState } from 'react'
import { motion } from 'framer-motion';
import { Button, Modal, Input, DatePicker } from 'antd';
import locale from 'antd/es/date-picker/locale/hu_HU';
import axios from "axios";
import { toast } from 'react-hot-toast';

function WorkshopGenerate() {
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
    const [workshopWorker1, setWorkshopWorker1] = useState("");
    const [workshopWorker2, setWorkshopWorker2] = useState("");
    const [workshopWorker3, setWorkshopWorker3] = useState("");
    const [workshopWorker4, setWorkshopWorker4] = useState("");
    const [workshopWorker5, setWorkshopWorker5] = useState("");
    const [workshopWorker6, setWorkshopWorker6] = useState("");
    const [workshopWorker7, setWorkshopWorker7] = useState("");
    const [workshopWorker8, setWorkshopWorker8] = useState("");
    const [workshopWorker9, setWorkshopWorker9] = useState("");
    const [workshopWorker10, setWorkshopWorker10] = useState("");
    const [workshopWorker11, setWorkshopWorker11] = useState("");
    const [workshopWorker12, setWorkshopWorker12] = useState("");
    const [expireDate, setExpireDate] = useState(new Date());

    const [worker3Visible, setWorker3Visible] = useState(false);
    const [worker4Visible, setWorker4Visible] = useState(false);
    const [worker5Visible, setWorker5Visible] = useState(false);
    const [worker6Visible, setWorker6Visible] = useState(false);
    const [worker7Visible, setWorker7Visible] = useState(false);
    const [worker8Visible, setWorker8Visible] = useState(false);
    const [worker9Visible, setWorker9Visible] = useState(false);
    const [worker10Visible, setWorker10Visible] = useState(false);
    const [worker11Visible, setWorker11Visible] = useState(false);
    const [worker12Visible, setWorker12Visible] = useState(false);
    const [worker13Visible, setWorker13Visible] = useState(false);

    const [worker2AddVisible, setWorker2AddVisible] = useState(true);
    const [worker3AddVisible, setWorker3AddVisible] = useState(false);
    const [worker4AddVisible, setWorker4AddVisible] = useState(false);
    const [worker5AddVisible, setWorker5AddVisible] = useState(false);
    const [worker6AddVisible, setWorker6AddVisible] = useState(false);
    const [worker7AddVisible, setWorker7AddVisible] = useState(false);
    const [worker8AddVisible, setWorker8AddVisible] = useState(false);
    const [worker9AddVisible, setWorker9AddVisible] = useState(false);
    const [worker10AddVisible, setWorker10AddVisible] = useState(false);
    const [worker11AddVisible, setWorker11AddVisible] = useState(false);
    const [worker12AddVisible, setWorker12AddVisible] = useState(false);

    const submitGenerate = () => {
        if(workshopName === "" || workshopId === "") {
            toast.error("Hiányzik a műhely neve/id-ja!")
        }else {
            const data = {
                muhelyid: workshopId,
                muhelynev: workshopName,
                dolgozo1: workshopWorker1,
                dolgozo2: workshopWorker2,
                dolgozo3: workshopWorker3,
                dolgozo4: workshopWorker4,
                dolgozo5: workshopWorker5,
                dolgozo6: workshopWorker6,
                dolgozo7: workshopWorker7,
                dolgozo8: workshopWorker8,
                dolgozo9: workshopWorker9,
                dolgozo10: workshopWorker10,
                dolgozo11: workshopWorker11,
                dolgozo12: workshopWorker12,
                lejarat: expireDate,
            }
            axios.post("http://localhost:9000/createworkshop", data).then((response) => {
                if(response.data.error) {
                    toast.error(response.data.message);
                }else {
                    if(response.data.message === "Már van egy műhely a tulajdonodban.") {
                        toast.error(response.data.message);
                    }else {
                    toast.success(response.data.message);
                    }
                }
            })
        }
        
    }

    const handle1Click = () => {
        setWorker2AddVisible(false);
        setWorker3Visible(true);
        setWorker3AddVisible(true);
    }
    const handle2Click = () => {
        setWorker3AddVisible(false);
        setWorker4Visible(true);
        setWorker4AddVisible(true);
    }
    const handle3Click = () => {
        setWorker4AddVisible(false);
        setWorker5Visible(true);
        setWorker5AddVisible(true);
    }
    const handle4Click = () => {
        setWorker5AddVisible(false);
        setWorker6Visible(true);
        setWorker6AddVisible(true);
    }
    const handle5Click = () => {
        setWorker6AddVisible(false);
        setWorker7Visible(true);
        setWorker7AddVisible(true);
    }
    const handle6Click = () => {
        setWorker7AddVisible(false);
        setWorker8Visible(true);
        setWorker8AddVisible(true);
    }
    const handle7Click = () => {
        setWorker8AddVisible(false);
        setWorker9Visible(true);
        setWorker9AddVisible(true);
    }
    const handle8Click = () => {
        setWorker9AddVisible(false);
        setWorker10Visible(true);
        setWorker10AddVisible(true);
    }
    const handle9Click = () => {
        setWorker10AddVisible(false);
        setWorker11Visible(true);
        setWorker11AddVisible(true);
    }
    const handle10Click = () => {
        setWorker11AddVisible(false);
        setWorker12Visible(true);
        setWorker12AddVisible(true);
    }
    const handle11Click = () => {
        setWorker12AddVisible(false);
        setWorker13Visible(true);
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
                    <div className='workshop-create-input'>
                        <Input onChange={(e) => setWorkshopWorker1(e.target.value)} placeholder='Alkalmazott IG neve...' style={{
                            width: 200,
                            height: 30,
                        }} />
                        <p>(szerveren lévő alkalmazottad neve)</p>
                    </div>
                    <div>
                        {worker2AddVisible &&
                            <span>Több alkalmazottad van? <span className='click-colored' onClick={() => handle1Click()}>Kattintással</span> adj hozzá egy újabb mezőt.</span>
                        }
                    </div>

                    
                    {worker3Visible &&
                        <div className='workshop-create-input'>
                            <Input onChange={(e) => setWorkshopWorker2(e.target.value)} placeholder='Alkalmazott IG neve...' style={{
                                width: 200,
                                height: 30,
                            }} />
                            <p>(szerveren lévő alkalmazottad neve)</p>
                        </div>
                    }
                    <div>
                        {worker3AddVisible &&
                            <span>Több alkalmazottad van? <span className='click-colored' onClick={() => handle2Click()}>Kattintással</span> adj hozzá egy újabb mezőt.</span>
                        }
                    </div>
                     
                    {worker4Visible &&
                        <div className='workshop-create-input'>
                            <Input onChange={(e) => setWorkshopWorker3(e.target.value)} placeholder='Alkalmazott IG neve...' style={{
                                width: 200,
                                height: 30,
                            }} />
                            <p>(szerveren lévő alkalmazottad neve)</p>
                        </div>
                    }
                    <div>
                        {worker4AddVisible &&
                            <span>Több alkalmazottad van? <span className='click-colored' onClick={() => handle3Click()}>Kattintással</span> adj hozzá egy újabb mezőt.</span>
                        }
                    </div>
                     
                    {worker5Visible &&
                        <div className='workshop-create-input'>
                            <Input onChange={(e) => setWorkshopWorker4(e.target.value)} placeholder='Alkalmazott IG neve...' style={{
                                width: 200,
                                height: 30,
                            }} />
                            <p>(szerveren lévő alkalmazottad neve)</p>
                        </div>
                    }
                    <div>
                        {worker5AddVisible &&
                            <span>Több alkalmazottad van? <span className='click-colored' onClick={() => handle4Click()}>Kattintással</span> adj hozzá egy újabb mezőt.</span>
                        }
                    </div>
                     
                    {worker6Visible &&
                        <div className='workshop-create-input'>
                            <Input onChange={(e) => setWorkshopWorker4(e.target.value)} placeholder='Alkalmazott IG neve...' style={{
                                width: 200,
                                height: 30,
                            }} />
                            <p>(szerveren lévő alkalmazottad neve)</p>
                        </div>
                    }
                    <div>
                        {worker6AddVisible &&
                            <span>Több alkalmazottad van? <span className='click-colored' onClick={() => handle5Click()}>Kattintással</span> adj hozzá egy újabb mezőt.</span>
                        }
                    </div>
                     
                    {worker7Visible &&
                        <div className='workshop-create-input'>
                            <Input onChange={(e) => setWorkshopWorker6(e.target.value)} placeholder='Alkalmazott IG neve...' style={{
                                width: 200,
                                height: 30,
                            }} />
                            <p>(szerveren lévő alkalmazottad neve)</p>
                        </div>
                    }
                    <div>
                        {worker7AddVisible &&
                            <span>Több alkalmazottad van? <span className='click-colored' onClick={() => handle6Click()}>Kattintással</span> adj hozzá egy újabb mezőt.</span>
                        }
                    </div>
                     
                    {worker8Visible &&
                        <div className='workshop-create-input'>
                            <Input onChange={(e) => setWorkshopWorker7(e.target.value)} placeholder='Alkalmazott IG neve...' style={{
                                width: 200,
                                height: 30,
                            }} />
                            <p>(szerveren lévő alkalmazottad neve)</p>
                        </div>
                    }
                    <div>
                        {worker8AddVisible &&
                            <span>Több alkalmazottad van? <span className='click-colored' onClick={() => handle7Click()}>Kattintással</span> adj hozzá egy újabb mezőt.</span>
                        }
                    </div>
                    
                    {worker9Visible &&
                        <div className='workshop-create-input'>
                            <Input onChange={(e) => setWorkshopWorker8(e.target.value)} placeholder='Alkalmazott IG neve...' style={{
                                width: 200,
                                height: 30,
                            }} />
                            <p>(szerveren lévő alkalmazottad neve)</p>
                        </div>
                    }
                     <div>
                        {worker9AddVisible &&
                            <span>Több alkalmazottad van? <span className='click-colored' onClick={() => handle8Click()}>Kattintással</span> adj hozzá egy újabb mezőt.</span>
                        }
                    </div>
                     
                    {worker10Visible &&
                        <div className='workshop-create-input'>
                            <Input onChange={(e) => setWorkshopWorker9(e.target.value)} placeholder='Alkalmazott IG neve...' style={{
                                width: 200,
                                height: 30,
                            }} />
                            <p>(szerveren lévő alkalmazottad neve)</p>
                        </div>
                    }
                    <div>
                        {worker10AddVisible &&
                            <span>Több alkalmazottad van? <span className='click-colored' onClick={() => handle9Click()}>Kattintással</span> adj hozzá egy újabb mezőt.</span>
                        }
                    </div>
                     
                    {worker11Visible &&
                        <div className='workshop-create-input'>
                            <Input onChange={(e) => setWorkshopWorker10(e.target.value)} placeholder='Alkalmazott IG neve...' style={{
                                width: 200,
                                height: 30,
                            }} />
                            <p>(szerveren lévő alkalmazottad neve)</p>
                        </div>
                    }
                    <div>
                        {worker11AddVisible &&
                            <span>Több alkalmazottad van? <span className='click-colored' onClick={() => handle10Click()}>Kattintással</span> adj hozzá egy újabb mezőt.</span>
                        }
                    </div>
                     
                    {worker12Visible &&
                        <div className='workshop-create-input'>
                            <Input onChange={(e) => setWorkshopWorker11(e.target.value)} placeholder='Alkalmazott IG neve...' style={{
                                width: 200,
                                height: 30,
                            }} />
                            <p>(szerveren lévő alkalmazottad neve)</p>
                        </div>
                    }
                    <div>
                        {worker12AddVisible &&
                            <span>Több alkalmazottad van? <span className='click-colored' onClick={() => handle11Click()}>Kattintással</span> adj hozzá egy újabb mezőt.</span>
                        }
                    </div>
                    {worker13Visible &&
                        <div className='workshop-create-input'>
                            <Input onChange={(e) => setWorkshopWorker12(e.target.value)} placeholder='Alkalmazott IG neve...' style={{
                                width: 200,
                                height: 30,
                            }} />
                            <p>(szerveren lévő alkalmazottad neve13)</p>
                        </div>
                    }
                    <div className='workshop-create-input'>
                    <DatePicker locale={locale} style={{marginTop: "0.5rem", marginRight: "0.5rem"}} size="medium" placeholder="Dátum kiválasztása" />
                    <p>(lejárat dátuma)</p>
                    </div>
                    <div className='workshop-create-input'>
                    <Button onClick={submitGenerate} type="primary" style={{marginTop: '0.3rem'}}>Létrehozás</Button>
                    
                    </div>
                </div>
            </Modal>
        </>
    )
}

export default WorkshopGenerate