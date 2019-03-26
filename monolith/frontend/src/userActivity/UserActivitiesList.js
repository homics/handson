import React from 'react'
import PropTypes from 'prop-types'
import {Col, Row, Table} from 'react-bootstrap';
import {userActivityShape} from "./userActivityShape";
import * as moment from "moment";

function UserActivityList({ userActivity }) {

  return (
    <section>
      <Row className="justify-content-md-center">
        <Col xs={8}>
          <Table striped bordered hover style={{ marginTop: '0.8rem', marginBottom: '0.8rem'}}>
            <thead>
              <tr>
                <th>Username</th>
                <th>Activity Type</th>
                <th>Activity Date</th>
              </tr>
            </thead>
            <tbody>
              { userActivity &&
                userActivity.map(activity =>
                  <tr key={activity.activityDate.toString()}>
                    <td>{activity.username}</td>
                    <td>{activity.activityType}</td>
                    <td>{activity.activityDate}</td>
                  </tr>
                )}
            </tbody>
          </Table>
        </Col>
      </Row>
    </section>
  )
}

UserActivityList.propTypes = {
  userActivity: PropTypes.arrayOf(userActivityShape),
};

export default UserActivityList
