import React from 'react'
import PropTypes from 'prop-types'

import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'
import { fetchUserActivity } from "./userActivityDuck";

import UserActivitiesList from "./UserActivitiesList";
import {userActivityShape} from "./userActivityShape";

class UserActivityPage extends React.Component {
  constructor(props) {
    super(props);
    this.state = { hasFetchUserActivities: false };
  }

  componentDidMount() {
    this.props.fetchUserActivity().finally(() =>
      this.setState({ hasFetchUserActivities: true }));
  }

  render() {
    const { userActivity } = this.props;
    const { hasFetchUserActivities } = this.state;

    return (
      <div>
        {hasFetchUserActivities && (
          <UserActivitiesList
            userActivity={userActivity}
          />
        )}
      </div>
    )
  }
}

UserActivityPage.propTypes = {
  userActivity: PropTypes.arrayOf(userActivityShape),
  fetchUserActivity: PropTypes.func.isRequired,
};

function mapStateToProps(state) {
  return {
    userActivity: state.userActivity.list,
  }
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators(
    {
      fetchUserActivity,
    },
    dispatch
  )
}

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(UserActivityPage)
