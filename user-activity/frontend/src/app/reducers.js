import {combineReducers} from 'redux';
import userActivity from "../userActivity/userActivityDuck";
import auth from "../auth/authDuck";

const appReducer = combineReducers({
  userActivity,
  auth,
});

const rootReducer = (state, action) => {
  if (action.type === 'SUCCESS_LOGOUT') {
    state = undefined
  }

  return appReducer(state, action)
};

export default rootReducer;
