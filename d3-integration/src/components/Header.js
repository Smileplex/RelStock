import React from 'react'

const Header = ({active}) => {
    return (
        active &&
        <div className='header'>
            <div className='search'>
                <i className="fas fa-search"></i>
            </div>
            <div className='logo'>
                <img src="logo_black.png"/>
            </div>
            <div className='menu'>
                <i className="far fa-comments"></i>
            </div>
        </div>
    )
}

export default Header;
