import React, {Component} from 'react';
import './App.css';

import ArticlePage from '../article/ArticlePage';
import {Provider} from 'react-redux';
import configureStore from './store';
import {ToastContainer} from "react-toastify";
import Header from "./Header";
import CartPage from "../order/CartPage";
import HistoryPage from '../order/HistoryPage';
import StatsPage from '../stats/StatsPage';
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
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
              <Route path="/cart" component={CartPage} />
              <Route path="/history" component={HistoryPage} />
              <Route path="/stats" component={StatsPage} />
              <Route path="/articles" component={ArticlePage} />
              <Route component={NotFound} />
            </Switch>
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
