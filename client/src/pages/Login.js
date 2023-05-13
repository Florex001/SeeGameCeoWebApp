import React, { useState, useEffect } from 'react'
import axios from "axios";
import { Toaster, toast } from 'react-hot-toast';
import { useNavigate } from "react-router-dom";
import { InfoCircleOutlined, UserOutlined, EyeTwoTone, EyeInvisibleOutlined } from '@ant-design/icons';
import { Button, Input, Tooltip, Space, Checkbox } from 'antd';
import { motion, AnimatePresence } from "framer-motion"

function Login() {

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
    <div className="login-page-container">
      <Toaster position="middle-right" reverseOrder={false} />
      <AnimatePresence>
        <motion.div layout className="login-h1 gradient-border"
          initial={{ opacity: 0.8 }}
          animate={{ opacity: 1 }}
          exit={{ opacity: 0.8 }}
          transition={{ delay: 0.9 }}>
          <motion.h1
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ opacity: 0 }}
            transition={{ delay: 1.2 }}>
            ROYALKAA UCP
          </motion.h1>
        </motion.div>
      </AnimatePresence>
      <AnimatePresence>
        <motion.div layout className="login-container"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          exit={{ opacity: 0 }}
          transition={{ delay: 0.7 }}>
          <motion.div className="login-form gradient-border">
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
              <Checkbox className='login-checkhbox'>Emlékezz rám</Checkbox>
              <Button className='login-button' onClick={login}>Bejelentkezés</Button>
            </Space>
          </motion.div>

        </motion.div>
      </AnimatePresence>
    </div>
  )
}

export default Login