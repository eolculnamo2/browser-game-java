import React from 'react';
import { BrowserRouter as Router, Link, Route, withRouter, Redirect, Switch } from 'react-router-dom';

import './Dashboard.scss';
import Troops from './components/Troops/Troops';
import CommandPanel from './components/CommandPanel/CommandPanel';

class Dashboard extends React.Component {
    render() {
        return(
            <div>
                <div className="Dashboard">
                    <ul className="Dashboard-side">
                        <Link to="/overview"><li>Overview</li></Link>
                        <Link to="/resources"><li>Resources</li></Link>
                        <Link to="/troops"><li>Troops</li></Link>
                        <Link to="/command-panel"><li>Command Panel</li></Link>
                    </ul>
                    <div className="Dashboard-body">
                        <Switch>
                            {<Route exact path="/troops" render={ () => (
                                 <Troops />
                            )}/>}
                            {<Route exact path="/command-panel" render={ () => (
                                 <CommandPanel />
                            )}/>}
                        </Switch>
                    </div>
                </div>
            </div>
        )
    }
}

export default Dashboard;