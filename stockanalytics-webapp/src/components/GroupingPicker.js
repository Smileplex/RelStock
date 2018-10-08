import React from 'react'
import PropTypes from "prop-types"
import './GroupingPicker.css'

class GroupingPicker extends React.Component{
    onBtnClick = (event) => {
        this.props.onChange(event.target.name)
    }

    render(){
        const {active} = this.props
        return (
            <div className = "GroupingPicker">
                <button className={`button ${active === 'all' && 'active'}`} name="all" onClick={this.onBtnClick}>All Grants</button>
                <button className={`button ${active === 'year' && 'active'}`} name="year" onClick={this.onBtnClick}>Grants By Year</button>
            </div>
        )
    }
}

GroupingPicker.propTypes = {
    onChange: PropTypes.func.isRequired,
    active: PropTypes.oneOf(['all','year']).isRequired
}

export default GroupingPicker;
