import {combineReducers} from 'redux';
import stats from "../stats/statsDuck";
import auth from "../auth/authDuck";

const appReducer = combineReducers({
  stats,
  auth,
});

const rootReducer = (state, action) => {
  if (action.type === 'SUCCESS_LOGOUT') {
    state = undefined
  }

  return appReducer(state, action)
};

export default rootReducer;