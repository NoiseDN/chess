import 'babel-polyfill';

import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, IndexRoute, browserHistory } from 'react-router';
import { Provider } from 'react-redux';

import FrontPage from 'pages/frontpage/FrontPageContainer';
import Game from 'pages/game/GameContainer';

import store from 'store';
import Root from 'root/RootContainer';

import 'prototype';

const ReduxProvider = (props) => {
    return (
        <Provider store={store}>
            <Root {...props}/>
        </Provider>
    );
};

ReactDOM.render((
    <Router history={browserHistory}>
        <Route path={'/'} component={ReduxProvider}>
            <IndexRoute component={FrontPage}/>
            <Route path="/game/:id" component={Game}/>
        </Route>
    </Router>
), document.getElementById('app'));