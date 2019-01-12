import React from 'react'
import './CommandPanel.scss';

class CommandPanel extends React.Component {
    constructor() {
        super();
        this.state = {
            attackablePlayers: [
                {
                    username: "SamplePlayer"
                }
            ],
            playerToAttack: ""
        }
    }
    setPlayerToAttack(user) {
        this.setState({playerToAttack: user});
    }
    render() {
        return (
            <div>
                <h1 className="Dashboard-heading">Command Panel</h1>
                <ul className="CommandPanel-top-panel">
                    <li>
                        <b>Spearmen </b>
                        <span>20</span>
                    </li>
                    <li>
                        <b>Archers </b>
                        <span>20</span>
                    </li>
                    <li>
                        <b>Heavy Swords </b>
                        <span>100</span>
                    </li>
                </ul>
                <div className="CommandPanel-flex-body">
                    <div className="CommandPanel-send-troops">
                        <h3>Send Troops</h3>
                        <label>Spearmen </label>
                        <input className="CommandPanel-input" type="number"/>
                        <label>Archers </label>
                        <input className="CommandPanel-input" type="number"/>
                        <label>Heavy Swords </label>
                        <input className="CommandPanel-input" type="number"/>
                    </div>
                    <div>
                        <h3>Attack Player</h3>
                        <select>
                            {this.state.attackablePlayers.map( x => {
                                return (<option onClick={this.setPlayerToAttack.bind(this,x.username)}>
                                            {x.username}
                                        </option>)
                            })}
                        </select>
                    </div>
                </div>
                <div className="CommandPanel-button-flex">
                    <button className="CommandPanel-send-button">SEND TROOPS</button>
                </div>
            </div>
        )
    }
}

export default CommandPanel;