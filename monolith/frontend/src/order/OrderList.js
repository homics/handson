import React from 'react'
import PropTypes from 'prop-types'
import { Media, Col, Row } from 'react-bootstrap';

function OrderList({ orders }) {
  return (
    <section>
      <Row className="justify-content-md-center">
        <Col xs={6}>
          <ul className="list-unstyled">
            {
              orders.map(order =>
                <div className="card" key={order.id} style={{ marginTop: '0.8rem', marginBottom: '0.8rem' }}>
                  <Media as="li" style={{ margin: '0.8rem' }}>
                    <Media.Body>
                      <Row>
                        <Col>
                          <h5>Order : {order.id}  [{order.status}]</h5>
                        </Col>
                        <Col>
                          <span className="float-right" size="sm">
                            {order.totalPrice} €
                          </span>
                        </Col>
                      </Row>
                      <Row>
                        <Col>
                          {
                            order.orderLines.map(orderLine =>
                              <img
                                key={orderLine.id}
                                width={64}
                                height={64}
                                className="mr-3"
                                src={orderLine.article.image}
                                alt={orderLine.article.name}
                              />
                            )
                          }
                        </Col>
                      </Row>
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

OrderList.propTypes = {
  orders: PropTypes.array,
};

export default OrderList
