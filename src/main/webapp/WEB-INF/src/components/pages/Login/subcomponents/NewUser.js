import React from 'react';

class NewUser extends React.Component {
    constructor() {
        super();
        this.state = {
            username: "",
            password: "",
            confirm: "",
            email: ""
        }
    }
    handleClick(type) {
        if(type === 'user') {
            this.props.changeView();
        } else if(type === 'submit') {
            const {username, password, email} = this.state;
            const payload = {username,password,email}
            this.props.createProfile(payload);
        }
    }
    setUsername(e) {
        let username = e.target.value;
        this.setState({username});
    }
    setPassword(e) {
        let password = e.target.value
        this.setState({password});
    }
    setConfirm(e) {
        let confirm = e.target.value;
        this.setState({confirm});
    }
    setEmail(e) {
        let email = e.target.value;
        this.setState({email});
    }
    render() {
        return (
            <div className="Login-flex-body">
                <h1>New User</h1>
                <p  className="Login-change-view-link"
                    onClick={this.handleClick.bind(this, "user")}>Existing User?</p>
                <p>Username</p>
                <input onChange={this.setUsername.bind(this)}
                       value={this.state.username} />
                <p>Password</p>
                <input onChange={this.setPassword.bind(this)}
                       type="password"
                       value={this.state.password} />
                <p>Confirm Password</p>
                <input onChange={this.setConfirm.bind(this)}
                       type="password"
                       value={this.state.confirm} />
                <p>Email</p>
                <input onChange={this.setEmail.bind(this)}
                       value={this.state.email} />
                <button onClick={this.handleClick.bind(this,"submit")} className="Login-button">Create Account</button>
            </div>
        )
    }
}

export default NewUser;