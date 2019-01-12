import React from 'react'
import './CommandPanel.scss';

class CommandPanel extends React.Component {
    constructor() {
        super();
        this.state = {
            attackablePlayers: [
                {
                    username: "SamplePlayer"
                },
                {
                    username: "SamplePlayer"
                }
            ],
            x: "",
            payload: {
                playerToAttack: "",
                spearmen: 0,
                archers: 0,
                heavySwords: 0
            }
        }
    }
    setPlayerToAttack(e) {
        const update = {...this.state.payload, playerToAttack: e.target.value};
        this.setState({payload: update});
    };

    setSpearmen(e) {
        const update = {...this.state.payload, spearmen: e.target.value};
        this.setState({payload: update});
    }

    setArchers(e) {
        const update = {...this.state.payload, archers: e.target.value};
        this.setState({payload: update});
    }

    setHeavySwords(e) {
        const update = {...this.state.payload, heavySwords: e.target.value};
        this.setState({payload: update});
    }

    sendTroops() {
        fetch('/make-battle',{
            method: "POST",
            body: JSON.stringify(this.state.payload),
            headers: { "Content-Type": "application/json" },
            credentials: "same-origin"
        })
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
                        <input onChange={this.setSpearmen.bind(this)} 
                               value={this.state.payload.spearmen} 
                               className="CommandPanel-input" 
                               type="number"/>
                        <label>Archers </label>
                        <input onChange={this.setArchers.bind(this)} 
                               value ={this.state.payload.archers}
                               className="CommandPanel-input" 
                               type="number"/>
                        <label>Heavy Swords </label>
                        <input onChange={this.setHeavySwords.bind(this)} 
                               value={this.state.payload.heavySwords} 
                               className="CommandPanel-input" 
                               type="number"/>
                    </div>
                    <div>
                        <h3>Attack Player</h3>
                        <select value={this.state.payload.playerToAttack} onChange={this.setPlayerToAttack.bind(this)}>
                            <option defaultValue="">Select</option>
                            {this.state.attackablePlayers.map((x,i) => {
                                return (<option key={x+i}
                                                value={x.username}>
                                            {x.username}
                                        </option>)
                            })}
                        </select>
                    </div>
                </div>
                <div className="CommandPanel-button-flex">
                    <button onClick={ this.sendTroops.bind(this) }className="CommandPanel-send-button">
                        SEND TROOPS
                    </button>
                </div>
            </div>
        )
    }
}

export default CommandPanel;