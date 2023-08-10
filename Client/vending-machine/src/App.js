import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import CustomerPage from './Customer'; 
import SupplierPage from './Supplier';
import './App.css'; // Import the CSS file

function App() {
  return (
    <Router>
      <div className="container">
        <h1>Welcome to Vending Machine Application</h1>
        <div className="button-container">
          <Link to="/customer" className="button">Customer</Link>
          <Link to="/supplier" className="button">Supplier</Link>
        </div>
      </div>

      <Routes>
        <Route path="/customer" element={<CustomerPage />} />
        <Route path="/supplier" element={<SupplierPage />} />
      </Routes>
    </Router>
  );
}

export default App;
