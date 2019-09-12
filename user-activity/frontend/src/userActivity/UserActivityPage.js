import React, {Fragment} from 'react'
import PropTypes from 'prop-types'

import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'
import { Button } from 'react-bootstrap';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSkullCrossbones, faServer } from '@fortawesome/free-solid-svg-icons'

import { fetchUserActivity, crashApplication } from "./userActivityDuck";

import UserActivitiesList from "./UserActivitiesList";
import { userActivityShape } from "./userActivityShape";

class UserActivityPage extends React.Component {
  constructor(props) {
    super(props);
    this.state = { hasFetchUserActivities: false };
  }

  componentDidMount() {
    this.props.fetchUserActivity().finally(() =>
      this.setState({ hasFetchUserActivities: true }));
  }

  crashApp = () => {
    this.props.crashApplication().then(window.location.reload());
  };

  openVersionPage = () => {
    this.props.history.push("/user/versions");
  };

  render() {
    const { userActivity } = this.props;
    const { hasFetchUserActivities } = this.state;

    return (
      <div>
        {hasFetchUserActivities && (
            <Fragment>
              <br />
              <div style={{display: 'flex', alignItems: 'center',  justifyContent: 'center',}}>
                <span>
                  <Button onClick={this.crashApp} >
                    Crash{` `}
                    <FontAwesomeIcon icon={faSkullCrossbones} />
                  </Button>{`\t`}

                  <Button onClick={this.openVersionPage} >
                    Versions{` `}
                    <FontAwesomeIcon icon={faServer} />
                  </Button>
                </span>
              </div>

              <br/>
              <br/>

              <UserActivitiesList
                  userActivity={userActivity}
              />
            </Fragment>

        )}
      </div>
    )
  }
}

UserActivityPage.propTypes = {
  userActivity: PropTypes.arrayOf(userActivityShape),
  fetchUserActivity: PropTypes.func.isRequired,
  crashApplication: PropTypes.func.isRequired,
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
      crashApplication,
    },
    dispatch
  )
}

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(UserActivityPage)
