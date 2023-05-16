import React, { useState } from 'react'
import axios from "axios";
import { Toaster, toast } from 'react-hot-toast';
import { useNavigate } from "react-router-dom";
import { InfoCircleOutlined, UserOutlined, MailOutlined } from '@ant-design/icons';
import { Button, Input, Tooltip, Space } from 'antd';
import { motion, AnimatePresence } from "framer-motion"

function Login() {

  // Login = 0, Register = 1
  const [selectedView, setSelectedView] = useState(0);

  axios.defaults.withCredentials = true

  const navigate = useNavigate();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  //const [user, setUser] = useState([])




  const login = () => {
    const data = { username: username, password: password };
    axios.post("http://localhost:9000/login", data).then((response) => {
      console.log(response)
      toast.success("Sikeres bejelentkezés!")
      navigate("/home");
    }).catch(function (error) {
      if (error.response) {
        toast.error(error.response.data.message)
      }
    });
  };

  return (
    <motion.div className="login-page-container">
      <div className="overlay" id="gradient4"></div>
      <Toaster position="middle-right" reverseOrder={false} />
      <AnimatePresence>
        <motion.div layout className="login-h1"
          initial={{ opacity: 0.8 }}
          animate={{ opacity: 1 }}
          exit={{ opacity: 0.8 }}
          transition={{ delay: 1.2 }}>
          <motion.h1
            initial={{ opacity: 1, y: 100 }}
            animate={{ opacity: 1, y: 0 }}
            exit={{ opacity: 0 }}
            transition={{ delay: 0.5 }}>
            SEEGAMECEO
          </motion.h1>
        </motion.div>
      </AnimatePresence>
      <AnimatePresence>

        <motion.div layout className="login-container">
          {selectedView === 0 &&
            <motion.div initial={{ opacity: 0 }}
              animate={{ opacity: 1 }}
              transition={{ delay: 0.7 }}
              exit={{ opacity: 0 }} className="login-form">
              <Input
                onChange={(e) => setUsername(e.target.value)}
                className='username-input'
                placeholder="Felhasználónév"
                prefix={<UserOutlined className="site-form-item-icon" />}
                suffix={
                  <Tooltip title="minimum 4 karakter">
                    <InfoCircleOutlined
                      style={{
                        color: 'rgba(0,0,0,.45)',
                      }}
                    />
                  </Tooltip>
                }
              />
              <Space direction="vertical">
                <Input.Password
                  onChange={(e) => setPassword(e.target.value)}
                  className='password-input'
                  placeholder="Jelszó" />
              </Space>
              <Space wrap>
                <motion.span whileHover={{ scale: 1.05 }} style={{ fontSize: '0.8rem', cursor: 'pointer' }}>Elfelejtett jelszó?</motion.span>
                <Button className='login-button' onClick={login}>Bejelentkezés</Button>
              </Space>
              <span style={{ fontSize: '0.85rem', marginTop: '1rem' }}>Nincs még fiókod? <span onClick={() => setSelectedView(1)} style={{ color: '#328cb6', cursor: 'pointer' }}>Regisztrálok</span>!</span>
            </motion.div>
          }

          {selectedView === 1 &&
    
              
              <motion.div layout className="login-form" initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ delay: 0.7 }}
                exit={{ opacity: 0 }}>
                <AnimatePresence>
                  <Input
                    onChange={(e) => setUsername(e.target.value)}
                    className='username-input'
                    placeholder="Felhasználónév"
                    prefix={<UserOutlined className="site-form-item-icon" />}
                    suffix={
                      <Tooltip title="minimum 4 karakter">
                        <InfoCircleOutlined
                          style={{
                            color: 'rgba(0,0,0,.45)',
                          }}
                        />
                      </Tooltip>
                    }
                  />
                  <Input
                    onChange={(e) => setUsername(e.target.value)}
                    className='username-input'
                    placeholder="E-mail cím"
                    prefix={<MailOutlined className="site-form-item-icon" />}
                  />
                  <Space direction="vertical">
                    <Input.Password
                      onChange={(e) => setPassword(e.target.value)}
                      className='password-input'
                      placeholder="Jelszó" />
                  </Space>
                  <Space wrap>
                    <Button className='login-button' onClick={login}>Regisztráció</Button>
                  </Space>
                  <span style={{ fontSize: '0.85rem', marginTop: '0.8rem' }}>Van már fiókod? <span onClick={() => setSelectedView(0)} style={{ color: '#328cb6', cursor: 'pointer' }}>Belépek</span>!</span>
                    <div className='warning-message'>
                      NE adj meg olyan adatokat, amelyeket máshol is használsz!
                      (semmiképp se a SEEMTA-s adataid add meg!)
                    </div>
                </AnimatePresence>
              </motion.div>
          }


        </motion.div>
      </AnimatePresence>
    </motion.div>
  )
}

export default Login