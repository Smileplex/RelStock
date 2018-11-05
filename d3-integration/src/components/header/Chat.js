import React from 'react'


class Chat extends React.Component{
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.chatTextArea.style.height = window.innerHeight-188+"px";
    }


    render(){
        return (
            <div className={'chat'}>
                <div className={"menu-header chat"}>
                    <i className="fas fa-angle-left"></i>네이버
                    <dim>&nbsp;(1,081)</dim>
                </div>
                <div className={"content"}>
                    <div className={"chatBox"}>
                        <div className={"chatTextArea"} ref={area => this.chatTextArea = area}>
                            <ul className={"chatTextList"}>
                                <li className={"self"}>
                                    <div className={"head"}>
                                        <span className={"name"}>한국홍가슴개미320</span>
                                    </div>
                                    <div className={"body"}>
                                        <div className={"msg"}></div>
                                        <span className={"time"}>
                                        5:45 am
                                    </span>
                                    </div>
                                </li>
                                <li className={"other"}>
                                    <div className={"head"}>
                                        <span className={"name"}>한국홍가슴개미371</span>
                                    </div>
                                    <div className={"body"}>
                                        <div className={"msg"}></div>
                                        <span className={"time"}>
                                        5:45 am
                                    </span>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}


export default Chat;
