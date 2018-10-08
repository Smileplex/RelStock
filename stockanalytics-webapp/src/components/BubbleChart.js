import React from 'react'
import PropTypes from "prop-types"

const BubbleChart = ({width, height, children}) => {
    return (
        <svg className = "bubbleChart" width={width} height={height}>
            {
                children
            }
        </svg>
    )
};

BubbleChart.propTypes = {
    width: PropTypes.number,
    height: PropTypes.number,
    children: PropTypes.node,
}


export default BubbleChart;
