import {combineReducers} from 'redux';
import article from '../article/articleDuck';
import order from "../order/orderDuck";
import stats from "../stats/statsDuck";
import userActivity from "../userActivity/userActivityDuck";
import auth from "../auth/authDuck";

const appReducer = combineReducers({
  article,
  order,
  stats,
  auth,
  userActivity,
});

const rootReducer = (state, action) => {
  if (action.type === 'SUCCESS_LOGOUT') {
    state = undefined
  }

  return appReducer(state, action)
};

export default rootReducer;
