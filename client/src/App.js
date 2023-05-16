import './App.css';
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from './pages/Login';
import Home from './pages/Home';
import axios from 'axios';

function App() {


  axios.defaults.withCredentials = true;


  return (
    <div className="App">
      <Router>

        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/home" element={<Home />} />
        </Routes>
      </Router>
    </div>
  );

}

export default App;
