import React from 'react';
import { BrowserRouter as Router, Link, Route, withRouter, Redirect, Switch } from 'react-router-dom';
import './HeaderAndFooter.scss';

class Header extends React.Component {
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
                    <span> <b>Wood</b> {this.props.user.wood}</span>
                    <span> <b>Steel</b> {this.props.user.steel}</span>
                    <span> <b>Silver</b> {this.props.user.silver}</span>
                </div>
            </div>
        )
    }
}

export default Header;