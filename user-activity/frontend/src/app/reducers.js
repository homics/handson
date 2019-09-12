import {combineReducers} from 'redux';
import userActivity from "../userActivity/userActivityDuck";
import auth from "../auth/authDuck";
import version from "../version/versionDuck";

const appReducer = combineReducers({
  userActivity,
  auth,
  version,
});

const rootReducer = (state, action) => {
  if (action.type === 'SUCCESS_LOGOUT') {
    state = undefined
  }

  return appReducer(state, action)
};

export default rootReducer;
