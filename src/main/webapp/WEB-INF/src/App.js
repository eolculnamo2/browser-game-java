import React from 'react';
import { BrowserRouter as Router, Link, Route, withRouter, Redirect, Switch } from 'react-router-dom';

import './App.scss';
import Dashboard from './components/pages/Dashboard/Dashboard';
import Header from './components/library/HeaderAndFooter/Header';
import Login from './components/pages/Login/Login';



class App extends React.Component {
    constructor() {
        super();
        this.state =  {
            user: {},
            ready: false
        }
        this.DashboardAndProps=this.DashboardAndProps.bind(this);
    }
    componentDidMount() {
        fetch("/get-user-data?username=rbertram8")
        .then( res => res.json())
        .then( user => {
           this.setState({user, ready: true})
        });
    }
     DashboardAndProps (props) {
            return  <Dashboard
                        user={this.state.user}
                        {...props}
                    />
    }
    render() {
        if(this.state.ready) {
            return (
                <div>
                    <Header/>
                    <Switch>
                        {/* Dashboard has nested links */}
                        <Route exact path='/' render= { this.DashboardAndProps } />
                        <Route exact path='/overview' component={ this.DashboardAndProps }/>
                        <Route exact path='/resources' component={ this.DashboardAndProps }/>
                        <Route exact path='/troops' component={ this.DashboardAndProps }/>
                        <Route exact path='/command-panel' component={ this.DashboardAndProps }/>
                        <Route exact path='/login' component={ Login }/>
                    </Switch>
                </div>
            )
        } else {
            return(<div>Loading...</div>)
        }
    }
}

export default App;