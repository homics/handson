import React, {Component} from 'react';
import './App.css';
import {Provider} from 'react-redux';
import configureStore from './store';
import {ToastContainer} from "react-toastify";
import Header from "./Header";
import UserActivityPage from "../userActivity/UserActivityPage";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import 'react-toastify/dist/ReactToastify.min.css';
import NotFound from "./NotFound";
import VersionPage from "../version/VersionPage";

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
                <Route path="/user/userActivity" component={UserActivityPage} />
                <Route path="/user/versions" component={VersionPage} />
                <Route component={NotFound} />
            </Switch>
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
