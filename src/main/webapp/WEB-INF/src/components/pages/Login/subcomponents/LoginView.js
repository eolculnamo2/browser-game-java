import React from 'react'

class LoginView extends React.Component {
    constructor() {
        super();
        this.state = {}
    }
    handleClick() {
        this.props.changeView();
    }
    login() {
        const payload = {
            username: "eolculnamo2",
            password: "test123"
        }
        fetch('/login',{
            method: "POST",
            body: JSON.stringify(payload),
            headers: { "Content-Type": "application/json" },
            credentials: "same-origin"
        })
        .then( res => res.json() )
        .then( data => window.location.href="/");
    }
    render() {
        return (
            <div className="Login-flex-body">
                <h1>Login</h1>
                <p  className="Login-change-view-link"
                    onClick={this.handleClick.bind(this)}>New User?</p>
                <p>Username</p>
                <input />
                <p>Password</p>
                <input />
                <button className="Login-button"
                        onClick={this.login.bind(this)}>Login</button>
            </div>
        )
    }
}

export default LoginView;