import axios from 'axios';

const initialState = {};
const USER_ACTIVITY_LOADED = 'USER_ACTIVITY_LOADED';

export default function userActivity(state = initialState, action) {
  switch (action.type) {
  case USER_ACTIVITY_LOADED:
    return {
      ...state,
      list: action.payload,
    };
  default:
    return state
  }
}

export function fetchUserActivity() {
  return dispatch =>
    axios
      .get("/user/internal/activity").then(response =>
        dispatch({
          type: USER_ACTIVITY_LOADED,
          payload: response.data,
        })
      );
}
