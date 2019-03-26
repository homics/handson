import axios from 'axios';

const initialState = {
  isLogged: false,
};
const SUCCESS_LOGIN = 'SUCCESS_LOGIN';
const SUCCESS_LOGOUT = 'SUCCESS_LOGOUT';

export default function auth(state = initialState, action) {
  switch (action.type) {
  case SUCCESS_LOGIN:
    return {...state, isLogged: true};
  default:
    return state
  }
}

export function performLogin(account) {
  const config = {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
  };

  const params = new URLSearchParams();
  params.append("username",account.username);
  params.append("password", account.password);
  return dispatch =>
    axios
      .post("/mono/perform_login", params, config)
      .then( () =>  {
        return dispatch({type: SUCCESS_LOGIN});
      });
}

export function performLogout(){
  return dispatch =>
    axios
      .post("/mono/perform_logout")
      .finally( () =>  {
        return dispatch({type: SUCCESS_LOGOUT});
      });
}

export function getCurrentUser(){
  return dispatch =>
      axios
          .get("/mono/user/current")
          .then( () =>  {
            return dispatch({type: SUCCESS_LOGIN});
          });
}
