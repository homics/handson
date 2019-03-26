import React from "react";
import {connect} from "react-redux";
import {bindActionCreators, compose} from "redux";
import {fetchCurrentOrder} from "./orderDuck";

const withCurrentOrder = (WrappedComponent) => {
  return class EnhancedComponent extends React.Component {
    constructor(props) {
      super(props);
      this.state = { hasFetchOrder: false };
    }

    componentDidMount() {
      this.props.fetchCurrentOrder().then(() =>
        this.setState({ hasFetchOrder: true })
      );
    }

    render() {
      return <WrappedComponent currentOrder={this.props.currentOrder} {...this.props}/>
    }
  }
};

function mapStateToProps(state) {
  return {
    currentOrder: state.order.currentOrder,
  }
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators(
    {
      fetchCurrentOrder
    },
    dispatch
  )
}

export default compose(
  connect(
    mapStateToProps,
    mapDispatchToProps
  ),
  withCurrentOrder
);
