import {combineReducers} from 'redux';
import article from '../article/articleDuck';
import order from "../order/orderDuck";
import auth from "../auth/authDuck";

const rootReducer = combineReducers({
  article,
  order,
  auth,
});

export default rootReducer;
