import React from 'react';
import UnitPurchase from '../subcomponents/UnitPurchase';
import './Troops.scss';

const troops = [
    {
        name: "Spearmen",
        description: "Spearmen are light infantry. Thanks to their light armor, they can carry a lot of loot"
    },
    {
        name: "Archers",
        description: "Archers offer support. While they have light armor, they are generally protected by heavier units."
    },
    {
        name: "Heavy Swords",
        description: "Heavily armored swordsmen are the backbone of any serious army. They have high attack, armor, and health."
    }
]

class Troops extends React.Component {
    constructor() {
        super();
        this.state = {
            unitType: "",
            amount: 0
        }
        this.makePurchase = this.makePurchase.bind(this);
    }

    makePurchase(unitType, amount) {
        const payload = { unitType, amount }

        fetch('/purchase-troops',{
            method: "POST",
            body: JSON.stringify(payload),
            headers: { "Content-Type": "application/json" },
            credentials: "same-origin"
            })
        .then( res => res.json() )
        .then( data => {
            alert("You purchased " + data.amount + " "+data.unitType);
        });
    }

    render() {
        return (
            <div className="Troops">
                {troops.map( x => <UnitPurchase  description={ x.description } 
                                                 makePurchase={ this.makePurchase} 
                                                 name={ x.name }/> )}
            </div>
        )
    }
}

export default Troops;