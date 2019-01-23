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
            gameData: {},
            ready: false
        }
        this.DashboardAndProps=this.DashboardAndProps.bind(this);
    }
    componentDidMount() {
        try {
        fetch("/get-game-data")
        .then( res => {
            console.log(res);
            if(res.status === 200) {
                //this.setState({ready: true});
                return res.json();
            }
        })
        .then( data => {
           this.setState({user: data.user, gameData: data, ready: true}, ()=>console.log(JSON.stringify(data,null,3)))
        });
    }   catch(e){}
    }
     DashboardAndProps (props) {
            return  <Dashboard
                        user={this.state.user}
                        gameData={this.state.gameData}
                        {...props}
                    />
    }
    render() {
        if(this.state.ready) {
            return (
                <div>
                    <Header user={this.state.user}/>
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