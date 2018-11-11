import React, {Component} from 'react';
import './App.css';
import './Style.css'
import StockMap from './containers/StockMap'
import Logo from "./components/header/Logo";
import SearchSite from "./containers/SearchSite";
import ChatSite from "./containers/ChatSite";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: [],
            grouping: 'all'
        }
    }

    render() {
        return (
            <div className="App">
                <SearchSite />
                <ChatSite/>
                <Logo/>
                <StockMap/>
                {/*<div id="page-wrap">*/}
                    {/*<StockMap width={width} height={height}>*/}
                        {/*<StockNodes data={this.state.data} data2={this.state.data2} forceStrength={forceStrength} center={center}/>*/}
                    {/*</StockMap>*/}
                {/*</div>*/}
            </div>
        );
    }
}

export default App;
