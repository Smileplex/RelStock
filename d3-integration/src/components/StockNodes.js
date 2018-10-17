import React from 'react';
import * as d3 from 'd3'
import PropTypes from "prop-types";
class StockNodes extends React.Component{
    constructor(props) {
        super(props);
        const {forceStrength, center} = this.props;
        this.simulation = d3.forceSimulation()
            .force('x', d3.forceX().strength(forceStrength).x(center.x))
            .force('y', d3.forceY().strength(forceStrength).y(center.y))
            .force('charge', d3.forceManyBody().strength(this.charge))
            .force("collide", d3.forceCollide(function(d){
                return d.radius * 1.2;
            }).strength(0.8))
            .on("tick", this.ticked).stop();

        const self = this;
        d3.interval(function(){
            // self.renderStockNodes(self.props.data2);
        },2000);
        this.state = {
            g: null,
        }
    }

    componentWillReceiveProps(nextProps){

        if(nextProps.data !== this.props.data){
            this.renderStockNodes(nextProps.data)
        }
    }

    renderStockNodes = (data) =>{

        const circle = this.state.g.selectAll('circle').data(data, d => d.id);
        circle.exit().remove();
        circle.enter().append('circle').classed('bubble', true)
            .attr('id', d => d.id)
            .attr('r', d => d.radius)
            .attr('cx', d => d.x)
            .attr('cy', d => d.y)
            .attr('stroke-width', 2)
            .attr('opacity', 0.5)
            .on('mouseover', this.switchRadius.bind(this,300))
            .on('mouseout', this.switchRadius.bind(this,0))// eslint-disable-line

        const text = this.state.g.selectAll('text').data(data, d => d.id);
        text.exit().remove();
        text.enter().append('text').text(function(d){
            return d.value;
        }).attr('dx', function(d){return d.x}).attr('dy', function(d){return d.y});

        this.simulation.nodes(data).alpha(1).restart();
    }

    onRef = (ref) => {
        this.setState({g: d3.select(ref)})
    }
    ticked = () => {
        this.state.g.selectAll('circle').attr('cx', d => d.x).attr('cy', d => d.y)
        this.state.g.selectAll('text').attr('dx', d => d.x).attr('dy', d=>d.y)
    }

    charge = (d) => {
        return -this.props.forceStrength * (d.radius ** 2.0)
    }


    switchRadius = (newRadius, target) => {
        if(newRadius === 0)
            newRadius = target.prevRadius;

        const self = this;
        d3.selectAll('.bubble').filter(function(t){
            return t.id === target.id;
        }).transition().duration(500).tween('radius', function(d){
            const that = d3.select(this);
            const i = d3.interpolate(d.radius, newRadius);
            return function(t){
                d.radius = i(t);
                that.attr('r', function(d){
                    return d.radius;
                });
                self.simulation.nodes(self.props.data);
            }
        });
        this.simulation.alpha(1).restart();
    }

    shouldComponentUpdate(){
        return false;
    }

    render(){
        return (
            <g ref={this.onRef} className="bubbles" />
        )
    }
}

export function showDetail(d){
    // d3.select(this).transition().duration(2000).attr('transform', function(d){
    //     var cx = d3.select(this).attr('cx');
    //     var dx =  500 - 100;
    //     var dy =  500 - 120;
    //     return "translate("+dx+","+dy+")";
    // }).attr('r', 100);
    // console.log('data', this);



}

export function hideDetail(d){
    d3.select(this).transition().duration(2000).attr('r',3);
}


StockNodes.propTypes = {
    center: PropTypes.shape({
        x: PropTypes.number.isRequired,
        y: PropTypes.number.isRequired,
    }),
    forceStrength: PropTypes.number.isRequired,
    // groupByYear: PropTypes.bool.isRequired,
    yearCenters: PropTypes.objectOf(PropTypes.shape({
        x: PropTypes.number.isRequired,
        y: PropTypes.number.isRequired,
    }).isRequired).isRequired,
    data: PropTypes.arrayOf(PropTypes.shape({
        x: PropTypes.number.isRequired,
        id: PropTypes.string.isRequired,
        radius: PropTypes.number.isRequired,
        value: PropTypes.number.isRequired,
        name: PropTypes.string.isRequired,
    })),
}


export default StockNodes;
