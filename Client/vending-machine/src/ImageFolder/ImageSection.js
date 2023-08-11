import React from 'react';
import './ImageSection.css'; // Import the CSS file for styling

function ImageSection() {
  return (
    <div className="image-section">
      <img src={require("./coke.jpg")} alt={"Coke"}/>
      <img src={require("./water.jpg")} alt={"Water"}/>
      <img src={require("./soda.jpg")} alt={"Soda"}/>
    </div>
  );
}

export default ImageSection;
