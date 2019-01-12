import React from 'react'
import LoginView from './subcomponents/LoginView';
import NewUser from './subcomponents/NewUser';
import './Login.scss';

class Login extends React.Component {
    constructor() {
        super();
        this.state = {
            newUser: false,
            username: "",
            password: "",
            powerRating: 0,
            email: "",
            spearmen: 0,
            archers: 0,
            heavySwords: 0,
            silver: 0,
            wood: 0,
            steel: 0
        }
        this.changeView=this.changeView.bind(this);
    }

    changeView() {
        this.setState({newUser: !this.state.newUser});
    }

    createProfile() {
        //a lot of this will be set in back end to prevent data manipulation by client
        const payload = {
            username: "eolculnamo2",
            password: "test123",
            lastLogin: new Date(),
            powerRating: 0,
            email: "rbertram8@gmail.com",
            spearmen: 0,
            archers: 0,
            heavySwords: 0,
            silver: 0,
            wood: 0,
            steel: 0
        }

        fetch('/create-profile',{
            method: "POST",
            body: JSON.stringify(payload),
            headers: { "Content-Type": "application/json" },
            credentials: "same-origin"
        })
    }
    render() {
        return (
            <div>
                <div className="Login-flex-body">
                    <div className="Login-body">
                        {this.state.newUser ? 
                            <NewUser changeView={this.changeView}/> 
                            : <LoginView changeView={this.changeView}/>}
                    </div>
                </div>
            </div>
        )
    }
}

export default Login;