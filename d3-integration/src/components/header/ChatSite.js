import React from 'react'
import {slide as Menu} from 'react-burger-menu'

class ChatSite extends React.Component{

    showSettings = (event) => {
        event.preventDefault();
    }

    render(){
        return(
            <Menu width={"320px"} right burgerButtonClassName={"far fa-comments chat"} customBurgerIcon={ <i className="far fa-comments"></i> }>
                <div className={"menu-header"}>
                    채팅 <dim>(8,255)</dim>
                </div>
                <div className={"content"}>
                    <div className={"search-bar"}>
                        <i className={"icon fa fa-search"}></i>
                        <input className={"input-text"} type={"text"} name={"eingabe"} placeholder={"종목명 또는 키워드를 검색하세요"}/>
                    </div>
                </div>
                <a onClick={ this.showSettings } className="menu-item--small" href="">Settings</a>
            </Menu>
        )
    }
}

export default ChatSite;
