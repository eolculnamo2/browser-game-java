import React from 'react';
import UnitPurchase from '../subcomponents/UnitPurchase';
import './Troops.scss';

class Troops extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {
            unitType: "",
            amount: 0,
            troops: [
                {
                    name: "Spearmen",
                    description: "Spearmen are light infantry. Thanks to their light armor, they can carry a lot of loot",
                    cost: {
                        woodCost: this.props.gameData.spearmenInfo.woodCost,
                        steelCost: this.props.gameData.spearmenInfo.steelCost,
                        silverCost: this.props.gameData.spearmenInfo.silverCost
                    }
                },
                {
                    name: "Archers",
                    description: "Archers offer support. While they have light armor, they are generally protected by heavier units.",
                    cost: {
                        woodCost: this.props.gameData.archerInfo.woodCost,
                        steelCost: this.props.gameData.archerInfo.steelCost,
                        silverCost: this.props.gameData.archerInfo.silverCost
                    }
                },
                {
                    name: "Heavy Swords",
                    description: "Heavily armored swordsmen are the backbone of any serious army. They have high attack, armor, and health.",
                    cost: {
                        woodCost: this.props.gameData.heavySwordsInfo.woodCost,
                        steelCost: this.props.gameData.heavySwordsInfo.steelCost,
                        silverCost: this.props.gameData.heavySwordsInfo.silverCost
                    }
                }
            ]
        }

        this.makePurchase = this.makePurchase.bind(this);
    }

    makePurchase(unitType, amount) {

        const params = {
            spearmen: 0,
            archers: 0,
            heavySwords: 0
        }
        params[unitType.toLowerCase()] = amount;
        //destructure params
        const {spearmen, archers, heavySwords} = params;
        let paramsString = "spearmen="+spearmen+"&archers="+archers+"&heavySwords="+heavySwords;

        fetch('/purchase-troops?'+paramsString,{
            method: "POST",
            headers: { "Content-Type": "application/json" },
            credentials: "same-origin"
            })
        // .then( res => res.json() )
        // .then( data => {
        //     alert("You purchased " + data.amount + " "+data.unitType);
        // });
    }

    render() {
        return (
            <div className="Troops">
                <h1 className="Dashboard-heading">Troops</h1>
                {this.state.troops.map( x => <UnitPurchase  cost={ x.cost }
                                                 description={ x.description }
                                                 key={ x.name }
                                                 makePurchase={ this.makePurchase }
                                                 name={ x.name }/> )}
            </div>
        )
    }
}

export default Troops;