import React from 'react';
import './Resources.scss';
import Building from './subcomponents/Building';

class Resources extends React.Component {
    constructor() {
        super();
        this.state = {
            username: "rbertram8",
            buildings: []
        }
    }
    componentDidMount() {
        let url = "/get-building-info?username="+this.state.username;
        fetch(url)
        .then( res => res.json())
        .then( data => {
            const buildings = [    
                                {
                                    name: "Logging Camps",
                                    type: "wood",
                                    description: "Logging camps produce wood. Wood is essential for buildings and troops",
                                    productionPerDay: data.woodProduction,
                                    costToUpgrade: data.woodUpgradeCost,
                                    level: data.woodLevel
                                },
                                {
                                    name: "Steel Foundry",
                                    type: "steel",
                                    description: "Steel is a valuable resource. Troops of all kinds, especially high quality troops require it.",
                                    productionPerDay: data.steelProduction,
                                    costToUpgrade: data.steelUpgradeCost,
                                    level: data.steelLevel
                                },
                                {
                                    name: "Taxable Economy",
                                    type: "silver",
                                    description: "Your silver income is reliant on taxes. The stronger your economy, the better the tax income.",
                                    productionPerDay: data.silverProduction,
                                    costToUpgrade: data.silverupgradeCost,
                                    level: data.silverLevel
                                }
                            ]
            this.setState({buildings})
        });
    }
    render() {
        return (
            <div>
                <h1>Resources and Buildings</h1>
                {this.state.buildings.map( x => <Building costToUpgrade={x.costToUpgrade}
                                                          description={x.description}
                                                          key={x.name}
                                                          level={x.level} 
                                                          name={x.name}
                                                          productionPerDay={x.productionPerDay}
                                                          type={x.type} />)}
            </div>
        )
    }
}

export default Resources;