import React from 'react'
import PropTypes from 'prop-types'

import {bindActionCreators, compose} from 'redux'
import {connect} from 'react-redux'
import {fetchArticles} from './articleDuck';
import {addArticleToOrder} from '../order/orderDuck';

import Article from './Article';
import {Col, Row} from "react-bootstrap";
import withOrder from "../order/withCurrentOrder";
import { toast } from 'react-toastify';

class ArticlePage extends React.Component {
  constructor(props) {
    super(props);
    this.state = { hasFetchArticles: false };
  }

  componentDidMount() {
    this.props.fetchArticles().then(() =>
      this.setState({ hasFetchArticles: true })
    );
  }

  onArticleClicked = (article) => {
    const { addArticleToOrder, currentOrder } = this.props;
    addArticleToOrder(currentOrder, article)
      .then(() => {
        toast.success("Article added to cart.")
      })
      .catch(error => {
        toast.error(error)
      });
  };

  render() {
    const { articles } = this.props;
    const { hasFetchArticles } = this.state;
    return (
      <div>
        {hasFetchArticles && (
          <section>
            <Row className="justify-content-md-center">
              <Col xs={6}>
                <ul className="list-unstyled">
                  {
                    articles.map(article =>
                      <Article key={article.id} article={article} onArticleClicked={this.onArticleClicked}/>
                    )
                  }
                </ul>
              </Col>
            </Row>
          </section>
        )}
      </div>
    )
  }
}

ArticlePage.propTypes = {
  articles: PropTypes.array,
  currentOrder: PropTypes.object,
  fetchArticles: PropTypes.func.isRequired,
  addArticleToOrder: PropTypes.func.isRequired,
  fetchCurrentOrder: PropTypes.func.isRequired,
};

function mapStateToProps(state) {
  return {
    articles: state.article.list,
    currentOrder: state.order.currentOrder,
  }
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators(
    {
      fetchArticles,
      addArticleToOrder
    },
    dispatch
  )
}

export default compose(
  connect(
    mapStateToProps,
    mapDispatchToProps,
  ),
  withOrder,
)(ArticlePage)
