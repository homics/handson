import React from 'react'
import PropTypes from 'prop-types'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCartPlus } from '@fortawesome/free-solid-svg-icons'
import { Button, Media, Col, Row } from 'react-bootstrap';

const Article = ({ article, stock, onArticleClicked }) => {
  const onCartClicked = () => {
    onArticleClicked(article)
  };

  return (
    <div className="card" key={article.id} style={{ marginTop: '0.8rem', marginBottom: '0.8rem' }}>
      <Media as="li" style={{ margin: '0.8rem' }}>
        <img
          width={64}
          height={64}
          className="mr-3"
          src={article.image}
          alt={article.name}
        />
        <Media.Body>
        <h5>{article.name} <small>({stock})</small></h5>
          <p>
            {article.description}
          </p>
          <Row>
            <Col>
              <Button className="float-right" size="sm" onClick={onCartClicked} style={{ minWidth: '6rem' }}>
                {article.price} € <FontAwesomeIcon icon={faCartPlus} />
              </Button>
            </Col>
          </Row>
        </Media.Body>
      </Media>
    </div>
  )
};

Article.propTypes = {
    article: PropTypes.object, 
    stock: PropTypes.string,
    onArticleSelected: PropTypes.func,
};

export default Article;
