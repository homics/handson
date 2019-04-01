import React, {Component} from 'react';
import './App.css';

import {Provider} from 'react-redux';
import configureStore from './store';
import Header from "./Header";
import StatsPage from '../stats/StatsPage';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import NotFound from "./NotFound";

const store = configureStore();

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <link
          rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
          crossOrigin="anonymous"
        />
        <Router>
          <div>
            <Header />
            <Switch>
                <Route path="/stats/stat-order" component={StatsPage} />
                <Route component={NotFound} />
            </Switch>
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
