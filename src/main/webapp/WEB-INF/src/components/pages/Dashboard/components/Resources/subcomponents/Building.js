import React from 'react';
import '../Resources.scss';

class Building extends React.Component {
    render() {
        return (
            <div className="Resources-building-wrap">
                <h3>{this.props.name} Level {this.props.level}</h3>
                <p>{this.props.description}</p>
                <button>Upgrade to level {this.props.level+1}</button>
            </div>
        )
    }
}

export default Building;