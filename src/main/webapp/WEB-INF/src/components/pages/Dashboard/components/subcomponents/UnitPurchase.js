import React from 'react';
import './UnitPurchase.scss';

class UnitPurchase extends React.Component {
    render() {
        return (
            <div className="UnitPurchase">
                <div>
                    <h3>{ this.props.name }</h3>
                    <input className="UnitPurchase-input" type="number" placeholder="# of Units" />
                    <button className="UnitPurchase-button">Purchase</button>
                </div>
                <div className="UnitPurchase-description-container">
                    <p className="UnitPurchase-description">{ this.props.description }</p>
                </div>
            </div>
        )
    }
}

export default UnitPurchase;