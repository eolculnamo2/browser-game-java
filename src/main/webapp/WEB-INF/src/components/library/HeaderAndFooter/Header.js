import React from 'react';
import './HeaderAndFooter.scss';

class Header extends React.Component {
    render() {
        return (
            <div className="Header">
                <div className="Header-flex">
                    <div>
                        <h1>
                            Medieval Wars
                        </h1>
                        <p>Command and Conquer</p>
                    </div>
                    <div>
                        <ul className="Header-menu-flex">
                            <li>Home</li>
                            <li>Dashboard</li>
                            <li>Nations</li>
                            <li>Help</li>      
                        </ul>
                    </div>
                </div>
            </div>
        )
    }
}

export default Header;