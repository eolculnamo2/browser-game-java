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
            username: "rbertram8",
            password: "test123"
        }
        fetch('/login-user',{
            method: "POST",
            body: JSON.stringify(payload),
            headers: { "Content-Type": "application/json" },
            credentials: "same-origin"
        })
        // .then( res => res.json() )
        // .then( data => window.location.href="/");
    }
    render() {
        return (
            <div className="Login-flex-body">
                <form method="POST" action="/login-user">
                    <h1>Login</h1>
                    <p  className="Login-change-view-link"
                        onClick={this.handleClick.bind(this)}>New User?</p>
                    <p>Username</p>
                    <input id="username" name="username"  />
                    <p>Password</p>
                    <input type="password" id="password" name="password" type="password" />
                    <button className="Login-button"
                            type="submit">Login</button>
                </form>
            </div>
        )
    }
}

export default LoginView;