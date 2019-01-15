import React from 'react'
import './CommandPanel.scss';

class CommandPanel extends React.Component {
    constructor() {
        super();
        this.state = {
            currentUser: "eolculnamo2",
            currentPlayer: {},
            attackablePlayers: [],
            payload: {
                playerToAttack: "",
                spearmen: 0,
                archers: 0,
                heavySwords: 0
            }
        }
    }
    componentWillMount() {
        fetch('get-all-users')
        .then( res => res.json() )
        .then( data => {
            const attackablePlayers = [];

            data.forEach( x => {
                if(x.username !== this.state.currentUser) {
                    attackablePlayers.push(x);
                } else {
                    this.setState({currentPlayer: x});
                }
            });

            this.setState({attackablePlayers})
        });
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
        const params = "?username="+this.state.currentPlayer.username+ 
                        "&defender="+this.state.payload.playerToAttack +
                        "&spearmen="+this.state.payload.spearmen+
                        "&archers="+this.state.payload.archers+
                        "&heavySwords="+this.state.payload.heavySwords;
                     
        const player = { ...this.state.currentPlayer };
        const payload = { ...this.state.payload };
        if(payload.spearmen <= player.spearmen && 
           payload.archers <= player.archers &&
           payload.heavySwords <= player.heavySwords ) {
            fetch('/make-battle'+params,{
                method: "POST",
                headers: { "Content-Type": "application/json" },
                credentials: "same-origin"
            })
        } else {
            alert("You can not send more troops than you have.");
        }
    }

    render() {
        return (
            <div>
                <h1 className="Dashboard-heading">Command Panel</h1>
                <ul className="CommandPanel-top-panel">
                    <li>
                        <b>Spearmen </b>
                        <span>{this.state.currentPlayer.spearmen}</span>
                    </li>
                    <li>
                        <b>Archers </b>
                        <span>{this.state.currentPlayer.archers}</span>
                    </li>
                    <li>
                        <b>Heavy Swords </b>
                        <span>{this.state.currentPlayer.heavySwords}</span>
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