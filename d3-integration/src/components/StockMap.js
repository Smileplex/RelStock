import React from 'react'

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
