import React, {Fragment} from 'react'
import logo from '../assets/logo.svg'
import styled from '@emotion/styled'
import {keyframes} from '@emotion/core'
import {Nav} from 'react-bootstrap';
import {LinkContainer} from 'react-router-bootstrap';
import {performLogout} from "../auth/authDuck";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";

const rotate360 = keyframes`
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
`;

const TopBar = styled.div`
  background-color: #222;
  height: 120px;
  padding: 20px;
  color: #fff;
  display: flex;
  flex-direction: row;

  .redux-logo {
    animation: ${rotate360} infinite 20s linear;
    height: 80px;
  }
`;

const Title = styled.div`
  background-color: #222;
  padding: 20px;
  font-size: 2em;
  color: #fff;
`;

const Header = ({performLogout}) => (
  <Fragment>
    <TopBar>
      <img src={logo} className="redux-logo" alt="logo" />
      <Title>HOMicS: Market Place Project</Title>
    </TopBar>
    <Nav variant="tabs">
      <Nav.Item>
        <LinkContainer to={"/articles"} >
          <Nav.Link eventKey={1}>
                      Articles
          </Nav.Link>
        </LinkContainer>
      </Nav.Item>
      <Nav.Item>
        <LinkContainer to={"/cart"} >
          <Nav.Link eventKey={2}>
                      Cart
          </Nav.Link>
        </LinkContainer>
      </Nav.Item>
      <Nav.Item>
        <LinkContainer to={"/history"} >
          <Nav.Link eventKey={3}>
                      History
          </Nav.Link>
        </LinkContainer>
      </Nav.Item>
      <Nav.Item>
        <Nav.Link href="/stats/stat-order">Stats micro</Nav.Link>
      </Nav.Item>
      <Nav.Item>
        <Nav.Item>
          <Nav.Link href="/user/userActivity">User Activity micro</Nav.Link>
        </Nav.Item>
      </Nav.Item>
      <Nav.Item>
        <Nav.Link onClick={performLogout}>Logout</Nav.Link>
      </Nav.Item>
    </Nav>

  </Fragment>
);


const mapDispatchToProps = dispatch => {
  return bindActionCreators(
    {
      performLogout,
    },
    dispatch
  )
};

export default connect(
  null,
  mapDispatchToProps,
)(Header)
