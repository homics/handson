import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import queryString from 'query-string';
import './index.css';
import App from './app/App';
import * as serviceWorker from './serviceWorker';

// Axios configuration
axios.defaults.baseURL = 'http://localhost:8080/';

/* eslint-disable no-undef */
/* eslint-disable quotes */
axios.defaults.withCredentials = true;
axios.defaults.xsrfHeaderName = "X-CSRFTOKEN";
axios.defaults.xsrfCookieName = "csrftoken";
axios.defaults.paramsSerializer = function(params) {
  return queryString.stringify(params, { arrayFormat: 'brackets' });
};

ReactDOM.render(<App />, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
