import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import CustomerPage from './Customer'; 
import SupplierPage from './Supplier';
import ImageSection from './ImageFolder/ImageSection'; 
import './App.css';

function App() {
  return (
    <Router>
      <div className="container">
        <div className="titles-container">
          <div className="title">
            <h2>Customer Interface</h2>
          </div>
          <div className="title">
            {}
          </div>
        </div>

        <ImageSection /> {}
        
        <Routes>
          <Route path="/customer" element={<CustomerPage />} />
          <Route path="/supplier" element={<SupplierPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
