import React from 'react'
import {slide as Menu} from 'react-burger-menu'

class SearchSite extends React.Component{

    showSettings = (event) => {
        event.preventDefault();
    }

    render(){
        return(
            <Menu width={"320px"} burgerButtonClassName={"fas fa-search search"} customBurgerIcon={ <i className="fas fa-search"></i> }>
                <a id="home" className="menu-item" href="/">Home</a>
                <a id="about" className="menu-item" href="/about">About</a>
                <a id="contact" className="menu-item" href="/contact">Contact</a>
                <a onClick={ this.showSettings } className="menu-item--small" href="">Settings</a>
            </Menu>
        )
    }
}
export default SearchSite;
