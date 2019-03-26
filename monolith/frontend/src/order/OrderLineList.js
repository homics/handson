import React from 'react'
import PropTypes from 'prop-types'
import { Media, Col, Row } from 'react-bootstrap';
import { faTrash } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { deleteOrderLine } from "./orderDuck";
import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'

class OrderLineList extends React.Component {

  onRemoveClick = (order, orderLine) => {
    console.log(orderLine)
    this.props.deleteOrderLine(order, orderLine);
  };

  render() {
    const { order } = this.props;

    return (
      <section>
        <Row className="justify-content-md-center">
          <Col xs={6}>
            <ul className="list-unstyled">
              {
                order.orderLines.map(orderLine =>
                  <div className="card" key={orderLine.article.id} style={{ marginTop: '0.8rem', marginBottom: '0.8rem' }}>
                    <Media as="li" style={{ margin: '0.8rem' }}>
                      <img
                        width={64}
                        height={64}
                        className="mr-3"
                        src={orderLine.article.image}
                        alt={orderLine.article.name}
                      />
                      <Media.Body>
                        <Row>
                          <Col>
                            <h5>{orderLine.article.name}<small> (x {orderLine.quantity})</small></h5>
                          </Col>
                          <Col>
                            <span className="float-right" size="sm">
                              <FontAwesomeIcon icon={faTrash} onClick={() => this.onRemoveClick(order, orderLine)} />
                            </span>
                          </Col>
                        </Row>
                        <p>
                          {orderLine.article.description}
                        </p>
                      </Media.Body>
                    </Media>
                  </div>
                )
              }
            </ul>
          </Col>
        </Row>
      </section>
    )
  }
}

OrderLineList.propTypes = {
  order: PropTypes.object,
};

function mapStateToProps(state) {
  return {}
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators(
    {
      deleteOrderLine,
    },
    dispatch
  )
}

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(OrderLineList)
