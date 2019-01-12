import React from 'react';
import { BrowserRouter as Router, Link, Route, withRouter, Redirect, Switch } from 'react-router-dom';

import './App.scss';
import Dashboard from './components/pages/Dashboard/Dashboard';
import Header from './components/library/HeaderAndFooter/Header';
class App extends React.Component {
    render() {
        return (
            <div>
                <Header/>
                <Switch>
                     {/* Dashboard has nested links */}
                    <Route exact path='/' component={ Dashboard }/>
                    <Route exact path='/overview' component={ Dashboard }/>
                    <Route exact path='/resources' component={ Dashboard }/>
                    <Route exact path='/troops' component={ Dashboard }/>
                    <Route exact path='/command-panel' component={ Dashboard }/>
                </Switch>
            </div>
        )
    }
}

export default App;