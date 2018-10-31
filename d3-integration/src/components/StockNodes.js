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
                return d.radius * 1.3;
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
            .on('mouseover', this.switchRadius.bind(this,300))
            .on('mouseout', this.switchRadius.bind(this,0))

        const base = this.state.g.selectAll('.base').data(data, d => d.id);
        base.exit().remove();

        let baseEnter = base.enter().append('foreignObject')
            .attr('class', 'base')
            .attr('x',d=>d.x)
            .attr('y',d=>d.y)
            .attr('width', d=>d.radius*2)
            .attr('height', d=>d.radius*2);

        let stockBaseDiv = baseEnter.filter(d=>d.type==='a').append("xhtml:div");
        stockBaseDiv.html((d)=>
            "<div class='stock-name'>"+d.name+"</div>"+
            "<div class='stock-code'>"+d.code+"&nbsp;|&nbsp; KOSPI</div>"+
            "<hr/>"+
            "<div class='stock-values "+d.arrow+"'>"+
            "<div class='stock-price'><i class='fas fa-caret-"+d.arrow+"'></i>&nbsp; "+Number(d.price).toLocaleString()+"</div>"+
            "<div class='stock-fluct'>"+Math.abs(d.fluct).toLocaleString()+"("+d.fluctRate+")</div>"+
            "</div>"
        );

        let keywordBaseDiv = baseEnter.filter(d=>d.type==='b').append("xhtml:div");
        keywordBaseDiv.html((d)=>
            "<div class='keyword-name'>"+d.name+"</div>"
        );



        const detail = this.state.g.selectAll('.detail').data(data, d => d.id);
        detail.exit().remove();
        let detailEnter = detail.enter().append('foreignObject')
            .attr('class', 'detail')
            .attr('x',d=>d.x)
            .attr('y',d=>d.y)
            .attr('width', 440)
            .attr('height', 400);

        let detailDiv = detailEnter.append('xhtml:div');
        detailDiv.html((d)=>
            "<div class='detail-name-code'><div class='detail-name'>"+d.name+"</div>"+
            "<div class='detail-code'>"+d.code+" | KOSPI</div></div>"+
            "<div class='detail-values "+d.arrow+"'>"+
            "<div class='detail-price'><i class='fas fa-caret-"+d.arrow+"'></i>&nbsp; "+Number(d.price).toLocaleString()+"</div>"+
            "<div class='detail-fluct'>"+Math.abs(d.fluct).toLocaleString()+"("+d.fluctRate+")</div></div>"+
            "<div class='detail-detail-values'>" +
            "<div class='inner-values'>" +
                "<div class='title'>전일종가</div><div class='value'>765,000</div>" +
                "<div class='title'>고가</div><div class='value'>765,000</div>" +
                "<div class='title'>저가</div><div class='value'>755,000</div>" +
                "<div class='title'>거래량</div><div class='value'>50,436</div>" +
            "</div>" +
            "<div class='inner-values'>" +
                "<div class='title'>거래대금</div><div class='value'>38,534백만</div>" +
                "<div class='title'>시가총액</div><div class='value'>25조 3,818억</div>" +
                "<div class='title'>액면가</div><div class='value'>500원 / 1주</div>" +
                "<div class='title'>외국인소진율</div><div class='value'>59.67%</div>" +
            "</div>" +
            "<hr class='border'/>"+
            "<div class='article'>" +
            "<div class='inner'><div class='title'>네이버 스마트렌즈 외화/와인 인식 추가</div><div class='date'>2018.10.27 | 파이낸셜 뉴스</div></div>" +
            "<div class='inner'><div class='title'>KEB하나은행, 네이버 스마트렌즈 기반 외국 화폐 조회 서비스 추가</div><div class='date'>2018.10.26 | 네이버 뉴스</div></div>" +
            "<div class='inner'><div class='title'>네이버 쇼핑 1위 LED 스탠드, 눈을 위한 조명 라문 아플레또 주목</div><div class='date'>2018.10.25 | 서울경제</div></div>" +
            "<div class='inner'><div class='title'>바닥 다진 네이버, 외국인들 러브콜 </div><div class='date'>2018.10.24 | 매일경제</div></div>" +
            "<div class='inner'><div class='title'>e편한세상 용인 한숨시티, 네이버 그라폴리오와 공모전개최</div><div class='date'>2018.10.24 | 헤럴드경제</div></div>" +
            "<div class='inner'><div class='title'>대학생 취업 선호 1위는 네이버</div><div class='date'>2018.10.23 | 파이낸셜 뉴스</div></div>" +
            "<div class='inner'><div class='title'>네이버, 매크로 방지책 추가, 로그인 보안 강화</div><div class='date'>2018.10.23 | 네이버 뉴스</div></div>" +
            "<div class='inner'><div class='title'>네이버, R&D의 힘</div><div class='date'>2018.10.22 | 매일경제</div></div>" +
            "<div class='inner'><div class='title'>네이버 클라우드 플랫폼, 건국대에 제공</div><div class='date'>2018.10.21 | 서울경제</div></div>" +
            "</div>"+
            "<div class='circle-bottom'></div>"+
            "<div class='chat-enter'><i class='far fa-comment fa-flip-horizontal'></i></i><span>종목 채팅 바로가기</span></div>"
        );

        this.simulation.nodes(data).alpha(1).restart();
    }

    onRef = (ref) => {
        this.setState({g: d3.select(ref)}, ()=>{
            d3.select('svg').append("linearGradient")
                .attr("id", "linearGrad")
                .attr("gradientUnits", "objectBoundingBox")
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
        this.state.g.selectAll('.detail').attr('x', d => d.x - d.radius).attr('y', d=>d.y - d.radius);
        this.state.g.selectAll('.base').attr('x', d => d.x - d.radius).attr('y', d=>d.y - d.radius);
    }

    charge = (d) => {
        return -this.props.forceStrength * (d.radius ** 2.0)
    }


    switchRadius = (newRadius, target) => {
        let visible = true;

        let detail = d3.selectAll('foreignObject.detail').filter(function(t){
            return t.id === target.id
        });

        let base = d3.selectAll('foreignObject.base').filter(function(t){
            return t.id === target.id
        });

        if(newRadius === 0){
            visible = false;
            detail.style('display', 'none');
            newRadius = target.prevRadius;
        }
        const self = this;
        d3.selectAll('circle').filter(function(t){
            return t.id === target.id;
        }).transition().duration(300).tween('radius', function(d){
            base.style('display', 'none');
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
            if(visible) {
                detail
                    .attr('width',d.radius*2)
                    .attr('height',d.radius*2)
                    .style('display','block');
            }else{
                base.style('display', 'block');
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
