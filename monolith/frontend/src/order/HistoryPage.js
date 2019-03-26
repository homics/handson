import React, { Fragment } from 'react'
import OrderList from './OrderList'

import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'
import { fetchOrdersHistory } from "./orderDuck";
import { Col, Row } from 'react-bootstrap';

class HistoryPage extends React.Component {
  constructor(props) {
    super(props);
    this.state = { hasFetchOrders: false };
  }

  componentDidMount() {
    this.props.fetchOrdersHistory().then(() =>
      this.setState({ hasFetchOrders: true })
    );
  }

  render() {
    const { orders } = this.props;
    const { hasFetchOrders } = this.state;

    return (
      <div>
        {hasFetchOrders && (
          <Fragment>
            <OrderList
              orders={orders}
            />
          </Fragment>
        )}
        {hasFetchOrders && orders.length === 0 && (
          <Row className="justify-content-md-center">
            <Col  md="auto">
              <p> Your don't have any orders.</p>
            </Col>
          </Row>
        )}
      </div>
    )
  }
}
function mapStateToProps(state) {
  return {
    orders: state.order.orders,
  }
}
function mapDispatchToProps(dispatch) {
  return bindActionCreators(
    {
      fetchOrdersHistory,
    },
    dispatch
  )
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(HistoryPage)
