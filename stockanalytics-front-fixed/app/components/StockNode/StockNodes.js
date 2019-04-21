import React from 'react';
import * as d3 from 'd3';
import PropTypes from 'prop-types';
class StockNodes extends React.Component {
  constructor(props) {
    super(props);
    const { forceStrength, center } = this.props;
    this.simulation = d3
      .forceSimulation()
      .force(
        'x',
        d3
          .forceX()
          .strength(forceStrength)
          .x(center.x),
      )
      .force(
        'y',
        d3
          .forceY()
          .strength(forceStrength)
          .y(center.y),
      )
      .force('charge', d3.forceManyBody().strength(this.charge))
      .force('collide', d3.forceCollide(d => d.radius * 1.3).strength(0.8))
      .on('tick', this.ticked)
      .stop();

    d3.interval(() => {}, 2000);
    this.state = {
      g: null,
    };
    this.dragHandler = d3
      .drag()
      .subject(d => d)
      .on('start', dragstarted)
      .on('drag', dragged)
      .on('end', dragended);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.data !== this.props.data) {
      this.renderStockNodes(nextProps.data);
    }
  }

  renderStockNodes = data => {
    d3.select('svg').on('click', () => {
      this.props.data
        .filter(
          d =>
            d.radius === 300 &&
            d.id !== Number(d3.event.target.id) &&
            d3.event.target.tagName !== 'A' &&
            d3.event.target.className !== 'circle-bottom',
        )
        .forEach(a => {
          this.switchRadius(0, a);
        });

      if (d3.event.target.className === 'circle-bottom') {
        this.props.onJoinRoom(d3.select(d3.event.target).attr('data-room'));
      }
    });

    const circle = this.state.g.selectAll('circle').data(data, d => d.id);
    circle.exit().remove();
    circle
      .enter()
      .append('circle')
      .attr('class', d => d.type)
      .attr('id', d => d.id)
      .attr('r', d => d.radius)
      .attr('cx', d => d.x)
      .attr('cy', d => d.y)
      .attr('stroke-width', 4)
      .on('mousedown', this.switchRadius.bind(this, 300))
      .call(this.dragHandler);
    // .on('', this.switchRadius.bind(this, 0));

    d3.selectAll('circle.StockKeyword').attr('fill', 'RGB(255,31,117)');

    const base = this.state.g.selectAll('.base').data(data, d => d.id);
    base.exit().remove();

    const baseEnter = base
      .enter()
      .append('foreignObject')
      .attr('class', 'base')
      .attr('x', d => d.x)
      .attr('y', d => d.y)
      .attr('width', d => d.radius * 2)
      .attr('height', d => d.radius * 2);

    const stockBaseDiv = baseEnter
      .filter(d => d.type === 'Stock')
      .append('xhtml:div');

    stockBaseDiv
      .append('div')
      .attr('class', 'stock-name')
      .style('font-size', d => `${d.radius / 6.0}px`)
      .text(d => d.name);

    stockBaseDiv
      .append('div')
      .attr('class', 'stock-code')
      .style('font-size', d => `${d.radius / 7.5}px`)
      .text(d => `${d.code} | KOSPI`);

    stockBaseDiv.append('hr');

    const stockValues = stockBaseDiv
      .append('div')
      .attr('class', d => `stock-values ${d.arrow}`);

    stockValues
      .append('div')
      .attr('class', 'stock-price')
      .style('font-size', d => `${d.radius / 6.1}px`)
      .html(
        d =>
          `<i class='fas fa-caret-${d.arrow}'></i>&nbsp;${Number(
            d.price,
          ).toLocaleString()}`,
      );

    stockValues
      .append('div')
      .attr('class', 'stock-fluct')
      .style('font-size', d => `${d.radius / 6.8}px`)
      .text(d => `${Math.abs(d.fluct).toLocaleString()}(${d.fluctRate}%)`);

    const keywordBaseDiv = baseEnter
      .filter(d => d.type === 'StockKeyword')
      .append('xhtml:div');
    keywordBaseDiv.html(d => `<div class='keyword-name'>${d.name}</div>`);

    const detail = this.state.g.selectAll('.detail').data(data, d => d.id);
    detail.exit().remove();
    const detailEnter = detail
      .enter()
      .append('foreignObject')
      .attr('class', 'detail')
      .attr('x', d => d.x)
      .attr('y', d => d.y)
      .attr('width', 440)
      .attr('height', 400);

    const detailDiv = detailEnter.append('xhtml:div');
    detailDiv.html(
      d =>
        d.type === 'Stock'
          ? `<div class='stock-detail'><div class='detail-name-code'><div class='detail-name'>
${d.name}</div>` +
            `<div class='detail-code'>${d.code} | KOSPI</div></div>` +
            `<div class='detail-values ${d.arrow}'>` +
            `<div class='detail-price'><i class='fas fa-caret-${
              d.arrow
            }'></i>&nbsp; ${Number(d.price).toLocaleString()}</div>` +
            `<div class='detail-fluct'>${Math.abs(d.fluct).toLocaleString()}(${
              d.fluctRate
            })</div></div>` +
            `<div class='detail-detail-values'>` +
            `<div class='inner-values'>` +
            `<div class='title'>전일종가</div><div class='value'>${d.pricePrev &&
              d.pricePrev.toLocaleString()}</div>` +
            `<div class='title'>고가</div><div class='value up'>${d.priceMax &&
              d.priceMax.toLocaleString()}</div>` +
            `<div class='title'>저가</div><div class='value down'>${d.priceMin &&
              d.priceMin.toLocaleString()}</div>` +
            `<div class='title'>거래량</div><div class='value'>${d.volumeTrade &&
              d.volumeTrade.toLocaleString()}</div>` +
            `</div>` +
            `<div class='inner-values'>` +
            `<div class='title'>거래대금</div><div class='value'>${d.volumeTradeAmount &&
              Math.floor(
                d.volumeTradeAmount / 1000000,
              ).toLocaleString()} 백만</div>` +
            `<div class='title'>시가총액</div><div class='value marketsum'>${d.marketSum &&
              d.marketSum.toLocaleString()} 백만</div>` +
            `<div class='title'>액면가</div><div class='value'>${d.faceVal &&
              d.faceVal.toLocaleString()} / 1주</div>` +
            `<div class='title'>외국인소진율</div><div class='value'>${
              d.frgnAcqRatio
            } %</div>` +
            `</div>` +
            `<hr class='border'/>` +
            `<div class='article'>` +
            `${JSON.parse(d.articles)
              .map(
                a =>
                  `<div class='inner'><div class='title'><a href=${
                    a.link
                  } target="_blank">${
                    a.title
                  }</a></div><div class='date'>${new Date(
                    a.createdDate,
                  ).toLocaleDateString()} | ${a.source}</div></div>`,
              )
              .join('')}` +
            `</div>` +
            `<div class='circle-bottom' data-room={"name":"${d.name}","type":"${
              d.type
            }"}></div>` +
            `<div class='chat-enter'><i class='far fa-comment fa-flip-horizontal'></i></i><span>종목 채팅 바로가기</span></div></div>`
          : `<div class='keyword-detail'><div class='detail-name-article'><div class='detail-name'>` +
            `${d.name}</div>` +
            `<div class='article'>` +
            `${d.articles &&
              JSON.parse(d.articles)
                .map(
                  a =>
                    `<div class='inner'><div class='title'><a href=${
                      a.link
                    } target="_blank">${
                      a.title
                    }</a></div><div class='date'>${new Date(
                      a.createdDate,
                    ).toLocaleDateString()} | ${a.source}</div></div>`,
                )
                .join('')}` +
            `</div><hr class='border'/></div>` +
            `<div class='clips'>` +
            `${d.clips &&
              JSON.parse(d.clips)
                .slice(0, 3)
                .map(
                  a =>
                    `<div class='inner'><div class='thumbnail'><img src=${
                      a.thumbnail
                    }></div><div class='title'><a href=${
                      a.link
                    } target="_blank">${a.title}</a></div><div class='date'>${
                      a.ago
                    } | ${a.source}</div></div>`,
                )
                .join('')}` +
            `</div>` +
            `<div class='circle-bottom' data-room={"name":"${d.name}","type":"${
              d.type
            }"}></div>` +
            `<div class='chat-enter'><i class='far fa-comment fa-flip-horizontal'></i></i><span>종목 채팅 바로가기</span></div></div>`,
    );

    this.simulation
      .nodes(data)
      .alpha(1)
      .restart();
  };

  onRef = ref => {
    this.setState({ g: d3.select(ref) }, () => {
      d3.select('svg')
        .append('linearGradient')
        .attr('id', 'linearGrad')
        .attr('gradientUnits', 'objectBoundingBox')
        .attr('x1', '0%')
        .attr('y1', '100%')
        .attr('x2', '100%')
        .attr('y2', '0%')
        .selectAll('stop')
        .data([
          { offset: '0%', color: 'rgb(255,15,119)' },
          { offset: '42%', color: 'rgb(182,9,164)' },
          { offset: '73%', color: 'rgb(128,6,119)' },
          { offset: '89%', color: 'rgb(128,6,119)' },
        ])
        .enter()
        .append('stop')
        .attr('offset', d => d.offset)
        .attr('stop-color', d => d.color);
    });
  };

  ticked = () => {
    this.state.g
      .selectAll('circle')
      .attr('cx', d => d.x)
      .attr('cy', d => d.y);
    this.state.g
      .selectAll('.detail')
      .attr('x', d => d.x - d.radius)
      .attr('y', d => d.y - d.radius);
    this.state.g
      .selectAll('.base')
      .attr('x', d => d.x - d.radius)
      .attr('y', d => d.y - d.radius);
  };

  charge = d => -this.props.forceStrength * d.radius ** 2.0;

  switchRadius = (newRadius, target) => {
    if (!target) return;
    let visible = true;
    let targetRadius = newRadius;
    const detail = d3
      .selectAll('foreignObject.detail')
      .filter(t => t.id === target.id);

    const base = d3
      .selectAll('foreignObject.base')
      .filter(t => t.id === target.id);

    if (newRadius === 0) {
      visible = false;
      detail.style('display', 'none');
      targetRadius = target.prevRadius;
      d3.selectAll('circle.StockKeyword')
        .filter(t => t.id === target.id)
        .attr('fill', 'RGB(255,31,117)');
    }

    const self = this;

    d3.selectAll('circle')
      .filter(t => t.id === target.id)
      .transition()
      .duration(300)
      .tween('radius', function(d) {
        base.style('display', 'none');
        const that = d3.select(this);
        const i = d3.interpolate(d.radius, targetRadius);
        const ref = d;
        return function(t) {
          ref.radius = i(t);
          that.attr('r', e => e.radius);
          self.simulation.nodes(self.props.data);
        };
      })
      .on('end', d => {
        if (visible) {
          detail
            .attr('width', d.radius * 2)
            .attr('height', d.radius * 2)
            .style('display', 'block');
          d3.selectAll('circle.StockKeyword')
            .filter(t => t.id === d.id)
            .attr('stroke', 'RGB(255,31,117)')
            .attr('stroke-width', 4)
            .attr('fill', '#fff');
        } else {
          base.style('display', 'block');
        }
      });
    this.simulation.alpha(1).restart();
  };

  shouldComponentUpdate() {
    return false;
  }

  render() {
    return <g ref={this.onRef} className="bubbles" />;
  }
}

StockNodes.propTypes = {
  center: PropTypes.shape({
    x: PropTypes.number.isRequired,
    y: PropTypes.number.isRequired,
  }),
  forceStrength: PropTypes.number.isRequired,
  data: PropTypes.arrayOf(
    PropTypes.shape({
      x: PropTypes.number.isRequired,
      id: PropTypes.string.isRequired,
      radius: PropTypes.number.isRequired,
      value: PropTypes.number.isRequired,
      name: PropTypes.string.isRequired,
    }),
  ),
  onJoinRoom: PropTypes.func,
};
export function dragstarted() {
  d3.event.sourceEvent.stopPropagation();
  d3.select(this).classed('dragging', true);
}

export function dragged(d) {
  d3.select(this)
    // eslint-disable-next-line no-param-reassign
    .attr('cx', (d.x = d3.event.x))
    // eslint-disable-next-line no-param-reassign
    .attr('cy', (d.y = d3.event.y));
}

export function dragended() {
  d3.select(this).classed('dragging', false);
}
export default StockNodes;
