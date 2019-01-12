import React from 'react';

class NewUser extends React.Component {
    handleClick() {
        this.props.changeView();
    }
    render() {
        return (
            <div className="Login-flex-body">
                <h1>New User</h1>
                <p  className="Login-change-view-link"
                    onClick={this.handleClick.bind(this)}>Existing User?</p>
                <p>Username</p>
                <input />
                <p>Password</p>
                <input />
                <p>Confirm Password</p>
                <input />
                <p>Email</p>
                <input />
                <button className="Login-button">Create Account</button>
            </div>
        )
    }
}

export default NewUser;