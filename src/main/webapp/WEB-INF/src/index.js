import React from 'react';
import createHistory from 'history/createBrowserHistory'
import { render } from 'react-dom';
import { Router } from 'react-router'
import App from './App';

const history = createHistory();

render(
    <Router history={ history }>
        <App />
    </Router>, document.getElementById("app"));