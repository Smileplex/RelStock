import React from 'react'
import PropTypes from "prop-types"
import * as d3 from "d3";

const StockMap = ({width, height, children}) => {
    return (
        <svg width={width} height={height}>
            {
                children
            }
        </svg>
    )
}



export default StockMap;
