import React from 'react';
import './UnitPurchase.scss';

class UnitPurchase extends React.Component {
    constructor() {
        super();
        this.purchaseNumber = React.createRef();
    }
    handlePurchaseClick() {
        this.props.makePurchase( this.props.name, this.purchaseNumber.current.value )
    }
    render() {
        return (
            <div className="UnitPurchase">
                <div>
                    <h3>{ this.props.name }</h3>
                    <input ref={ this.purchaseNumber } className="UnitPurchase-input" type="number" placeholder="# of Units" />
                    <button onClick={ this.handlePurchaseClick.bind(this) } className="UnitPurchase-button">Purchase</button>
                </div>
                <div className="UnitPurchase-description-container">
                    <p className="UnitPurchase-description">{ this.props.description }</p>
                </div>
            </div>
        )
    }
}

export default UnitPurchase;