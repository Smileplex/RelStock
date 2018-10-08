import React from 'react'
import PropTypes from "prop-types"

const StockMap = ({width, height, children}) => {
    console.log(width);
    return (
        <svg width={width} height={height}>
            {children}
        </svg>
    )
};

StockMap.propTypes = {
    width: PropTypes.number,
    height: PropTypes.number,
    children: PropTypes.node,
}

export default StockMap;
