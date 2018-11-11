import React from 'react'


class Chat extends React.Component{
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.chatTextArea.style.height = window.innerHeight-230+"px";
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
                                <li className={"other"}>
                                    <div className={"head"}>
                                        <span className={"name"}>한국홍가슴개미371</span>
                                    </div>
                                    <div className={"body"}>
                                        <div className={"msg"}>그래서 하는게 고배당을 통해 그돈으로 증여세를 대납할 겁니다. 즉 배당이 높아질수도.</div>
                                        <span className={"time"}>
                                        17:00
                                        </span>
                                    </div>
                                </li>
                                <li className={"self"}>
                                    <div className={"head"}>
                                        <span className={"name"}>한국홍가슴개미320</span>
                                    </div>
                                    <div className={"body"}>
                                        <span className={"time"}>
                                        16:49
                                        </span>
                                        <div className={"msg"}>넓게 보셔야 합니다 ~ 이회사는 키스코가 대주주로 부인 및 자식에게 주식 증여 했습니다.<br/>헐 큰일났다...</div>
                                    </div>
                                </li>
                                <li className={"self"}>
                                    <div className={"head"}>
                                        <span className={"name"}>한국홍가슴개미320</span>
                                    </div>
                                    <div className={"body"}>
                                        <span className={"time"}>
                                        16:49
                                        </span>
                                        <div className={"msg"}>넓게 보셔야 합니다 ~ 이회사는 키스코가 대주주로 부인 및 자식에게 주식 증여 했습니다.<br/>헐 큰일났다...</div>
                                    </div>
                                </li>
                                <li className={"other"}>
                                    <div className={"head"}>
                                        <span className={"name"}>한국홍가슴개미371</span>
                                    </div>
                                    <div className={"body"}>
                                        <div className={"msg"}>그래서 하는게 고배당을 통해 그돈으로 증여세를 대납할 겁니다. 즉 배당이 높아질수도.</div>
                                        <span className={"time"}>
                                        17:00
                                        </span>
                                    </div>
                                </li>
                                <li className={"other"}>
                                    <div className={"head"}>
                                        <span className={"name"}>짱구개미104</span>
                                    </div>
                                    <div className={"body"}>
                                        <div className={"msg"}>지긋지긋하다. 그만 눌러담아라 하....</div>
                                        <span className={"time"}>
                                        17:01
                                        </span>
                                    </div>
                                </li>
                                <li className={"other"}>
                                    <div className={"head"}>
                                        <span className={"name"}>노랑꼬치레개미093</span>
                                    </div>
                                    <div className={"body"}>
                                        <div className={"msg"}>적자나도 배당은 하는거요....</div>
                                        <span className={"time"}>
                                        17:02
                                        </span>
                                    </div>
                                </li>
                                <li className={"self"}>
                                    <div className={"head"}>
                                        <span className={"name"}>한국홍가슴개미320</span>
                                    </div>
                                    <div className={"body"}>
                                        <span className={"time"}>
                                        16:49
                                        </span>
                                        <div className={"msg"}>사야할까요 말아야할까요 흠...... <br/> 전일 종가를 보면 그리 좋지가 않은데.. 같이 사실분 안계신가요?</div>
                                    </div>
                                </li>
                                <li className={"self"}>
                                    <div className={"head"}>
                                        <span className={"name"}>한국홍가슴개미320</span>
                                    </div>
                                    <div className={"body"}>
                                        <span className={"time"}>
                                        16:49
                                        </span>
                                        <div className={"msg"}>배당은 나올 것 같음 <br/> 유형 자산을 손실로 했기 때문에 그리고 적자만 나는것을 한큐에 빼버렸기 때문에 음...</div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div className={"chatForm"}>
                            <textarea placeholder={"메세지를 입력하세요"}></textarea>
                            <input type={"button"} value="전송"/>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}


export default Chat;
