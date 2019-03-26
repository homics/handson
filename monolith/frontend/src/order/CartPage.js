import React, { Fragment } from 'react'
import OrderLineList from './OrderLineList'
import { Button, Col, Row } from 'react-bootstrap';

import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'
import { fetchCurrentOrder, payOrder } from "./orderDuck";
import { faShoppingCart } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { toast } from 'react-toastify';

class CartPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = { hasFetchCurrentOrder: false };
    }

    componentDidMount() {
        this.props.fetchCurrentOrder().then(() =>
            this.setState({ hasFetchCurrentOrder: true })
        );
    }

    onPayOrderClick = () => {
        const { order, payOrder } = this.props;
        payOrder(order)
            .then(() => {
                toast.success("Order Payed")
            })
            .catch(error => {
                if (error && error.response && error.response.data && error.response.data.message) {
                    toast.error(error.response.data.message)
                }
            });
    };

    render() {
        const { order } = this.props;
        const { hasFetchCurrentOrder } = this.state;

        return (
            <div>
                {hasFetchCurrentOrder && order.orderLines.length > 0 && (
                    <Fragment>
                        <OrderLineList
                            order={order}
                        />
                        <Row className="justify-content-md-center">
                            <Col xs={6}>
                                <Button variant="primary" onClick={this.onPayOrderClick} className="float-right">
                                    <FontAwesomeIcon icon={faShoppingCart} /> Pay Order
                                </Button>
                            </Col>
                        </Row>
                    </Fragment>
                )}
                {hasFetchCurrentOrder && order.orderLines.length === 0 && (
                    <Row className="justify-content-md-center" style={{ marginTop: '1rem' }}>
                        <p>Your cart is empty.</p>
                    </Row>
                )}
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        order: state.order.currentOrder,
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators(
        {
            fetchCurrentOrder,
            payOrder,
        },
        dispatch
    )
}

export default connect(
    mapStateToProps,
    mapDispatchToProps,
)(CartPage)
