import React, {Component} from 'react';
import './App.css';

import {Provider} from 'react-redux';
import configureStore from './store';
import {ToastContainer} from "react-toastify";
import Header from "./Header";
import LoginPage from "../auth/LoginPage";
import LogoutPage from "../auth/LogoutPage";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import 'react-toastify/dist/ReactToastify.min.css';
import NotFound from "./NotFound";
import PrivateRoute from "./PrivateRoute";

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
        <Router>
          <div>
            <Header />
            <Switch>
              <Route path="/login" component={LoginPage} />
              <Route path="/logout" component={LogoutPage} />
              <PrivateRoute component={NotFound} />
            </Switch>
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
