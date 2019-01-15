import React from 'react';
import './Resources.scss';
import Building from './subcomponents/Building';

class Resources extends React.Component {
    constructor() {
        super();
        this.state = {
            buildings: [
                {
                    name: "Logging Camps",
                    description: "test test test test test test test",
                    level: 1
                }
            ]
        }
    }
    render() {
        return (
            <div>
                <h1>Resources and Buildings</h1>
                {this.state.buildings.map( x => <Building description={x.description}
                                                          level={x.level} 
                                                          name={x.name} />)}
            </div>
        )
    }
}

export default Resources;