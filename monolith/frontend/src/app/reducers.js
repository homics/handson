import {combineReducers} from 'redux';
import article from '../article/articleDuck';
import order from "../order/orderDuck";
import stats from "../stats/statsDuck";
import auth from "../auth/authDuck";

const rootReducer = combineReducers({
  article,
  order,
  stats,
  auth,
});

export default rootReducer;
