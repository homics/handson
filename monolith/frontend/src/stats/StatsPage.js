import React from 'react'

import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'
import { fetchOrderStats } from "../stats/statsDuck";
import { Row, Col, Card } from 'react-bootstrap';

class OrdersPage extends React.Component {
  constructor(props) {
    super(props);
    this.state = { hasFetchStats: false };
  }

  componentDidMount() {
    this.props.fetchOrderStats().then(() =>
      this.setState({ hasFetchStats: true })
    );
  }

  render() {
    const { orderCount, avgCart } = this.props;
    const { hasFetchStats } = this.state;

    return (
      <div>
        {hasFetchStats && (
          <Row className="justify-content-md-center" style={{  marginTop: '1rem'}}>
            <Col md="auto">
              <Card style={{ width: '18rem' }}>
                <Card.Header>Number of orders</Card.Header>
                <Card.Body>
                  <Card.Title>{orderCount}</Card.Title>
                </Card.Body>
              </Card>
            </Col>
            <Col xs lg="1"/>
            <Col md="auto">
              <Card style={{ width: '18rem' }}>
                <Card.Header>Average cart price</Card.Header>
                <Card.Body>
                  <Card.Title>{avgCart}€</Card.Title>
                </Card.Body>
              </Card>
            </Col>
          </Row>
        )}
      </div>
    )
  }
}

function mapStateToProps(state) {
  return {
    orderCount: state.stats.orderCount,
    avgCart: state.stats.avgCart,
  }
}
function mapDispatchToProps(dispatch) {
  return bindActionCreators(
    {
      fetchOrderStats,
    },
    dispatch
  )
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(OrdersPage)
