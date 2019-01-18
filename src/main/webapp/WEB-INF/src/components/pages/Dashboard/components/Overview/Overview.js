import React from 'react';
import './Overview.scss'

class Overview extends React.Component {
    
    render() {
        const {username, spearmen, archers, heavySwords,
              silver, steel, wood,
              silverLevel, steelLevel, woodLevel} = this.props.user;
        return (
            <div>
                <h1>{username}</h1>
                <div className="Overview-flex">
                    <div>
                        <h3>Troops</h3>
                        <ul>
                            <li>Spears {spearmen}</li>
                            <li>Archers {archers}</li>
                            <li>Heavy Swords {heavySwords}</li>
                        </ul>
                    </div>
                    <div>
                        <h3>Resources</h3>
                        <ul>
                            <li>Silver {silver}</li>
                            <li>Steel {steel}</li>
                            <li>Wood {wood}</li>
                        </ul>
                    </div>
                    <div>
                        <h3>Resources</h3>
                        <ul>
                            <li>Taxable Economy {silverLevel}</li>
                            <li>Steel Foundry {steelLevel}</li>
                            <li>Logging Camps {woodLevel}</li>
                        </ul>
                    </div>
                </div>
            </div>
        )
    }
}

export default Overview;