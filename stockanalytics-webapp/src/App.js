import React, {Component} from 'react';
import * as d3 from 'd3';
import logo from './logo.svg';
import {createNodes} from './utils';
import './App.css';
import BubbleChart from './components/BubbleChart'
import Bubbles from './components/Bubbles'
import YearsTitles from './components/YearsTitles'
import GroupingPicker from './components/GroupingPicker'
import {height, width, center, yearCenters} from "./constants";


class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],
            grouping: 'all'
        }
    }

    componentDidMount() {
        d3.csv('data/gates_money.csv', (err, data) => {
            if (err) {
                console.log(err);
                return;
            }

            this.setState({
                data: createNodes(data)
            })
        })
    }

    onGroupingChanged = (newGrouping) => {
        this.setState({
            grouping: newGrouping
        })
    }

    render() {
        const {data, grouping} = this.state;
        return (
            <div className='App'>
                <GroupingPicker onChange = {this.onGroupingChanged} active={grouping}/>
                <BubbleChart width={width} height={height}>
                    <Bubbles data={data} forceStrength={0.03} center={center} yearCenters={yearCenters} groupByYears={grouping ==='year'}/>
                    {
                        grouping === 'year' &&
                            <YearsTitles width={width} yearCenters={yearCenters} />
                    }
                </BubbleChart>
            </div>

        );
    }
}

export default App;
