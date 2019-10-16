import React from 'react'
import PropTypes from 'prop-types'

import {bindActionCreators, compose} from 'redux'
import {connect} from 'react-redux'

import {performLogout} from './authDuck';
import {withRouter} from "react-router-dom";

class LogoutPage extends React.Component {

  componentDidMount() {
    this.props.performLogout().then(window.location.reload());
  }

    render() {
        return "";
    }

}

LogoutPage.propTypes = {
    performLogin: PropTypes.func.isRequired,
};

function mapDispatchToProps(dispatch) {
    return bindActionCreators(
        {
            performLogout,
        },
        dispatch
    )
}


export default compose(
    connect(
        null,
        mapDispatchToProps),
    withRouter,
)(LogoutPage)
