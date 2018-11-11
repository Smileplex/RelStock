import * as d3 from 'd3'

export function createNodes(rawData){
    const maxAmount = d3.max(rawData, d=> +d.total_amount);

    const radiusScale = d3.scalePow().exponent(0.1).range([40, 100]).domain([0, maxAmount]);

    const myNodes = rawData.filter(d=>d.id).map(d => ({
        id: d.id,
        radius: radiusScale(+d.total_amount),
        prevRadius: radiusScale(+d.total_amount),
        value: +d.total_amount,
        name: d.grant_title,
        org: d.organization,
        group: d.group,
        year: d.start_year,
        x: Math.random() * 900,
        y: Math.random() * 800,
        type: d.type,
        code: d.code,
        price: d.price,
        fluct: d.fluct,
        fluctRate: d.fluctRate,
        arrow: d.fluct>0 ? "up" : "down"
    }))

    myNodes.sort((a,b)=> b.value = a.value);

    return myNodes;
}
