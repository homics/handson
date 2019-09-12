import React from 'react'
import PropTypes from 'prop-types'
import { Col, Row } from 'react-bootstrap';

function VersionList({ versions }) {
  return (
    <section>
      <Row className="justify-content-md-center">
        <Col xs={6}>
          <ul className="list-unstyled">
            {
                versions && versions.map(version =>
                <p key={version.creationDate} style={{ marginTop: '0.1rem', marginBottom: '0.1rem' }}>
                    Version : <span style={{  color: version.name =='0.0.1-SNAPSHOT' ? 'green' : 'blue' }}>{version.name}</span> {`\n`} BuildTime: {version.createdDate} {`\n`} {`\n`} At: {version.sendDate}
                </p>
              )
            }
          </ul>
        </Col>
      </Row>
    </section>
  )
}

VersionList.propTypes = {
  versions: PropTypes.array,
};

export default VersionList
