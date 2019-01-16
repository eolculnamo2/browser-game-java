import React from 'react';
import '../Resources.scss';

class Building extends React.Component {
    render() {
        return (
            <div className="Resources-building-wrap">
                <h3 className="Resources-heading">{this.props.name} Level {this.props.level}</h3>
                <b className="Resources-production">{this.props.productionPerDay} {this.props.type} per day</b>
                <p className="Resources-text">{this.props.description}</p>
                <p className="Resources-text Resources-text--extra-m">Upgrade Cost: {this.props.costToUpgrade} {this.props.type}</p>
                <button className="Resources-upgrade-btn">Upgrade to level {this.props.level+1}</button>
            </div>
        )
    }
}

export default Building;