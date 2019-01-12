import React from 'react'

class Login extends React.Component {
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
                <h1>Login Page</h1>
                <button onClick={this.createProfile.bind(this)}>Create Test User</button>
            </div>
        )
    }
}

export default Login;