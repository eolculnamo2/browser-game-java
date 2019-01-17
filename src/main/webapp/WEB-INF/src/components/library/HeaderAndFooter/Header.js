import React from 'react';
import { BrowserRouter as Router, Link, Route, withRouter, Redirect, Switch } from 'react-router-dom';
import './HeaderAndFooter.scss';

class Header extends React.Component {
    constructor() {
        super();
        this.state = {
            username: "rbertram8",
            wood: 0,
            steel: 0,
            silver: 0
        }
    }
    componentDidMount() {
        //get resources
        const params = "?username="+this.state.username;
        fetch("/get-user-data"+params)
        .then( res => res.json())
        .then( data => {
            const { wood, steel, silver } = data;
            this.setState({ wood, steel, silver });
        })
    }
    render() {
        return (
            <div>
                <div className="Header">
                    <div className="Header-flex">
                        <div>
                            <h1>
                                Medieval Wars
                            </h1>
                            <p>Command and Conquer</p>
                        </div>
                        <div>
                            <ul className="Header-menu-flex">
                                <Link to="/"><li>Home</li></Link>
                                <Link to="/overview"><li>Dashboard</li></Link>
                                <Link to="/"><li>Nations</li></Link>
                                <Link to="/login"><li>Login</li></Link>
                            </ul>
                        </div>
                    </div>
                </div>
                <div className="Header-resources">
                    <span> <b>Wood</b> {this.state.wood}</span>
                    <span> <b>Steel</b> {this.state.steel}</span>
                    <span> <b>Silver</b> {this.state.silver}</span>
                </div>
            </div>
        )
    }
}

export default Header;