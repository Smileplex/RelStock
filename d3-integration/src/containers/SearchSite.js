import React from 'react'
import {slide as Menu} from 'react-burger-menu'
import SearchBody from "../components/header/SearchBody";

class SearchSite extends React.Component{

    render(){
        return(
            <Menu width={"375px"} burgerButtonClassName={"fas fa-search search"} customBurgerIcon={ <i className="fas fa-search"></i> }>
                <SearchBody/>
            </Menu>
        )
    }
}
export default SearchSite;
