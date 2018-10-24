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
                return d.radius * 1.4;
            }).strength(0.8))
            .on("tick", this.ticked).stop();

        const self = this;
        d3.interval(function(){

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
        circle.enter().append('circle').attr('class', d => d.type)
            .attr('id', d => d.id)
            .attr('r', d => d.radius)
            .attr('cx', d => d.x)
            .attr('cy', d => d.y)
            .attr('stroke-width', 4)
            // .attr('opacity', 0.5)
            .on('mouseover', this.switchRadius.bind(this,300))
            .on('mouseout', this.switchRadius.bind(this,0))// eslint-disable-line

        const text = this.state.g.selectAll('text').data(data, d => d.id);
        text.exit().remove();
        text.enter().append('text').text(function(d){
            return d.value;
        })
            .attr('dx', function(d){return d.x})
            .attr('dy', function(d){return d.y});

        const base = this.state.g.selectAll('.base').data(data, d => d.id);
        base.exit().remove();
        let baseEnter = base.enter().append('foreignObject')
            .attr('class', 'base')
            .attr('x',d=>d.x)
            .attr('y',d=>d.y)
            .attr('width', 30)
            .attr('height', 30);


        const detail = this.state.g.selectAll('.detail').data(data, d => d.id);
        detail.exit().remove();
        let detailEnter = detail.enter().append('foreignObject').attr('class', 'detail')
            .attr('x',d=>d.x)
            .attr('y',d=>d.y)
            .attr('width', 440)
            .attr('height', 400);

        let div = detailEnter.append('xhtml:div').append('div')
        div.append('p')
            .attr('class', 'lead')
            .html('Holmes was certainly not a difficult man to live with.');
        div.append('p')
            .html('He was quiet in his ways, and his habits were regular. It was rare for him to be up after ten at night, and he had invariably breakfasted and gone out before I rose in the morning.');

        // detail.attr('height', foHeight);

        this.simulation.nodes(data).alpha(1).restart();
    }

    onRef = (ref) => {
        this.setState({g: d3.select(ref)}, ()=>{
            d3.select('svg').append("linearGradient")
                .attr("id", "linearGrad")
                .attr("gradientUnits", "userSpaceOnUse")
                .attr("x1", "0%").attr("y1", "100%")
                .attr("x2", "100%").attr("y2", "0%")
                .selectAll("stop")
                .data([
                    {offset: "0%", color: "rgb(255,15,119)"},
                    {offset: "42%", color: "rgb(182,9,164)"},
                    {offset: "73%", color: "rgb(128,6,119)"},
                    {offset: "89%", color: "rgb(128,6,119)"}
                ])
                .enter().append("stop")
                .attr("offset", function(d) { return d.offset; })
                .attr("stop-color", function(d) { return d.color; });
        })
    }
    ticked = () => {
        this.state.g.selectAll('circle').attr('cx', d => d.x).attr('cy', d => d.y)
        this.state.g.selectAll('text').attr('dx', d => d.x).attr('dy', d=>d.y)
        this.state.g.selectAll('.detail').attr('x', d => d.x - 220).attr('y', d=>d.y - 200);
        this.state.g.selectAll('.base').attr('x', d => d.x).attr('y', d=>d.y);
    }

    charge = (d) => {
        return -this.props.forceStrength * (d.radius ** 2.0)
    }


    switchRadius = (newRadius, target) => {
        let visible = true;
        let detail = d3.selectAll('foreignObject').filter(function(t){
            return t.id === target.id
        });

        if(newRadius === 0){
            newRadius = target.prevRadius;
            detail.style('display', 'none');
            visible = false;
        }
        const self = this;
        d3.selectAll('circle').filter(function(t){
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
        }).on("end",function(d){
            if(visible) detail.style('display','block');

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
