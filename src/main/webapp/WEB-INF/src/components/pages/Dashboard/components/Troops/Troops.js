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
    render() {
        return (
            <div className="Troops">
                {troops.map( x => <UnitPurchase description={ x.description } name={ x.name }/> )}
            </div>
        )
    }
}

export default Troops;