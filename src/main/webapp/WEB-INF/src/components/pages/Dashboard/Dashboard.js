import React from 'react';
import { BrowserRouter as Router, Link, Route, withRouter, Redirect, Switch } from 'react-router-dom';

import './Dashboard.scss';
import Troops from './components/Troops/Troops';

class Dashboard extends React.Component {
    render() {
        return(
            <div>
                <div className="Dashboard">
                    <ul className="Dashboard-side">
                        <li>Overview</li>
                        <li>Resources</li>
                        <li>Troops</li>
                        <li>Command Panel</li>
                    </ul>
                    <div className="Dashboard-body">
                        <Switch>
                            {<Route exact path="/troops" render={props =>(
                                 <Troops />
                            )}/>}
                        </Switch>
                    </div>
                </div>
            </div>
        )
    }
}

export default Dashboard;