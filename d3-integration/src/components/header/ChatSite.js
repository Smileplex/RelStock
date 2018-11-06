import React from 'react'
import {slide as Menu} from 'react-burger-menu'
import ChatRoom from "./ChatRoom";
import Chat from "./Chat";
import PropTypes from 'prop-types';


class ChatSite extends React.Component{
    constructor(props) {
        super(props);

        this.state = {
            chatActivated : false
        }
    }

    changeActivation = () => {
        this.setState({
            chatActivated : true
        });
    }

    isMenuOpen = ({isOpen}) => {
        if(!isOpen)
            this.setState({
                chatActivated : false
            });
    }

    render(){
        return(
            <Menu onStateChange={this.isMenuOpen} width={"375px"} right burgerButtonClassName={"far fa-comments chat"} customBurgerIcon={ <i className="far fa-comments"></i> }>
                {!this.state.chatActivated ? <ChatRoom goclick={this.changeActivation}/> : <Chat/> }
            </Menu>
        )
    }
}

ChatSite.propTypes = {
    chatActivated : PropTypes.bool
}


export default ChatSite;
