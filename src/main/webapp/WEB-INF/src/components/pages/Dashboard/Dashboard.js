import React from 'react';
import { BrowserRouter as Router, Link, Route, withRouter, Redirect, Switch } from 'react-router-dom';

import './Dashboard.scss';
import Troops from './components/Troops/Troops';
import CommandPanel from './components/CommandPanel/CommandPanel';
import Resources from './components/Resources/Resources';
import Overview from './components/Overview/Overview';

class Dashboard extends React.Component {
    constructor() {
        super();
        this.OverviewWithProps = this.OverviewWithProps.bind(this);
        this.CommandPanelWithProps = this.CommandPanelWithProps.bind(this);
    }
    OverviewWithProps(props) {
        return(
            <Overview
                user={this.props.user}
                {...props}
                />
        )
    }
    CommandPanelWithProps(props) {
        return(
            <CommandPanel
                user={this.props.user}
                {...props}
                />
        )
    }
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
                            {<Route exact path="/overview" render={this.OverviewWithProps}/>}
                            {<Route exact path="/troops" render={ () => (
                                 <Troops />
                            )}/>}
                            {<Route exact path="/command-panel" render={this.CommandPanelWithProps}/>}
                            {<Route exact path="/resources" render={ () => (
                                 <Resources />
                            )}/>}
                        </Switch>
                    </div>
                </div>
            </div>
        )
    }
}

export default Dashboard;