import React, {Component} from 'react';
import './App.css';

import ArticlePage from '../article/ArticlePage';
import {Provider} from 'react-redux';
import configureStore from './store';
import {ToastContainer} from "react-toastify";
import Header from "./Header";
import CartPage from "../order/CartPage";
import UserActivityPage from "../userActivity/UserActivityPage";
import LoginPage from "../auth/LoginPage";
import HistoryPage from '../order/HistoryPage';
import StatsPage from '../stats/StatsPage';
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import PrivateRoute from "./PrivateRoute";
import 'react-toastify/dist/ReactToastify.min.css';
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
        <ToastContainer autoClose={5000} />
        <Router basename={"/mono"}>
          <div>
            <Header />
            <Switch>
              <Route path="/login" component={LoginPage} />
              <PrivateRoute path="/cart" component={CartPage} />
              <PrivateRoute path="/history" component={HistoryPage} />
              <PrivateRoute path="/stats" component={StatsPage} />
              <PrivateRoute path="/articles" component={ArticlePage} />
              <PrivateRoute path="/userActivity" component={UserActivityPage} />
              <PrivateRoute component={NotFound} />
            </Switch>
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
